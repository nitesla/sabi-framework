package com.sabi.framework.service;

import com.google.gson.Gson;
import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.requestDto.RolePermissionDto;
import com.sabi.framework.dto.responseDto.RolePermissionResponseDto;
import com.sabi.framework.exceptions.ConflictException;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.helpers.CoreValidations;
import com.sabi.framework.models.RolePermission;
import com.sabi.framework.repositories.RolePermissionRepository;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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

    /** <summary>
     * RolePermission creation
     * </summary>
     * <remarks>this method is responsible for creation of new RolePermission</remarks>
     */

    public RolePermissionResponseDto createRolePermission(RolePermissionDto request) {
        coreValidations.validateRolePermission(request);
        RolePermission rolePermission = mapper.map(request,RolePermission.class);
        RolePermission rolePermissionExist = rolePermissionRepository
                .findByRoleId(request.getRoleId());
        if(rolePermissionExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,
                    " RolePermission already exist");
        }
        rolePermission.setCreatedBy(0L);
        rolePermission.setIsActive(true);
        rolePermission = rolePermissionRepository.save(rolePermission);
        log.debug("Create new RolePermission - {}"+ new Gson().toJson(rolePermission));
        return mapper.map(rolePermission, RolePermissionResponseDto.class);
    }


    /** <summary>
     * RolePermission update
     * </summary>
     * <remarks>this method is responsible for updating already existing RolePermission</remarks>
     */

    public RolePermissionResponseDto updateRolePermission(RolePermissionDto request) {
        coreValidations.validateRolePermission(request);
        RolePermission rolePermission = mapper.map(request, RolePermission.class);
        RolePermission rolePermissionExist = rolePermissionRepository
                .findByRoleId(request.getId());
        if(rolePermissionExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,
                    " RolePermission already exist");
        }
        mapper.map(request, rolePermission);
        rolePermission.setUpdatedBy(0L);
        rolePermissionRepository.save(rolePermission);
        log.debug("RolePermission record updated - {}"+ new Gson().toJson(rolePermission));
        return mapper.map(rolePermission, RolePermissionResponseDto.class);
    }


    /** <summary>
     * Find RolePermission
     * </summary>
     * <remarks>this method is responsible for getting a single record</remarks>
     */
    public RolePermissionResponseDto findRolePermission(Long id){
        RolePermission rolePermission = rolePermissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested RolePermission id does not exist!"));
        log.info(String.valueOf(Arrays.asList(rolePermission.getPermissionIds())));
        return mapper.map(rolePermission, RolePermissionResponseDto.class);
    }


    /** <summary>
     * Find all functions
     * </summary>
     * <remarks>this method is responsible for getting all records in pagination</remarks>
     */
    public Page<RolePermission> findAll(Long roleId, Boolean isActive, PageRequest pageRequest ){
        Page<RolePermission> functions = rolePermissionRepository.findRolePermission(roleId, isActive, pageRequest);
        if(functions == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, " No record found !");
        }
        return functions;
    }

    public void enableDisEnableState (EnableDisEnableDto request){
        RolePermission creditLevel  = rolePermissionRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested creditLevel id does not exist!"));
        creditLevel.setIsActive(request.getIsActive());
        creditLevel.setUpdatedBy(0L);
        rolePermissionRepository.save(creditLevel);

    }
}
