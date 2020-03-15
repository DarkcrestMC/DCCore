package com.Jacksonnn.DCCore.Events;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.Events.SubCommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class EventCommand implements CommandExecutor, TabCompleter {

    //  /dcevents add <event> <player(s)>
    //  /dcevents remove <event> <player(s)>
    //  /dcevents startEvent <event>
    //  /dcevents endEvent <event>
    //  /dcevents eventList
    //  /dcevents playerList <event>
    //  /dcevents broadcast <message>
    //  /dcevents help

    private List<EventSubCommand> subCommands = new ArrayList<>();
    private DCCore plugin;

    public EventCommand(DCCore dcCore) {
        this.plugin = dcCore;
        registerSubCommand();
    }

    private void registerSubCommand() {
        subCommands.add(new AddCommand(plugin));
        subCommands.add(new EndEventCommand(plugin));
        subCommands.add(new EventBroadcastCommand(plugin));
        subCommands.add(new EventListCommand(plugin));
        subCommands.add(new PlayerListCommand(plugin));
        subCommands.add(new RemoveCommand(plugin));
        subCommands.add(new StartEventCommand(plugin));
        subCommands.add(new HelpCommand(plugin));
        subCommands.add(new TeleportCommand(plugin));
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("DCCore.EventStaff")) {
            if (args.length >= 1) {
                for (EventSubCommand subCommand : subCommands) {
                    if (subCommand
                            .getAliases()
                            .contains(
                                    args[0]
                            )
                            ||
                            subCommand
                            .getName()
                                    .equalsIgnoreCase(
                                            args[0]
                                    )) {
                        subCommand.execute(sender, buildArguments(args));
                        return true;
                    }
                }
                EventGeneral.getHelp(sender);
            } else {
                EventGeneral.getHelp(sender);
            }
        } else {
            sender.sendMessage(EventGeneral.eventPrefix + "Insufficient permission to execute these commands.");
        }
        return true;
    }

    private List<String> buildArguments(String[] args) {
        List<String> bArgs = new ArrayList<>();
        int i = 0;
        for (String arg : args) {
            if (i == 0) {

            } else {
                bArgs.add(arg);
            }
            i++;
        }
        return bArgs;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
