package com.Jacksonnn.DCCore.StaffUtils.StaffChannels;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onLeaveEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String chatmode = ConfigManager.defaultConfig.get().getString("DCStaffChat." + player.getName());

        if (chatmode != null) {
            ConfigManager.defaultConfig.get().set("DCStaffChat." + player.getName(), null);
            ConfigManager.defaultConfig.save();

            DCCore.permissions.playerRemove(player, "-discordsrv.chat");
        }
    }
}
