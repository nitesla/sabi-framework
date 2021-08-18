package com.sabi.framework.dto.requestDto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    private String middleName;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String phone;
}
