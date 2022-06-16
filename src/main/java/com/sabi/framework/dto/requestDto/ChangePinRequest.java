package com.sabi.framework.dto.requestDto;


import lombok.Data;

@Data
public class ChangePinRequest {


    private Long id;
    private String currentPin;
    private String newPin;
    private String password;

}
