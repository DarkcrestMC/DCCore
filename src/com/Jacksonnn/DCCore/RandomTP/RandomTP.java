package com.Jacksonnn.DCCore.RandomTP;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomTP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            if (sender.hasPermission("DCCore.RandomTP.others")) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player == null) {
                    sender.sendMessage(GeneralMethods.errorPrefix);
                    return true;
                }
                World world = world;
                int radius;

                if (ConfigManager.defaultConfig.get().getBoolean("RandomTP.SetRadius")) {
                    radius = ConfigManager.defaultConfig.get().getInt("RandomTP.Radius");
                } else {
                    radius = ((int) world.getWorldBorder().getSize()) / ConfigManager.defaultConfig.get().getInt("RandomTP.WorldSizeDividedBy");
                }

                int randX = (int) (Math.random() * radius);
                int randY = (int) (Math.random() * radius);
                int randZ = (int) (Math.random() * radius);

                Location endLoc = new Location(world, randX, randY, randZ);

                player.teleport(GeneralMethods.toHighestBlock(endLoc));
                player.performCommand("unstuck");
                sender.sendMessage(GeneralMethods.successPrefix + ChatColor.of("&#00BF45") + "Successfully teleported player to " + GeneralMethods.locToString(GeneralMethods.toHighestBlock(endLoc)));
                player.sendMessage(GeneralMethods.successPrefix + ChatColor.of("&#00BF45") + "Successfully teleported to " + GeneralMethods.locToString(GeneralMethods.toHighestBlock(endLoc)));
            }
        } else if (args.length == 0) {
            if (sender.hasPermission("DCCore.RandomTP.self")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(GeneralMethods.errorPrefix + ChatColor.of("&#00BF45") + "You must be a player to execute this command.");
                } else {
                    World world = ((Player) sender).getWorld();
                    double worldSize = world.getWorldBorder().getSize();
                    int radius = (int) worldSize / 8;

                    if (ConfigManager.defaultConfig.get().getBoolean("RandomTP.SetRadius")) {
                        radius = ConfigManager.defaultConfig.get().getInt("RandomTP.Radius");
                    } else {
                        radius = ((int) world.getWorldBorder().getSize()) / ConfigManager.defaultConfig.get().getInt("RandomTP.WorldSizeDividedBy");
                    }

                    int randX = (int) (Math.random() * radius);
                    int randY = (int) (Math.random() * radius);
                    int randZ = (int) (Math.random() * radius);

                    Location endLoc = new Location(world, randX, randY, randZ);

                    ((Player) sender).teleport(GeneralMethods.toHighestBlock(endLoc));
                    ((Player) sender).performCommand("unstuck");
                    sender.sendMessage(GeneralMethods.successPrefix + ChatColor.of("#00BF45") + "Successfully teleported to " + GeneralMethods.locToString(GeneralMethods.toHighestBlock(endLoc)));
                }
            }
        } else {
            sender.sendMessage(GeneralMethods.errorPrefix + ChatColor.of("&#00BF45") + "/randomtp [<player>]");
        }
        return true;
    }
}
