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
    CREATE_TOURNAMENT(
            "INSERT INTO `tournaments` (startedBy, name) VALUES (?, ?)",
            "INSERT INTO users (startedBy, name) VALUES (?, ?)");

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
