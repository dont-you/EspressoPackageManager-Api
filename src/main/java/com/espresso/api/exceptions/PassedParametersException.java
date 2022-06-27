package com.espresso.api.exceptions;

public class PassedParametersException extends ApiExceptions{
    public PassedParametersException(String message,int code,String tableName){
        super(message,code,tableName);
    }

    @Override
    public String toString() {
        return "PassedParameters";
    }
}

/*
 * DESCRIPTION :
 *     These errors occur when the client parameters passed on the request are incorrect
 * ERROR CODES :
 *     1 - passed not everything fields (For example: passed fields 'name,password',
 *     but request must contain 'name,password,email')
 *     2 - client passed unexsisting fields (For example: passed field 'age'
 *     doesn't exist in the table 'application')
 *     3 - some parameters are passed in array formt, although they should have one value
 *     4 - some json field is incorrect (For example: json field 'installedApplications'
 *     in the table 'user' presented in array format [1,23,3,4], but client pass something
 *     like this - {"id_app1": 1, "id_app2": 23, "id_app3": 3, "id_app4": 4})
 *     5 - requred id of entry, but passed id is wrong
 *     6 - where condition is wrong
 */
