package com.sabi.framework.dto.requestDto;


import lombok.Data;

@Data
public class SetTransactionPin {

    private Long id;
    private String transactionPin;
//    private String oldTransactionPin;
}
