package com.espresso.api;

import java.sql.ResultSet;

public interface ITable{
    public ITable createInstance();
    public String getTableName();
    public void fill(ResultSet data);
    public void print();
}
