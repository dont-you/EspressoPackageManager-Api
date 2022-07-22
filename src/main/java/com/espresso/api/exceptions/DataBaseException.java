package com.espresso.api.exceptions;

public class DataBaseException extends ApiException {
    public DataBaseException(String message, int code) {
        super(message, code);
    }
}
