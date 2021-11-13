package com.sabi.framework.integrations.payment_integration.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class PaymentCard {
    private String id;
    private String scheme;
    @JsonProperty("uniquecode")
    private String uniqueCode;
}
