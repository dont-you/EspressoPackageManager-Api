package com.espresso.api.tables;

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
}
