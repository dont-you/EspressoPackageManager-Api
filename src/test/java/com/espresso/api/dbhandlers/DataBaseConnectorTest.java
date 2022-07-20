package com.espresso.api.dbhandlers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;

import com.espresso.api.ClientForTests;
import com.espresso.api.tables.IconTable;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DataBaseConnectorTest extends ClientForTests{

    @Mock
    private IconTable iconEntry;

    public DataBaseConnectorTest(){
        MockitoAnnotations.initMocks(this);
    }

    static DataBaseConnector dBaseConnector;

    @BeforeClass
    public static void setup(){
        dBaseConnector = new DataBaseConnector(user,password,DBName);
    }

    @Before
    public void setupForMethod(){
        // iconEntry = new IconTable();
    }

    @Test
    public void When_getById_Expect_objectWithSameId(){
        String requireId = "1";
        String[] requireFields = new String[]{};
        when(iconEntry.getSelectStatementById(requireId, requireFields)).thenReturn("SELECT * FROM icon");
        JSONObject response = dBaseConnector.getById(iconEntry,requireId,requireFields);
        assertEquals("The object with the required ID must have the same ID in the pesponse",(int)Integer.valueOf(requireId),response.getInt("id"));
    }

    // @Test
    // public void When_getById_Expect_sameResultWeGetWhenExecutingQueryManualy(){
    //     DataBaseConnector dBaseConnector = new DataBaseConnector(user,password,DBName);
    //     int requireId = 1;
    //     String[] requiredFields = new String[]{};
    //     ResultSet rs = this.performQuery("SELECT * FROM icon WHERE id=1");
    //     JSONObject response = dBaseConnector.getById();
    //     assertEquals("The object with the required ID must have the same ID in the pesponse",requireId,response.getInt("id"));
    // }
}
