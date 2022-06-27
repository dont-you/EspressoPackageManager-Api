package com.espresso.api.servlets.handlers;

import java.util.Map;

import com.espresso.api.exceptions.PassedParametersException;
import com.espresso.api.tables.Table;

public class GetParametersControl extends ParametersControl{
    public void performChecout(Table entry, Map<String,String[]> parameters) throws PassedParametersException{
        try{
            WhereConditionHandler whereCond = new WhereConditionHandler();
            String cond = "";
            if(parameters.containsKey("_where"))
                cond = whereCond.recursive(parameters.get("_where")[0]);
        }catch(Exception e){
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(e.getMessage());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            throw new PassedParametersException("something went wrong with where condition", 6,entry.getTableName());
        }
    }
}
