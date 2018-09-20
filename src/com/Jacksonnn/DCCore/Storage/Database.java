package com.Jacksonnn.DCCore.Storage;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Database {

    public abstract Connection getConnection();

    protected abstract void init() throws ClassNotFoundException, SQLException;


}
