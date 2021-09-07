package com.sabi.framework.dto.requestDto;

import lombok.Data;

import java.util.List;

@Data
public class RolePermissionDto {

    private Long roleId;
    private List<Long> permissionIds;
}
