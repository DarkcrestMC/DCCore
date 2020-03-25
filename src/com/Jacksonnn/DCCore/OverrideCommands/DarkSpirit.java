package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class DarkSpirit implements CommandExecutor {

    // /DarkSpirit [<player>] - moves to DarkSpirit Group & adds all elements.

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        PermissionUser pexUser;

        if (sender.hasPermission("bending.admin") || sender.hasPermission("bending.DarkSpirit")) {
            if (args.length == 1) {
                if (sender.hasPermission("bending.admin")) {
                    Player player = Bukkit.getPlayer(args[0]);
                    pexUser = PermissionsEx.getUser(player);

                    if (pexUser.inGroup("JMod")) {
                        pexUser.removeGroup("JMod");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("JMod");
                    } else if (pexUser.inGroup("Architect")) {
                        pexUser.removeGroup("Architect");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("Architect");
                    } else if (pexUser.inGroup("Artist")) {
                        pexUser.removeGroup("Artist");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("Artist");
                    } else if (pexUser.inGroup("Royal")) {
                        pexUser.removeGroup("Royal");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("Royal");
                    } else if (pexUser.inGroup("Noble")) {
                        pexUser.removeGroup("Noble");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("Noble");
                    } else if (pexUser.inGroup("Official")) {
                        pexUser.removeGroup("Official");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("Official");
                    } else if (pexUser.inGroup("Baron")) {
                        pexUser.removeGroup("Baron");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("Baron");
                    } else if (pexUser.inGroup("Merchant")) {
                        pexUser.removeGroup("Merchant");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("Merchant");
                    } else if (pexUser.inGroup("Citizen")) {
                        pexUser.removeGroup("Citizen");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("Citizen");
                    } else if (pexUser.inGroup("Member")) {
                        pexUser.removeGroup("Member");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("Member");
                    } else if (pexUser.inGroup("Guest")) {
                        pexUser.removeGroup("Guest");
                        pexUser.addGroup("DarkSpirit");
                        pexUser.addGroup("Guest");
                    }

                    player.sendMessage(GeneralMethods.serverPrefix + "You can now choose DarkSpirit.");
                    sender.sendMessage(GeneralMethods.successColor + "You have given " + args[0] + " DarkSpirit permissions!");
                }
            } else if (args.length == 0) {
                if (sender.hasPermission("bending.DarkSpirit")) {
                    Player player = (Player) sender;
                    pexUser = PermissionsEx.getUser(player);
                    sender.sendMessage(GeneralMethods.serverPrefix + "You can now choose DarkSpirit.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "/darkspirit [<player]>]");
            }
        } else {
            sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permission to execute this command.");
        }
        return true;
    }
}
