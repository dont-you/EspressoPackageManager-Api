package com.espresso.api;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

public class MainServlet
{
    protected static void doGet(HttpServletRequest req, HttpServletResponse resp, ITable instanceCreator){
        DataBaseReader.initialize();
        DataBaseReader dataBaseReader = new DataBaseReader();
        List<ITable> res = dataBaseReader.getData(req.getParameterMap(),instanceCreator);

        for(ITable user: res){
            if(user!=null)
                user.print();
        }

        try {
            Gson gson = new Gson();
            resp.getOutputStream().println(gson.toJson(res));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected static void doPost(HttpServletRequest req, HttpServletResponse resp, ITable instanceCreator) throws ServletException, IOException {
        DataBaseReader.initialize();
        DataBaseReader dataBaseReader = new DataBaseReader();
        String res = dataBaseReader.postData(req.getParameterMap(), instanceCreator);
        resp.getOutputStream().println(res);
    }

    protected static void doPut(HttpServletRequest req, HttpServletResponse resp, ITable instanceCreator) throws ServletException, IOException {
        DataBaseReader.initialize();
        DataBaseReader dataBaseReader = new DataBaseReader();
        String res = dataBaseReader.putData(req.getParameterMap(), instanceCreator);
        resp.getOutputStream().println(res);
    }

    protected static void doDelete(HttpServletRequest req, HttpServletResponse resp, ITable instanceCreator) throws ServletException, IOException {
        DataBaseReader.initialize();
        DataBaseReader dataBaseReader = new DataBaseReader();
        String res = dataBaseReader.deleteData(req.getParameterMap(), instanceCreator);
        resp.getOutputStream().println(res);
    }
}
