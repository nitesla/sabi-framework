package com.sabi.framework.dto.responseDto;

import com.sabi.framework.models.Permission;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RolePermissionResponseDto {
    private Long roleId;
    private List<Permission> permissions;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long createdBy;
    private Long updatedBy;
    private Boolean isActive;
}
