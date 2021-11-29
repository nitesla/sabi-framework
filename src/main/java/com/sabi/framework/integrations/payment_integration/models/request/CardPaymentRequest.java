package com.sabi.framework.integrations.payment_integration.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardPaymentRequest {
    //    private String privateKey;
    @NotNull(message = "Amount can not be empty")
    @DecimalMin(value = "0.0", message = "Amount can not be less than 0")
    private BigDecimal amount;
    private BigDecimal fee;
    private String fullName;
    private String mobileNumber;
    @NotBlank(message = "Currency can not be empty")
    private String currency;
    @NotBlank(message = "Country can not be empty")
    private String country;
    @NotBlank(message = "Email can not be empty")
    @Email(message = "Email pattern has to be valid")
    private String email;
    private String productId;
    private String productDescription;
    private String clientAppCode;
    private String redirectUrl;
    @NotBlank(message = "Channel type can not be empty")
    private String channelType;
    private String deviceType;
    private String sourceIP;
    @NotBlank(message = "Card number can not be empty")
    private String cardNumber;
    @NotBlank(message = "Cvv can not be empty")
    private String cvv;
    @NotBlank(message = "Expiry month can not be empty")
    private String expiryMonth;
    @NotBlank(message = "Expiry year can not be empty")
    private String expiryYear;
    private String pin;
//    private Boolean retry;
//    private String invoiceNumber;
}
