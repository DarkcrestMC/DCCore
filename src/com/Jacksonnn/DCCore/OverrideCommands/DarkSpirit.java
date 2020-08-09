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
                    if (DCCore.permissions.playerInGroup(null, player, "JMod")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "JMod");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "JMod");
                    } else if (DCCore.permissions.playerInGroup(null, player, "Architect")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "Architect");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "Architect");
                    } else if (DCCore.permissions.playerInGroup(null, player, "Artist")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "Artist");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "Artist");
                    } else if (DCCore.permissions.playerInGroup(null, player, "Royal")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "Royal");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "Royal");
                    } else if (DCCore.permissions.playerInGroup(null, player, "Noble")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "Noble");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "Noble");
                    } else if (DCCore.permissions.playerInGroup(null, player, "Official")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "Official");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "Official");
                    } else if (DCCore.permissions.playerInGroup(null, player, "Baron")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "Baron");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "Baron");
                    } else if (DCCore.permissions.playerInGroup(null, player, "Merchant")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "Merchant");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "Merchant");
                    } else if (DCCore.permissions.playerInGroup(null, player, "Citizen")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "Citizen");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "Citizen");
                    } else if (DCCore.permissions.playerInGroup(null, player, "Member")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "Member");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "Member");
                    } else if (DCCore.permissions.playerInGroup(null, player, "Guest")) {
                        DCCore.permissions.playerRemoveGroup(null, player, "Guest");
                        DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
                        DCCore.permissions.playerAddGroup(null, player, "Guest");
                    }

                    player.sendMessage(GeneralMethods.prefix + "You can now choose DarkSpirit.");
                    sender.sendMessage(GeneralMethods.successPrefix + "You have given " + args[0] + " DarkSpirit permissions!");
                }
            } else if (args.length == 0) {
                if (sender.hasPermission("bending.DarkSpirit")) {
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
