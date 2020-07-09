package com.Jacksonnn.DCCore.StaffUtils.StaffChannels;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class onLeaveEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String chatmode = ConfigManager.defaultConfig.get().getString("DCStaffChat." + player.getName());

        if (chatmode != null) {
            ConfigManager.defaultConfig.get().set("DCStaffChat." + player.getName(), null);
            ConfigManager.defaultConfig.save();

            PermissionUser pexUser = PermissionsEx.getUser(e.getPlayer());
            pexUser.removePermission("-discordsrv.chat");
        }
    }
}
