package com.espresso.api;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import com.espresso.api.ClientForTests;

import org.junit.Test;

public class GetByIdTests extends ClientForTests{

    String full_access_token = "sdalhbfyuhbalnjl";

    @Test
    public void When_allGood_Except_200(){
        // given().when().get("/users/1?fields=id,name,password&access_token=Sdfhalbhsgyu13h21hgguysadG").then().statusCode(200);
    }

    @Test
    public void When_unknownFieldInFieldsParametrForUserEntity_Except_400(){
        String unknownField = "size";
        given().when().get("/users/1?access_toke="+full_access_token+"&fields=id,name,"+unknownField).then().statusCode(400);
    }

    @Test
    public void When_entityOrIdDoesNotExist_Except_404(){
        // given().when().get("/applications").then().statusCode(400);
    }

    @Test
    public void When_passedFileIsNotSupported_Except_415(){
        // given().when().get("/applications").then().statusCode(400);
    }

    @Test
    public void When_unknownRequestForServer_Except_418(){
        // given().when().get("/applications").then().statusCode(400);
    }

    @Test
    public void When_tokenDoesNotPassAuthentication_Except_419(){
        // given().when().get("/applications").then().statusCode(400);
    }

    @Test
    public void When_bodyOfQueryDoesNotPassedValidation_Except_422(){
        // given().when().get("/applications").then().statusCode(400);
    }
}
