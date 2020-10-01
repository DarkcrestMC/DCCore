package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.DCCore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DoGuestQuizCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player))
            return true;
        Player player = (Player)commandSender;
        String[] groups = DCCore.permissions.getPlayerGroups(player);
        if (Rankup.isPlayerInGroup(player, "Guest")) Rankup.guestCheck(player, 1);
//        if (!DCCore.permissions.playerInGroup(null, player, "Guest")) {
//            return true;
//        }
//        Rankup.guestCheck(player, 1);
        return true;
    }
}
