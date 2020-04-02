package com.Jacksonnn.DCCore.Events.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.Events.Event;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventSubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class EventListCommand implements EventSubCommand {
    private DCCore plugin;

    public EventListCommand(DCCore dccore) {
        plugin = dccore;
    }

    @Override
    public String getName() {
        return "listEvents";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("loe");
        aliases.add("events");
        aliases.add("le");
        aliases.add("list");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/dcevents listEvents";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Events.CommandDescriptions.EventList");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (sender.hasPermission("DCCore.EventHost.eventList")) {
            ArrayList<Event> events = EventGeneral.events;

            sender.sendMessage(EventGeneral.eventPrefix + "List of Active Events (" + events.size() + "):");
            for (Event event : events) {
                sender.sendMessage(ChatColor.BLUE + event.getEventName() + ChatColor.GREEN + " - " + event.getEventStaff());
            }
        } else {
            sender.sendMessage(EventGeneral.eventPrefix + "Insufficient permission.");
        }
    }
}
