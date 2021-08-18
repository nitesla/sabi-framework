package com.sabi.framework.models;

public class ResponseModel<T> {

    private String status;
    private String message;
    private T data;

    public ResponseModel(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseModel(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setData(T data) {
        this.data = data;
    }

}
