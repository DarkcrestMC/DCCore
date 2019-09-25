package com.Jacksonnn.DCCore.Events.PlayerEvents;

import com.Jacksonnn.DCCore.Events.Event;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveEvent implements Listener {

    public void onLeaveEvent(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        for (Event event : EventGeneral.events) {
            for (EventPlayer eventPlayer : event.getEventPlayers()) {
                if (eventPlayer.getPlayer() == player) {
                    event.removePlayer(eventPlayer);
                }
            }
        }
    }

}