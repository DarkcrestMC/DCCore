package com.Jacksonnn.DCCore.Tournaments;

import com.Jacksonnn.DCCore.DCCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TournamentListener implements Listener {
    private DCCore plugin;

    public TournamentListener(DCCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!plugin.getDcManager().hasUser(e.getPlayer().getUniqueId())) {
            plugin.getDcManager().createUser(e.getPlayer().getUniqueId());
        }
    }
}
