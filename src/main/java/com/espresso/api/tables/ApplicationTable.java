package com.espresso.api.tables;

public class ApplicationTable extends Table{
    Integer id = null;
    String name = null;
    String[] tags = null;
    String description = null;
    String linkOnWebSite = null;
    Integer user_id = null;
    Integer icon_id = null;
    String linkToSource = null;

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
