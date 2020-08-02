package com.Jacksonnn.DCCore.StaffUtils.StaffChannels;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Collection;
import java.util.Objects;

public class onChatEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String chatmode = ConfigManager.defaultConfig.get().getString("DCStaffChat." + player.getName());

        if (chatmode == null) {
            return;
        }

        if (chatmode.equalsIgnoreCase("HeadOfStaff")) {
            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.HoS.Prefix")));
            String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.HoS.msgColor")));
            Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

            for (Player onlinePlayer : onlinePlayers) {
                if (onlinePlayer.hasPermission("DCCore.staffchats.HOS")) {
                    onlinePlayer.sendMessage(chatprefix + player.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                }
            }
            Bukkit.getLogger().info(chatprefix + player.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else if (chatmode.equalsIgnoreCase("Managers")) {
            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Managers.Prefix")));
            String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Managers.msgColor")));
            Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

            for (Player onlinePlayer : onlinePlayers) {
                if (onlinePlayer.hasPermission("DCCore.staffchats.Managers")) {
                    onlinePlayer.sendMessage(chatprefix + player.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                }
            }
            Bukkit.getLogger().info(chatprefix + player.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else if (chatmode.equalsIgnoreCase("Moderators")) {
            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Moderators.Prefix")));
            String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Moderators.msgColor")));
            Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

            for (Player onlinePlayer : onlinePlayers) {
                if (onlinePlayer.hasPermission("DCCore.staffchats.Moderators")) {
                    onlinePlayer.sendMessage(chatprefix + player.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                }
            }
            Bukkit.getLogger().info(chatprefix + player.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else if (chatmode.equalsIgnoreCase("Staff")) {
            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix")));
            String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor")));
            Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

            for (Player onlinePlayer : onlinePlayers) {
                if (onlinePlayer.hasPermission("DCCore.staffchats.Staff")) {
                    onlinePlayer.sendMessage(chatprefix + player.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                }
            }
            Bukkit.getLogger().info(chatprefix + player.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        } else if (chatmode.equalsIgnoreCase("Artists")) {
            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Artists.Prefix")));
            String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Artists.msgColor")));
            Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

            for (Player onlinePlayer : onlinePlayers) {
                if (onlinePlayer.hasPermission("DCCore.staffchats.Artists")) {
                    onlinePlayer.sendMessage(chatprefix + player.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
                }
            }
            Bukkit.getLogger().info(chatprefix + player.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        }
    }
}
