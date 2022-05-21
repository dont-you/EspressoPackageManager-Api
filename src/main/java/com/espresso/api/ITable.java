package com.espresso.api;

import java.sql.ResultSet;

public interface ITable{
    public void fill(ResultSet data);
    public void print();
}
