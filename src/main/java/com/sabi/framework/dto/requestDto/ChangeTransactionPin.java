package com.sabi.framework.dto.requestDto;


import lombok.Data;

@Data
public class ChangeTransactionPin {

    private Long id;
    private String transactionPin;
    private String oldTransactionPin;
}
