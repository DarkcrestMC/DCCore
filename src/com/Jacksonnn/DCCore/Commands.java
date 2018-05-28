package com.Jacksonnn.DCCore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.Jacksonnn.DCCore.ChatSensor.ChatListener;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String player = sender.getName();

        if (args.length == 0) {
            sender.sendMessage(GeneralMethods.prefix + "DCCore Help:");
            sender.sendMessage(ChatColor.GOLD + "/dccore chat");

        } else if (args.length >= 1 && args[1].equalsIgnoreCase("chat")) {
            if (args[2].equalsIgnoreCase("toggle")) {

                if (sender.hasPermission("DCCore.chat.toggle")) {
                    ChatListener.chatEnabled = !ChatListener.chatEnabled;

                    sender.sendMessage(ChatColor.GREEN + (ChatListener.chatEnabled ? GeneralMethods.prefix + GeneralMethods.successColor + "Unmuted the chat" : GeneralMethods.prefix + GeneralMethods.successColor + "Muted the chat"));
                }
            } else {
                    sender.sendMessage(GeneralMethods.prefix + GeneralMethods.errorColor + "To use this command, /dccore chat <toggle>.");
            }
        }
        return true;
    }
}