package com.Jacksonnn.DCCore.QuickDeposit;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static com.Jacksonnn.DCCore.Configuration.ConfigManager.defaultConfig;

public class QuickDepositListener implements Listener {

	public static boolean qdEnabled = false;

	@EventHandler
	public void onLeftClick(PlayerInteractEvent event) {
		if (defaultConfig.get().getBoolean("QuickDeposit.players." + event.getPlayer().getName())) {
			if (event.getPlayer().hasPermission("DCCore.QuickDeposit.use")) {
				if (event.getPlayer().isSneaking()) {
					if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
						BlockState state = event.getClickedBlock().getState();

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
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		if (qdEnabled) {
			if (player.hasPermission("DCCore.QuickDeposit.use")) {
				if (player.isSneaking()) {
					if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						BlockState state = event.getClickedBlock().getState();

						if (state instanceof Chest) {
							Chest chest = (Chest) state;
							Material blockAbove = event.getClickedBlock().getRelative(BlockFace.UP).getType();

							if (blockAbove.isOccluding())
								return;


							event.setCancelled(true);


							for (ItemStack item : player.getInventory().getStorageContents()) {

								int empty = chest.getBlockInventory().firstEmpty();

								if (empty == -1)
									break;
								if (item == null)
									continue;
								chest.getBlockInventory().addItem(item);
								event.getPlayer().getInventory().removeItem(item);
								event.getPlayer().updateInventory();
							}
						}
					}
				}
			}
		}
	}
}