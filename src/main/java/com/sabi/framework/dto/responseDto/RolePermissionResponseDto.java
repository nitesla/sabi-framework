package com.sabi.framework.dto.responseDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RolePermissionResponseDto {
    private Long roleId;
    private Long permissionId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long createdBy;
    private Long updatedBy;
    private Boolean isActive;
}
