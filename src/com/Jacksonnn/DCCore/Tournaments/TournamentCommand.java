package com.Jacksonnn.DCCore.Tournaments;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TournamentCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args[0].equalsIgnoreCase("create")) {
            if (sender.hasPermission("bending.tournament.admin")) {
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permission to execute this command");
            }
        } else if (args[0].equalsIgnoreCase("join")) {
            if (sender.hasPermission("bending.tournament.player")) {
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permission to execute this command");
            }
        } else if (args[0].equalsIgnoreCase("leave")) {
            if (sender.hasPermission("bending.tournament.player")) {
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permission to execute this command");
            }
        } else if (args[0].equalsIgnoreCase("winner")) {
            if (sender.hasPermission("bending.tournament.admin")) {
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permission to execute this command");
            }
        } else if (args[0].equalsIgnoreCase("info")) {
            if (sender.hasPermission("bending.tournament.player")) {
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permission to execute this command");
            }
        }
        return true;
    }
}
