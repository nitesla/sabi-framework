package com.sabi.framework.integrations.payment_integration.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VerveOtpRequest {
    private VerveTransaction transaction;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class VerveTransaction {
        private String linkingreference;
        private String otp;
    }
}
