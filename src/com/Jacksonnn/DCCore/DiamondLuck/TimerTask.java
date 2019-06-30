package com.Jacksonnn.DCCore.DiamondLuck;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TimerTask extends java.util.TimerTask {
    CommandSender sender;
    ArrayList<String> players;
    Location spawnpoint;
    Location center;
    int radius;

    TimerTask(CommandSender sender, ArrayList<String> players, Location center, int radius, Location spawnpoint) {
        this.spawnpoint = spawnpoint;
        this.center = center;
        this.radius = radius;
        this.players = players;
        this. sender = sender;
    }

    public void run() {
            int number = (int) (Math.random() * players.size());
            Player out = Bukkit.getPlayer(players.get(number));
            sender.sendMessage(GeneralMethods.prefix + "Player, " + out.getName() + ", is out!");
            Location underPlayer = out.getLocation().add(0, -1, 0);
            underPlayer.getBlock().setType(Material.DIAMOND_BLOCK);
            out.teleport(spawnpoint);
            sender.sendMessage(GeneralMethods.prefix + "You're out!");
            underPlayer.getBlock().setType(Material.COARSE_DIRT);
            players.remove(number);

            if (players.size() == 0) {
                sender.sendMessage(GeneralMethods.prefix + "Player, " + out.getName() + ", won!!!");
            }
    }
}
/*
Votifier 8192
Plan 8804
 */