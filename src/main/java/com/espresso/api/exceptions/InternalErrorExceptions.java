package com.espresso.api.exceptions;

public class InternalErrorExceptions extends ApiExceptions{
    public InternalErrorExceptions(String message,int code){
        super(message,code);
    }

    @Override
    public String toString() {
        return "InternalError";
    }
}

/*
 * DESCRIPTION :
 *     This errors occurred on the server side, and to solve them,
 *     you should contact the system administrator
 * ERROR CODES :
 *     1 - means that lost connection with database
 */
