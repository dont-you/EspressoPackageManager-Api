package com.espresso.api.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.espresso.api.dbhandlers.DataBaseReader;
import com.espresso.api.tables.WhereConditionHandler;
import com.espresso.api.exceptions.ApiExceptions;
import com.espresso.api.servlets.handlers.DeleteParametersControl;
import com.espresso.api.servlets.handlers.GetParametersControl;
import com.espresso.api.servlets.handlers.PostParametersControl;
import com.espresso.api.tables.Table;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainServlet
{
    public static void doGet(HttpServletRequest req, HttpServletResponse resp, Table entry) throws IOException{
        System.out.println("---------------------------------------------------------------------------------");
        String res;
        try{
            GetParametersControl parametersControl = new GetParametersControl();
            DataBaseReader dataBaseReader = new DataBaseReader();
            Map<String, String[]> parameters = req.getParameterMap();
            String cond = null;
            parametersControl.performChecout(entry, parameters);
            WhereConditionHandler whereCond = new WhereConditionHandler();

            if(parameters.containsKey("where")){
                cond = parameters.get("where")[0];
                cond = whereCond.recursive(cond);
            }

            String fields = "*";
            if(parameters.containsKey("fields")){
                fields = Arrays.stream(parameters.get("fields")).collect(Collectors.joining(","));
            }

            res = "{\"response\":" + dataBaseReader.getData(entry,fields,cond) + "}";

            JSONObject jsonObject = new JSONObject(res);
            JSONArray jsonArray = new JSONArray(jsonObject.get("response").toString());
            System.out.println(jsonArray.get(0));

        } catch(ApiExceptions e) {
            res = "{\"response\":" + e.getJsonDescribe() + "}";
        }
        resp.getOutputStream().println(res);
        System.out.println("---------------------------------------------------------------------------------");
    }

    public static void doPost(HttpServletRequest req, HttpServletResponse resp, Table entry, String jsonBody) throws ServletException, IOException {
        String res;
        try{
            Gson gson = Table.createGson();
            PostParametersControl parametersControl = new PostParametersControl();
            DataBaseReader dataBaseReader = new DataBaseReader();

            parametersControl.performCheckout(entry, jsonBody);
            entry = gson.fromJson(jsonBody, entry.getClass());
            entry.dataValidation();
            res = "{\"response\":" + dataBaseReader.postData(entry) + "}";
        } catch(ApiExceptions e) {
            res = "{\"response\":" + e.getJsonDescribe() + "}";
        }

        resp.getOutputStream().println(res);
    }

    public static void doPut(HttpServletRequest req, HttpServletResponse resp, Table entry, String jsonBody) throws ServletException, IOException {
        String res;
        try{
            Gson gson = Table.createGson();
            GetParametersControl parametersControl = new GetParametersControl();
            DataBaseReader dataBaseReader = new DataBaseReader();
            Map<String, String[]> parameters = req.getParameterMap();
            String cond = null;
            parametersControl.performChecout(entry, parameters);
            WhereConditionHandler whereCond = new WhereConditionHandler();

            if(parameters.containsKey("where")){
                cond = parameters.get("where")[0];
                cond = whereCond.recursive(cond);
            }

            if(parameters.containsKey("fields")){
                Arrays.stream(parameters.get("fields")).collect(Collectors.joining(","));
            }

            System.out.println(cond);

            entry = gson.fromJson(jsonBody, entry.getClass());
            entry.dataValidation();
            res = "{\"response\":" + dataBaseReader.putData(entry,cond) + "}";

        } catch(ApiExceptions e) {
            res = "{\"response\":" + e.getJsonDescribe() + "}";
        }
        resp.getOutputStream().println(res);
    }

    public static void doDelete(HttpServletRequest req, HttpServletResponse resp, Table entry) throws ServletException, IOException {
        String res;
        try {

            Gson gson = Table.createGson();
            JSONObject jsonObject = new JSONObject();
            DeleteParametersControl parametersControl = new DeleteParametersControl();
            DataBaseReader dataBaseReader = new DataBaseReader();
            String URI = req.getRequestURI();
            Pattern pattern = Pattern.compile("/");
            Matcher matcher = pattern.matcher(URI);
            String id = "";

            while(matcher.find()){
                id = URI.substring(matcher.start() + 1);
            }

            if(req.getParameter("id")!=null)
                id = req.getParameter("id");

            parametersControl.performCheckout(entry,id);
            jsonObject.put("id", id);
            entry = gson.fromJson(jsonObject.toString(), entry.getClass());
            res = "{\"response\":" + dataBaseReader.deleteData(entry) + "}";
        } catch (ApiExceptions e) {
            res = "{\"response\":" + e.getJsonDescribe() + "}";
        }

        resp.getOutputStream().println(res);
    }
}
