package com.sabi.framework.dto.requestDto;

import lombok.Data;

@Data
public class ActivateUserAccountDto {


    private String resetToken;

    private Long updatedBy;
    private Boolean isActive;
}
