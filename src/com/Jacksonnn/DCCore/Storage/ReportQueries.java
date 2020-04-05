package com.Jacksonnn.DCCore.Storage;

public enum ReportQueries {
    CREATE_TABLE_REPORTS("CREATE TABLE IF NOT EXISTS `reports` ("
            + "`id` INT NOT NULL AUTO_INCREMENT,"
            + "`player` TEXT NOT NULL,"
            + "`staffMember` TEXT,"
            + "`resolved` TEXT,"
            + "`message` TEXT,"
            + "`bugType` TEXT,"
            + "`tested` TEXT,"
            + "`type` TEXT"
            + "PRIMARY KEY (`id`)"
            + ");", "CREATE TABLE IF NOT EXISTS reports ("
            + "id integer PRIMARY KEY AUTOINCREMENT,"
            + "player text,"
            + "staffMember text,"
            + "resolved text,"
            + "message text,"
            + "bugType text,"
            + "tested text,"
            + "type text"
            + ");"),
    CREATE_TODO_REPORT(
            "INSERT INTO `reports` (staffMember, message, type) VALUES (?, ?, ?)",
            "INSERT INTO reports (staffMember, message, type) VALUES (?, ?, ?)"),
    CREATE_BUG_REPORT(
            "INSERT INTO `reports` (staffMember, bugType, tested, message, type) VALUES (?, ?, ?, ?, ?)",
            "INSERT INTO reports (staffMember, bugType, tested, message, type) VALUES (?, ?, ?, ?, ?)"),
    CREATE_STAFF_REPORT(
            "INSERT INTO `reports` (player, staffMember, resolved, message, type) VALUES (?, ?, ?, ?, ?)",
            "INSERT INTO reports (player, staffMember, resolved, message, type) VALUES (?, ?, ?, ?, ?)"),
    CREATE_PLAYER_REPORT(
            "INSERT INTO `reports` (player, staffMember, resolved, message, type) VALUES (?, ?, ?, ?, ?)",
            "INSERT INTO reports (player, staffMember, resolved, message, type) VALUES (?, ?, ?, ?, ?)"),
    GET_REPORTS(
            "SELECT * FROM `reports`",
            "SELECT * FROM reports"),
    DELETE_REPORT(
            "DELETE FROM `reports` WHERE (id, type) VALUES (?, ?)",
            "DELETE FROM reports WHERE (id, type) VALUES (?, ?)"),
    CLEAR_REPORTS(
            "DELETE FROM `reports` WHERE type=?",
            "DELETE FROM reports WHERE type=?");

    private String mysqlQuery;
    private String sqliteQuery;

    ReportQueries(String mysqlQuery, String sqliteQuery) {
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
