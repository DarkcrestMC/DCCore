package com.Jacksonnn.DCCore.ChatSensor;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class BannedWordsCommand implements CommandExecutor {

    private List<String> bannedWords = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        bannedWords = ConfigManager.bannedWords.get().getStringList("AntiCurse.bannedWords");
        if (sender.hasPermission("DCCore.bannedwords")) {
            if (args.length == 0) {
                sender.sendMessage(GeneralMethods.prefix + "Banned Words Commands:");
                sender.sendMessage(ChatColor.YELLOW + "/bannedwords list");
                sender.sendMessage(ChatColor.YELLOW + "/bannedwords add <word>");
                sender.sendMessage(ChatColor.YELLOW + "/bannedwords remove <word>");

                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {

                sender.sendMessage(GeneralMethods.prefix + "Banned Words:");
                for (String words : bannedWords) {
                    sender.sendMessage(words);
                }
                return true;

            } else if (args.length == 2 && args[0].equalsIgnoreCase("add")) {

                if (bannedWords.contains(args[1])) {
                    sender.sendMessage(GeneralMethods.errorColor + "This word is already added!");
                } else {
                    bannedWords.add(args[1]);
                    ConfigManager.bannedWords.get().set("AntiCurse.bannedWords", bannedWords);
                    ConfigManager.bannedWords.save();

                    sender.sendMessage(GeneralMethods.successColor + "Added the word, " + args[1] + ", to the banned words list.");
                    return true;
                }
            } else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {

                if (bannedWords.contains(args[1])) {
                    bannedWords.remove(args[1]);
                    ConfigManager.bannedWords.get().set("AntiCurse.bannedWords", bannedWords);
                    ConfigManager.bannedWords.save();
                    sender.sendMessage(GeneralMethods.successColor + "Removed the banned word, " + args[1] + ", from the list.");

                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "The word " + args[1] + " is not in the banned words list.");
                }
            } else {
                sender.sendMessage(GeneralMethods.prefix + "Banned Words Commands:");
                sender.sendMessage(ChatColor.YELLOW + "/bannedwords list");
                sender.sendMessage(ChatColor.YELLOW + "/bannedwords add <word>");
                sender.sendMessage(ChatColor.YELLOW + "/bannedwords remove <word>");

                return true;
            }
            return true;
        } else {
            sender.sendMessage(GeneralMethods.errorColor + "You have insufficient permission to access this command. Please contact the administraitor if this is incorrect.");
            return true;
        }
    }
}
