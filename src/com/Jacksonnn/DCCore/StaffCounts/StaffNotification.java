package com.Jacksonnn.DCCore.StaffCounts;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StaffNotification implements Listener {

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent e) {
        if (e.getPlayer().hasPermission("DCCore.staffchats.Staff")) {
            GeneralMethods.updateStaffCount(e.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        if (e.getPlayer().hasPermission("DCCore.staffchats.Staff")) {
            GeneralMethods.updateStaffCount(null);
        }
    }
}
