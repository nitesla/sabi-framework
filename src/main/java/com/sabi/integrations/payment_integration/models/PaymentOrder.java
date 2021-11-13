package com.sabi.integrations.payment_integration.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentOrder {
    private String amount;
    private String productId;
    private String productDescription;
    private String currency;
}
