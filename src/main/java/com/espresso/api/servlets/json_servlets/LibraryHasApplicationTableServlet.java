package com.espresso.api.servlets.json_servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.espresso.api.servlets.MainServlet;
import com.espresso.api.tables.LibraryHasApplicationTable;

@WebServlet(urlPatterns = { "/libraryes_has_applications","/libraryes_has_applications*"})
public class LibraryHasApplicationTableServlet extends HttpServlet
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
            MainServlet.doGet(req, resp, new LibraryHasApplicationTable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        MainServlet.doPost(req, resp, new LibraryHasApplicationTable(),jsonBody);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        MainServlet.doPut(req, resp, new LibraryHasApplicationTable(),jsonBody);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainServlet.doDelete(req, resp, new LibraryHasApplicationTable());
    }

    @Override
    public void destroy() {
        log("destroy");
    }

}
