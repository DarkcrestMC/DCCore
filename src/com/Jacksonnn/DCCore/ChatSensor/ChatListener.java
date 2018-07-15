package com.Jacksonnn.DCCore.ChatSensor;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;


public class ChatListener implements Listener {

    public static volatile boolean chatEnabled = true;


    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        /*
            ArrayList<String> words = new ArrayList<String>();

            words.add("");
        */
        if (!event.getPlayer().hasPermission("DCCore.chat.staff")) {
            if (event.getMessage().startsWith("/")) {
                return;
            }
            if (!chatEnabled) {
                event.setCancelled(true);
            }
        }
    }
}
