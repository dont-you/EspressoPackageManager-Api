package com.espresso.api.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.espresso.api.tables.Table;

public class MainServlet
{
    public static void doGet(HttpServletRequest req, HttpServletResponse resp, Table entry) throws IOException{
    }

    public static void doPost(HttpServletRequest req, HttpServletResponse resp, Table entry, String jsonBody) throws ServletException, IOException {
    }

    public static void doPut(HttpServletRequest req, HttpServletResponse resp, Table entry, String jsonBody) throws ServletException, IOException {
    }

    public static void doDelete(HttpServletRequest req, HttpServletResponse resp, Table entry) throws ServletException, IOException {
    }
}
