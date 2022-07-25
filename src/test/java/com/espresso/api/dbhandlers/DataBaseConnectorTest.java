package com.espresso.api.dbhandlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        iconEntry.imagePath = "/somepath/somefile";
        iconEntry.size = 50;
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

    @Test(expected = DataBaseException.class)
    public void When_listGet_With_someError_Expect_dataBaseException() throws SQLException, DataBaseException{
        int expectedSize;
        ResultSet rs = this.performQuery("SELECT COUNT(id) FROM icon");

        rs.next();
        expectedSize = rs.getInt("COUNT(id)");

        String requireFields = "*";
        String where_condition="";

        when(iconEntry.getSelectStatement(requireFields,where_condition)).thenReturn("simulation of the error");
        assertEquals("Should throw an exception because we are simulating some kind of error",expectedSize, dBaseConnector.listGet(iconEntry,where_condition,requireFields).length());
    }

    @Test
    public void When_createNewEntry_Expect_passedEntry_shouldEquals_entryInDataBase_withIdRecivedFromFunction() throws SQLException{

        when(iconEntry.getInsertStatement()).thenReturn(
                "INSERT INTO icon (imagePath,size) VALUES('" + iconEntry.imagePath + "'," + iconEntry.size + ")");

        iconEntry.id = Integer.valueOf(dBaseConnector.createNewEntry(iconEntry));
        ResultSet rs = this.performQuery("SELECT * FROM icon WHERE id="+iconEntry.id);
        JSONObject createdEntry = new JSONObject();

        rs.next();
        createdEntry.put("id", rs.getInt("id"));
        createdEntry.put("imagePath", rs.getString("imagePath"));
        createdEntry.put("size", rs.getInt("size"));


        when(iconEntry.getJson()).thenReturn(
                new JSONObject("{\"id\":" + iconEntry.id + ", \"imagePath\":\"" + iconEntry.imagePath + "\",\"size\":"
                        + iconEntry.size + "}").toString());

        assertTrue("The created entry must be equal to the passed entry to the function",
                iconEntry.getJson().equals(createdEntry.toString()));
    }
}