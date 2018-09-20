package com.Jacksonnn.DCCore.Storage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlLite extends Database {

    private File sqlFile;
    private Connection connection;

    public SqlLite(String filePath) {
        sqlFile = new File(filePath);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    protected void init() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager
                .getConnection("jdbc:sqlite:" + this.sqlFile.getAbsolutePath());
    }
}