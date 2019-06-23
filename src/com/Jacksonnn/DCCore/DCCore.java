package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.Broadcast.BroadcastCommand;
import com.Jacksonnn.DCCore.ChatSensor.BannedWordsCommand;
import com.Jacksonnn.DCCore.ChatSensor.ChatListener;
import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DiamondLuck.DiamondLuck;
import com.Jacksonnn.DCCore.DiamondLuck.ResponseListener;
import com.Jacksonnn.DCCore.QuickDeposit.QuickDepositListener;
import com.Jacksonnn.DCCore.Rankup.PlayTime;
import com.Jacksonnn.DCCore.Rankup.Ranks;
import com.Jacksonnn.DCCore.Rankup.Rankup;
import com.Jacksonnn.DCCore.Spawners.SpawnerListener;
import com.Jacksonnn.DCCore.Storage.DatabaseManager;
import com.Jacksonnn.DCCore.Storage.Mysql;
import com.Jacksonnn.DCCore.Storage.SqlQueries;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class DCCore extends JavaPlugin {
	
	public static DCCore plugin;
	private PluginManager pm = Bukkit.getServer().getPluginManager();
	private DatabaseManager databaseManager;
	private DCManager dcManager;

	public void onEnable() {
		plugin = this;
		registerListeners();
		registerCommands();
		new ConfigManager();
		
		Bukkit.getServer().getLogger().info("DCCore has successfully been enabled!");

		databaseManager = new DatabaseManager(this);
		try {
			databaseManager.init();
		} catch (SQLException e) {
			e.printStackTrace();
			getServer().getPluginManager().disablePlugin(this);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			getServer().getPluginManager().disablePlugin(this);
		}

		try {
			installDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dcManager = new DCManager(this);
	}

	private void installDatabase() throws SQLException {
		if (databaseManager.getDatabase() instanceof Mysql) {
			databaseManager.getDatabase().getConnection().createStatement().execute(SqlQueries.CREATE_TOURNAMENTS.getMysqlQuery());
			databaseManager.getDatabase().getConnection().createStatement().execute(SqlQueries.CREATE_USERS.getMysqlQuery());
		} else {
			databaseManager.getDatabase().getConnection().createStatement().execute(SqlQueries.CREATE_TOURNAMENTS.getSqliteQuery());
			databaseManager.getDatabase().getConnection().createStatement().execute(SqlQueries.CREATE_USERS.getMysqlQuery());
		}
	}
	
	public void onDisable() {
		Bukkit.getServer().getLogger().info("DCCore has successfully been disabled!");
	}
	
/*
 * REGISTER LISTENERS AND COMMANDS
 */
	private void registerListeners() {
		pm.registerEvents(new SpawnerListener(), this);
		pm.registerEvents(new QuickDepositListener(), this);
		pm.registerEvents(new ChatListener(), this);
		pm.registerEvents(new ResponseListener(), this);
	}
	
	private void registerCommands() {
		this.getCommand("broadcast").setExecutor(new BroadcastCommand());
		this.getCommand("bannedwords").setExecutor(new BannedWordsCommand());
		this.getCommand("dccore").setExecutor(new CoreCommands());
		this.getCommand("rankup").setExecutor(new Rankup());
		this.getCommand("playtime").setExecutor(new PlayTime());
		this.getCommand("ranks").setExecutor(new Ranks());
		this.getCommand("diamondluck").setExecutor(new DiamondLuck());
	}

	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

	public DCManager getDcManager() {
		return dcManager;
	}
}

