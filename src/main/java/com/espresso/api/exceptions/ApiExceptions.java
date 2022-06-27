package com.espresso.api.exceptions;

public class ApiExceptions extends Exception{
    public String error = "";
    public int code;
    public String tableName = null;

    public ApiExceptions(String message, int code){
        super(message);
        this.error = message;
        this.code = code;
    }

    public ApiExceptions(String message, int code,String tableName){
        this(message, code);
        this.tableName = tableName;
    }

    public String getJsonDescribe(){
        String jsonDescribe = "{";

        jsonDescribe += "\"error\":\"" + error + "\"" + "," + "\"error_name\":\"" + this.toString()
                + "\"" + "," + "\"code\":\"" + code + "\"" ;
        if(tableName!=null)
            jsonDescribe += "," + "\"table\":" + "\"" + tableName + "\"";

        return jsonDescribe + "}";
    }

}
