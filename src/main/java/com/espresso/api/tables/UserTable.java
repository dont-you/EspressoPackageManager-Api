package com.espresso.api.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTable extends Table{
    public Integer id = null;
    public String name = null;
    public String password = null;
    public String email = null;
    public String isContributor = null;
    public String description = null;
    public String companyName = null;
    public String linkOnWebSite = null;
    public String linkOnGit = null;
    public Integer icon_id = null;

    @Override
    public String[] getFieldsNames() {
        return new String[]{"id","name","password","email","isContributor","description","companyName","linkOnWebSite","linkOnGit","icon_id"};
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    @Override
    public String[] getRequiredFieldsWhileCreating() {
        return new String[]{"name","email","password"};
    }

    @Override
    public String getTableName() {
        return "user";
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
