package com.sabi.framework.dto.responseDto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RolePermissionResponseDto {
    private long role_id;
    private List<Integer> permission_id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long createdBy;
    private Long updatedBy;
    private Boolean isActive;
}
