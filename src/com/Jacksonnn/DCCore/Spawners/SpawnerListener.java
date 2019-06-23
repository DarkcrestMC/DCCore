package com.Jacksonnn.DCCore.Spawners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpawnerListener implements Listener {
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Block theBlock = event.getBlock();
		Player player = event.getPlayer();
		
		Material blockPlaced = theBlock.getType();
		if (blockPlaced == Material.SPAWNER) {
			if (!player.hasPermission("DCCore.spawner.place")) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		Block theBlock = event.getBlock();
		Player player = event.getPlayer();
		
		Material blockBroken = theBlock.getType();
		if (blockBroken == Material.SPAWNER) {
			if (!player.hasPermission("DCCore.spawner.break")) {
				event.setCancelled(true);
			}
		}
	}

}
