package com.sabi.framework.service;


import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.exceptions.ConflictException;
import com.sabi.framework.exceptions.ProcessingException;
import com.sabi.framework.helpers.API;
import com.sabi.framework.helpers.Encryptions;
import com.sabi.framework.models.PaymentDetails;
import com.sabi.framework.repositories.PaymentDetailRepository;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.integrations.payment_integration.models.CheckOutDto;
import com.sabi.integrations.payment_integration.models.request.AuthenticationRequest;
import com.sabi.integrations.payment_integration.models.request.CheckOutRequest;
import com.sabi.integrations.payment_integration.models.response.CheckOutResponse;
import com.sabi.integrations.payment_integration.models.response.PaymentAuthenticationResponse;
import com.sabi.integrations.payment_integration.models.response.PaymentStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PaymentService {
    @Value("${payment.testkey.secret}")
    private String secretKey;

    @Value("${payment.testkey.public}")
    private String publicKey;

    @Value("${payment.baseurl}")
    private String baseUrl;

    @Autowired
    private API api;

    @Autowired
    private PaymentDetailRepository paymentDetailRepository;

    private Map<String, String> getHeaders(){
        String token = "Bearer " + authenticationService();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", token);
        return headers;
    }

    private String authenticationService(){
        String url = baseUrl + "/encrypt/keys";
        String key = secretKey +"."+publicKey;
        AuthenticationRequest auth = new AuthenticationRequest();
        auth.setKey(key);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        PaymentAuthenticationResponse response = api.get(url, PaymentAuthenticationResponse.class, headers);
        return response.getData().getEncryptedSecKey().getEncryptedKey();
    }

    public CheckOutResponse checkOut(CheckOutRequest checkOutRequest){
        ModelMapper mapper = new ModelMapper();
        CheckOutDto checkOutDto = mapper.map(checkOutRequest, CheckOutDto.class);
        checkOutDto.setPublicKey(publicKey);
        checkOutDto.setPaymentReference((String.valueOf(System.currentTimeMillis())));
        checkOutDto.setHashType("sha256");
        String stringToHash = "amount="+checkOutDto.getAmount()+"&callbackUrl="+checkOutDto.getCallbackUrl()+
                "&country="+checkOutDto.getCountry()+"&currency="+checkOutDto.getCurrency()+
                "&email="+checkOutDto.getEmail()+"&paymentReference="+checkOutDto.getPaymentReference()+
                "&productDescription="+checkOutDto.getProductDescription()+
                "&productId="+checkOutDto.getProductId()+"&publicKey=" + publicKey;
        checkOutDto.setHash(Encryptions.generateSha256(stringToHash));

        savePaymentDetails(checkOutDto);

        return api.post(baseUrl + "/payments", checkOutDto, CheckOutResponse.class, getHeaders());
    }

    private void savePaymentDetails(CheckOutDto checkOutRequest){
        ModelMapper mapper = new ModelMapper();
        PaymentDetails paymentDetail = mapper.map(checkOutRequest, PaymentDetails.class);
        paymentDetail.setStatus("PENDING");
        log.info("Saving payment Detail to DB: " + paymentDetail.toString());
        paymentDetailRepository.save(paymentDetail);
    }

    public PaymentStatusResponse checkStatus(String paymentReference){
        PaymentDetails paymentDetails = paymentDetailRepository.findByPaymentReference(paymentReference);
        if(paymentDetails == null) throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Payment reference does not exist");

        PaymentStatusResponse response = api.get(baseUrl + "/payments/query/" + paymentReference, PaymentStatusResponse.class, getHeaders());
        if(!paymentDetails.getStatus().equals("PENDING") &&
                response.getData().getPayments().getAmount().compareTo(paymentDetails.getAmount()) != 0) throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, "Payment Status conflict");

        if(response.getData().getCode().equals("00") && response.getData().getPayments().getGatewayCode().equals("00")
        && response.getData().getPayments().getProcessorCode().equals("00")) {
            paymentDetails.setStatus("SUCCESS");
        }
        else {
            paymentDetails.setStatus("FAILED");
        }
        paymentDetailRepository.save(paymentDetails);
        return response;
    }
}
