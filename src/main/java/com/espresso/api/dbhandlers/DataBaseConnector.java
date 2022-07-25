package com.espresso.api.dbhandlers;

import static org.mockito.Mockito.never;

import java.sql.*;

import com.espresso.api.exceptions.DataBaseException;
import com.espresso.api.tables.Table;

import org.json.JSONArray;
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

    public ResultSet performQuery(String query) throws DataBaseException{
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            return rs;

        } catch (SQLException e) {
            throw new DataBaseException("Error when executing a request with the message: \""+e.getMessage()+"\"",1);
        }
    }

    public JSONObject getById(Table tableInstance,String id, String requiredFields) throws DataBaseException{
        String query = tableInstance.getSelectStatementById(requiredFields,id);
        JSONArray jsonArray = ResultSetConverter.convert(performQuery(query));

        if(jsonArray.length()!=0)
            return jsonArray.getJSONObject(0);
        else
            throw new DataBaseException("The item was not found", 2);
    }

    public JSONArray listGet(Table tableInstance, String whereCond, String requiredFields) throws DataBaseException{
        String query = tableInstance.getSelectStatement(requiredFields, whereCond);
        return ResultSetConverter.convert(performQuery(query));
    }

    public int createNewEntry(Table entry) throws DataBaseException{
        Integer id = null;

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(entry.getInsertStatement(), Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            while(generatedKeys.next()){
                id = (int) generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error when executing a request with the message: \""+e.getMessage()+"\"",1);
        }

        if(id==null)
            throw new DataBaseException("For some reason recieve id from response was failure",3);

        return id;
    }

    // public void updateEachFieldInEntry(Table entry){

    // }

    // public Table updateSelectedFields(Table entry){
    //     return null;
    // }

    // public void deleteIfExists(int id){

    // }
}
