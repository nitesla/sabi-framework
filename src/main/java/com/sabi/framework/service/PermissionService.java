package com.sabi.framework.service;


import com.google.gson.Gson;
import com.sabi.framework.dto.requestDto.PermissionDto;
import com.sabi.framework.dto.responseDto.PermissionResponseDto;
import com.sabi.framework.exceptions.ConflictException;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.helpers.CoreValidations;
import com.sabi.framework.models.Permission;
import com.sabi.framework.repositories.PermissionRepository;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PermissionService {

    private  PermissionRepository permissionRepository;
    private final ModelMapper mapper;
    private final CoreValidations coreValidations;



    public PermissionService(PermissionRepository permissionRepository, ModelMapper mapper, CoreValidations coreValidations) {
        this.permissionRepository = permissionRepository;
        this.mapper = mapper;
        this.coreValidations = coreValidations;
    }


    /** <summary>
     * Permission creation
     * </summary>
     * <remarks>this method is responsible for creation of new permission</remarks>
     */

    public PermissionResponseDto createPermission(PermissionDto request) {
        coreValidations.validateFunction(request);
        Permission permission = mapper.map(request,Permission.class);
        Permission permissionExist = permissionRepository.findByName(request.getName());
        if(permissionExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " Permission already exist");
        }
        permission.setCreatedBy(0l);
        permission.setActive(true);
        permission = permissionRepository.save(permission);
        log.debug("Create new permission - {}"+ new Gson().toJson(permission));
        return mapper.map(permission, PermissionResponseDto.class);
    }




    /** <summary>
     * Permission update
     * </summary>
     * <remarks>this method is responsible for updating already existing permission</remarks>
     */

    public PermissionResponseDto updatePermission(PermissionDto request) {
        coreValidations.validateFunction(request);
        Permission permission = permissionRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested permission id does not exist!"));
        mapper.map(request, permission);
        permission.setUpdatedBy(0l);
        permissionRepository.save(permission);
        log.debug("permission record updated - {}"+ new Gson().toJson(permission));
        return mapper.map(permission, PermissionResponseDto.class);
    }




    /** <summary>
     * Find permission
     * </summary>
     * <remarks>this method is responsible for getting a single record</remarks>
     */
    public PermissionResponseDto findPermission(Long id){
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested permission id does not exist!"));
        return mapper.map(permission,PermissionResponseDto.class);
    }




    /** <summary>
     * Find all functions
     * </summary>
     * <remarks>this method is responsible for getting all records in pagination</remarks>
     */
    public Page<Permission> findAll(String name,Boolean isActive, PageRequest pageRequest ){
        Page<Permission> functions = permissionRepository.findFunctions(name,isActive,pageRequest);
        if(functions == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, " No record found !");
        }
        return functions;

    }

}
