package com.Jacksonnn.DCCore.Storage;

public enum SqlQueries {
    CREATE_TOURNAMENTS("CREATE TABLE IF NOT EXISTS `tournaments` ("
            + "`id` INT NOT NULL AUTO_INCREMENT,"
            + "`startedBy` TEXT NOT NULL,"
            + "`name` TEXT,"
            + "PRIMARY KEY (`id`)"
            + ");", "CREATE TABLE IF NOT EXISTS guilds ("
            + "id integer PRIMARY KEY AUTOINCREMENT,"
            + "startedBy text,"
            + "name text"
            + ");"),
    CREATE_USERS("CREATE TABLE IF NOT EXISTS `users` ("
            + "`id` INT NOT NULL AUTO_INCREMENT,"
            + "`uuid` TEXT NOT NULL,"
            + "`tournament1` TEXT,"
            + "`tournament2 TEXT,"
            + "PRIMARY KEY (`id`)"
            + ");", "CREATE TABLE IF NOT EXISTS guilds ("
            + "id integer PRIMARY KEY AUTOINCREMENT,"
            + "uuid text,"
            + "tournament1 text,"
            + "tournament2 text"
            + ");"),
    CREATE_USER(
            "INSERT INTO `users` (uuid) VALUES (?)",
            "INSERT INTO users (uuid) VALUES (?)"),
    GET_USER(
            "SELECT * FROM `users` WHERE uuid=?",
            "SELECT * FROM users WHERE uuid=?");
    private String mysqlQuery;
    private String sqliteQuery;

    SqlQueries(String mysqlQuery, String sqliteQuery) {
        this.mysqlQuery = mysqlQuery;
        this.sqliteQuery = sqliteQuery;
    }


    public String getMysqlQuery() {
        return mysqlQuery;
    }

    public String getSqliteQuery() {
        return sqliteQuery;
    }
}
