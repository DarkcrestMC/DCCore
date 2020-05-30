package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import net.ess3.api.events.VanishStatusChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class Vanish implements Listener {

    @EventHandler
    public void vanishToggleEvent(VanishStatusChangeEvent e) {
        if (e.getValue() /*true*/) {
            if (Bukkit.getPlayer(e.getAffected().getName()).hasPermission("DCCore.staff.Vanish")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.Vanish.PlayerLeaveMessage"))) + " " + e.getAffected().getBase().getName());
            }
        } else {
            if (Bukkit.getPlayer(e.getAffected().getName()).hasPermission("DCCore.staff.Vanish")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.Vanish.PlayerJoinMessage"))) + " " + e.getAffected().getBase().getName());
            }
        }
    }
}
