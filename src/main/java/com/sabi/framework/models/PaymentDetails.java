package com.sabi.framework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails extends CoreEntity{

    private BigDecimal amount;
    private BigDecimal approvedAmount;
    private Long orderId;
    private String currency;
    private String country;
    private String paymentReference;
    private Date transactionDate = new Date();
    private String email;
    private String productId;
    private String productDescription;
    private String callbackUrl;
    private String hash;
    private String hashType;
    private String status;
    private String responseCode;
    private String responseDescription;
    private String linkingReference;
}
