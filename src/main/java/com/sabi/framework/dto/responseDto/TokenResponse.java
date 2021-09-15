package com.sabi.framework.dto.responseDto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponse {

    private String token;
    private String expiresIn;
    private String refreshToken;
    private String refreshExpiresIn;
    private String userId;
    private String role;
    private String fingerPrint;
}
