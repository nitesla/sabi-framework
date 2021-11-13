package com.sabi.integrations.payment_integration.models.request;

import com.sabi.integrations.payment_integration.models.PaymentOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderAfterPayment {
    private String paymentReference;
    private String publicKey;
    private List<PaymentOrder> orders;
}
