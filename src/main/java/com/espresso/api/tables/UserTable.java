package com.espresso.api.tables;

public class UserTable extends Table{
    Integer id = null;
    String name = null;
    String password = null;
    String email = null;
    String isContributor = null;
    String description = null;
    String companyName = null;
    String linkOnWebSite = null;
    String linkOnGit = null;
    Integer icon_id = null;

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
}
