package com.espresso.api.tables;

public class ApplicationTable extends Table{
    public Integer id = null;
    public String name = null;
    public String[] tags = null;
    public String description = null;
    public String linkOnWebSite = null;
    public Integer user_id = null;
    public Integer icon_id = null;
    public String linkToSource = null;

    @Override
    public String[] getFieldsNames() {
        return new String[]{"id","name","tags","description","linkOnWebSite","user_id","icon_id","linkToSource"};
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    @Override
    public String[] getRequiredFieldsWhileCreating() {
        return new String[]{"name","tags","user_id","linkToSource"};
    }

    @Override
    public String getTableName() {
        return "application";
    }
    @Override
    public void dataValidation() {

    }
}
