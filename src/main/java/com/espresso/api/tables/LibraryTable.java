package com.espresso.api.tables;

public class LibraryTable extends Table{
    public Integer id=null;
    public String name=null;
    public String description=null;
    public String linkToSource = null;

    @Override
    public String[] getFieldsNames() {
        return new String[] {"id","name","description","linkToSource"};
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    @Override
    public String[] getRequiredFieldsWhileCreating() {
        return new String[] {"id","name","linkToSource"};
    }

    @Override
    public String getTableName() {
        return "library";
    }
    @Override
    public void dataValidation() {

    }

}
