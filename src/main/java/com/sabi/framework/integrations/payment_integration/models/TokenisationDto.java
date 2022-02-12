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
public class TokenisationDto {

    private String publicKey;
    private String agentId;
    private BigDecimal amount;
    private String fullName;
    private String mobileNumber;
    private String currency;
    private String country;
    private String paymentReference;
    private String email;
    private String productId;
    private String productDescription;
    private String cardNumber;
    private String cvv;
    private String expiryMonth;
    private String expiryYear;
    private String pin;
}
