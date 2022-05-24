package com.espresso.api;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        MainServlet.doGet(req, resp, new UserTable());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainServlet.doPost(req, resp, new UserTable());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainServlet.doPut(req, resp, new UserTable());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainServlet.doDelete(req, resp, new UserTable());
    }

    @Override
    public void destroy() {
        log("destroy");
    }


    class UserTable implements ITable{
        public String login=null;
        public String password=null;

        @Override
        public String getTableName() {
            return "user";
        }

        @Override
        public ITable createInstance() {
            return new UserTable();
        }

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
