package com.Jacksonnn.DCCore;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.Jacksonnn.DCCore.Spawners.SpawnerListener;

public class DCCore extends JavaPlugin {
	
	public static DCCore plugin;
	PluginManager pm = Bukkit.getServer().getPluginManager();
	public Permission placeSpawner = new Permission("DCCore.spawner.place");
	public Permission breakSpawner = new Permission("DCCore.spawner.break");
	
	public void onEnable() {
		plugin = this;
		registerListeners();
		registerPermissions();
		
		Bukkit.getServer().getLogger().info("DCCore has successfully been enabled!");
	}
	
	public void onDisable() {
		Bukkit.getServer().getLogger().info("DCCore has successfully been disabled!");
	}
	
	
/*
 * REGISTER PERMS AND LISTENERS
 */
	public void registerListeners() {
		pm.registerEvents(new SpawnerListener(), this);
	}
	
	public void registerPermissions() {
		pm.addPermission(placeSpawner);
		pm.addPermission(breakSpawner);
	}
}
