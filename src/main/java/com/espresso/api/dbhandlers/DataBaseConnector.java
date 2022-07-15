package com.espresso.api.dbhandlers;

import java.sql.*;

import com.espresso.api.tables.Table;

import org.json.JSONArray;
import org.json.JSONObject;

public final class DataBaseConnector {
    public Connection connection = null;

    private String serverAdress = "localhost:3306";
    private String dataBaseName = "PackageManager";
    private String dataBaseUserName = "root";
    private String password = "1337";

    private void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+serverAdress+"/"+dataBaseName,dataBaseUserName,password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("==================================================================================================================================");
            System.out.println(e.getMessage());
            System.out.println("==================================================================================================================================");
        }
    }

    public DataBaseConnector(){
        connect();
    }

    public DataBaseConnector(String dataBaseUserName, String password ,String dataBaseName){
        this.dataBaseName = dataBaseName;
        this.password = password;
        this.dataBaseUserName = dataBaseUserName;
        connect()
    }

    public DataBaseConnector(String dataBaseUserName, String password ,String dataBaseName, String serverAdress){
        this(dataBaseUserName,password,dataBaseName);
        this.serverAdress = serverAdress;
    }

    public JSONObject getById(int id, String[] requiredFields){
        return null;
    }

    public JSONArray listGet(String whereCond, String[] requiredFields){
        return null;
    }

    public Integer createNewEntry(Table entry){
        return null;
    }

    public void updateEachFieldInEntry(Table entry){

    }

    public Table updateSelectedFields(Table entry){
        return null;
    }

    public void deleteIfExists(int id){

    }
}
