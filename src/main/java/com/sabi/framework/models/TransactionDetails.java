package com.sabi.framework.models;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TransactionDetails extends CoreEntity{
    //Agentid, payment status, ordered  reference no, date, amount, amount approved, payment url,
    private Long agentId;
    private String paymentStatus;
    private Long orderId;
    private String referenceNo;
    private Date paymentDate;
    private BigDecimal amountApproved;
    private String paymentUrl;
}
