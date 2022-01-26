package com.sabi.framework.dto.requestDto;


import lombok.Data;

@Data
public class PermissionDto {

    private Long id;
    private String name;
    private String code;
    private String menuName;
    private String url;
    private String permissionType;


}
