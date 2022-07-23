package com.espresso.api.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public abstract class Table{

    static private GsonBuilder gsonBuilder;

    static{
        gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static Gson createGson(){
        return gsonBuilder.create();
    }


    public abstract String[] getFieldsNames();
    public abstract String[] getRequiredFieldsWhileCreating();
    public abstract String getTableName();
    public abstract String getPrimaryKeyName();
    public abstract void dataValidation();
    public abstract String retrivePrimaryKeyFromResultSet(ResultSet generatedKeys) throws SQLException;

    public String getJson() {
        Gson gson = createGson();
        return gson.toJson(this);
    }

    public String getInsertStatement() {
        Gson gson = Table.createGson();
        JSONObject entry = new JSONObject(gson.toJson(this));
        String[] fieldsNames = JSONObject.getNames(entry);

        String fields = "(";
        fields+=Arrays.stream(fieldsNames).map(e -> {
            return e;
        }).collect(Collectors.joining(","));
        fields+=")";

        String values = "VALUES(";
        values+=Arrays.stream(fieldsNames).map(e -> {
            return "'"+entry.get(e).toString()+"'";
        }).collect(Collectors.joining(","));
        values+=")";

        return "INSERT INTO "+this.getTableName()+fields+values;
    }

    public String getDeleteStatement(){
        Gson gson = Table.createGson();
        JSONObject entry = new JSONObject(gson.toJson(this));
        String[] keys = JSONObject.getNames(entry);

        String where_condition = "WHERE ";
        int i = 0;
        for(String key: keys){
            where_condition += key + "='" + entry.get(key) + "'";
            if (i != keys.length-1)
                where_condition += ", ";
        }

        return "DELETE FROM " + this.getTableName() + " " + where_condition;
    }

    public String getSelectStatement(String fields,String where_condition){
        if(where_condition!=null)
            where_condition = "WHERE " + where_condition;
        else
            where_condition = "";
        return "SELECT " + fields + " FROM " + this.getTableName() + " " + where_condition;
    }

    public String getSelectStatementById(String fields, String id){
        return null;
    }

    public String getUpdateStatement(String where_condition){

        Gson gson = Table.createGson();
        JSONObject entry = new JSONObject(gson.toJson(this));
        String[] fieldsNames = JSONObject.getNames(entry);

        if(where_condition!=null)
            where_condition = "WHERE " + where_condition;
        else
            where_condition = "";

        String set = "SET ";
        set+=Arrays.stream(fieldsNames).map(e -> {
            return e+"="+"'"+entry.get(e).toString()+"'";
        }).collect(Collectors.joining(","));

        return "UPDATE " + this.getTableName() + " " + set + " " + where_condition;
    }
}
