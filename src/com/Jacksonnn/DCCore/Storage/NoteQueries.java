package com.Jacksonnn.DCCore.Storage;

public enum NoteQueries {
    CREATE_TABLE_NOTES("CREATE TABLE IF NOT EXISTS `notes` ("
            + "`id` INT NOT NULL AUTO_INCREMENT,"
            + "`player` TEXT NOT NULL,"
            + "`staffMember` TEXT,"
            + "`message` TEXT"
            + "PRIMARY KEY (`id`)"
            + ");", "CREATE TABLE IF NOT EXISTS notes ("
            + "id integer PRIMARY KEY AUTOINCREMENT,"
            + "player text,"
            + "staffMember text,"
            + "message text"
            + ");"),
    CREATE_NOTE(
            "INSERT INTO `notes` (player, staffMember, message) VALUES (?, ?, ?)",
            "INSERT INTO notes (player, staffMember, message) VALUES (?, ?, ?)"),
    GET_NOTES(
            "SELECT * FROM `notes`",
            "SELECT * FROM notes"),
    DELETE_NOTE(
            "DELETE FROM `notes` WHERE id=?",
            "DELETE FROM notes WHERE id=?"),
    CLEAR_USER_NOTES(
            "DELETE FROM `notes` WHERE player=?",
            "DELETE FROM notes WHERE player=?");

    private String mysqlQuery;
    private String sqliteQuery;

    NoteQueries(String mysqlQuery, String sqliteQuery) {
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
