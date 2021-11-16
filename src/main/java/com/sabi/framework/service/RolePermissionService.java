package com.sabi.framework.service;

import com.google.gson.Gson;
import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.requestDto.RolePermissionDto;
import com.sabi.framework.dto.responseDto.RolePermissionResponseDto;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.helpers.CoreValidations;
import com.sabi.framework.models.RolePermission;
import com.sabi.framework.models.User;
import com.sabi.framework.repositories.RolePermissionRepository;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final ModelMapper mapper;
    private final CoreValidations coreValidations;


    public RolePermissionService(RolePermissionRepository RolePermissionRepository,
                                 ModelMapper mapper, CoreValidations coreValidations) {
        this.rolePermissionRepository = RolePermissionRepository;
        this.mapper = mapper;
        this.coreValidations = coreValidations;
    }

    /**
     * <summary>
     * RolePermission creation
     * </summary>
     * <remarks>this method is responsible for creation of new RolePermission</remarks>
     */

    public RolePermissionResponseDto createRolePermission(RolePermissionDto request) {
        coreValidations.validateRolePermission(request);
        User userCurrent = TokenService.getCurrentUserFromSecurityContext();
        RolePermission rolePermission = new RolePermission();
        for (long permission : request.getPermissionIds()) {
            rolePermission.setPermissionId(permission);
            rolePermission.setRoleId(request.getRoleId());
            rolePermission.setCreatedBy(userCurrent.getId());
            rolePermission.setIsActive(true);
            boolean exists = rolePermissionRepository
                    .existsByRoleIdAndPermissionId(request.getRoleId(), permission);
            if (!exists) {
                rolePermission = rolePermissionRepository.save(rolePermission);
                log.debug("Create new RolePermission - {}" + new Gson().toJson(rolePermission));
            }
        }
        return mapper.map(rolePermission, RolePermissionResponseDto.class);
    }


    /**
     * <summary>
     * RolePermission update
     * </summary>
     * <remarks>this method is responsible for updating already existing RolePermission</remarks>
     */

    public RolePermissionResponseDto updateRolePermission(RolePermissionDto request) {
        coreValidations.validateRolePermission(request);
        User userCurrent = TokenService.getCurrentUserFromSecurityContext();
        rolePermissionRepository.findById(request.getId()).orElseThrow(() ->
                new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested role permission id does not exist!")
        );
        RolePermission rolePermission = new RolePermission();
        for (long permission : request.getPermissionIds()) {
            rolePermission.setPermissionId(permission);
            rolePermission.setId(request.getId());
            rolePermission.setRoleId(request.getRoleId());
            rolePermission.setUpdatedBy(userCurrent.getId());
            rolePermission.setIsActive(true);
            boolean exists = rolePermissionRepository
                    .existsByRoleIdAndPermissionId(request.getRoleId(), permission);
            if (!exists) {
                rolePermission = rolePermissionRepository.save(rolePermission);
                log.debug("Create new RolePermission - {}" + new Gson().toJson(rolePermission));
            }
        }
        return mapper.map(rolePermission, RolePermissionResponseDto.class);
    }


    /**
     * <summary>
     * Find RolePermission
     * </summary>
     * <remarks>this method is responsible for getting a single record</remarks>
     */
    public RolePermissionResponseDto findRolePermission(Long id) {
        RolePermission rolePermission = rolePermissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested RolePermission id does not exist!"));
//        log.info(String.valueOf(Arrays.asList(rolePermission.getPermissionId())));
        return mapper.map(rolePermission, RolePermissionResponseDto.class);
    }


    /**
     * <summary>
     * Find all functions
     * </summary>
     * <remarks>this method is responsible for getting all records in pagination</remarks>
     */
    public Page<RolePermission> findAll(Long roleId, Boolean isActive, PageRequest pageRequest) {
        Page<RolePermission> functions = rolePermissionRepository.findRolePermission(roleId, isActive, pageRequest);
        if (functions == null) {
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, " No record found !");
        }
        return functions;
    }

    public void enableDisEnableState(EnableDisEnableDto request) {
        User userCurrent = TokenService.getCurrentUserFromSecurityContext();
        RolePermission creditLevel = rolePermissionRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested creditLevel id does not exist!"));
        creditLevel.setIsActive(request.isActive());
        creditLevel.setUpdatedBy(userCurrent.getId());
        rolePermissionRepository.save(creditLevel);

    }

    public List<RolePermission> getAll(Long roleId, Boolean isActive){
        List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleIdAndIsActive(roleId, isActive);
        return rolePermissions;

    }
}
