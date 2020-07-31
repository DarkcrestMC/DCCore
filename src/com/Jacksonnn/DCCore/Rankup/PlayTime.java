package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayTime implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player))
            return true;
        Player player = (Player)sender;
        sender.sendMessage(String.format(GeneralMethods.prefix + sender.getName() + ", you currently have %.2f hours of playtime.", getPlayTimeHours(player)));
        return true;
    }

    public static double getPlayTimeHours(Player player) {
        // NOTE: PLAY_ONE_MINUTE's name is misleading. It gives the number of ticks the user has played, not their minutes.
        return player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000.0;
        // divided by 72000 = (20 (# ticks in a second) * 60 (# seconds in a minute) * 60 (# minutes in an hour))
//        return PlayTimeAPI.getSecs(player) * 1000;
        /*
        UUID playerUUID = player.getUniqueId();
        long planPlayTime = QueryService.getInstance().getCommonQueries().fetchPlaytime(playerUUID, QueryService.getInstance().getServerUUID().get(), 0, System.currentTimeMillis());

        Session activeSession = SessionCache.getCachedSession(playerUUID).get();
        long sessionPlayTime = activeSession.getValue(SessionKeys.LENGTH).get();

        return planPlayTime + sessionPlayTime;
        */
    }
}
