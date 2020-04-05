package com.Jacksonnn.DCCore.Storage;

public enum WarningQueries {
    CREATE_TABLE_WARNINGS("CREATE TABLE IF NOT EXISTS `warnings` ("
            + "`id` INT NOT NULL AUTO_INCREMENT,"
            + "`player` TEXT NOT NULL,"
            + "`staffMember` TEXT,"
            + "`reason` TEXT"
            + "PRIMARY KEY (`id`)"
            + ");", "CREATE TABLE IF NOT EXISTS warnings ("
            + "id integer PRIMARY KEY AUTOINCREMENT,"
            + "player text,"
            + "staffMember text,"
            + "reason text"
            + ");"),
    CREATE_WARNING(
            "INSERT INTO `warnings` (player, staffMember, reason) VALUES (?, ?, ?)",
            "INSERT INTO warnings (player, staffMember, reason) VALUES (?, ?, ?)"),
    GET_WARNINGS(
            "SELECT * FROM `warnings`",
            "SELECT * FROM warnings"),
    DELETE_WARNING(
            "DELETE FROM `warnings` WHERE id=?",
            "DELETE FROM warnings WHERE id=?"),
    CLEAR_USER_WARNINGS(
            "DELETE FROM `warnings` WHERE player=?",
            "DELETE FROM warnings WHERE player=?");

    private String mysqlQuery;
    private String sqliteQuery;

    WarningQueries(String mysqlQuery, String sqliteQuery) {
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
