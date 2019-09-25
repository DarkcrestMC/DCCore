package com.Jacksonnn.DCCore.Events.PlayerEvents;

import com.Jacksonnn.DCCore.Events.Event;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerKillEvent implements Listener {

    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();

        for (Event event : EventGeneral.events) {
            for (EventPlayer eventPlayer : event.getEventPlayers()) {
                if (eventPlayer.getPlayer() == player) {
                    event.removePlayer(eventPlayer);
                }
            }
        }
    }
}
