package com.sabi.framework.dto;

public class AbstractResponse {

    private String code;
    private String message;
    private Object data;

    public AbstractResponse(){

    }

    public AbstractResponse(String code, String message, Object details) {
        this.code = code;
        this.message = message;
        this.data = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AbstractResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
