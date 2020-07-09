package com.Jacksonnn.DCCore.BannedWords;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class BannedWordsListener implements Listener {

    @EventHandler
    public void onRegularChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        for (String word : event.getMessage().split(" ")) {
            for (String bannedWord : ConfigManager.defaultConfig.get().getStringList("AntiCurse.bannedWords")) {
                if (word.equalsIgnoreCase(bannedWord)) {
                    if (player.hasPermission("DCCore.AntiCurse.bypass")) {
                        return;
                    } else {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(GeneralMethods.prefix + " Please rethink your choice of words... (don't cuss!)");
                    }
                }
            }
        }
    }
}
