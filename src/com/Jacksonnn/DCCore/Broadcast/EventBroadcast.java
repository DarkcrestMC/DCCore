package com.Jacksonnn.DCCore.Broadcast;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EventBroadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        String message = StringUtils.join(args, " ");

        if (sender instanceof Player) {

            String player = sender.getName();

            if (sender.hasPermission("DCCore.broadcast.events")) {
                if (args.length == 0) {
                    sender.sendMessage(GeneralMethods.errorColor + "Please type message: /eventbroadcast <message>");
                } else if (args.length == 1) {
                    Bukkit.broadcastMessage(GeneralMethods.eventPrefix + message + " -" + player);
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "Insufficient permission!");
            }
        } else {
            if (args.length == 0) {
                sender.sendMessage(GeneralMethods.errorColor + "Please type message: /eventbroadcast <message>");
            } else if (args.length == 1) {
                Bukkit.broadcastMessage(GeneralMethods.eventPrefix + message + " -Console");
            }
        }
        return true;
    }

}
