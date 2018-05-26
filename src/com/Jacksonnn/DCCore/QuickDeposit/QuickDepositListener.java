package com.Jacksonnn.DCCore.QuickDeposit;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class QuickDepositListener implements Listener {
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (!event.getPlayer().hasPermission("QuickDeposit.use")) {
			return;
		} else {
			if (event.getPlayer().isSneaking()) {
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
					BlockState state = (BlockState) event.getClickedBlock().getState();
					
					if (state instanceof Chest) {
						Chest chest = (Chest) state;            
						Material blockAbove = event.getClickedBlock().getRelative(BlockFace.UP).getType();
						if (blockAbove.isOccluding()) {
						return;
					}
					int empty = chest.getBlockInventory().firstEmpty();
													
					if (empty != -1) {
						ItemStack handItem = event.getPlayer().getInventory().getItemInMainHand();
														
						chest.getBlockInventory().addItem(handItem);
														
						event.getPlayer().getInventory().removeItem(handItem);
						event.getPlayer().updateInventory();
						}
					}
				}
			}		
		}
	}
}