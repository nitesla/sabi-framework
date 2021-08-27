package com.sabi.framework.service;

import com.sabi.framework.dto.requestDto.RoleDto;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.models.ResponseModel;
import com.sabi.framework.models.Role;
import static com.sabi.framework.utils.Constants.*;
import com.sabi.framework.repositories.RoleRepository;
import com.sabi.framework.utils.CustomResponseCode;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<ResponseModel> createRole(RoleDto roleDto){
        try{
            Role role = modelMapper.map(roleDto, Role.class);
            Role rolecheck = roleRepository.findByName(roleDto.getName());
            if(rolecheck == null){
                roleRepository.save(role);

                return new ResponseEntity<>(new ResponseModel(REQUEST_SUCCESSFUL, OPERATION_SUCCESSFUL_MESSAGE, role), HttpStatus.OK);
            }
        return null;
        }catch (Exception ex){
            logger.info("createRole error{ }", ex);
            return new ResponseEntity<>(new ResponseModel(REQUEST_FAILED, OPERATION_ERROR_MESSAGE), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseModel> updateRole(RoleDto roleDto){
        try{
            Role role = modelMapper.map(roleDto, Role.class);
            Optional<Role> roleCheck = roleRepository.findById(roleDto.getId());
            if(!roleCheck.isPresent())
                throw new NotFoundException(RESOURCE_NOT_FOUND, "Invalid role provided!");
            roleRepository.save(role);
            return new ResponseEntity<>(new ResponseModel(REQUEST_SUCCESSFUL, OPERATION_SUCCESSFUL_MESSAGE, role), HttpStatus.OK);
        }
        catch (Exception ex){
            logger.info("createRole error{ }", ex);
            return new ResponseEntity<>(new ResponseModel(REQUEST_FAILED, OPERATION_ERROR_MESSAGE), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseModel> fetchRole(Long roleId) {

        try{
            Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                    "Requested role Id does not exist!"));
            return new ResponseEntity<>(new ResponseModel(REQUEST_SUCCESSFUL, OPERATION_SUCCESSFUL_MESSAGE, role), HttpStatus.OK);

        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseModel(RESOURCE_NOT_FOUND, RESOURCE_NOT_FOUND_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
