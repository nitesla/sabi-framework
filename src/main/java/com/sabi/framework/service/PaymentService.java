package com.sabi.framework.service;


import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.exceptions.ConflictException;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.helpers.API;
import com.sabi.framework.integrations.payment_integration.models.*;
import com.sabi.framework.integrations.payment_integration.models.request.*;
import com.sabi.framework.integrations.payment_integration.models.response.*;
import com.sabi.framework.models.PaymentDetails;
import com.sabi.framework.repositories.PaymentDetailRepository;
import com.sabi.framework.repositories.UserRepository;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public PaymentService(ModelMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }


    private Map<String, String> getHeaders() {
        String token = "Bearer " + authenticationService();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", token);
        return headers;
    }

    private String authenticationService() {
        String url = baseUrl + "/encrypt/keys";
        String key = secretKey + "." + publicKey;
        AuthenticationRequest auth = new AuthenticationRequest();
        auth.setKey(key);
        PaymentAuthenticationResponse response = api.post(url, auth, PaymentAuthenticationResponse.class);
        log.info("Token from seerbit " + response.getData().getEncryptedSecKey().getEncryptedKey());
        return response.getData().getEncryptedSecKey().getEncryptedKey();
    }

    private String getHash(HashObject hashObject) {
        log.info("attempting hash:: ");
        HashResponse hashResponse = api.post(baseUrl + "/encrypt/hashs", hashObject, HashResponse.class, getHeaders());
        return hashResponse.getData().getHash().getHash();
    }

    public CheckOutResponse checkOut(CheckOutRequest checkOutRequest) {
        CheckOutDto checkOutDto = mapper.map(checkOutRequest, CheckOutDto.class);
        checkOutDto.setPublicKey(publicKey);
        checkOutDto.setPaymentReference((String.valueOf(System.currentTimeMillis())));
        checkOutDto.setHashType("sha256");

        checkOutDto.setHash(getHash(mapper.map(checkOutDto, HashObject.class)));

         savePaymentDetails(checkOutDto);

        return  api.post(baseUrl + "/payments", checkOutDto, CheckOutResponse.class, getHeaders());
    }

    private void savePaymentDetails(Object checkOutRequest) {
        PaymentDetails paymentDetail = mapper.map(checkOutRequest, PaymentDetails.class);
        paymentDetail.setStatus("PENDING");
        log.info("Saving payment Detail to DB: " + paymentDetail.toString());
        paymentDetailRepository.save(paymentDetail);
    }

    public PaymentStatusResponse checkStatus(String paymentReference) {
        PaymentDetails paymentDetails = paymentDetailRepository.findByPaymentReference(paymentReference);
        if (paymentDetails == null)
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Payment reference does not exist");

        PaymentStatusResponse paymentStatusResponse = api.get(baseUrl + "/payments/query/" + paymentReference, PaymentStatusResponse.class, getHeaders());
        log.info("Payment status message {}" ,paymentStatusResponse.getMessage());
        if(paymentStatusResponse.getStatus() != null &&
                paymentStatusResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
            log.info("Payment status is success");
            paymentDetails.setApprovedAmount(paymentStatusResponse.getData().getPayments().getAmount());
            paymentStatusResponse.setPaymentDetails(paymentDetails);
        }
        return paymentStatusResponse;
    }

    public void updatePaymentStatus(PaymentStatusResponse response){
        PaymentDetails details = response.getPaymentDetails();
        PaymentDetails updatePaymentDetails = paymentDetailRepository.findByIdAndPaymentReference(details.getId(), details.getPaymentReference());
        if(updatePaymentDetails == null) throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Payment does not exist");

        if (!updatePaymentDetails.getStatus().equals("PENDING") ||
                response.getData().getPayments().getAmount().compareTo(updatePaymentDetails.getAmount()) != 0)
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, "Payment Status conflict");

        if (response.getData() != null && response.getData().getCode().equals("00") && response.getData().getPayments().getGatewayCode().equals("00")
                && response.getData().getPayments().getProcessorCode().equals("00")) {
            updatePaymentDetails.setStatus("SUCCESS");
        } else {
            updatePaymentDetails.setStatus("FAILED");
        }
        paymentDetailRepository.save(updatePaymentDetails);
    }

    public CardPaymentResponse payWithCard(CardPaymentRequest paymentRequest) {
//        log.debug("Payment Request amount " + paymentRequest.getAmount());
//        CardPaymentDto paymentDto = new CardPaymentDto();
//        BeanUtils.copyProperties(paymentRequest, paymentDto);
        String paymentReference = String.valueOf(System.currentTimeMillis());
        userRepository.findById(paymentRequest.getUserId()).orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                "user id does not exist!"));

        mapper.getConfiguration().setAmbiguityIgnored(true);

        CardPaymentDto paymentDto = mapper.map(paymentRequest, CardPaymentDto.class);
        log.debug("Amount to be paid with card for is " + paymentDto.getAmount());
        paymentDto.setPublicKey(publicKey);
        paymentDto.setPaymentReference(paymentReference);
        paymentDto.setPaymentType("CARD");
        paymentDto.setRetry(false);
        PaymentDetails map = mapper.map(paymentDto, PaymentDetails.class);
        savePaymentDetails(map);


        CardPaymentResponse post = api.post(baseUrl + "/payments/initiates", paymentDto, CardPaymentResponse.class, getHeaders());

        PaymentStatusResponse paymentStatusResponse = checkStatus(paymentReference);
        updatePaymentStatus(paymentStatusResponse);
        return post;
    }

    public CardPaymentResponse confirmVerveOtp(VerveOtpRequest verveOtpRequest) {
        return api.post(baseUrl + "/payments/otp", verveOtpRequest, CardPaymentResponse.class, getHeaders());
    }

    public TokenisationResponse tokenise(TokenisationRequest tokenisationRequest) {
        TokenisationDto tokenisationDto = mapper.map(tokenisationRequest, TokenisationDto.class);
        tokenisationDto.setPublicKey(publicKey);
        tokenisationDto.setPaymentReference(String.valueOf(System.currentTimeMillis()));
        TokenisationResponse post = api.post(baseUrl + "/payments/tokenize", tokenisationRequest, TokenisationResponse.class, getHeaders());
        log.debug("The status from tokeniszation is " + post.getStatus());
        return post;
    }

}
