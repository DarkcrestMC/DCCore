package com.Jacksonnn.DCCore.DiamondLuck;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Timer;

public class DiamondLuck implements CommandExecutor {

    int Cx;
    int Cy;
    int Cz;
    int radius;
    int Ox;
    int Oy;
    int Oz;
    List<String> players;

    /*
            /dl -195 70 -210 6 -195 71 -218

            /diamondluck <Cx> <Cy> <Cz> <radius> <Ox> <Oy> <Oz>

            1. get coords for center and spawnpoint
            2. get players
            3. spawn diamond block
            4. player that stands on diamond block is out
            5. repeat until 1 player left
            6. if one player left, they are declared winner

     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        World world = null;
        if(sender instanceof Player) {
            world = ((Player) sender).getWorld();
        }

        if (args.length != 7) {
            sender.sendMessage(GeneralMethods.errorColor + "/diamondluck <Cx> <Cy> <Cz> <radius> <Ox> <Oy> <Oz>");
        } else {
            Cx = Integer.valueOf(args[0]);
            Cy = Integer.valueOf(args[1]);
            Cz = Integer.valueOf(args[2]);
            radius = Integer.valueOf(args[3]);
            Ox = Integer.valueOf(args[4]);
            Oy = Integer.valueOf(args[5]);
            Oz = Integer.valueOf(args[6]);

            ResponseListener.userReponseForPlayers = true;
            sender.sendMessage(GeneralMethods.prefix + "Enter player names");
            while(ResponseListener.userReponseForPlayers) {
                //do nothing
            }
            players = ResponseListener.players;
            start(sender, players, new Location(world, Cx, Cy, Cz), radius, new Location(world, Ox, Oy, Oz));
        }
        return true;
    }
    public void start(CommandSender sender, List<String> players, Location center, int radius, Location spawnpoint) {
        TimerTask outTask = new TimerTask(sender, players, center, radius, spawnpoint);
        Timer timer = new Timer();

        for (int i = players.size(); i == 0; i--) {
            timer.schedule(outTask, 1000);
        }
        players.removeAll(ResponseListener.players);
    }
}
