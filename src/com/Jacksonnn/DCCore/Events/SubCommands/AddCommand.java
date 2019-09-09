package com.Jacksonnn.DCCore.Events.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.Events.Event;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventPlayer;
import com.Jacksonnn.DCCore.Events.EventSubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class AddCommand implements EventSubCommand {
    private DCCore plugin;

    public AddCommand(DCCore dccore) {
        plugin = dccore;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("addPlayer");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/dcevents add <event> <player(s)>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Events.CommandDescriptions.AddCommand");
    }
        //  /dcevents add (event) (player1) (player2) (...)
        //                 0       1         2        (...)
    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (sender.hasPermission("DCCore.EventHost.add")) {
            if (args.size() == 0) {
                sender.sendMessage(EventGeneral.eventPrefix + "/dcevents add <event> <player(s)>");
                return;
            }

            Event event = EventGeneral.getEvent(args.get(0));

            if (event == null) {
                sender.sendMessage(EventGeneral.eventPrefix + "Event not found!");
                return;
            }

            ArrayList<EventPlayer> eventPlayers = event.getEventPlayers();

            if (args.size() > 2) {
                for (String arg : args) {
                    if (!(arg.equalsIgnoreCase(args.get(0)))) {
                        event.addPlayer(sender, arg);
                        sender.sendMessage(EventGeneral.eventPrefix + "Added player, " + arg + ", to event, " + event.getEventName() + ".");
                    }
                }
            } else if (args.size() == 2) {
                event.addPlayer(sender, args.get(1));
                sender.sendMessage(EventGeneral.eventPrefix + "Added player, " + args.get(1) + ", to event, " + event.getEventName() + ".");
            } else {
                sender.sendMessage(EventGeneral.eventPrefix + "/dcevents add <event> <player(s)>");
            }

        } else {
            sender.sendMessage(EventGeneral.eventPrefix + "Insufficient Permission");
        }
    }
}
