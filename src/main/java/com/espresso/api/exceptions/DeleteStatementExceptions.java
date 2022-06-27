package com.espresso.api.exceptions;

public class DeleteStatementExceptions extends ApiExceptions{
    public DeleteStatementExceptions(String message,int code,String tableName){
        super(message,code,tableName);
    }

    @Override
    public String toString() {
        return "DeleteError";
    }
}

/*
 * DESCRIPTION :
 *     Exceptions that occur when inserting data into the database.
 * ERROR CODES :
*/
