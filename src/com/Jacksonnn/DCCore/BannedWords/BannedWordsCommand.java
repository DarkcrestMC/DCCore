package com.Jacksonnn.DCCore.BannedWords;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class BannedWordsCommand implements CommandExecutor {

    public final static String color = ChatColor.of("#e5d900").toString();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        List<String> bannedWords = ConfigManager.bannedWords.get().getStringList("AntiCurse.bannedWords");
        if (sender.hasPermission("DCCore.bannedwords")) {
            if (args.length == 0) {
                sendHelpMenu(sender);
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {

                sender.sendMessage(GeneralMethods.prefix + color + "Banned Words:");
                for (String word : bannedWords)
                    sender.sendMessage(color + word);
                return true;

            } else if (args.length == 2 && args[0].equalsIgnoreCase("add")) {

                if (bannedWords.contains(args[1])) {
                    sender.sendMessage(GeneralMethods.errorPrefix + color + "This word is already added!");
                } else {
                    bannedWords.add(args[1]);
                    ConfigManager.bannedWords.get().set("AntiCurse.bannedWords", bannedWords);
                    ConfigManager.bannedWords.save();

                    sender.sendMessage(GeneralMethods.successPrefix + color + "Added the word \"" + args[1] + "\" to the banned words list.");
                    return true;
                }
            } else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {

                if (bannedWords.contains(args[1])) {
                    bannedWords.remove(args[1]);
                    ConfigManager.bannedWords.get().set("AntiCurse.bannedWords", bannedWords);
                    ConfigManager.bannedWords.save();
                    sender.sendMessage(GeneralMethods.successPrefix + color + "Removed the banned word, " + args[1] + ", from the list.");

                } else {
                    sender.sendMessage(GeneralMethods.errorPrefix + color + "The word " + args[1] + " is not in the banned words list.");
                }
            } else {
                sendHelpMenu(sender);
                return true;
            }
            return true;
        } else {
            sender.sendMessage(GeneralMethods.errorPrefix + color + "You have insufficient permission to access this command. Please contact the administrator if this is incorrect.");
            return true;
        }
    }

    private static void sendHelpMenu(CommandSender sender) {
        sender.sendMessage(GeneralMethods.prefix + color + "Banned Words Commands:");
        sender.sendMessage(color + "/bannedwords list");
        sender.sendMessage(color + "/bannedwords add <word>");
        sender.sendMessage(color + "/bannedwords remove <word>");
    }
}
