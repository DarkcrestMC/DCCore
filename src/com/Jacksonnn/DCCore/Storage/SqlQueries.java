package com.Jacksonnn.DCCore.Storage;

public enum SqlQueries {
    CREATE_TOURNAMENTS("CREATE TABLE IF NOT EXISTS `tournaments` ("
            + "`id` INT NOT NULL AUTO_INCREMENT,"
            + "`startedBy` TEXT NOT NULL,"
            + "`name` TEXT,"
            + "`winner` TEXT"
            + "PRIMARY KEY (`id`)"
            + ");", "CREATE TABLE IF NOT EXISTS guilds ("
            + "id integer PRIMARY KEY AUTOINCREMENT,"
            + "startedBy text,"
            + "name text,"
            + "winner text"
            + ");"),
    CREATE_USERS("CREATE TABLE IF NOT EXISTS `users` ("
            + "`id` INT NOT NULL AUTO_INCREMENT,"
            + "`uuid` TEXT NOT NULL,"
            + "`tournament` TEXT,"
            + "`tournament_element` TEXT,"
            + "PRIMARY KEY (`id`)"
            + ");", "CREATE TABLE IF NOT EXISTS guilds ("
            + "id integer PRIMARY KEY AUTOINCREMENT,"
            + "uuid text,"
            + "tournament1 text,"
            + "tournament1_element text,"
            + ");"),
    CREATE_TOURNAMENT(
            "INSERT INTO `tournaments` (startedBy, name) VALUES (?, ?)",
            "INSERT INTO users (startedBy, name) VALUES (?, ?)"),
    CREATE_USER(
            "INSERT INTO `users` (uuid) VALUES (?)",
            "INSERT INTO users (uuid) VALUES (?)"),
    GET_USERS(
            "SELECT * FROM `users`",
            "SELECT * FROM users"),
    GET_TOURNAMENTS(
            "SELECT * FROM `tournaments`",
            "SELECT * FROM tournaments"),
    GET_USER(
            "SELECT * FROM `users` WHERE uuid=?",
            "SELECT * FROM users WHERE uuid=?"),
    SET_WINNER(
            "UPDATE `tournaments` SET winner=? WHERE name=?",
            "UPDATE tournaments SET winner=? WHERE name=?"),
    JOIN_TOURNAMENT(
            "UPDATE `users` SET tournament=?, tournament_element=? WHERE uuid=?",
            "UPDATE users SET tournament=?, tournament_element=? WHERE uuid=?");

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
