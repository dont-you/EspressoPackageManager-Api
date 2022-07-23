package com.espresso.api.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IconTable extends Table{
    public Integer id = null;
    public String imagePath = null;
    public Integer size = null;

    @Override
    public String[] getFieldsNames() {
        return new String[]{"id","imagePath","size"};
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    @Override
    public String[] getRequiredFieldsWhileCreating() {
        return new String[]{"id","imagePath","size"};
    }

    @Override
    public String getTableName() {
        return "icon";
    }

    @Override
    public void dataValidation() {

    }

    @Override
    public String retrivePrimaryKeyFromResultSet(ResultSet generatedKeys) throws SQLException {
        String pk = null;
        while(generatedKeys.next()){
            pk = generatedKeys.getLong(1) + "";
        }
        return pk;
    }
}
