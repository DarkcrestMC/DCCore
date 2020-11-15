package com.Jacksonnn.DCCore;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DCPlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        DCPlayer dcPlayer = GeneralMethods.getDCPlayer(e.getPlayer().getUniqueId());

        if (dcPlayer == null) {
            new DCPlayer(e.getPlayer());
        } else {
            if (dcPlayer.getName() != e.getPlayer().getName()) {
                dcPlayer.setName(e.getPlayer().getName());
            }
            dcPlayer.setTimesJoined(dcPlayer.getTimesJoined() + 1);
            dcPlayer.setLastPlayed(e.getPlayer().getLastPlayed());
            dcPlayer.setRanks(DCCore.permissions.getPlayerGroups(null, e.getPlayer()));
            dcPlayer.setLastLocation(e.getPlayer().getLocation());
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        DCCore.permissions.playerRemove(null, e.getPlayer(), "-discordsrv.chat");

        DCPlayer dcPlayer = GeneralMethods.getDCPlayer(e.getPlayer().getUniqueId());

        if (dcPlayer != null) {

            dcPlayer.setPlayTime(e.getPlayer().getStatistic(Statistic.PLAY_ONE_MINUTE) / 60 * 1000);
            dcPlayer.setChatMode(GeneralMethods.ChatModes.GENERAL);
            dcPlayer.setLastLocation(e.getPlayer().getLocation());
            dcPlayer.setLastPlayed(e.getPlayer().getLastPlayed());
            dcPlayer.setRanks(DCCore.permissions.getPlayerGroups(null, e.getPlayer()));

            DCCore.getDCPM().updateDCPlayer(dcPlayer);
        } else {
            Bukkit.getLogger().info("[DCCore] There is not a DCPlayer for " + e.getPlayer().getName() + " (" + e.getPlayer().getUniqueId() + ")... Error code: 4.");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        // Not really sure why we have this in the first place.
        // Why do we need to store kills and deaths in DCPlayer when Minecraft natively stores them in Player?

        DCPlayer dcPlayer = GeneralMethods.getDCPlayer(e.getEntity().getUniqueId());
        if (dcPlayer != null) {
            dcPlayer.setDeaths(e.getEntity().getStatistic(Statistic.DEATHS));
        }

        Player playerKiller = e.getEntity().getKiller();
        if (playerKiller != null) {
            DCPlayer dcPlayerKiller = GeneralMethods.getDCPlayer(playerKiller.getUniqueId());
            if (dcPlayerKiller != null) dcPlayerKiller.setKills(playerKiller.getStatistic(Statistic.PLAYER_KILLS));
        }
    }
}
