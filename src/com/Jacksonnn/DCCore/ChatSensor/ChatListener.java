package com.Jacksonnn.DCCore.ChatSensor;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.entity.Player;
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

    @EventHandler
    public void onRegularChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        for (String s : event.getMessage().split(" ")) {
            if(ConfigManager.defaultConfig.get().getStringList("AntiCurse.bannedWords").contains(s)) {
                if (player.hasPermission("DCCore.AntiCurse.bypass")) {
                    return;
                } else {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(GeneralMethods.prefix + " Please rethink your choice of words... (don\'t cuss!)");
                }
            }
        }
    }
}
