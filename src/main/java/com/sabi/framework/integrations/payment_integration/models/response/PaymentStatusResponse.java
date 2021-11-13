package com.sabi.framework.integrations.payment_integration.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentStatusResponse {
    private String status;
    private PaymentStatusData data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PaymentStatusData {
        private String code;
        private PaymentStatusDetail payments;
        private Customers customers;
        private String message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PaymentStatusDetail {
        @JsonProperty("redirectLink")
        private String redirectLink;
        @JsonProperty("amount")
        private BigDecimal amount;
        @JsonProperty("email")
        private String email;
        @JsonProperty("mobilenumber")
        private String mobilenumber;
        @JsonProperty("publicKey")
        private String publicKey;
        @JsonProperty("paymentType")
        private String paymentType;
        @JsonProperty("productId")
        private String productId;
        @JsonProperty("productDescription")
        private String productDescription;
        @JsonProperty("maskedPan")
        private String maskedPan;
        @JsonProperty("gatewayMessage")
        private String gatewayMessage;
        @JsonProperty("gatewayCode")
        private String gatewayCode;
        @JsonProperty("gatewayref")
        private String gatewayref;
        @JsonProperty("mode")
        private String mode;
        @JsonProperty("callbackurl")
        private String callbackurl;
        @JsonProperty("redirecturl")
        private String redirecturl;
        @JsonProperty("channelType")
        private String channelType;
        @JsonProperty("sourceIP")
        private String sourceIP;
        @JsonProperty("deviceType")
        private String deviceType;
        @JsonProperty("cardBin")
        private String cardBin;
        @JsonProperty("lastFourDigits")
        private String lastFourDigits;
        @JsonProperty("type")
        private String type;
        private String country;
        private String currency;
        @JsonProperty("paymentReference")
        private String paymentReference;
        @JsonProperty("processorCode")
        private String processorCode;
        @JsonProperty("processorMessage")
        private String processorMessage;
        private String reason;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Customers {
        @JsonProperty("customerName")
        private String customerName;
        @JsonProperty("customerMobile")
        private String customerMobile;
        @JsonProperty("customerEmail")
        private String customerEmail;
        @JsonProperty("customerId")
        private String customerId;
        @JsonProperty("fee")
        private String fee;
    }
}
