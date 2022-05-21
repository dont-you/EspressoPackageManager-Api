package com.espresso.api;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class DataBaseReader {
    static public Connection connection;

    static final private String serverAdress = "localhost:3306";
    static final private String dataBaseName = "test";
    static final private String dataBaseUserName = "root";
    static final private String dataBaseUserPassword = "1337";

    public static void initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + serverAdress + "/" + dataBaseName,
                    dataBaseUserName, dataBaseUserPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ResultSet executeRequest(String req) throws IOException, SQLException {
        if (connection == null)
            throw new IOException("connection is null");
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(req);
        } catch (SQLException e) {
            throw e;
        }

    }

    private String formateParametrToWhereCondition(String condition){
        String result = "";
        for(char c: condition.toCharArray()){
            if (c != ':')
                result += c;
            else
                result += "=\'";
        }
        result += "\'";
        return result;
    }

    private String formateEntryToWhereCondition(Entry<String,String[]> entry){
        String result="";

        int i = 0;
        for(String condition: entry.getValue()){
            System.out.println(condition);
            result += formateParametrToWhereCondition(condition);
            if (++i != entry.getValue().length)
                result+=" OR ";
        }

        System.out.println("result");
        return result;
    }
    public List<ITable> getData(Map<String, String[]> parametress, String tab_references,InstanceCreator instanceCreator) {

        List<ITable> result = new ArrayList<>();

        try {
            String select_expr = "*";
            String where_condition = null;
            String order_by_col_name = null;
            String limit_row_count = null;

            if (parametress.containsKey("fields") && parametress.get("fields").length != 0)
                select_expr = Arrays.asList(parametress.get("fields")).stream().collect(Collectors.joining(","));

            String request = "SELECT " + select_expr + " FROM " + tab_references;
            if (where_condition != null)
                request += "WHERE " + where_condition;
            if (order_by_col_name != null)
                request += "ORDER BY " + order_by_col_name;
            if (limit_row_count != null)
                request += "LIMIT " + limit_row_count;

            ResultSet data = executeRequest(request);

            result = new ArrayList<>();

            int i = 0;
            while (data.next()) {
                result.add(instanceCreator.create());
                result.get(i).fill(data);
                ;
                i++;
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String postData(Map<String, String[]> parametress, String tab_references){
        String result="successful";
        String col_names="";
        String value_list = "";
        List<String> argumentNames=new ArrayList<>();
        List<String> argumentValues=new ArrayList<>();

        for(Entry<String,String[]> entry: parametress.entrySet()){
            argumentNames.add(entry.getKey());
            argumentValues.add("\'"+entry.getValue()[0]+"\'");
        }

        col_names=argumentNames.stream().collect(Collectors.joining(",","(",")"));
        value_list=argumentValues.stream().collect(Collectors.joining(",","(",")"));
        String request = "INSERT INTO " + tab_references + col_names + " VALUES" + value_list + ";";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }


    public String putData(Map<String, String[]> parametress, String tab_references){
        String result="successful";
        String assignment_list = "";
        String where_condition = "";
        List<String> sets=new ArrayList<>();

        for(Entry<String,String[]> entry: parametress.entrySet()){
            if(entry.getKey().compareTo("conditions")==0){
                where_condition+=formateEntryToWhereCondition(entry);
            } else {
                sets.add(entry.getKey() + "=" + "\'" +entry.getValue()[0] + "\'");
            }
        }

        assignment_list=sets.stream().collect(Collectors.joining(", "));

        String request = "UPDATE " + tab_references + " SET " + assignment_list + " WHERE " + where_condition + ";";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }


    public String deleteData(Map<String, String[]> parametress, String tab_references){
        String result="successful";
        String where_condition = "";

        System.out.println(parametress.entrySet().size());
        for(Entry<String,String[]> entry: parametress.entrySet()){
            if(entry.getKey().compareTo("conditions")==0){
                where_condition += formateEntryToWhereCondition(entry);
            }
        }

        String request = "DELETE FROM " + tab_references + " WHERE " + where_condition + ";";
        System.out.println(request);
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }

    static interface InstanceCreator {
        ITable create();
    }
}
