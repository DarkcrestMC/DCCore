package com.Jacksonnn.DCCore;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.Jacksonnn.DCCore.Broadcast.BroadcastCommand;
import com.Jacksonnn.DCCore.QuickDeposit.QuickDepositListener;
import com.Jacksonnn.DCCore.Spawners.SpawnerListener;
import com.Jacksonnn.DCCore.ChatSensor.ChatListener;

public class DCCore extends JavaPlugin {
	
	public static DCCore plugin;
	PluginManager pm = Bukkit.getServer().getPluginManager();
	
	public void onEnable() {
		plugin = this;
		registerListeners();
		registerCommands();
		
		Bukkit.getServer().getLogger().info("DCCore has successfully been enabled!");
		createConfig();
	}
	
	public void onDisable() {
		Bukkit.getServer().getLogger().info("DCCore has successfully been disabled!");
	}
	
	private void createConfig() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}
			File file = new File(getDataFolder(), "deaths and kills.yml");
			if (!file.exists()) {
				getLogger().info("Deaths and kills.yml not found, creating!");
				saveDefaultConfig();
			} else {
				getLogger().info("Death and kills.yml found, loading!");
			}
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	
/*
 * REGISTER LISTENERS AND COMMANDS
 */
	public void registerListeners() {
		pm.registerEvents(new SpawnerListener(), this);
		pm.registerEvents(new QuickDepositListener(), this);
		pm.registerEvents(new ChatListener(), this);
	}
	
	public void registerCommands() {
		this.getCommand("bc").setExecutor(new BroadcastCommand());
		this.getCommand("broadcast").setExecutor(new BroadcastCommand());
		this.getCommand("bcast").setExecutor(new BroadcastCommand());
		this.getCommand("dccore").setExecutor(new Commands());
	}
}
