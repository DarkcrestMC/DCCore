package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.Storage.Mysql;
import com.Jacksonnn.DCCore.Storage.SqlLite;
import com.Jacksonnn.DCCore.Storage.SqlQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DCManager {
    private DCCore dcMain;

    public DCManager(DCCore dcMain) {
        this.dcMain = dcMain;
    }

    public void createUser(UUID createUser) {
        String query;
        if (dcMain.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = SqlQueries.CREATE_USER.getSqliteQuery();
        } else {
            query = SqlQueries.CREATE_USER.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = dcMain.getDatabaseManager().getDatabase()
                    .getConnection().prepareStatement(query);
            preparedStatement.setString(1, createUser.toString());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasUser(UUID uniqueId) {

        String query;
        if (dcMain.getDatabaseManager().getDatabase() instanceof Mysql) {
            query = SqlQueries.GET_USER.getMysqlQuery();
        } else {
            query = SqlQueries.GET_USER.getSqliteQuery();
        }
        boolean ret = false;

        try {
            PreparedStatement preparedStatement = dcMain.getDatabaseManager().getDatabase()
                    .getConnection().prepareStatement(query);
            preparedStatement.setString(1, uniqueId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isClosed()) {
                return false;
            }
            while (resultSet.next()) {
                ret = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return ret;
    }
}
