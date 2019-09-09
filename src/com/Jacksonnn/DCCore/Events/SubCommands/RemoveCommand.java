package com.Jacksonnn.DCCore.Events.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.Events.Event;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventSubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class RemoveCommand implements EventSubCommand {
    private DCCore plugin;

    public RemoveCommand(DCCore dccore) {
        plugin = dccore;
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("removePlayer");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/dcevents remove <event> <[player] | all>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Events.CommandDescriptions.RemoveCommand");
    }

    //  /dcevents remove <event> <[player] | all>
    //                   0       1

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (sender.hasPermission("DCCore.EventHost.remove")) {
            if (args.size() == 2) {
                Event event = EventGeneral.getEvent(args.get(0));

                if (event == null) {
                    sender.sendMessage(EventGeneral.eventPrefix + "Event does not exist!");
                    return;
                }

                if (args.get(1).equalsIgnoreCase("all")) {
                    event.getEventPlayers().removeAll(event.getEventPlayers());
                    sender.sendMessage(EventGeneral.eventPrefix + "Successfully removed all people from the event.");
                } else {
                    event.removePlayer(sender, args.get(1));
                    sender.sendMessage(EventGeneral.eventPrefix + "Successfully removed, " + args.get(1) + ", from the event.");
                }

            } else {
                sender.sendMessage(EventGeneral.eventPrefix + "/dcevents remove <event> <[player] | all>");
            }
        } else {
            sender.sendMessage(EventGeneral.eventPrefix + "Insufficient permission.");
        }
    }
}
