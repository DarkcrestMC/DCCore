package com.Jacksonnn.DCCore.StaffUtils.StaffChannels;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class onChatEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        DCPlayer dcPlayer = GeneralMethods.getDCPlayer(player.getUniqueId());
        GeneralMethods.ChatModes chatmode = dcPlayer.getChatMode();

        if (chatmode == GeneralMethods.ChatModes.GENERAL) {
            return;
        }

        if (chatmode == GeneralMethods.ChatModes.HOS) {

            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.HOS.Prefix"))));
            String msgColor = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.HOS.msgColor"))));

            Bukkit.broadcast(chatprefix + dcPlayer.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(e.getMessage())), GeneralMethods.ChatModes.HOS.getChatPerm());

        } else if (chatmode == GeneralMethods.ChatModes.MANAGERS) {

            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Managers.Prefix"))));
            String msgColor = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Managers.msgColor"))));

            Bukkit.broadcast(chatprefix + dcPlayer.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(e.getMessage())), GeneralMethods.ChatModes.MANAGERS.getChatPerm());

        } else if (chatmode == GeneralMethods.ChatModes.DEVELOPER) {

            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Developer.Prefix"))));
            String msgColor = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Developer.msgColor"))));

            Bukkit.broadcast(chatprefix + dcPlayer.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(e.getMessage())), GeneralMethods.ChatModes.DEVELOPER.getChatPerm());

        } else if (chatmode == GeneralMethods.ChatModes.EVENTHOSTS) {

            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.EventHosts.Prefix"))));
            String msgColor = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.EventHosts.msgColor"))));

            Bukkit.broadcast(chatprefix + dcPlayer.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(e.getMessage())), GeneralMethods.ChatModes.EVENTHOSTS.getChatPerm());

        } else if (chatmode == GeneralMethods.ChatModes.MODERATORS) {

            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Moderators.Prefix"))));
            String msgColor = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Moderators.msgColor"))));

            Bukkit.broadcast(chatprefix + dcPlayer.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(e.getMessage())), GeneralMethods.ChatModes.MODERATORS.getChatPerm());

        } else if (chatmode == GeneralMethods.ChatModes.STAFF) {

            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix"))));
            String msgColor = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor"))));

            Bukkit.broadcast(chatprefix + dcPlayer.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(e.getMessage())), GeneralMethods.ChatModes.STAFF.getChatPerm());

        } else if (chatmode == GeneralMethods.ChatModes.ARTIST) {

            e.setCancelled(true);
            String chatprefix = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Artists.Prefix"))));
            String msgColor = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Artists.msgColor"))));

            Bukkit.broadcast(chatprefix + dcPlayer.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(e.getMessage())), GeneralMethods.ChatModes.ARTIST.getChatPerm());

        }
    }
}
