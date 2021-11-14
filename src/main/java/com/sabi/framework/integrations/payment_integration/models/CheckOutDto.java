package com.sabi.framework.integrations.payment_integration.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckOutDto {
    private String publicKey;
    private String amount;
    private String currency;
    private String country;
    private String paymentReference;
    private String email;
    private String productId;
    private String productDescription;
    private String callbackUrl;
    private String hash;
    private String hashType;
}
