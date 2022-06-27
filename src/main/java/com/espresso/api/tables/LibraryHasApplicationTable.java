package com.espresso.api.tables;

public class LibraryHasApplicationTable extends Table{
    Integer library_id=null;
    Integer application_id=null;

    @Override
    public String[] getFieldsNames() {
        return new String[] {"library_id","application_id"};
    }

    @Override
    public String getPrimaryKeyName() {
        return "";
    }

    @Override
    public String[] getRequiredFieldsWhileCreating() {
        return new String[] {"library_id","application_id"};
    }

    @Override
    public String getTableName() {
        return "library_has_application";
    }
    @Override
    public void dataValidation() {

    }

}
