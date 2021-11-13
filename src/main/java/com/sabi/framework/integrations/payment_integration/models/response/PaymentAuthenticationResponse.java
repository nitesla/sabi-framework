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
public class PaymentAuthenticationResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private AuthData data;
}
