package com.Jacksonnn.DCCore.AutoAnnouncer;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class AnnouncementManager {
    private static boolean isEnabled = ConfigManager.announcer.get().getBoolean("announcements.enabled");

    private static String prefix = GeneralMethods.translateHEXColorCode(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.announcer.get().getString("announcements.prefix"))));

    private static long interval = ConfigManager.announcer.get().getLong("announcements.interval");

    private static ArrayList<String> messages = getAllAnnouncements();

    private static ArrayList<String> getAllAnnouncements() {
        ArrayList<String> announcements = new ArrayList<>();
        for (String message : (List<String>) requireNonNull(ConfigManager.announcer.get().getList("announcements.messages")))
            announcements.add(GeneralMethods.translateHEXColorCode(ChatColor.translateAlternateColorCodes('&', message)));
        return announcements;
    }

    public static boolean isEnabled() {
        return isEnabled;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static long getInterval() {
        return interval;
    }

    public static ArrayList<String> getAnnouncements() {
        return messages;
    }

    public static String getAnnouncement(int index) {
        return messages.get(index);
    }

    public static void addAnnouncement(String message) {
        message = GeneralMethods.translateHEXColorCode(ChatColor.translateAlternateColorCodes('&', message));
        messages.add(message);
        ConfigManager.announcer.get().set("announcements.messages", messages);
        ConfigManager.announcer.save();
    }

    public static void removeAnnouncement(int index) {
        messages.remove(index);
        ConfigManager.announcer.get().set("announcements.messages", messages);
        ConfigManager.announcer.save();
    }

    public static void sendError(CommandSender sender, String message) {
        message = GeneralMethods.translateHEXColorCode(ChatColor.translateAlternateColorCodes('&', message));
        sender.sendMessage(getPrefix() + ChatColor.DARK_RED + "Error! " + ChatColor.RED + message);
    }

    public static void announce(int index) {
        Bukkit.getServer().broadcastMessage(getPrefix() + getAnnouncement(index));
    }
}
