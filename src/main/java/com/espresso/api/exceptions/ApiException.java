package com.espresso.api.exceptions;

public class ApiException extends Exception{

    protected int code;

    public ApiException(String message,int code){
        super(message);
        this.code = code;
    }
}
