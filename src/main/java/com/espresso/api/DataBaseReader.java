package com.espresso.api;

import java.sql.*;

public class DataBaseReader
{
    static public Connection connection;

    static final private String serverAdress = "localhost:3306";
    static final private String dataBaseName="test";
    static final private String dataBaseUserName = "root";
    static final private String dataBaseUserPassword = "1337";


    public static void initialize(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+serverAdress+"/"+dataBaseName, dataBaseUserName, dataBaseUserPassword);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String test()
    {
        try {
            if(connection == null) System.out.println("connection is null");
            Statement stmt = connection.createStatement();
            ResultSet rSet = stmt.executeQuery("SELECT * FROM user");

            String temp = "";
            while(rSet.next()){
                temp += rSet.getString(1)+"\n";
            }

            return temp;

        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }

    }
}
