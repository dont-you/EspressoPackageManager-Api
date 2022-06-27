package com.espresso.api.servlets.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.espresso.api.exceptions.PassedParametersException;
import com.espresso.api.tables.Table;
import com.google.gson.Gson;

import org.json.JSONObject;

public class PostParametersControl extends ParametersControl{
    public void performCheckout(Table entry,String jsonBody) throws PassedParametersException{

        entry = tryPerformFilling(entry, jsonBody);
        Gson gson = Table.createGson();
        List<String> missingFields = new ArrayList<>();
        String[] existingFields = JSONObject.getNames(new JSONObject(entry.getJson()));

        for(String requiredField: entry.getRequiredFieldsWhileCreating()){
            if(!Arrays.stream(existingFields).anyMatch(e -> e.equals(requiredField)))
                missingFields.add(requiredField);
        }

        if(missingFields.size()!=0){
            String jsonMissingFields = gson.toJson(missingFields);
            throw new PassedParametersException("fields - "+jsonMissingFields+" are required", 1, entry.getTableName());
        }

        JSONObject passed = new JSONObject(jsonBody);
        List<String> extraFields = getExtraFieldsFromJson(entry.getFieldsNames(), passed);
        if(extraFields.size()!=0){
            String jsonExtraFields = gson.toJson(extraFields);
            throw new PassedParametersException("fields - "+jsonExtraFields+" doesn't exist", 2,entry.getTableName());
        }
    }
}
