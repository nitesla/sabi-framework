package com.sabi.framework.helpers;


import com.sabi.framework.dto.requestDto.PermissionDto;
import com.sabi.framework.dto.requestDto.RoleDto;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@SuppressWarnings("All")
@Slf4j
@Service
public class CoreValidations {


    public void validateRole(RoleDto roleDto) {
        if (roleDto.getName() == null || roleDto.getName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Name cannot be empty");

        if (roleDto.getDescription() == null || roleDto.getDescription().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Description cannot be empty");
    }

    public void validateFunction(PermissionDto permissionDto) {
        if (permissionDto.getName() == null || permissionDto.getName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Name cannot be empty");

        if (permissionDto.getCode() == null || permissionDto.getCode().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Description cannot be empty");
    }
}
