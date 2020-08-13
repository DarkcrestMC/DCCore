package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.ChatSensor.ChatListener;
import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.Guides.DCGuideCommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CoreCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String player = sender.getName();

        if (args.length == 2 && args[0].equalsIgnoreCase("chat")) {
            if (args[1].equalsIgnoreCase("toggle")) {

                if (sender.hasPermission("DCCore.chat.toggle")) {
                    ChatListener.chatEnabled = !ChatListener.chatEnabled;

                    GeneralMethods.broadcast(ChatListener.chatEnabled ? (GeneralMethods.prefix + ChatColor.of("#E59200") + "Chat has been unmuted by " + player + ".") : (GeneralMethods.prefix + ChatColor.of("#00dee5") + "Chat has been muted by " + player + "."));
                    sender.sendMessage(ChatListener.chatEnabled ? GeneralMethods.successPrefix + "Unmuted the chat." : GeneralMethods.successPrefix + "Muted the chat.");
                    return true;
                }
            } else {
                    sender.sendMessage(GeneralMethods.errorPrefix + "To use this command, /dccore chat toggle.");
                    return true;
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("lookup")) {
            if (sender.hasPermission("DCCore.lookup")) {
                try {
                    Material item = Bukkit.getPlayer(sender.getName()).getItemOnCursor().getType();
                    ItemStack itemStack = Bukkit.getPlayer(sender.getName()).getItemOnCursor();

                    sender.sendMessage(GeneralMethods.prefix + " -={ Item Lookup }=-");
                    sender.sendMessage(GeneralMethods.accentColor + item.name());
                    sender.sendMessage(GeneralMethods.accentColor + "Item ID:" + item.getId());
                    try {
                        sender.sendMessage(GeneralMethods.accentColor + "Display Name: " + itemStack.getItemMeta().getDisplayName());
                    } catch (Exception e) {
                        sender.sendMessage(GeneralMethods.accentColor + "Display Name: " + item.name());
                    }
                    sender.sendMessage(GeneralMethods.accentColor + "Display Name:" + itemStack.getItemMeta().getLore());
                    sender.sendMessage(GeneralMethods.accentColor + "Display Name:" + itemStack.getItemMeta().getAttributeModifiers());
                } catch (Exception e) {
                    sender.sendMessage(GeneralMethods.errorPrefix + "There is nothing in your hand!");
                }
            }
        } else if (args.length == 1 && (args[0].equalsIgnoreCase("quickdeposit") || args[0].equalsIgnoreCase("qd"))) {
//            qdEnabled = !qdEnabled;
            DCPlayer dcPlayer = GeneralMethods.getDCPlayer(sender.getName());
            if (dcPlayer == null) {
                sender.sendMessage(GeneralMethods.errorPrefix + "An error has occurred. Your DCPlayer file is not loaded.");
                return true;
            }
            dcPlayer.setQuickdeposit(!dcPlayer.isQuickdeposit());
            sender.sendMessage(dcPlayer.isQuickdeposit() ? GeneralMethods.successPrefix + "QuickDeposit Feature has been enabled." : GeneralMethods.errorPrefix + "QuickDeposit Feature has been disabled.");
        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            ConfigManager.defaultConfig.reload();
            sender.sendMessage(GeneralMethods.successPrefix + "Reloaded config.yml");
            ConfigManager.langConfig.reload();
            sender.sendMessage(GeneralMethods.successPrefix + "Reloaded language.yml");
            ConfigManager.bannedWords.reload();
            sender.sendMessage(GeneralMethods.successPrefix + "Reloaded bannedWords.yml");
            ConfigManager.announcer.reload();
            sender.sendMessage(GeneralMethods.successPrefix + "Reloaded announcements.yml");
        } else if (args.length == 1 && args[0].equalsIgnoreCase("dcguide")) {
            if (sender instanceof Player)
                DCGuideCommand.giveGuide((Player)sender);
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
            sender.sendMessage(ChatColor.GOLD + "/bannedwords");
            sender.sendMessage(ChatColor.GOLD + "/events");
            sender.sendMessage(ChatColor.GOLD + "/diamondluck - UNDER CONSTRUCTION");
            return true;
        }

        return true;
    }
}