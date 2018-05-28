package com.Jacksonnn.DCCore.ChatSensor;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;


public class ChatListener implements Listener {

    public static volatile boolean chatEnabled = true;

    //List<String> cuss = new ArrayList<>(Arrays.asList("Bitch", "Fuck", "Cunt", "Nigga", "Nigger", ""));

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        /*
            ArrayList<String> words = new ArrayList<String>();

            words.add("");
        */

        if (!chatEnabled) {
            event.setCancelled(false);
        }
    }
}
