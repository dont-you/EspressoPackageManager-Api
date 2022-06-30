package com.espresso.api.dbhandlers;

import java.sql.*;
import java.sql.ResultSet;

import com.espresso.api.exceptions.DeleteStatementExceptions;
import com.espresso.api.exceptions.GetStatementExceptions;
import com.espresso.api.exceptions.InsertStatementExceptions;
import com.espresso.api.exceptions.InternalErrorExceptions;
import com.espresso.api.exceptions.UpdateParametersExceptions;
import com.espresso.api.tables.Table;

import org.json.JSONArray;


public class DataBaseReader {
    static public Connection connection = null;

    static final private String serverAdress = "localhost:3306";
    static final private String dataBaseName = "PackageManager";
    static final private String dataBaseUserName = "root";
    static final private String dataBaseUserPassword = "1337";

    public DataBaseReader() throws InternalErrorExceptions {
        try {
            if(connection==null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + serverAdress + "/" + dataBaseName,
                                                         dataBaseUserName, dataBaseUserPassword);
            }
        } catch (Exception e) {
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(e.getMessage());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            throw new InternalErrorExceptions("Something went wrong on the server",1);
        }
    }

    public JSONArray getData(Table entry, String fields, String where_cond) throws GetStatementExceptions {
        JSONArray result = new JSONArray();

        try {
            System.out.println("select statement - " + entry.getSelectStatement(fields, where_cond));
            ResultSet rs =  connection.createStatement().executeQuery(entry.getSelectStatement(fields, where_cond));
            result = ResultSetConverter.convert(rs);
        } catch (SQLException e) {

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(e.getMessage()+"    | code - "+e.getErrorCode());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            DataBaseExceptionsHandler eHandler = new DataBaseExceptionsHandler(e.getMessage(),
                    e.getErrorCode(), entry);
            throw new GetStatementExceptions(eHandler.handledErrorMessage,eHandler.handledErrorCode, entry.getTableName());
        }

        return result;
    }

    public String postData(Table entry) throws InsertStatementExceptions{
        String result = "";

        try {
            System.out.println("insert statement - " + entry.getInsertStatement());
            connection.createStatement().execute(entry.getInsertStatement());
            result = "successfull";
        } catch (SQLException e) {

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(e.getMessage()+"    | code - "+e.getErrorCode());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            DataBaseExceptionsHandler eHandler = new DataBaseExceptionsHandler(e.getMessage(),
                    e.getErrorCode(), entry);
            throw new InsertStatementExceptions(eHandler.handledErrorMessage,eHandler.handledErrorCode, entry.getTableName());
        }


        return result;
    }


    public String putData(Table entry,String where_cond) throws UpdateParametersExceptions{
        String result = "";
        try {
            System.out.println("update statement - " + entry.getUpdateStatement(where_cond));
            connection.createStatement().executeUpdate(entry.getUpdateStatement(where_cond));
        } catch (SQLException e) {

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(e.getMessage()+"    | code - "+e.getErrorCode());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            DataBaseExceptionsHandler eHandler = new DataBaseExceptionsHandler(e.getMessage(),
                    e.getErrorCode(), entry);
            throw new UpdateParametersExceptions(eHandler.handledErrorMessage,eHandler.handledErrorCode, entry.getTableName());
        }

        return result;
    }


    public String deleteData(Table entry) throws DeleteStatementExceptions{
        String result = "";
        try {
            System.out.println("delete statement - " + entry.getDeleteStatement());
            int count = connection.createStatement().executeUpdate(entry.getDeleteStatement());
            result = "successfull, deleted "+count+" rows";
        } catch (SQLException e) {

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println(e.getMessage()+"    | code - "+e.getErrorCode());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            DataBaseExceptionsHandler eHandler = new DataBaseExceptionsHandler(e.getMessage(),
                    e.getErrorCode(), entry);
            throw new DeleteStatementExceptions(eHandler.handledErrorMessage,eHandler.handledErrorCode, entry.getTableName());
        }


        return result;
    }
}
