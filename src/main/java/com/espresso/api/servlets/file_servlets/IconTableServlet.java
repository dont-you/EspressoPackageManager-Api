package com.espresso.api.servlets.file_servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.espresso.api.servlets.MainServlet;
import com.espresso.api.tables.IconTable;

import org.json.JSONObject;

@WebServlet(urlPatterns = {"/icons","/icons/*"})
@MultipartConfig(maxFileSize = 262144,location = "/home/who/Projects/PackageManager/DataBaseFiles/icons")
public class IconTableServlet extends HttpServlet
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
        try {
            MainServlet.doGet(req, resp, new IconTable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonBody = FileManager.writeFileFromeRequest(req, "image","/home/who/Projects/PackageManager/DataBaseFiles/icons/");
        MainServlet.doPost(req, resp, new IconTable(),jsonBody.toString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // MainServlet.doPut(req, resp, new IconTable());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainServlet.doDelete(req, resp, new IconTable());
    }

    @Override
    public void destroy() {
        log("destroy");
    }

}
