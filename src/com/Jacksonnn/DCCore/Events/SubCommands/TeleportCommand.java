package com.Jacksonnn.DCCore.Events.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.Events.Event;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventPlayer;
import com.Jacksonnn.DCCore.Events.EventSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeleportCommand implements EventSubCommand {
    private DCCore plugin;

    public TeleportCommand(DCCore dccore) {
        plugin = dccore;
    }

    @Override
    public String getName() {
        return "teleport";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("tp");
        aliases.add("tphere");
        aliases.add("movehere");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/dcevents teleport <event>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Events.CommandDescriptions.TeleportCommand");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (sender.hasPermission("DCCore.EventHost.teleport")) {
            if (args.size() == 1) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(EventGeneral.eventPrefix + "You must be a player to execute this command!");
                }
                Event event = EventGeneral.getEvent(args.get(0));

                if (event == null) {
                    sender.sendMessage("There is no event by this name: " + args.get(0));
                    return;
                }

                ArrayList<EventPlayer> eventPlayers = event.getEventPlayers();

                for (EventPlayer player : eventPlayers) {
                    player.getPlayer().teleport(Bukkit.getPlayer(sender.getName()).getLocation());
                }

            } else {
                sender.sendMessage(EventGeneral.eventPrefix + getProperUse());
            }
        }
    }
}
