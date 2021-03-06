package com.Jacksonnn.DCCore.DiamondLuck;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
public class ResponseListener implements Listener {
    public static volatile boolean userReponseForPlayers = false;
    public static ArrayList<String> players = new ArrayList<>();

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        if (userReponseForPlayers) {
            String message = e.getMessage();
            if (!message.equalsIgnoreCase("end") && !message.startsWith("/")) {
                players
                        .add
                                (message);
                e.getPlayer().sendMessage(GeneralMethods.prefix + "Added " + message + " to the player list.");
                e.setCancelled(true);
            } else {
                e.getPlayer().sendMessage(GeneralMethods.prefix + "All players in DiamondLuck Game:");
                e.getPlayer().sendMessage(GeneralMethods.prefix + String.join(", ", players));
                userReponseForPlayers = false;
                e.setCancelled(true);
            }
        }
    }
}
