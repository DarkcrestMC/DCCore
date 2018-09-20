package com.Jacksonnn.DCCore.Tournaments;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TournamentCommand implements CommandExecutor {

    private DCCore dcMain;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args[0].equalsIgnoreCase("create")) {
            if (args[1] != null) {
                if (sender.hasPermission("bending.tournament.admin")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        dcMain.getDcManager().createTournament(player.getUniqueId(), args[1]);
                    } else {
                        sender.sendMessage(GeneralMethods.errorColor + "You must be a player to create a tournament.");
                    }
                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permission to execute this command.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You must provide the name for the tournament.");
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
                if (args[1] != null) {
                    if (args[2] != null) {
                        Player player = Bukkit.getPlayer(args[2]);
                            dcMain.getDcManager().setWinner(player.getUniqueId(), args[1]);
                    } else {
                        sender.sendMessage(GeneralMethods.errorColor + "You must specify a player.");
                    }
                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "You must specify a tournament.");
                }
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
