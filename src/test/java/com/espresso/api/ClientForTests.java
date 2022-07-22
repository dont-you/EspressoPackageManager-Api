package com.espresso.api;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ClientForTests{
    protected static Connection connection;
    protected static final String DBName = "PackageManagerTests";
    protected static final String user = "root";
    protected static final String password = "1337";

    @BeforeClass
    public static void setup() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBName,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("==================================================================================================================================");
            System.out.println(e.getMessage());
            System.out.println("==================================================================================================================================");
        }

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "mysql -u root -p1337 < src/test/resources/prepare_icon_table_for_tests.sql");
        try {
            Process start = processBuilder.start();
            InputStream inputStream = start.getInputStream();
            int c;
            while((c=inputStream.read())!=-1){
                System.out.print((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ResultSet performQuery(String query){
        try {
            return connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
