package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;

public class BendingFix implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (sender.hasPermission("bending.admin")) {
                Player player = Bukkit.getPlayer(args[0]);

                fixBender(PermissionsEx.getUser(player), player, sender);
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permission to execute this command.");
            }
        } else if (args.length == 0) {
            Player player = (Player) sender;

            fixBender(PermissionsEx.getUser(player), player, sender);
        } else {
            sender.sendMessage(GeneralMethods.errorColor + "/bendingfix [<player]>]");
        }
        return true;
    }

    public void fixBender(PermissionUser pexUser, Player player, CommandSender sender) {
        ArrayList<String> userGroups = new ArrayList<>();
        if (pexUser.inGroup("Bender")) {
            sender.sendMessage(GeneralMethods.errorColor + "Player permissions are already fixed!");
        }
        if (pexUser.inGroup("JMod")) {
            userGroups.add("JMod");
        } else if (pexUser.inGroup("Architect")) {
            userGroups.add("Architect");
        } else if (pexUser.inGroup("Artist")) {
            userGroups.add("Artist");
        } else if (pexUser.inGroup("Royal")) {
            userGroups.add("Royal");
        } else if (pexUser.inGroup("Noble")) {
            userGroups.add("Noble");
        } else if (pexUser.inGroup("Official")) {
            userGroups.add("Official");
        } else if (pexUser.inGroup("Baron")) {
            userGroups.add("Baron");
        } else if (pexUser.inGroup("Merchant")) {
            userGroups.add("Merchant");
        } else if (pexUser.inGroup("Citizen")) {
            userGroups.add("Citizen");
        } else if (pexUser.inGroup("Member")) {
            userGroups.add("Member");
        } else if (pexUser.inGroup("Guest")) {
            userGroups.add("Guest");
        }

        if (pexUser.inGroup("LightSpirit")) {
            userGroups.add("LightSpirit");
        }
        if (pexUser.inGroup("DarkSpirit")) {
            userGroups.add("DarkSpirit");
        }
        if (pexUser.inGroup("Avatar")) {
            userGroups.add("Avatar");
        }



        for (String group : userGroups) {
            pexUser.removeGroup(group);
        }

        pexUser.addGroup("Bender");

        if (userGroups.contains("LightSpirit")) {
            pexUser.addGroup("LightSpirit");
            userGroups.remove("LightSpirit");
        }
        if (userGroups.contains("DarkSpirit")) {
            pexUser.addGroup("DarkSpirit");
            userGroups.remove("DarkSpirit");
        }
        if (userGroups.contains("Avatar")) {
            pexUser.addGroup("Avatar");
            userGroups.remove("Avatar");
        }

        for (String group : userGroups) {
            pexUser.addGroup(group);
        }

        player.sendMessage(GeneralMethods.serverPrefix + "Your bending has been fixed.");
        sender.sendMessage(GeneralMethods.successColor + "You have fixed " + player.getName() + "\'s bending permissions permissions!");
    }
}
