package com.Jacksonnn.DCCore.BannedWords;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class BannedWordsListener implements Listener {

    @EventHandler
    public void onRegularChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("DCCore.AntiCurse.bypass"))
            return;

        List<String> bannedWords = ConfigManager.bannedWords.get().getStringList("AntiCurse.bannedWords");

        String message = player.getDisplayName().toLowerCase() + event.getMessage().toLowerCase().replace(" ", "");
        ArrayList<String> wordsUsed = null;
        for (String bannedWord : bannedWords) {
            if (message.contains(bannedWord.toLowerCase())) {
                if (wordsUsed == null)
                    wordsUsed = new ArrayList<>();
                wordsUsed.add(bannedWord);
            }
        }

        if (wordsUsed != null) {
            event.setCancelled(true);
            player.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5D900") + " Please rethink your choice of words... (check your username, nickname, or chat message!!!)");
            StringBuilder builder = new StringBuilder(GeneralMethods.accentColor + " " + ChatColor.ITALIC + "Banned words used: " + ChatColor.RESET + bannedWords.get(0));
            for (int i = 1; i < bannedWords.size(); i++) {
                if (i == bannedWords.size()-1)
                    builder.append(" and ");
                else
                    builder.append(", ");
                builder.append(bannedWords.get(i));
            }
            player.sendMessage(builder.toString());
        }
    }
}
