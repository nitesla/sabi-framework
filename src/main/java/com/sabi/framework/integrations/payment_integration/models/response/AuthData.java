package com.sabi.framework.integrations.payment_integration.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthData {
    private String code;
    @JsonProperty("encryptedSecKey")
    private EncryptedSecKey EncryptedSecKey;
    private String message;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EncryptedSecKey {
        private String encryptedKey;
    }
}
