package com.espresso.api.dbhandlers;

import java.sql.*;

public final class DataBaseConnector {
    static public Connection connection = null;

    static final private String serverAdress = "localhost:3306";
    static final private String dataBaseName = "PackageManager";
    static final private String dataBaseUserName = "root";
}
