package com.sabi.framework.globaladminintegration;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccesTokenRequest {

    private String password;
    private String username;
}
