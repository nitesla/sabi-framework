package com.sabi.framework.integrations.payment_integration.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckOutRequest {

    @NotNull(message = "Amount can not be empty")
    @DecimalMin(message = "Amount can not be lesser than 0.0", value = "0.0")
    private BigDecimal amount;
    @NotBlank(message = "Currency can not be empty")
    private String currency;
    @NotBlank(message = "Country can not be empty")
    private String country;
    @Email(message = "Email should be a valid email address")
    @NotBlank(message = "Email can not be empty")
    private String email;
    @NotBlank(message = "Product Id can not be empty")
    private String productId;
//    @NotBlank(message = "Product description can not be empty")
    private String productDescription;
    @NotBlank(message = "Call back url can not be empty")
    private String callbackUrl;
}
