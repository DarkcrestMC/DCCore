package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Avatar implements CommandExecutor {

    // /avatar [<player>] - moves to Avatar Group & adds all elements.

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        PermissionUser pexUser;

        if (sender.hasPermission("bending.admin") || sender.hasPermission("bending.avatar")) {
            if (args.length == 1) {
                if (sender.hasPermission("bending.admin")) {
                    Player player = Bukkit.getPlayer(args[0]);
                    pexUser = PermissionsEx.getUser(player);


                    if (pexUser.inGroup("JMod")) {
                        pexUser.removeGroup("JMod");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("JMod");
                    } else if (pexUser.inGroup("Architect")) {
                        pexUser.removeGroup("Architect");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("Architect");
                    } else if (pexUser.inGroup("Artist")) {
                        pexUser.removeGroup("Artist");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("Artist");
                    } else if (pexUser.inGroup("Royal")) {
                        pexUser.removeGroup("Royal");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("Royal");
                    } else if (pexUser.inGroup("Noble")) {
                        pexUser.removeGroup("Noble");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("Noble");
                    } else if (pexUser.inGroup("Official")) {
                        pexUser.removeGroup("Official");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("Official");
                    } else if (pexUser.inGroup("Baron")) {
                        pexUser.removeGroup("Baron");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("Baron");
                    } else if (pexUser.inGroup("Merchant")) {
                        pexUser.removeGroup("Merchant");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("Merchant");
                    } else if (pexUser.inGroup("Citizen")) {
                        pexUser.removeGroup("Citizen");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("Citizen");
                    } else if (pexUser.inGroup("Member")) {
                        pexUser.removeGroup("Member");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("Member");
                    } else if (pexUser.inGroup("Guest")) {
                        pexUser.removeGroup("Guest");
                        pexUser.addGroup("Avatar");
                        pexUser.addGroup("Guest");
                    }

                    player.performCommand("b add all");
                    player.sendMessage(GeneralMethods.serverPrefix + ChatColor.DARK_PURPLE + "You are now an avatar.");
                    sender.sendMessage(GeneralMethods.successColor + "You have given " + args[0] + " avatar permissions!");
                }
            } else if (args.length == 0) {
                if (sender.hasPermission("bending.avatar")) {
                    Player player = (Player) sender;
                    pexUser = PermissionsEx.getUser(player);
                    player.performCommand("b add all");
                    sender.sendMessage(GeneralMethods.serverPrefix + ChatColor.DARK_PURPLE + "You are now an avatar.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "/avatar [<player]>]");
            }
        } else {
            sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permission to execute this command.");
        }
        return true;
    }
}
