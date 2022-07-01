package com.espresso.api.servlets.handlers;

import java.util.List;
import java.util.Map;

import com.espresso.api.dbhandlers.WhereConditionHandler;
import com.espresso.api.exceptions.PassedParametersException;
import com.espresso.api.tables.Table;
import com.google.gson.Gson;

import org.json.JSONObject;

public final class UpdateParametersControl extends ParametersControl{
    public void performChecout(Table entry, Map<String,String[]> parameters, String jsonBody) throws PassedParametersException{

        entry = tryPerformFilling(entry, jsonBody);
        Gson gson = Table.createGson();

        JSONObject passed = new JSONObject(jsonBody);
        List<String> extraFields = getExtraFieldsFromJson(entry.getFieldsNames(), passed);
        if(extraFields.size()!=0){
            String jsonExtraFields = gson.toJson(extraFields);
            throw new PassedParametersException("fields - "+jsonExtraFields+" doesn't exist", 2,entry.getTableName());
        }

        try{
            WhereConditionHandler whereCond = new WhereConditionHandler();
            if(parameters.containsKey("_where"))
                whereCond.recursive(parameters.get("_where")[0]);
        }catch(Exception e){
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(e.getMessage());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            throw new PassedParametersException("something went wrong with where condition", 6,entry.getTableName());
        }
    }

}
