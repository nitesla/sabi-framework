package com.sabi.framework.integrations.payment_integration.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HashResponse {
    private String status;
    private HashData data;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class HashData {
        private String code;
        private String message;
        private Hash hash;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Hash {
        private String hash;
    }
}
