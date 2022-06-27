package com.espresso.api.servlets.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.espresso.api.exceptions.PassedParametersException;
import com.espresso.api.tables.Table;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

public abstract class ParametersControl{
    public List<String> getExtraFieldsFromJson(String[] fieldsInTable, JSONObject passedJson){
        List<String> extraFields = new ArrayList<>();

        for(String name: JSONObject.getNames(passedJson)){
            if(!Arrays.stream(fieldsInTable).anyMatch(e -> e.equals(name)))
                extraFields.add(name);
        }
        return extraFields;
    }

    public Table tryPerformFilling(Table testEntry, String jsonBody) throws PassedParametersException{
        try{
            Gson gson = Table.createGson();
            testEntry = gson.fromJson(jsonBody, testEntry.getClass());
        } catch (JsonSyntaxException e){
            String message = e.getMessage();
            String tableName = testEntry.getTableName();

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(message);
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            Pattern pattern = Pattern.compile("\\$");
            Matcher matcher = pattern.matcher(message);
            if(matcher.find())
                throw new PassedParametersException("json in the '"+ message.substring(matcher.start()+2)+
                                                    "' field is incorrect please see the documentation for an example of filling this field",
                                                    4, tableName);
            else
                throw new PassedParametersException("some json field is incorrect", 4, tableName);

        }

        return testEntry;
    }
}
