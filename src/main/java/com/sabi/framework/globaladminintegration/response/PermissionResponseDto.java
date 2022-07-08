package com.sabi.framework.globaladminintegration.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionResponseDto {

    private Long id;
    private String name;
    private String permissionType;
    private String appPermission;
    private String createdDate;
    private String updatedDate;
    private Long createdBy;
    private Long updatedBy;
    private Boolean isActive;


}
