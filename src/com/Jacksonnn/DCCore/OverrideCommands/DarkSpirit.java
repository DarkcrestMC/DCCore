package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DarkSpirit implements CommandExecutor {

    // /DarkSpirit [<player>] - moves to DarkSpirit Group & adds all elements.

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bending.admin") || sender.hasPermission("bending.DarkSpirit")) {
            if (args.length == 1) {
                if (sender.hasPermission("bending.admin")) {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (DCCore.permissions.playerInGroup(player, "JMod")) {
                        DCCore.permissions.playerRemoveGroup(player, "JMod");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "JMod");
                    } else if (DCCore.permissions.playerInGroup(player, "Architect")) {
                        DCCore.permissions.playerRemoveGroup(player, "Architect");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "Architect");
                    } else if (DCCore.permissions.playerInGroup(player, "Artist")) {
                        DCCore.permissions.playerRemoveGroup(player, "Artist");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "Artist");
                    } else if (DCCore.permissions.playerInGroup(player, "Royal")) {
                        DCCore.permissions.playerRemoveGroup(player, "Royal");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "Royal");
                    } else if (DCCore.permissions.playerInGroup(player, "Noble")) {
                        DCCore.permissions.playerRemoveGroup(player, "Noble");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "Noble");
                    } else if (DCCore.permissions.playerInGroup(player, "Official")) {
                        DCCore.permissions.playerRemoveGroup(player, "Official");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "Official");
                    } else if (DCCore.permissions.playerInGroup(player, "Baron")) {
                        DCCore.permissions.playerRemoveGroup(player, "Baron");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "Baron");
                    } else if (DCCore.permissions.playerInGroup(player, "Merchant")) {
                        DCCore.permissions.playerRemoveGroup(player, "Merchant");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "Merchant");
                    } else if (DCCore.permissions.playerInGroup(player, "Citizen")) {
                        DCCore.permissions.playerRemoveGroup(player, "Citizen");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "Citizen");
                    } else if (DCCore.permissions.playerInGroup(player, "Member")) {
                        DCCore.permissions.playerRemoveGroup(player, "Member");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "Member");
                    } else if (DCCore.permissions.playerInGroup(player, "Guest")) {
                        DCCore.permissions.playerRemoveGroup(player, "Guest");
                        DCCore.permissions.playerAddGroup(player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(player, "Guest");
                    }

                    player.sendMessage(GeneralMethods.prefix + "You can now choose DarkSpirit.");
                    sender.sendMessage(GeneralMethods.successPrefix + "You have given " + args[0] + " DarkSpirit permissions!");
                }
            } else if (args.length == 0) {
                if (sender.hasPermission("bending.DarkSpirit")) {
                    Player player = (Player) sender;
                    sender.sendMessage(GeneralMethods.prefix + "You can now choose DarkSpirit.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorPrefix + "/darkspirit [<player]>]");
            }
        } else {
            sender.sendMessage(GeneralMethods.errorPrefix + "You do not have sufficient permission to execute this command.");
        }
        return true;
    }
}
