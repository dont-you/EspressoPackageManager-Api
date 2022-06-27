package com.espresso.api.exceptions;

public class UpdateParametersExceptions extends ApiExceptions{
    public UpdateParametersExceptions(String message,int code,String tableName){
        super(message,code,tableName);
    }

    @Override
    public String toString() {
        return "UpdateError";
    }
}

/*
 * DESCRIPTION :
 *     Exceptions that occur when inserting data into the database.
 * ERROR CODES :
 *     0 - unknow error unaccounted by developers
 *     1 - some data is ununical
 *     2 - some data is too long and doesn't fit in col
 */
