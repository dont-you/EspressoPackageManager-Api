package com.espresso.api.dbhandlers;

import java.sql.*;

import com.espresso.api.tables.Table;

import org.json.JSONObject;

public class DataBaseConnector {
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
        connect();
    }

    public DataBaseConnector(String dataBaseUserName, String password ,String dataBaseName, String serverAdress){
        this.dataBaseName = dataBaseName;
        this.password = password;
        this.dataBaseUserName = dataBaseUserName;
        this.serverAdress = serverAdress;
        connect();
    }

    public JSONObject getById(Table tableInstance,String id, String requiredFields){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(tableInstance.getSelectStatementById(requiredFields,id));

            // TODO replace with a function from ResultSetConverter
            return ResultSetConverter.convert(rs).getJSONObject(0);
            // TODO replace with a function from ResultSetConverter

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // public JSONArray listGet(String whereCond, String[] requiredFields){
    //     return null;
    // }

    // public Integer createNewEntry(Table entry){
    //     return null;
    // }

    // public void updateEachFieldInEntry(Table entry){

    // }

    // public Table updateSelectedFields(Table entry){
    //     return null;
    // }

    // public void deleteIfExists(int id){

    // }
}
