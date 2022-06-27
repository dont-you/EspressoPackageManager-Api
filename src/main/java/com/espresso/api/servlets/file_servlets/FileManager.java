package com.espresso.api.servlets.file_servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.json.JSONObject;

public class FileManager{
    public static JSONObject writeFileFromeRequest(HttpServletRequest req, String fileKeyName, String writePath) throws IOException, ServletException{
        JSONObject result = new JSONObject();

        for(Part part: req.getParts()){
            String partName = part.getName();
            if(partName.equals(fileKeyName)){
                String fileName = UUID.randomUUID().toString() + part.getSubmittedFileName();
                part.write(fileName);
                result.put(fileKeyName+"Path",writePath+fileName);
            }else{
                InputStream inputStream = part.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                String value = new BufferedReader(isr)
                    .lines()
                    .collect(Collectors.joining(""));
                result.put(partName, value);
            }
        }

        return result;
    }
}
