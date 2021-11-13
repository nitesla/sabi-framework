package com.sabi.framework.dto.requestDto;

import lombok.Data;

@Data
public class UserDto {


    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
    private Long roleId;
//    private String password;
//    private String fingerPrint;

}
