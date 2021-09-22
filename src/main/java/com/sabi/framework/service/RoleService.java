package com.sabi.framework.service;


import com.google.gson.Gson;
import com.sabi.framework.dto.requestDto.RoleDto;
import com.sabi.framework.dto.responseDto.RoleResponseDto;
import com.sabi.framework.exceptions.ConflictException;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.helpers.CoreValidations;
import com.sabi.framework.models.Role;
import com.sabi.framework.repositories.RoleRepository;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class RoleService {

    private  RoleRepository roleRepository;
    private final ModelMapper mapper;
    private final CoreValidations coreValidations;


    public RoleService(RoleRepository roleRepository, ModelMapper mapper, CoreValidations coreValidations) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.coreValidations = coreValidations;
    }



    /** <summary>
     * Role creation
     * </summary>
     * <remarks>this method is responsible for creation of new role</remarks>
     */

    public RoleResponseDto createRole(RoleDto request) {
        coreValidations.validateRole(request);
        Role role = mapper.map(request,Role.class);
        Role roleExist = roleRepository.findByName(request.getName());
        if(roleExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " Role already exist");
        }
        role.setCreatedBy(0l);
        role.setActive(true);
        role = roleRepository.save(role);
        log.debug("Create new role - {}"+ new Gson().toJson(role));
        return mapper.map(role, RoleResponseDto.class);
    }




    /** <summary>
     * Role update
     * </summary>
     * <remarks>this method is responsible for updating already existing role</remarks>
     */

    public RoleResponseDto updateRole(RoleDto request) {
        coreValidations.validateRole(request);
        Role role = roleRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested role id does not exist!"));
        mapper.map(request, role);
        role.setUpdatedBy(0l);
        roleRepository.save(role);
        log.debug("role record updated - {}"+ new Gson().toJson(role));
        return mapper.map(role, RoleResponseDto.class);
    }



    /** <summary>
     * Find role
     * </summary>
     * <remarks>this method is responsible for getting a single record</remarks>
     */
    public RoleResponseDto findRole(Long id){
        Role role  = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested role id does not exist!"));
        return mapper.map(role,RoleResponseDto.class);
    }



    /** <summary>
     * Find all roles
     * </summary>
     * <remarks>this method is responsible for getting all records in pagination</remarks>
     */
    public Page<Role> findAll(String name,Boolean isActive, PageRequest pageRequest ){
        Page<Role> roles = roleRepository.findRoles(name,isActive,pageRequest);
        if(roles == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, " No record found !");
        }
        return roles;

    }


    public List<Role> getAll(Boolean isActive){
        List<Role> roles = roleRepository.findByIsActive(isActive);
        return roles;

    }




}
