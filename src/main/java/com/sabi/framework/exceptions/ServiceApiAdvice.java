package com.sabi.framework.exceptions;


import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.loggers.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
@Slf4j
@ControllerAdvice(annotations = RestController.class, basePackages = "com.sabi.api.controller")
@ResponseBody
public class ServiceApiAdvice {


    private static final Logger logger = LoggerFactory.getLogger(ServiceApiAdvice.class);

    @Autowired
    private ResponseHelper helper;

    @ExceptionHandler(LockedException.class)
    @ResponseStatus(value = HttpStatus.LOCKED)
    @ResponseBody
    public Response handleLockedException(LockedException e) {
        Response response = new Response();
        response.setCode(e.getCode());
        response.setDescription(e.getMessage());

        logger.error(e.toString());
        LoggerUtil.logError(logger, e);
        return response;
    }

    @ExceptionHandler(UserLoginException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Response handleLoginException(UserLoginException e) {
        Response response = new Response();
        response.setCode(e.getCode());
        response.setDescription(e.getMessage());

        logger.error(e.toString());
        LoggerUtil.logError(logger, e);
        return response;
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleServletRequestBindingException(ServletRequestBindingException e) {
        Response response = new Response();
        response.setCode("10011");
        response.setDescription("Invalid Credentials");

        logger.error(e.toString());
        LoggerUtil.logError(logger, e);
        return response;
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public Response handleCreateUserException(ConflictException e) {
        Response response = new Response();
        response.setCode(e.getCode());
        response.setDescription(e.getMessage());

        logger.error(e.toString());
        LoggerUtil.logError(logger, e);
        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Response handleNotFoundException(NotFoundException ex) {
        Response response = new Response();
        response.setCode(ex.getCode());
        response.setDescription(ex.getMessage());

        logger.error(ex.toString());
        LoggerUtil.logError(logger, ex);
        return response;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleNotFoundException(BadRequestException e) {
        Response response = new Response();
        response.setCode(e.getCode());
        response.setDescription(e.getMessage());

        logger.error(e.toString());
        LoggerUtil.logError(logger, e);
        return response;
    }


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Response handleUnauthorizedException(UnauthorizedException e) {
        Response response = new Response();
        response.setCode(e.getCode());
        response.setDescription(e.getMessage());

        logger.error(e.toString());
        LoggerUtil.logError(logger, e);
        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public Response handleSecurityAccessDenied() {
        Response response = new Response();
        response.setCode("10012");
        response.setDescription("Security: Access Denied");
        logger.error("Access violation: Access Denied Exception");
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response handleException(Exception e) {
        Response response = new Response();
        response.setCode("9999");
        response.setDescription("System Error Occurred. Contact System Administrator.");

        logger.error(e.toString());
        LoggerUtil.logError(logger, e);
        return response;
    }

    @ExceptionHandler(FailedRequestException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public Response handleFailedRequestException(FailedRequestException e) {
        Response response = new Response();
        response.setCode(e.getCode());
        response.setDescription(e.getMessage());
        logger.error(e.toString());
        LoggerUtil.logError(logger, e);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return helper.buildError(MissingFieldException.builder()
                .message("Validation error").fields(errors).build(), HttpStatus.BAD_REQUEST, "Bad Request");
    }

}
