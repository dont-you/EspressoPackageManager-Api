package com.espresso.api.dbhandlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.espresso.api.tables.Table;

public class DataBaseExceptionsHandler{

    String handledErrorMessage = null;
    Integer handledErrorCode = null;

    public DataBaseExceptionsHandler(String message, int errorCode, Table instance){
        switch(errorCode){
        case 1062:{
            String fieldName=null;

            if (message.contains("PRIMARY")) {
                fieldName = instance.getPrimaryKeyName();
            } else {

                Pattern pattern = Pattern.compile("\\..*_");
                Matcher matcher = pattern.matcher(message);
                if(matcher.find())
                    fieldName = message.substring(matcher.start()+1, matcher.end()-1);
            }

            if(fieldName!=null)
                handledErrorMessage="value of field \'" + fieldName + "\' should be unique";
            else
                handledErrorMessage = "some value of field is not unique";

            handledErrorCode = 1;
            break;
        }case 1406: {
             Pattern pattern = Pattern.compile("'.*'");
             Matcher matcher = pattern.matcher(message);

             if(matcher.find()){
                 String fieldName=message.substring(matcher.start(),matcher.end());
                 handledErrorMessage="value of field " + fieldName + " too long";
             }else{
                handledErrorMessage = "some value is too long";
             }
             handledErrorCode = 2;
             break;
        } default: {
            handledErrorMessage="unknow error, contact to administrator";
            handledErrorCode = 0;
        }
        }
    }
}
