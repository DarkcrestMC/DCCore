package com.Jacksonnn.DCCore.QuickDeposit;

import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.GeneralMethods;
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
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		if (player.hasPermission("DCCore.QuickDeposit.use")) {
			DCPlayer dcPlayer = GeneralMethods.getDCPlayer(event.getPlayer().getUniqueId());
			if (dcPlayer == null) {
				event.getPlayer().sendMessage(GeneralMethods.errorPrefix + "An error has occurred. Your DCPlayer file is not loaded.");
				return;
			}
			if (dcPlayer.isQuickdeposit()) {
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