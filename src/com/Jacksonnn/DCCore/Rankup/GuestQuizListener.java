package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import static com.Jacksonnn.DCCore.Rankup.Rankup.guestCheck;

public class GuestQuizListener implements Listener {
    @EventHandler
    public void onInventoryPress(InventoryClickEvent e) {
        Player player = Bukkit.getPlayer(e.getWhoClicked().getName());
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

            if (clickedItem.getItemMeta().getLocalizedName().equals("correct")) {
                player.closeInventory();
                int questionNumber = Integer.parseInt(currentInventory.getItem(1).getItemMeta().getDisplayName().substring(2, 4));
                if (questionNumber != 1) {
                    guestCheck(player, 1);
                } else {
                    DCCore.permissions.playerRemoveGroup(player, "Guest");
                    DCCore.permissions.playerAddGroup(player, "Bender");
                    DCCore.permissions.playerAddGroup(player, "Member");
                    Bukkit.broadcastMessage(GeneralMethods.prefix + "Congratulations, " + player.getName() + ", on becoming a Member! -Console");
                    player.sendMessage(GeneralMethods.successColor + "Congratulations on achieving the Member rank!");
                }
            } else {
                player.closeInventory();
                player.sendMessage(GeneralMethods.errorColor + "You have failed your rankup test! Do /rankup to try again.");
            }
        }
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        if (e.getMessage().equals("Crafting")) {
            e.setCancelled(true);
        }
    }
}
