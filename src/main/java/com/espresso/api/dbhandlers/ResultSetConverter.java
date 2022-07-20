package com.espresso.api.dbhandlers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultSetConverter{
    public static JSONArray convert (ResultSet rs) throws SQLException{
        ResultSetMetaData md = rs.getMetaData();
        JSONArray result = new JSONArray();
        int num_cols = md.getColumnCount();
        List<String> colNames = IntStream.range(0, num_cols).mapToObj( i -> {
                try{
                    return md.getColumnName(i+1);
                } catch (SQLException e) {
                    return "?";
                }
            }).collect(Collectors.toList());

        while(rs.next()){
            JSONObject row = new JSONObject();
            colNames.forEach(cn -> {
                    try{
                        if(md.getColumnType(colNames.indexOf(cn)+1)==java.sql.Types.LONGVARCHAR){
                            row.put(cn,new JSONArray(rs.getString(cn)));
                        } else {
                            row.put(cn, rs.getObject(cn));
                        }

                    } catch( JSONException | SQLException e){
                        e.printStackTrace();
                    }
                });
            result.put(row);
        }
        return result;
    }
}
