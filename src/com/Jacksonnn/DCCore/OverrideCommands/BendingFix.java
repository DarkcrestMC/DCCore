package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@Deprecated
public class BendingFix implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (sender.hasPermission("bending.admin")) {
                Player player = Bukkit.getPlayer(args[0]);

                fixBender(player, sender);
            } else {
                sender.sendMessage(GeneralMethods.errorPrefix + "You do not have sufficient permission to execute this command.");
            }
        } else if (args.length == 0) {
            Player player = (Player) sender;

            fixBender(player, sender);
        } else {
            sender.sendMessage(GeneralMethods.errorPrefix + "/bendingfix [<player]>]");
        }
        return true;
    }

    public void fixBender(Player player, CommandSender sender) {
        ArrayList<String> userGroups = new ArrayList<>();
        if (DCCore.permissions.playerInGroup(null, player, "Bender")) {
            sender.sendMessage(GeneralMethods.errorPrefix + "Player permissions are already fixed!");
        }
        if (DCCore.permissions.playerInGroup(null, player, "JMod")) {
            userGroups.add("JMod");
        } else if (DCCore.permissions.playerInGroup(null, player, "Architect")) {
            userGroups.add("Architect");
        } else if (DCCore.permissions.playerInGroup(null, player, "Artist")) {
            userGroups.add("Artist");
        } else if (DCCore.permissions.playerInGroup(null, player, "Royal")) {
            userGroups.add("Royal");
        } else if (DCCore.permissions.playerInGroup(null, player, "Noble")) {
            userGroups.add("Noble");
        } else if (DCCore.permissions.playerInGroup(null, player, "Chancellor")) {
            userGroups.add("Chancellor");
        } else if (DCCore.permissions.playerInGroup(null, player, "Official")) {
            userGroups.add("Official");
        } else if (DCCore.permissions.playerInGroup(null, player, "Baron")) {
            userGroups.add("Baron");
        } else if (DCCore.permissions.playerInGroup(null, player, "Merchant")) {
            userGroups.add("Merchant");
        } else if (DCCore.permissions.playerInGroup(null, player, "Citizen")) {
            userGroups.add("Citizen");
        } else if (DCCore.permissions.playerInGroup(null, player, "Member")) {
            userGroups.add("Member");
        } else if (DCCore.permissions.playerInGroup(null, player, "Guest")) {
            userGroups.add("Guest");
        }

        if (DCCore.permissions.playerInGroup(null, player, "LightSpirit")) {
            userGroups.add("LightSpirit");
        }
        if (DCCore.permissions.playerInGroup(null, player, "DarkSpirit")) {
            userGroups.add("DarkSpirit");
        }
        if (DCCore.permissions.playerInGroup(null, player, "Avatar")) {
            userGroups.add("Avatar");
        }



        for (String group : userGroups) {
            DCCore.permissions.playerRemoveGroup(null, player, group);
        }

        DCCore.permissions.playerAddGroup(null, player, "Bender");

        if (userGroups.contains("LightSpirit")) {
            DCCore.permissions.playerAddGroup(null, player, "LightSpirit");
            userGroups.remove("LightSpirit");
        }
        if (userGroups.contains("DarkSpirit")) {
            DCCore.permissions.playerAddGroup(null, player, "DarkSpirit");
            userGroups.remove("DarkSpirit");
        }
        if (userGroups.contains("Avatar")) {
            DCCore.permissions.playerAddGroup(null, player, "Avatar");
            userGroups.remove("Avatar");
        }

        for (String group : userGroups) {
            DCCore.permissions.playerAddGroup(null, player, group);
        }

        player.sendMessage(GeneralMethods.prefix + "Your bending has been fixed.");
        sender.sendMessage(GeneralMethods.successPrefix + "You have fixed " + player.getName() + "'s bending permissions permissions!");
    }
}
