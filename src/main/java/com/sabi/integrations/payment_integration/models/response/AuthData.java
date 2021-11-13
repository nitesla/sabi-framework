package com.sabi.integrations.payment_integration.models.response;

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
    @JsonProperty("code")
    private String code;
    @JsonProperty("EncryptedSecKey")
    private EncryptedSecKey encryptedSecKey;
    @JsonProperty("message")
    private String message;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EncryptedSecKey {
        @JsonProperty("encryptedKey")
        private String encryptedKey;
    }
}
