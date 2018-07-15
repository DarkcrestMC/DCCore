package com.Jacksonnn.DCCore.KillMoney;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static org.bukkit.Statistic.DEATHS;
import static org.bukkit.Statistic.PLAYER_KILLS;

public class KillListener implements Listener {
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity().getPlayer();
		Player killer = event.getEntity().getKiller();

		long killerKills = killer.getStatistic(PLAYER_KILLS);
		long killerDeaths = killer.getStatistic(DEATHS);

		if (killerDeaths == 0) {
		    long killerAverage = killerKills;

        } else if (killerDeaths >= 1) {
		    long killerAverage = (killerKills / killerDeaths);

        }
	}
}
