package com.sabi.framework.integrations.payment_integration.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardPaymentResponse {
    private String status;
    private CardPaymentData data;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CardPaymentData {
        private String code;
        private CardPayments payments;
        private String message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CardPayments {
        private String paymentReference;
        private String linkingReference;
        private String redirectUrl;
        private String reference;
    }
}
