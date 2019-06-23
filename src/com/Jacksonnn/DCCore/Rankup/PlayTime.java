package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.GeneralMethods;
import com.djrapitops.plan.api.PlanAPI;
import com.djrapitops.plan.data.container.Session;
import com.djrapitops.plan.data.store.keys.SessionKeys;
import com.djrapitops.plan.data.store.mutators.SessionsMutator;
import com.djrapitops.plan.system.cache.SessionCache;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class PlayTime implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());
        sender.sendMessage(GeneralMethods.prefix + sender.getName() + ", you current have " + GeneralMethods.milliToHours(getPlayTime(player)) + " of playtime.");
        return true;
    }

    public static long getPlayTime(Player player) {
        UUID playerUUID = player.getUniqueId();
        long planPlayTime = SessionsMutator.forContainer(PlanAPI.getInstance().fetchFromPlanDB().getPlayerContainer(playerUUID)).toPlaytime();

        Optional<Session> activeSession = SessionCache.getCachedSession(playerUUID);
        long sessionPlayTime = activeSession.get().getValue(SessionKeys.LENGTH).get();

        long playTime = planPlayTime + sessionPlayTime;

        return playTime;
    }
}
