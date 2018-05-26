package com.Jacksonnn.DCCore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.Jacksonnn.DCCore.QuickDeposit.QuickDepositListener;
import com.Jacksonnn.DCCore.Spawners.SpawnerListener;

public class DCCore extends JavaPlugin {
	
	public static DCCore plugin;
	PluginManager pm = Bukkit.getServer().getPluginManager();
	
	public void onEnable() {
		plugin = this;
		registerListeners();
		
		Bukkit.getServer().getLogger().info("DCCore has successfully been enabled!");
	}
	
	public void onDisable() {
		Bukkit.getServer().getLogger().info("DCCore has successfully been disabled!");
	}
	
	
/*
 * REGISTER LISTENERS
 */
	public void registerListeners() {
		pm.registerEvents(new SpawnerListener(), this);
		pm.registerEvents(new QuickDepositListener(), this);
	}
}
