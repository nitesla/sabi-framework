package com.sabi.framework.integrations.payment_integration.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardPaymentDto {
    private String publicKey;
    private BigDecimal amount;
    private BigDecimal fee;
    private String fullName;
    private String mobileNumber;
    private String currency;
    private String country;
    private String paymentReference;
    private String email;
    private String productId;
    private String productDescription;
    private String clientAppCode;
    private String redirectUrl;
    private String paymentType;
    private String channelType;
    private String deviceType;
    private String sourceIP;
    private String cardNumber;
    private String cvv;
    private String expiryMonth;
    private String expiryYear;
    private String pin;
    private Boolean retry;
    private String invoiceNumber;

}
