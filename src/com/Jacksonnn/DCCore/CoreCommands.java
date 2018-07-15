package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.QuickDeposit.QuickDepositListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.Jacksonnn.DCCore.ChatSensor.ChatListener;

public class CoreCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String player = sender.getName();

        if (args.length == 0) {
            sender.sendMessage(GeneralMethods.prefix + "DCCore Help:");
            sender.sendMessage(ChatColor.GOLD + "/dccore chat");
            sender.sendMessage(ChatColor.GOLD + "/dccore quickdeposit (qd)");
            sender.sendMessage(ChatColor.GOLD + "/dccore lookup");
            return true;

        } else if (args.length == 2 && args[0].equalsIgnoreCase("chat")) {
            if (args[1].equalsIgnoreCase("toggle")) {

                if (sender.hasPermission("DCCore.chat.toggle")) {
                    ChatListener.chatEnabled = !ChatListener.chatEnabled;

                    Bukkit.broadcastMessage(ChatListener.chatEnabled ? GeneralMethods.serverPrefix + "Chat has been unmuted by " + player + "." : GeneralMethods.serverPrefix + "Chat has been muted by " + player + ".");
                    sender.sendMessage(ChatListener.chatEnabled ? GeneralMethods.prefix + GeneralMethods.successColor + "Unmuted the chat." : GeneralMethods.prefix + GeneralMethods.successColor + "Muted the chat.");
                    return true;
                }
            } else {
                    sender.sendMessage(GeneralMethods.prefix + GeneralMethods.errorColor + "To use this command, /dccore chat toggle.");
                    return true;
            }
        } /*else if (args.length == 2 && args[0].equalsIgnoreCase("lookup")) {
            if (sender.hasPermission("DCCore.lookup")) {

            }
        }*/ else if (args.length == 2 && (args[0].equalsIgnoreCase("quickdeposit") || args[0].equalsIgnoreCase("qd"))) {
            QuickDepositListener.qdEnabled = !QuickDepositListener.qdEnabled;

            sender.sendMessage(QuickDepositListener.qdEnabled ? GeneralMethods.prefix + GeneralMethods.successColor + "QuickDeposit Feature has been enabled." : GeneralMethods.prefix + GeneralMethods.successColor + "QuickDeposit Feature has been disabled.");
        }
        return true;
    }
}