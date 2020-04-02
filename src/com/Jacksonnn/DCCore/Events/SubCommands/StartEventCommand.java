package com.Jacksonnn.DCCore.Events.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.Events.Event;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class StartEventCommand implements EventSubCommand {
    private DCCore plugin;

    public StartEventCommand(DCCore dccore) {
        plugin = dccore;
    }

    @Override
    public String getName() {
        return "startevent";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("start");
        aliases.add("create");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/dcevents startEvent <event>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Events.CommandDescriptions.StartEventCommand");
    }

    //  /dcevents create (name)
    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (sender.hasPermission("DCCore.EventHost.create")) {
            if (args.size() == 1) {
                Event event = new Event(args.get(0), sender.getName());
                sender.sendMessage(EventGeneral.eventPrefix + "Successfully created event, " + event.getEventName() + ", by, " + event.getEventStaff());
                sender.sendMessage(" ");
                Bukkit.broadcastMessage(EventGeneral.eventPrefix + "Now starting event, " + event.getEventName() + ", hosted by, " + event.getEventStaff() + ". -Console");
            } else {
                sender.sendMessage(EventGeneral.eventPrefix + "/dcevents startEvent <event>");
            }
        } else {
            sender.sendMessage(EventGeneral.eventPrefix + "Insufficient permission.");
        }
    }
}
