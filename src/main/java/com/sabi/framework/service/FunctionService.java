package com.sabi.framework.service;

import com.sabi.framework.dto.requestDto.FunctionDto;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.models.Function;
import com.sabi.framework.models.ResponseModel;
import com.sabi.framework.repositories.FunctionRepository;
import com.sabi.framework.utils.CustomResponseCode;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sabi.framework.utils.Constants.*;
import static com.sabi.framework.utils.Constants.OPERATION_ERROR_MESSAGE;

@Service
public class FunctionService {

    private final FunctionRepository functionRepository;
    private final ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(FunctionService.class);

    @Autowired
    public FunctionService(FunctionRepository functionRepository, ModelMapper modelMapper) {
        this.functionRepository = functionRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<ResponseModel> createFunction(FunctionDto functionDto){
        try{
            Function function = modelMapper.map(functionDto, Function.class);
            Function functioncheck = functionRepository.findByName(functionDto.getName());
            if(functioncheck == null){
                functionRepository.save(function);
                return new ResponseEntity<>(new ResponseModel(REQUEST_SUCCESSFUL, OPERATION_SUCCESSFUL_MESSAGE, function), HttpStatus.OK);
            }
            return null;
        }catch (Exception ex){
            logger.info("createfunction error{ }", ex);
            return new ResponseEntity<>(new ResponseModel(REQUEST_FAILED, OPERATION_ERROR_MESSAGE), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseModel> updateFunction(FunctionDto functionDto){
        try{
            Function function = modelMapper.map(functionDto, Function.class);
            Optional<Function> functionCheck = functionRepository.findById(functionDto.getId());
            if(!functionCheck.isPresent())
                throw new NotFoundException(RESOURCE_NOT_FOUND, "Invalid function provided!");

            functionRepository.save(function);
            return new ResponseEntity<>(new ResponseModel(REQUEST_SUCCESSFUL, OPERATION_SUCCESSFUL_MESSAGE, function), HttpStatus.OK);
        }
        catch (Exception ex){
            logger.info("updatefunction error{ }", ex);
            return new ResponseEntity<>(new ResponseModel(REQUEST_FAILED, OPERATION_ERROR_MESSAGE), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseModel> fetchFunction(Long functionId) {

        try{
            Function function = functionRepository.findById(functionId).orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                    "Requested function Id does not exist!"));
            return new ResponseEntity<>(new ResponseModel(REQUEST_SUCCESSFUL, OPERATION_SUCCESSFUL_MESSAGE, function), HttpStatus.OK);

        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseModel(RESOURCE_NOT_FOUND, RESOURCE_NOT_FOUND_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
