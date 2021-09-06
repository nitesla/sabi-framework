package com.sabi.framework.dto.requestDto;

import lombok.Data;

import java.util.List;

@Data
public class RolePermissionDto {

    private long role_id;
    private List<Integer> permission_id;
}
