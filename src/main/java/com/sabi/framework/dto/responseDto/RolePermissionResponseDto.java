package com.sabi.framework.dto.responseDto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RolePermissionResponseDto {
    private Long roleId;
    private List<Long> permissionIds;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long createdBy;
    private Long updatedBy;
    private Boolean isActive;
}
