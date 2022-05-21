package com.espresso.api;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

@WebServlet(urlPatterns = {"/users","/users/*"})
public class UserTableServlet extends HttpServlet
{

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("init");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        DataBaseReader.initialize();
        DataBaseReader.InstanceCreator userTableInstanceCreator = () -> {
            return new UserTable();
        };
        DataBaseReader dataBaseReader = new DataBaseReader();
        List<ITable> res = dataBaseReader.getData(req.getParameterMap(), "user", userTableInstanceCreator);

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseReader.initialize();
        DataBaseReader dataBaseReader = new DataBaseReader();
        String res = dataBaseReader.postData(req.getParameterMap(), "user");
        resp.getOutputStream().println(res);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseReader.initialize();
        DataBaseReader dataBaseReader = new DataBaseReader();
        String res = dataBaseReader.putData(req.getParameterMap(), "user");
        resp.getOutputStream().println(res);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseReader.initialize();
        DataBaseReader dataBaseReader = new DataBaseReader();
        String res = dataBaseReader.deleteData(req.getParameterMap(), "user");
        resp.getOutputStream().println(res);
    }

    @Override
    public void destroy() {
        log("destroy");
    }


    class UserTable implements ITable{
        public String login=null;
        public String password=null;

        @Override
        public void fill(ResultSet data){
            try{
                login = data.getString("login");
            }catch(SQLException e){
                System.out.println("Field will not found");
            }

            try{
                password = data.getString("password");
            }catch(SQLException e){
                System.out.println("Field will not found");
            }
        }

        @Override
        public void print(){
            if(login!=null) System.out.println("login:" +login);
            if(password!=null) System.out.println("password:" +password);
        }
    }
}
