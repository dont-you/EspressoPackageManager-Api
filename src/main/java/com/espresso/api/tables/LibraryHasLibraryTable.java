package com.espresso.api.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryHasLibraryTable extends Table{
    public Integer library_id=null;
    public Integer library_id1=null;

    @Override
    public String[] getFieldsNames() {
        return new String[] {"library_id","library_id1"};
    }

    @Override
    public String getPrimaryKeyName() {
        return "";
    }

    @Override
    public String[] getRequiredFieldsWhileCreating() {
        return new String[] {"library_id","library_id1"};
    }

    @Override
    public String getTableName() {
        return "library_has_library";
    }
    @Override
    public void dataValidation() {

    }

    @Override
    public String retrivePrimaryKeyFromResultSet(ResultSet generatedKeys) throws SQLException {
        return null;
    }
}
