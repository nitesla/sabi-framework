package com.sabi.framework.integrations.payment_integration.models.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TokenisationRequest {

    @NotNull(message = "amount can not be null")
    @DecimalMin(value = "0.0", message = "Amount can not be lesser than 0")
    private BigDecimal amount;
    private String fullName;
    private String mobileNumber;
    @NotBlank(message = "Currency is required")
    private String currency;
    @NotBlank(message = "Country is required")
    private String country;
    private String paymentReference;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be a valid email")
    private String email;
    private String productId;
    private String productDescription;
    @NotBlank(message = "CardNumber is required")
    private String cardNumber;
    private String cvv;
    @NotBlank(message = "Expiry month is required")
    private String expiryMonth;
    @NotBlank(message = "Expiry year is required")
    private String expiryYear;
    private String pin;
}
