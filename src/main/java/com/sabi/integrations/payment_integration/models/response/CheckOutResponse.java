package com.sabi.integrations.payment_integration.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckOutResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private CheckOutData data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CheckOutData {
        @JsonProperty("code")
        private String code;
        @JsonProperty("payments")
        private Payments payments;
        @JsonProperty("message")
        private String message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Payments {
        @JsonProperty("redirectLink")
        private String redirectLink;
        @JsonProperty("paymentStatus")
        private String paymentStatus;
    }
}
