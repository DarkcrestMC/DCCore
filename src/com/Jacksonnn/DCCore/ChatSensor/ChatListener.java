package com.Jacksonnn.DCCore.ChatSensor;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatListener implements Listener {

    public static volatile boolean chatEnabled = true;

    @EventHandler
    public void onMutedChat(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().hasPermission("DCCore.chat.staff")) {
            if (!chatEnabled) {
                if (!event.getMessage().startsWith("/")) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
