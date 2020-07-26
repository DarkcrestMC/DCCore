package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.Storage.DCPlayerQueries;
import com.Jacksonnn.DCCore.Storage.Mysql;
import com.Jacksonnn.DCCore.Storage.SqlLite;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DCPlayerManager {
    private DCCore plugin;

    public DCPlayerManager(DCCore plugin) {
        this.plugin = plugin;
    }

    public void createDCPlayersTable() throws SQLException {
        if (plugin.getDatabaseManager().getDatabase() instanceof Mysql) {
            plugin.getDatabaseManager().getDatabase().getConnection().createStatement().execute(DCPlayerQueries.CREATE_TABLE_DCPLAYERS.getMysqlQuery());
        } else {
            plugin.getDatabaseManager().getDatabase().getConnection().createStatement().execute(DCPlayerQueries.CREATE_TABLE_DCPLAYERS.getSqliteQuery());
        }
    }

    public void createDCPlayer(DCPlayer dcPlayer) {
        GeneralMethods.addDCPlayer(dcPlayer);

        String query;
        if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = DCPlayerQueries.CREATE_DCPLAYER.getSqliteQuery();
        } else {
            query = DCPlayerQueries.CREATE_DCPLAYER.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase().getConnection().prepareStatement(query);
            preparedStatement.setString(1, dcPlayer.getUuid().toString());
            preparedStatement.setString(2, dcPlayer.getName());
            preparedStatement.setLong(3, dcPlayer.getPlayTime());
            preparedStatement.setString(4, GeneralMethods.booleanToString(dcPlayer.isQuickdeposit()));
            preparedStatement.setString(5, dcPlayer.getChatMode().getChatName());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadDCPlayers() {
        String query;
        if (plugin.getDatabaseManager().getDatabase() instanceof Mysql) {
            query = DCPlayerQueries.GET_DCPLAYERS.getMysqlQuery();
        } else {
            query = DCPlayerQueries.GET_DCPLAYERS.getSqliteQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase().getConnection().prepareStatement(query);
            ResultSet getDCPlayers = preparedStatement.executeQuery();

            int i = 0;
            while (getDCPlayers.next()) {
                UUID uuid = UUID.fromString(getDCPlayers.getString("uuid"));
                String name = getDCPlayers.getString("name");
                long playTime = getDCPlayers.getLong("playtime");
                boolean quickdeposit = GeneralMethods.stringToBool(getDCPlayers.getString("quickdeposit"));
                GeneralMethods.ChatModes chatMode = GeneralMethods.ChatModes.GENERAL;

                switch (getDCPlayers.getString("chatmode")) {
                    case "HOS":
                        chatMode = GeneralMethods.ChatModes.HOS; break;
                    case "developer":
                        chatMode = GeneralMethods.ChatModes.DEVELOPER; break;
                    case "managers":
                        chatMode = GeneralMethods.ChatModes.MANAGERS; break;
                    case "moderators":
                        chatMode = GeneralMethods.ChatModes.MODERATORS; break;
                    case "staff":
                        chatMode = GeneralMethods.ChatModes.STAFF; break;
                    case "artists":
                        chatMode = GeneralMethods.ChatModes.ARTIST; break;
                    case "eventhosts":
                        chatMode = GeneralMethods.ChatModes.EVENTHOSTS; break;
                    default:
                        chatMode = GeneralMethods.ChatModes.GENERAL;
                }

                DCPlayer dcPlayer = new DCPlayer(name, uuid, this);
                GeneralMethods.addDCPlayer(dcPlayer);

                dcPlayer.setPlayTime(playTime);
                dcPlayer.setQuickdeposit(quickdeposit);
                dcPlayer.setChatMode(chatMode);

                i++;
            }

            Bukkit.getLogger().info("[DCCore] Loaded " + i + " notes.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDCPlayer(DCPlayer dcPlayer) {
        GeneralMethods.addDCPlayer(dcPlayer);

        String query;
        if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = DCPlayerQueries.UPDATE_DCPLAYER.getSqliteQuery();
        } else {
            query = DCPlayerQueries.UPDATE_DCPLAYER.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase().getConnection().prepareStatement(query);
            preparedStatement.setString(1, dcPlayer.getName());
            preparedStatement.setLong(2, dcPlayer.getPlayTime());
            preparedStatement.setString(3, GeneralMethods.booleanToString(dcPlayer.isQuickdeposit()));
            preparedStatement.setString(4, dcPlayer.getChatMode().getChatName());
            preparedStatement.setString(5, dcPlayer.getUuid().toString());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDCPlayer(DCPlayer dcPlayer) {
        GeneralMethods.removeDCPlayer(dcPlayer);

        String query;
        if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = DCPlayerQueries.DELETE_DCPLAYER.getSqliteQuery();
        } else {
            query = DCPlayerQueries.DELETE_DCPLAYER.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                    .getConnection().prepareStatement(query);
            preparedStatement.setString(1, dcPlayer.getUuid().toString());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
