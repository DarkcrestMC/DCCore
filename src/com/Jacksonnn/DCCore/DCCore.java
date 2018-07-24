package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.Broadcast.BroadcastCommand;
import com.Jacksonnn.DCCore.ChatSensor.BannedWordsCommand;
import com.Jacksonnn.DCCore.ChatSensor.ChatListener;
import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.QuickDeposit.QuickDepositListener;
import com.Jacksonnn.DCCore.Spawners.SpawnerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DCCore extends JavaPlugin {
	
	public static DCCore plugin;
	private PluginManager pm = Bukkit.getServer().getPluginManager();
	
	public void onEnable() {
		plugin = this;
		registerListeners();
		registerCommands();
		new ConfigManager();
		
		Bukkit.getServer().getLogger().info("DCCore has successfully been enabled!");
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
	}
	
	private void registerCommands() {
		this.getCommand("broadcast").setExecutor(new BroadcastCommand());
		this.getCommand("bannedwords").setExecutor(new BannedWordsCommand());
		this.getCommand("dccore").setExecutor(new CoreCommands());
	}
}
