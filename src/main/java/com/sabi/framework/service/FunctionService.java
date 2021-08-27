package com.sabi.framework.service;

import com.sabi.framework.dto.requestDto.FunctionDto;
import com.sabi.framework.models.Function;
import com.sabi.framework.models.ResponseModel;
import com.sabi.framework.repositories.FunctionRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            functionRepository.findByName(functionDto.getName());

        }
    }
}
