package com.espresso.api;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import io.restassured.RestAssured;

@RunWith(JUnit4.class)
public class ClientForTests{
    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if(port==null){
            port = "8080";
        }
        RestAssured.port = Integer.valueOf(port);

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/test-api/";
        }
        RestAssured.basePath = basePath;
    }
}
