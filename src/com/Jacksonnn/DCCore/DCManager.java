package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.Storage.Mysql;
import com.Jacksonnn.DCCore.Storage.SqlLite;
import com.Jacksonnn.DCCore.Storage.SqlQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DCManager {
    private DCCore dcMain;
    List<String> tournaments;
    List<String> users;

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

    public List<String> getTournaments() {
        String query;
        if (dcMain.getDatabaseManager().getDatabase() instanceof Mysql) {
            query = SqlQueries.GET_TOURNAMENTS.getMysqlQuery();
        } else {
            query = SqlQueries.GET_TOURNAMENTS.getSqliteQuery();
        }
        try {
            PreparedStatement preparedStatement = dcMain.getDatabaseManager().getDatabase().getConnection().prepareStatement(query);
            ResultSet getTournaments = preparedStatement.executeQuery();


            while (getTournaments.next()) {
                tournaments.add(getTournaments.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tournaments;
    }

    public List<String> getUsers() {
        String query;
        if (dcMain.getDatabaseManager().getDatabase() instanceof Mysql) {
            query = SqlQueries.GET_USERS.getMysqlQuery();
        } else {
            query = SqlQueries.GET_USERS.getSqliteQuery();
        }
        try {
            PreparedStatement preparedStatement = dcMain.getDatabaseManager().getDatabase().getConnection().prepareStatement(query);
            ResultSet getUsers = preparedStatement.executeQuery();


            while (getUsers.next()) {
                users.add(getUsers.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public String hasTournament(UUID uniqueId) {
        return "In Progress";
    }

    public void createTournament(UUID startedBy, String name) {
        String query;
        if (dcMain.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = SqlQueries.CREATE_TOURNAMENT.getSqliteQuery();
        } else {
            query = SqlQueries.CREATE_TOURNAMENT.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = dcMain.getDatabaseManager().getDatabase()
                    .getConnection().prepareStatement(query);
            preparedStatement.setString(1, startedBy.toString());
            preparedStatement.setString(2, name);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setWinner(UUID winner, String name) {
        String query;
        if (dcMain.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = SqlQueries.SET_WINNER.getSqliteQuery();
        } else {
            query = SqlQueries.SET_WINNER.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = dcMain.getDatabaseManager().getDatabase()
                    .getConnection().prepareStatement(query);
            preparedStatement.setString(1, winner.toString());
            preparedStatement.setString(2, name);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void joinTournament(UUID user, String tournament, String element) {
        String query;
        if (dcMain.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = SqlQueries.JOIN_TOURNAMENT.getSqliteQuery();
        } else {
            query = SqlQueries.JOIN_TOURNAMENT.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = dcMain.getDatabaseManager().getDatabase()
                    .getConnection().prepareStatement(query);
            preparedStatement.setString(1, tournament);
            preparedStatement.setString(2, element);
            preparedStatement.setString(3, user.toString());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
