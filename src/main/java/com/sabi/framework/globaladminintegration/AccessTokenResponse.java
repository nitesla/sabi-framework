package com.sabi.framework.globaladminintegration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessTokenResponse {

    private String accessToken;
    private String email;
    private long tokenExpiry;
    private long userId;

}
