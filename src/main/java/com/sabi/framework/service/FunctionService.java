package com.sabi.framework.service;


import com.google.gson.Gson;
import com.sabi.framework.dto.requestDto.FunctionDto;
import com.sabi.framework.dto.responseDto.FunctionResponseDto;
import com.sabi.framework.exceptions.ConflictException;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.helpers.CoreValidations;
import com.sabi.framework.models.Function;
import com.sabi.framework.repositories.FunctionRepository;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class FunctionService {

    private final FunctionRepository functionRepository;
    private final ModelMapper mapper;
    private final CoreValidations coreValidations;



    public FunctionService(FunctionRepository functionRepository, ModelMapper mapper,CoreValidations coreValidations) {
        this.functionRepository = functionRepository;
        this.mapper = mapper;
        this.coreValidations = coreValidations;
    }


    /** <summary>
     * Function creation
     * </summary>
     * <remarks>this method is responsible for creation of new function</remarks>
     */

    public FunctionResponseDto createFunction(FunctionDto request) {
        coreValidations.validateFunction(request);
        Function function = mapper.map(request,Function.class);
        Function functionExist = functionRepository.findByName(request.getName());
        if(functionExist !=null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, " Function already exist");
        }
        function.setCreatedBy(0l);
        function.setIsActive(true);
        function = functionRepository.save(function);
        log.debug("Create new function - {}"+ new Gson().toJson(function));
        return mapper.map(function, FunctionResponseDto.class);
    }




    /** <summary>
     * Function update
     * </summary>
     * <remarks>this method is responsible for updating already existing function</remarks>
     */

    public FunctionResponseDto updateFunction(FunctionDto request) {
        coreValidations.validateFunction(request);
        Function function = functionRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested function id does not exist!"));
        mapper.map(request, function);
        function.setUpdatedBy(0l);
        functionRepository.save(function);
        log.debug("function record updated - {}"+ new Gson().toJson(function));
        return mapper.map(function, FunctionResponseDto.class);
    }




    /** <summary>
     * Find function
     * </summary>
     * <remarks>this method is responsible for getting a single record</remarks>
     */
    public FunctionResponseDto findFunction(Long id){
        Function function  = functionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION,
                        "Requested function id does not exist!"));
        return mapper.map(function,FunctionResponseDto.class);
    }




    /** <summary>
     * Find all functions
     * </summary>
     * <remarks>this method is responsible for getting all records in pagination</remarks>
     */
    public Page<Function> findAll(String name, PageRequest pageRequest ){
        Page<Function> functions = functionRepository.findFunctions(name,pageRequest);
        if(functions == null){
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, " No record found !");
        }
        return functions;

    }

}
