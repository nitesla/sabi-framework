package com.sabi.framework.helpers;

import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelper {
    public ResponseEntity<Response> buildResponse(Object response, HttpStatus httpCode, String description){
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription(description);
        resp.setData(response);
        return new ResponseEntity<>(resp, httpCode);
    }

    public ResponseEntity<Response>buildEnableDisable(){
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public Response buildError(Object response, HttpStatus httpCode, String description){
        Response resp = new Response();
        resp.setCode(((Integer) httpCode.value()).toString());
        resp.setDescription(description);
        resp.setData(response);
        return resp;
    }
}
