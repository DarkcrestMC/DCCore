package com.Jacksonnn.DCCore.AutoAnnouncer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AnnouncementCommands implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("DCCore.staff.announcements")) {
            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                getHelp(sender);
            } else if (args[0].equalsIgnoreCase("add")) {
                if (args.length >= 2) {
                    StringBuilder messageToAnnounce = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        messageToAnnounce.append(args[i]);
                        messageToAnnounce.append(" ");
                    }
                    AnnouncementManager.addAnnouncement(messageToAnnounce.toString());
                    sender.sendMessage(AnnouncementManager.getPrefix() + "Successfully added announcement.");
                } else {
                    AnnouncementManager.sendError(sender, "Please specify a message to announce.");
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (args.length == 2) {
                    try {
                        AnnouncementManager.removeAnnouncement(Integer.valueOf(args[1]).intValue());
                        sender.sendMessage(AnnouncementManager.getPrefix() + "Successfully removed announcement.");
                    } catch (Exception e) {
                        AnnouncementManager.sendError(sender, "Did you put a number for the ID?");
                    }
                } else {
                    AnnouncementManager.sendError(sender, "Do you have the right command? /announcer remove <id>");
                }
            } else if (args[0].equalsIgnoreCase("broadcast") || args[0].equalsIgnoreCase("bc")) {
                if (args.length == 2) {
                    ArrayList<String> messages = new ArrayList<>();
                    messages.addAll(AnnouncementManager.getAnnouncements());
                    try {
                        AnnouncementManager.announce(Integer.valueOf(args[1]).intValue());
                    } catch (Exception e) {
                        AnnouncementManager.sendError(sender, "Did you put the right number for the ID?");
                    }
                } else {
                    AnnouncementManager.sendError(sender, "Do you have the right command? /announce broadcast <id>");
                }
            } else if (args[0].equalsIgnoreCase("say")) {
                if (args.length >= 2) {
                    StringBuilder messageToAnnounce = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        messageToAnnounce.append(args[i]);
                        messageToAnnounce.append(" ");
                    }
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnnouncementManager.getPrefix() + messageToAnnounce.toString()));
                }
            } else if (args[0].equalsIgnoreCase("list")) {
                ArrayList<String> messages = new ArrayList<>();
                messages.addAll(AnnouncementManager.getAnnouncements());
                sender.sendMessage(AnnouncementManager.getPrefix() + "List of Active Announcements:");
                for (int i = 0; i <= messages.size() - 1; i++)
                    sender.sendMessage("(ID: " + i + ") " + messages.get(i));
            }
            return true;
        }
        AnnouncementManager.sendError(sender, "You do not have sufficient permissions to execute this command.");
        return true;
    }

    public void getHelp(CommandSender sender) {
        sender.sendMessage(AnnouncementManager.getPrefix() + "Command Help");
        sender.sendMessage(AnnouncementManager.getPrefix() + "/announcer");
        sender.sendMessage(AnnouncementManager.getPrefix() + "/announcer add <message>");
        sender.sendMessage(AnnouncementManager.getPrefix() + "/announcer remove <id>");
        sender.sendMessage(AnnouncementManager.getPrefix() + "/announcer broadcast <id>");
        sender.sendMessage(AnnouncementManager.getPrefix() + "/announcer say <message>");
        sender.sendMessage(AnnouncementManager.getPrefix() + "/announcer list");
        sender.sendMessage(AnnouncementManager.getPrefix() + "/announcer help");
    }
}
