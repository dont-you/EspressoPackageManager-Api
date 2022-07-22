package com.espresso.api.dbhandlers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.espresso.api.ClientForTests;
import com.espresso.api.exceptions.DataBaseException;
import com.espresso.api.tables.IconTable;

import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DataBaseConnectorTest extends ClientForTests{

    @Mock
    private IconTable iconEntry;
    private DataBaseConnector dBaseConnector;

    public DataBaseConnectorTest(){
        MockitoAnnotations.initMocks(this);
        dBaseConnector = new DataBaseConnector(user,password,DBName);
    }

    @Test
    public void When_getById_Expect_objectWithSameId() throws DataBaseException{
        String requireFields = "*";
        String requireId = "1";
        when(iconEntry.getSelectStatementById(requireFields, requireId)).thenReturn("SELECT * FROM icon WHERE id=1");
        JSONObject response = dBaseConnector.getById(iconEntry,requireId,requireFields);
        assertEquals("The object with the required ID must have the same ID in the pesponse",(int)Integer.valueOf(requireId),response.getInt("id"));
    }

    @Test
    public void When_listGet_Expect_arrayWithEqualsRealValueFromDataBase() throws SQLException, DataBaseException{
        int expectedSize;
        ResultSet rs = this.performQuery("SELECT COUNT(id) FROM icon");

        rs.next();
        expectedSize = rs.getInt("COUNT(id)");

        String requireFields = "*";
        String where_condition="";

        when(iconEntry.getSelectStatement(requireFields,where_condition)).thenReturn("SELECT * FROM icon");
        assertEquals("The recieved array must be the same size in database",expectedSize, dBaseConnector.listGet(iconEntry,where_condition,requireFields).length());
    }

}
