package com.espresso.api.dbhandlers;

import java.sql.*;

public final class DataBaseConnector {
    static public Connection connection = null;

    static final private String serverAdress = "localhost:3306";
    static final private String dataBaseName = "PackageManager";
    static final private String dataBaseUserName = "root";

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+serverAdress+"/"+dataBaseName,dataBaseUserName,"1337");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("==================================================================================================================================");
            System.out.println(e.getMessage());
            System.out.println("==================================================================================================================================");
        }
    }
}
