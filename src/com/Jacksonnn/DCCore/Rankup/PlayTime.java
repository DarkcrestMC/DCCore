package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayTime implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());
        sender.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + sender.getName() + ", you current have " + GeneralMethods.milliToHours(getPlayTime(player)) + " of playtime.");
        return true;
    }

    public static long getPlayTime(Player player) {
        // NOTE: PLAY_ONE_MINUTE's name is misleading. It gives the number of ticks the user has played, not their minutes.
        return player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 60 * 1000;
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
