package com.Jacksonnn.DCCore.SpectatorDisabler;

import com.projectkorra.projectkorra.event.AbilityStartEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class OnGMChange implements Listener {

    public void onGMChange(PlayerGameModeChangeEvent event) {
        GameMode newGM = event.getNewGameMode();
        Player player = event.getPlayer();

        if (newGM == GameMode.SPECTATOR) {
            if (!player.hasPermission("DCCore.GMSpectator")) {
                event.setCancelled(true);
            }
        }
    }

    public void onBendingProgress(AbilityStartEvent event) {
        event.getAbility().getElement();
    }

}
