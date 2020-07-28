package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BendingFix implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (sender.hasPermission("bending.admin")) {
                Player player = Bukkit.getPlayer(args[0]);

                fixBender(player, sender);
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permission to execute this command.");
            }
        } else if (args.length == 0) {
            Player player = (Player) sender;

            fixBender(player, sender);
        } else {
            sender.sendMessage(GeneralMethods.errorColor + "/bendingfix [<player]>]");
        }
        return true;
    }

    public void fixBender(Player player, CommandSender sender) {
        ArrayList<String> userGroups = new ArrayList<>();
        if (DCCore.permissions.playerInGroup(player, "Bender")) {
            sender.sendMessage(GeneralMethods.errorColor + "Player permissions are already fixed!");
        }
        if (DCCore.permissions.playerInGroup(player, "JMod")) {
            userGroups.add("JMod");
        } else if (DCCore.permissions.playerInGroup(player, "Architect")) {
            userGroups.add("Architect");
        } else if (DCCore.permissions.playerInGroup(player, "Artist")) {
            userGroups.add("Artist");
        } else if (DCCore.permissions.playerInGroup(player, "Royal")) {
            userGroups.add("Royal");
        } else if (DCCore.permissions.playerInGroup(player, "Noble")) {
            userGroups.add("Noble");
        } else if (DCCore.permissions.playerInGroup(player, "Official")) {
            userGroups.add("Official");
        } else if (DCCore.permissions.playerInGroup(player, "Baron")) {
            userGroups.add("Baron");
        } else if (DCCore.permissions.playerInGroup(player, "Merchant")) {
            userGroups.add("Merchant");
        } else if (DCCore.permissions.playerInGroup(player, "Citizen")) {
            userGroups.add("Citizen");
        } else if (DCCore.permissions.playerInGroup(player, "Member")) {
            userGroups.add("Member");
        } else if (DCCore.permissions.playerInGroup(player, "Guest")) {
            userGroups.add("Guest");
        }

        if (DCCore.permissions.playerInGroup(player, "LightSpirit")) {
            userGroups.add("LightSpirit");
        }
        if (DCCore.permissions.playerInGroup(player, "DarkSpirit")) {
            userGroups.add("DarkSpirit");
        }
        if (DCCore.permissions.playerInGroup(player, "Avatar")) {
            userGroups.add("Avatar");
        }



        for (String group : userGroups) {
            DCCore.permissions.playerRemoveGroup(player, group);
        }

        DCCore.permissions.playerAddGroup(player, "Bender");

        if (userGroups.contains("LightSpirit")) {
            DCCore.permissions.playerAddGroup(player, "LightSpirit");
            userGroups.remove("LightSpirit");
        }
        if (userGroups.contains("DarkSpirit")) {
            DCCore.permissions.playerAddGroup(player, "DarkSpirit");
            userGroups.remove("DarkSpirit");
        }
        if (userGroups.contains("Avatar")) {
            DCCore.permissions.playerAddGroup(player, "Avatar");
            userGroups.remove("Avatar");
        }

        for (String group : userGroups) {
            DCCore.permissions.playerAddGroup(player, group);
        }

        player.sendMessage(GeneralMethods.prefix + "Your bending has been fixed.");
        sender.sendMessage(GeneralMethods.successColor + "You have fixed " + player.getName() + "'s bending permissions permissions!");
    }
}
