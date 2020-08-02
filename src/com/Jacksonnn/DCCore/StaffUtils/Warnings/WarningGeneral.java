package com.Jacksonnn.DCCore.StaffUtils.Warnings;

import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class WarningGeneral {
    public String warningPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "DC Warnings" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + " ";
    private static ArrayList<Warning> warnings = new ArrayList<>();

    public Warning getWarning(int id) {
        for (Warning warning : warnings) {
            if (warning.getID() == id) {
                return warning;
            }
        }

        return null;
    }

    public ArrayList<Warning> getAllWarnings() {
        return warnings;
    }

    public void addWarning(Warning warning) {
        Bukkit.getLogger().info("Adding warning to overall warnings...");
        warnings.add(warning);

        DCPlayer dcPlayer = GeneralMethods.getDCPlayer(warning.getPlayer());
        dcPlayer.addWarning(warning);

        Bukkit.getLogger().info("Successfully added warning!");
    }

    public void removeWarning(Warning warning) {
        Bukkit.getLogger().info("Removing warning from overall warnings...");
        warnings.remove(warning);

        DCPlayer dcPlayer = GeneralMethods.getDCPlayer(warning.getPlayer());
        dcPlayer.removeWarning(warning);

        Bukkit.getLogger().info("Successfully removed warning!");
    }

    public static ArrayList<Warning> getPlayerWarnings(Player player) {
        ArrayList<Warning> playersWarnings = new ArrayList<>();
        for (Warning warning : warnings) {
            if (warning.getPlayer() == player.getUniqueId()) {
                playersWarnings.add(warning);
            }
        }

        return playersWarnings;
    }

    public void getHelp(CommandSender sender) {
        sender.sendMessage(warningPrefix + "Warning Commands: ");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/warning help");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/warning add <player> <note>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/warning delete <id>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/warning list <player>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/warning clear <player>");
    }
}
