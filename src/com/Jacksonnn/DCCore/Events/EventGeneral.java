package com.Jacksonnn.DCCore.Events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class EventGeneral {
    public static String eventPrefix = ChatColor.GREEN + "[" + ChatColor.BLUE + "DC Events" + ChatColor.GREEN + "]" + ChatColor.YELLOW + " ";
    public static ArrayList<Event> events = new ArrayList<>();

    public static Event getEvent(String name) {
        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(name)) {
                return event;
            }
        }

        return null;
    }

    public static void getHelp(CommandSender sender) {
        sender.sendMessage(EventGeneral.eventPrefix + "Event Commands: ");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/dcevents add <event> <player(s)>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/dcevents remove <event> <player(s)>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/dcevents startEvent <event>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/dcevents endEvent <event>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/dcevents list");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/dcevents playerList <event>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/dcevents broadcast <message>");
    }
}
