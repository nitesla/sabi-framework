package com.sabi.framework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails extends CoreEntity{
    private String publicKey;
    private BigDecimal amount;
    private String currency;
    private String country;
    private String paymentReference;
    private String email;
    private String productId;
    private String productDescription;
    private String callbackUrl;
    private String hash;
    private String hashType;
    private String status;
}
