package com.Jacksonnn.DCCore.Events.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.Events.Event;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventPlayer;
import com.Jacksonnn.DCCore.Events.EventSubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class PlayerListCommand implements EventSubCommand {
    private DCCore plugin;

    public PlayerListCommand(DCCore dccore) {
        plugin = dccore;
    }

    @Override
    public String getName() {
        return "playerList";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("listOfPlayers");
        aliases.add("lop");
        aliases.add("lp");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/dcevents playerList <event>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Events.CommandDescriptions.PlayerList");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (sender.hasPermission("DCCore.EventHost.playerList")) {
            String eventName = args.get(0);
            Event event = EventGeneral.getEvent(eventName);

            if (event == null) {
                sender.sendMessage(EventGeneral.eventPrefix + "This event does not exist!");
            }

            ArrayList<EventPlayer> players = event.getEventPlayers();

            if (players == null) {
                sender.sendMessage(EventGeneral.eventPrefix + "Event has no players yet! Add by doing /dcevents add " + event.getEventName() + " <player(s)>.");
            }

            String[] playerList = new String[players.size()];

            for (int i = 0; i < players.size(); i++) {
                playerList[i] = players.get(i).getName();
            }

            sender.sendMessage(EventGeneral.eventPrefix + "Event Players (" + (players.size() + 1) + "):");
            sender.sendMessage(ChatColor.GREEN + String.join(", ", playerList) + ", " + ChatColor.BLUE + event.getEventStaff());
        } else {
            sender.sendMessage(EventGeneral.eventPrefix + "Insufficient permission.");
        }
    }
}
