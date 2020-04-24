package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.ChatSensor.ChatListener;
import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import static com.Jacksonnn.DCCore.Configuration.ConfigManager.defaultConfig;
import static com.Jacksonnn.DCCore.QuickDeposit.QuickDepositListener.qdEnabled;

public class CoreCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String player = sender.getName();

        if (args.length == 2 && args[0].equalsIgnoreCase("chat")) {
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
        } else if (args.length == 1 && args[0].equalsIgnoreCase("lookup")) {
            if (sender.hasPermission("DCCore.lookup")) {
                try {
                    Material item = Bukkit.getPlayer(sender.getName()).getItemOnCursor().getType();
                    ItemStack itemStack = Bukkit.getPlayer(sender.getName()).getItemOnCursor();

                    sender.sendMessage(GeneralMethods.serverPrefix + " -={ Item Lookup }=-");
                    sender.sendMessage(ChatColor.YELLOW + item.name());
                    sender.sendMessage(ChatColor.YELLOW + "Item ID:" + item.getId());
                    try {
                        sender.sendMessage(ChatColor.YELLOW + "Display Name: " + itemStack.getItemMeta().getDisplayName());
                    } catch (Exception e) {
                        sender.sendMessage(ChatColor.YELLOW + "Display Name: " + item.name());
                    }
                    sender.sendMessage(ChatColor.YELLOW + "Display Name:" + itemStack.getItemMeta().getLore());
                    sender.sendMessage(ChatColor.YELLOW + "Display Name:" + itemStack.getItemMeta().getAttributeModifiers());
                } catch (Exception e) {
                    sender.sendMessage(GeneralMethods.errorColor + "There is nothing in your hand!");
                }
            }
        } else if (args.length == 1 && (args[0].equalsIgnoreCase("quickdeposit") || args[0].equalsIgnoreCase("qd"))) {
            qdEnabled = !qdEnabled;

            if (qdEnabled) {
                defaultConfig.get().addDefault("QuickDeposit.players." + sender.getName(), "true");
                defaultConfig.save();
            } else {
                defaultConfig.get().addDefault("QuickDeposit.players." + sender.getName(), "false");
                defaultConfig.save();
            }

            sender.sendMessage(qdEnabled ? GeneralMethods.successColor + "QuickDeposit Feature has been enabled." : GeneralMethods.disableColor + "QuickDeposit Feature has been disabled.");
        } else if (args[0].equalsIgnoreCase("reload")) {
            ConfigManager.defaultConfig.reload();
            sender.sendMessage(GeneralMethods.successColor + "Reloaded config.yml");
            ConfigManager.langConfig.reload();
            sender.sendMessage(GeneralMethods.successColor + "Reloaded language.yml");
            ConfigManager.bannedWords.reload();
            sender.sendMessage(GeneralMethods.successColor + "Reloaded bannedWords.yml");
            ConfigManager.announcer.reload();
            sender.sendMessage(GeneralMethods.successColor + "Reloaded announcements.yml");
        } else {

            sender.sendMessage(GeneralMethods.prefix + "DCCore Help:");
            sender.sendMessage(ChatColor.GOLD + "/dccore chat");
            sender.sendMessage(ChatColor.GOLD + "/dccore quickdeposit");
            sender.sendMessage(ChatColor.GOLD + "/dccore lookup - UNDER CONSTRUCTION");

            sender.sendMessage(ChatColor.GOLD + "/broadcast");
            sender.sendMessage(ChatColor.GOLD + "/rankup");
            sender.sendMessage(ChatColor.GOLD + "/ranks");
            sender.sendMessage(ChatColor.GOLD + "/playtime");
            sender.sendMessage(ChatColor.GOLD + "/randomtp");
            sender.sendMessage(ChatColor.GOLD + "/bendinghelp");
            sender.sendMessage(ChatColor.GOLD + "/bendingfix");
            sender.sendMessage(ChatColor.GOLD + "/avatar");
            sender.sendMessage(ChatColor.GOLD + "/lightspirit");
            sender.sendMessage(ChatColor.GOLD + "/darkspirit");
            sender.sendMessage(ChatColor.GOLD + "/bannedwords - UNDER CONSTRUCTION");
            sender.sendMessage(ChatColor.GOLD + "/events - UNDER CONSTRUCTION");
            sender.sendMessage(ChatColor.GOLD + "/diamondluck - UNDER CONSTRUCTION");
            return true;
        }

        return true;
    }
}