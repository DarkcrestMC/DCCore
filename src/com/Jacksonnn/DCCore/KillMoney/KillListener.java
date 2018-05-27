package com.Jacksonnn.DCCore.KillMoney;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillListener {
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity().getPlayer();
		Player killer = event.getEntity().getKiller();
		
		
	}
}
