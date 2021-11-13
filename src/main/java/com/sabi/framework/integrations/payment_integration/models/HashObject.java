package com.sabi.framework.integrations.payment_integration.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HashObject {
    private String publicKey;
    private BigDecimal amount;
    private String currency;
    private String country;
    private String paymentReference;
    private String email;
    private String productId;
    private String productDescription;
    private String callbackUrl;
}
