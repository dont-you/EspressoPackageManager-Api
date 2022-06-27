package com.espresso.api.servlets.handlers;

import java.util.regex.Pattern;

import com.espresso.api.exceptions.DeleteStatementExceptions;
import com.espresso.api.exceptions.PassedParametersException;
import com.espresso.api.tables.Table;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

public class DeleteParametersControl extends ParametersControl{
    public void performCheckout(Table entry,String id) throws PassedParametersException {
        if(!id.matches("^[\\d]+$")){

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("wrong id");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            throw new PassedParametersException("id is wrong",5, entry.getTableName());
        }
    }
}
