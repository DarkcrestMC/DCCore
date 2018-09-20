package com.Jacksonnn.DCCore.Storage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql extends com.Jacksonnn.DCCore.Storage.Database {

    private String username, password, host, port, database;
    private Connection connection;
    private boolean ssl;

    public Mysql(String username, String password, String host, String port, String database,
                 boolean ssl) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.database = database;
        this.ssl = ssl;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    protected void init() throws ClassNotFoundException, SQLException {
        String url =
                "jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password="
                        + password + "&useSSL=" + ssl + "&autoReconnect=true";
        //This prevents Passwords with special chars
        try {
            url =
                    "jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password="
                            + URLEncoder.encode(password, "UTF-8") + "&useSSL=" + ssl + "&autoReconnect=true";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(url);
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection(url);

        }

    }
}
