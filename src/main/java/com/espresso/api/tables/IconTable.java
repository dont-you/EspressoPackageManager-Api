package com.espresso.api.tables;

public class IconTable extends Table{
    Integer id = null;
    String imagePath = null;
    String size = null;

    // @Override
    // public String[] getFieldsNames() {
    //     return null;
    // }

    // @Override
    // public String getPrimaryKeyName() {
    //     return null;
    // }

    // @Override
    // public String[] getRequiredFieldsWhileCreating() {
    //     return null;
    // }

    // @Override
    // public String getTableName() {
    //     return null;
    // }
    // @Override
    // public void dataValidation() {

    // }

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
