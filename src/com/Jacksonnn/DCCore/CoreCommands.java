package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.ChatSensor.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static com.Jacksonnn.DCCore.Configuration.ConfigManager.defaultConfig;
import static com.Jacksonnn.DCCore.QuickDeposit.QuickDepositListener.qdEnabled;

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
                    sender.sendMessage(ChatListener.chatEnabled ? GeneralMethods.successColor + "Unmuted the chat." : GeneralMethods.successColor + "Muted the chat.");
                    return true;
                }
            } else {
                    sender.sendMessage(GeneralMethods.errorColor + "To use this command, /dccore chat toggle.");
                    return true;
            }
        } /*else if (args.length == 2 && args[0].equalsIgnoreCase("lookup")) {
            if (sender.hasPermission("DCCore.lookup")) {

            }
        }*/ else if (args.length == 1 && (args[0].equalsIgnoreCase("quickdeposit") || args[0].equalsIgnoreCase("qd"))) {
            qdEnabled = !qdEnabled;

            if (qdEnabled) {
                defaultConfig.get().addDefault("QuickDeposit.players." + sender.getName(), "true");
                defaultConfig.save();
            } else {
                defaultConfig.get().addDefault("QuickDeposit.players." + sender.getName(), "false");
                defaultConfig.save();
            }

            sender.sendMessage(qdEnabled ? GeneralMethods.successColor + "QuickDeposit Feature has been enabled." : GeneralMethods.disableColor + "QuickDeposit Feature has been disabled.");
        }
        return true;
    }
}