package com.Jacksonnn.DCCore.Events.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EventBroadcastCommand implements EventSubCommand {
    private DCCore plugin;

    public EventBroadcastCommand(DCCore dccore) {
        plugin = dccore;
    }

    @Override
    public String getName() {
        return "broadcast";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("bcast");
        aliases.add("bc");
        aliases.add("broadc");
        aliases.add("announce");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/dcevents broadcast <message>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Events.CommandDescriptions.Broadcast");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (sender.hasPermission("DCCore.EventHost.broadcast")) {
            String[] wordsOfMess = new String[args.size()];

            for (int i = 0; i < args.size(); i++) {
                wordsOfMess[i] = args.get(i);
            }
            if (sender instanceof Player) {
                Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "[" + ChatColor.BLUE + "DC Events" + ChatColor.GREEN + "] " + ChatColor.GRAY + String.join(" ", wordsOfMess) + " -" + sender.getName());
            } else {
                Bukkit.getServer().broadcastMessage(ChatColor.GRAY + String.join(" ", wordsOfMess) + " -Console");
            }
        } else {
            sender.sendMessage(EventGeneral.eventPrefix + "Insufficient permission.");
        }
    }
}
