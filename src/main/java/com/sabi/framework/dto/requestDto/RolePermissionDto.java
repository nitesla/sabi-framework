package com.sabi.framework.dto.requestDto;

import com.sabi.framework.models.Permission;
import lombok.Data;

import java.util.List;

@Data
public class RolePermissionDto {

    private Long roleId;
    private List<Permission> permissions;
}
