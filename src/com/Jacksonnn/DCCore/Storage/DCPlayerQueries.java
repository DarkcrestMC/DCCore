package com.Jacksonnn.DCCore.Storage;

public enum DCPlayerQueries {
    CREATE_TABLE_DCPLAYERS("CREATE TABLE IF NOT EXISTS `dcplayers` ("
                               + "`id` INT NOT NULL AUTO_INCREMENT,"
                               + "`uuid` TEXT NOT NULL,"
                               + "`name` TEXT NOT NULL,"
                               + "`playtime` BIGINT NOT NULL,"
                               + "`quickdeposit` TEXT NOT NULL,"
                               + "`chatmode` TEXT NOT NULL,"
                               + "PRIMARY KEY (`id`)"
                               + ");", "CREATE TABLE IF NOT EXISTS dcplayers ("
                               + "id integer PRIMARY KEY AUTOINCREMENT,"
                               + "uuid text,"
                               + "name text,"
                               + "playtime bigint,"
                               + "quickdeposit text,"
                               + "chatmode text"
                               + ");"),
    CREATE_DCPLAYER(
            "INSERT INTO `dcplayers` (uuid, name, playtime, quickdeposit, chatmode) VALUES (?, ?, ?, ?, ?)",
                    "INSERT INTO dcplayers (uuid, name, playtime, quickdeposit, chatmode) VALUES (?, ?, ?, ?, ?)"),
    GET_DCPLAYERS(
            "SELECT * FROM `dcplayers`",
                    "SELECT * FROM dcplayers"),
    UPDATE_DCPLAYER(
            "UPDATE `dcplayers` SET name=?, playtime=?, quickdeposit=?, chatmode=? WHERE uuid=?",
                    "UPDATE dcplayers SET name=? playtime=?, quickdeposit=?, chatmode=? WHERE uuid=?"),
    DELETE_DCPLAYER(
            "DELETE FROM `dcplayers` WHERE uuid=?",
                    "DELETE FROM dcplayers WHERE uuid=?");

    private String mysqlQuery;
    private String sqliteQuery;

    DCPlayerQueries(String mysqlQuery, String sqliteQuery) {
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
