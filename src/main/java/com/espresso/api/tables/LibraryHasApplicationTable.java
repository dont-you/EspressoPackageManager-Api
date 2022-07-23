package com.espresso.api.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryHasApplicationTable extends Table{
    public Integer library_id=null;
    public Integer application_id=null;

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

    @Override
    public String retrivePrimaryKeyFromResultSet(ResultSet generatedKeys) throws SQLException {
        return null;
    }
}
