package com.sabi.framework.dto.requestDto;

import lombok.Data;

@Data
public class ForgetPasswordDto {

    private String email;
    private String fingerPrint;
}
