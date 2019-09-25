package com.Jacksonnn.DCCore.Events;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class Event {
    String eventName;
    String eventStaff;
    ArrayList<EventPlayer> eventPlayers = new ArrayList<>();

    public Event(String name, String staff) {
        eventName = name;
        eventStaff = staff;

        EventGeneral.events.add(this);
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventStaff() {
        return eventStaff;
    }

    public ArrayList<EventPlayer> getEventPlayers() {
        return eventPlayers;
    }

    public EventPlayer getEventPlayer(String name) {
        for (EventPlayer player : eventPlayers) {
            if (player.getName() == name) {
                return player;
            }
        }
        return null;
    }

    public void addPlayer(CommandSender sender, String name) {
        EventPlayer player = new EventPlayer(eventPlayers.size() + 1, Bukkit.getPlayer(name));
        if (eventPlayers.contains(player)) {
            sender.sendMessage(EventGeneral.eventPrefix + "Player" + name + " has already joined the event!");
        } else {
            eventPlayers.add(player);
        }
    }

    public void addPlayer(CommandSender sender, EventPlayer player) {
        if (eventPlayers.contains(player)) {
            sender.sendMessage(EventGeneral.eventPrefix + "Player" + player.getName() + " has already joined the event!");
        } else {
            eventPlayers.add(player);
        }
    }

    public void removePlayer(CommandSender sender, String name) {
        EventPlayer player = getEventPlayer(name);
        if (player != null) {
            eventPlayers.remove(player);
        } else {
            sender.sendMessage(EventGeneral.eventPrefix + "Player" + name + " is not apart of this event!");
        }
    }

    public void removePlayer(CommandSender sender, EventPlayer player) {

        if (eventPlayers.contains(player)) {
            eventPlayers.remove(player);
        } else {
            sender.sendMessage(EventGeneral.eventPrefix + "Player" + player.getName() + " is not apart of this event!");
        }
    }

    public void removePlayer(EventPlayer player) {
        if (eventPlayers.contains(player)) {
            eventPlayers.remove(player);
            Bukkit.getServer().getLogger().info(EventGeneral.eventPrefix + "Successfully removed player, " + player.getName() + ", from event, " + eventName);
        } else {
            Bukkit.getServer().getLogger().info(EventGeneral.eventPrefix + "Player" + player.getName() + " is not apart of any events.");
        }
    }
}
