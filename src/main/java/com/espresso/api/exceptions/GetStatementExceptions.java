package com.espresso.api.exceptions;

public class GetStatementExceptions extends ApiExceptions{
    public GetStatementExceptions(String message,int code,String tableName){
        super(message,code,tableName);
    }

    @Override
    public String toString() {
        return "GetStatementExceptions";
    }
}

/*
 * DESCRIPTION :
 *     Exceptions that occur when inserting data into the database.
 * ERROR CODES :
*/
