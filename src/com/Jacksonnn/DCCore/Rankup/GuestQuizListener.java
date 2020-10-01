package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class GuestQuizListener implements Listener {
    @EventHandler
    public void onInventoryPress(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player))
            return;
        Player player = (Player)e.getWhoClicked();
        Inventory currentInventory = e.getClickedInventory();
        ItemStack clickedItem = e.getCurrentItem();
        InventoryView inventoryView = e.getView();

        if (currentInventory == null) {
            return;
        }

        if (inventoryView.getTitle().equals(ChatColor.DARK_PURPLE + "DarkcrestMC Vibe Check")) {
            e.setCancelled(true);

            if (clickedItem == null || !clickedItem.hasItemMeta()) {
                return;
            }

            if (clickedItem.getItemMeta() != null && clickedItem.getItemMeta().getLocalizedName().equals("correct")) {
//                int questionNumber = Integer.parseInt(currentInventory.getItem(1).getItemMeta().getDisplayName().substring(2, 4));
//                if (questionNumber != 1) {
//                    Rankup.guestCheck(player, 1);
//                } else {
                    succeedCheck(player);
//                }
            }
            player.closeInventory();
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player))
            return;

        if (e.getView().getTitle().equals(ChatColor.DARK_PURPLE + "DarkcrestMC Vibe Check"))
            failCheck((Player)e.getPlayer());
    }

    private void succeedCheck(Player player) {
        DCCore.permissions.playerAddGroup(null, player, "Bender");
        Rankup.attemptRankup(player, "Guest", "Member",
                ConfigManager.defaultConfig.get().getIntegerList("Rankup.Prices.Ranks").get(0),
                ConfigManager.defaultConfig.get().getIntegerList("Rankup.Hours.Ranks").get(0));
    }

    private void failCheck(Player player) {
        player.sendMessage(GeneralMethods.errorPrefix + "You have failed your rankup test! Do /rankup to try again.");
        player.performCommand("spawn");
    }
}
