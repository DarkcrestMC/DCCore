package com.Jacksonnn.DCCore.QuickDeposit;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class QuickDepositListener implements Listener {
	@EventHandler
	public void onLeftClick(PlayerInteractEvent event) {
		if (!event.getPlayer().hasPermission("DCCore.quickdeposit.use")) {
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
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent event, InventoryInteractEvent event2) {
		
		event2.setCancelled(true);
		
		Player player = event.getPlayer();
		if (!player.hasPermission("DCCore.quickdeposit.use"))
			return;
		else {
			if (player.isSneaking()) {
				if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					BlockState state = (BlockState) event.getClickedBlock().getState();
					
					if (state instanceof Chest) {
						Chest chest = (Chest) state;
						Material blockAbove = event.getClickedBlock().getRelative(BlockFace.UP).getType();
						
						if (blockAbove.isOccluding())
							return;
						
						
						for(ItemStack item : player.getInventory().getStorageContents()) {
							int empty = chest.getBlockInventory().firstEmpty();
							
							if (empty != -1)
								break;
						    if(item == null)
						        continue;
						    if(!chest.getBlockInventory().addItem(item).isEmpty())
						        break;
						}
					}
				}
			}
		}
	}
}