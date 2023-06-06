package com.nischal.aws.ecs.exceptions;

public class CustomException extends Exception{

    String message;
    int code;
    String details;
    public CustomException(int code, String message, String details)
    {
        this.message=message;
        this.code=code;
        this.details = details;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
