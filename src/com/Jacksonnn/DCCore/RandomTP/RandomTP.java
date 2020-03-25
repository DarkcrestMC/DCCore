package com.Jacksonnn.DCCore.RandomTP;

import com.Jacksonnn.DCCore.GeneralMethods;
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
                World world = player.getWorld();
                double worldSize = world.getWorldBorder().getSize();
                int radius = (int) worldSize / 8;

                int randX = (int) (Math.random() * radius);
                int randY = (int) (Math.random() * radius);
                int randZ = (int) (Math.random() * radius);

                Location endLoc = new Location(world, randX, randY, randZ);

                player.teleport(GeneralMethods.toHighestBlock(endLoc));
                player.performCommand("unstuck");
                sender.sendMessage(GeneralMethods.successColor + "Successfully teleported player to " + GeneralMethods.toHighestBlock(endLoc).toString());
                player.sendMessage(GeneralMethods.successColor + "Successfully teleported to " + GeneralMethods.toHighestBlock(endLoc).toString());
            }
        } else if (args.length == 0) {
            if (sender.hasPermission("DCCore.RandomTP.self")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(GeneralMethods.errorColor + "You must be a player to execute this command.");
                } else {
                    World world = ((Player) sender).getWorld();
                    double worldSize = world.getWorldBorder().getSize();
                    int radius = (int) worldSize / 2;

                    int randX = (int) (Math.random() * radius);
                    int randY = (int) (Math.random() * radius);
                    int randZ = (int) (Math.random() * radius);

                    Location endLoc = new Location(world, randX, randY, randZ);

                    ((Player) sender).teleport(GeneralMethods.toHighestBlock(endLoc));
                    ((Player) sender).performCommand("unstuck");
                    sender.sendMessage(GeneralMethods.successColor + "Successfully teleported to " + GeneralMethods.toHighestBlock(endLoc).toString());
                }
            }
        } else {
            sender.sendMessage(GeneralMethods.errorColor + "/randomtp [<player>]");
        }
        return true;
    }
}
