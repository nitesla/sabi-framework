package com.sabi.framework.helpers;


import com.sabi.framework.dto.requestDto.PermissionDto;
import com.sabi.framework.dto.requestDto.RoleDto;
import com.sabi.framework.dto.requestDto.RolePermissionDto;
import com.sabi.framework.dto.requestDto.UserDto;
import com.sabi.framework.exceptions.BadRequestException;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.models.Permission;
import com.sabi.framework.repositories.PermissionRepository;
import com.sabi.framework.repositories.RoleRepository;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@SuppressWarnings("All")
@Slf4j
@Service
public class CoreValidations {
    private RoleRepository roleRepository;
    private PermissionRepository permissionRepository;

    public CoreValidations(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

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

    public void validateRolePermission(RolePermissionDto rolePermissionDto) {
        if ((Long) rolePermissionDto.getRoleId() == null)
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Role Id cannot be empty");
        if (rolePermissionDto.getPermissionIds() == null)
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Role permission(s) cannot be empty");
        roleRepository.findById(rolePermissionDto.getRoleId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        " Enter a valid Role"));
        rolePermissionDto.getPermissionIds().forEach((p) -> {
            permissionRepository.findById(p).orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                    " Permission " + p + " Does not exist"));
        });
    }


    public void validateUser(UserDto userDto) {
        if (userDto.getFirstName() == null || userDto.getFirstName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "First name cannot be empty");

        if (userDto.getLastName() == null || userDto.getLastName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Last name cannot be empty");

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "email cannot be empty");

        if (userDto.getPhone() == null || userDto.getPhone().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Phone number cannot be empty");

        if (userDto.getPassword() == null || userDto.getPassword().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Password cannot be empty");
    }

    public void updateUser(UserDto userDto) {
        if (userDto.getFirstName() == null || userDto.getFirstName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "First name cannot be empty");

        if (userDto.getLastName() == null || userDto.getLastName().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Last name cannot be empty");

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "email cannot be empty");

        if (userDto.getPhone() == null || userDto.getPhone().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Phone number cannot be empty");

    }

}
