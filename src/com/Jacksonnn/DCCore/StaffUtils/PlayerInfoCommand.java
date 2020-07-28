package com.Jacksonnn.DCCore.StaffUtils;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import com.Jacksonnn.DCCore.StaffUtils.Notes.NotesGeneral;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportGeneral;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.WarningGeneral;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlayerInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("DCCore.PlayerInfo")) {
            if (args.length == 1) {

            /* /pinfo <username>
            Player info: --[Jacksonnn]--
            Last Login: 15:17:11 (11/12/2014) | Times Joined: 12431
            Playtime: 10hours 354minutes 343seconds | Joined: 07/11/2011
            Notes: 524 | Warnings: 23421 | Reports Made: 421453
            Last Location: world, 236, 74, -65
            Rank: BLAH, BLAH2, BLAH3
            Kills: 52345324 | Deaths: 35235234
            IP: 543.54.352.132
             */

                Player player = Bukkit.getPlayer(args[0]);

                if (player == null) {
                    sender.sendMessage(GeneralMethods.errorColor + "Player not online! Checking offline players...");

                    OfflinePlayer oPlayer = Bukkit.getOfflinePlayer(args[0]);

                    if (oPlayer == null) {
                        sender.sendMessage(GeneralMethods.errorColor + "Player has never joined server...");
                        return true;
                    }

                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(GeneralMethods.prefix + "PlayerInfo: --[" + ChatColor.GRAY + args[0] + ChatColor.YELLOW + "]--");

                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(oPlayer.getFirstPlayed());
                    String joinDate = sdf.format(calendar.getTime());

                    SimpleDateFormat sdfMinute = new SimpleDateFormat("HH:mm:ss (MM/dd/yyyy)");
                    calendar.setTimeInMillis(oPlayer.getLastPlayed());
                    String lastLogin = sdfMinute.format(calendar.getTime());

                    sender.sendMessage(ChatColor.YELLOW + "Last Login: " + lastLogin +
                            ChatColor.YELLOW + " | Times Joined: " + ChatColor.WHITE + (oPlayer.getPlayer().getStatistic(Statistic.LEAVE_GAME) + 1));
                    sender.sendMessage(ChatColor.YELLOW + "Playtime: " +
                            ChatColor.WHITE + GeneralMethods.milliToHours(player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 60 * 1000) +
                            ChatColor.YELLOW + " | Joined: " + ChatColor.WHITE + joinDate);
                    sender.sendMessage(ChatColor.YELLOW + "Notes: " +
                            ChatColor.AQUA + NotesGeneral.getPlayerNotes(oPlayer.getPlayer()).size() +
                            ChatColor.YELLOW + " | Warnings: " +
                            ChatColor.GOLD + WarningGeneral.getPlayerWarnings(oPlayer.getPlayer()).size() +
                            ChatColor.YELLOW + " | Reports: " +
                            ChatColor.DARK_RED + ReportGeneral.getPlayerReports(oPlayer.getPlayer()).size());
                    sender.sendMessage(ChatColor.YELLOW + "Last Location: " +
                            ChatColor.WHITE + GeneralMethods.locToString(oPlayer.getPlayer().getLocation()));

                    String playerRanks = StringUtils.join(DCCore.permissions.getPlayerGroups(oPlayer.getPlayer()), ", ");

                    sender.sendMessage(ChatColor.YELLOW + "Ranks: " + ChatColor.WHITE + playerRanks);
                    sender.sendMessage(ChatColor.YELLOW + "Kills: " +
                            ChatColor.WHITE + oPlayer.getPlayer().getStatistic(Statistic.PLAYER_KILLS) +
                            ChatColor.YELLOW + " | Deaths: " + ChatColor.WHITE + oPlayer.getPlayer().getStatistic(Statistic.DEATHS));
                    sender.sendMessage(ChatColor.YELLOW + "Last IP: " + oPlayer.getPlayer().getAddress().getAddress().toString());
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                } else {
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage(GeneralMethods.prefix + "Player Info: --[" + ChatColor.GRAY + args[0] + ChatColor.YELLOW + "]--");

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(player.getFirstPlayed());
                    String joinDate = simpleDateFormat.format(calendar.getTime());

                    sender.sendMessage(ChatColor.YELLOW + "Last Login: " + ChatColor.GREEN + "Online!" +
                            ChatColor.YELLOW + " | Times Joined: " + ChatColor.WHITE + (player.getStatistic(Statistic.LEAVE_GAME) + 1));
                    sender.sendMessage(ChatColor.YELLOW + "Playtime: " +
                            ChatColor.WHITE + GeneralMethods.milliToHours(player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 60 * 1000) +
                            ChatColor.YELLOW + " | Joined: " + ChatColor.WHITE + joinDate);
                    sender.sendMessage(ChatColor.YELLOW + "Notes: " +
                            ChatColor.AQUA + NotesGeneral.getPlayerNotes(player).size() +
                            ChatColor.YELLOW + " | Warnings: " +
                            ChatColor.GOLD + WarningGeneral.getPlayerWarnings(player).size() +
                            ChatColor.YELLOW + " | Reports: " +
                            ChatColor.DARK_RED + ReportGeneral.getPlayerReports(player).size());
                    sender.sendMessage(ChatColor.YELLOW + "Last Location: " + ChatColor.WHITE + GeneralMethods.locToString(player.getLocation()));

                    String playerRanks = StringUtils.join(DCCore.permissions.getPlayerGroups(player.getPlayer()), ", ");

                    sender.sendMessage(ChatColor.YELLOW + "Ranks: " + ChatColor.WHITE + playerRanks);
                    sender.sendMessage(ChatColor.YELLOW + "Kills: " + ChatColor.WHITE + player.getStatistic(Statistic.PLAYER_KILLS) +
                            ChatColor.YELLOW + " | Deaths: " + ChatColor.WHITE + player.getStatistic(Statistic.DEATHS));
                    sender.sendMessage(ChatColor.YELLOW + "Last IP: " + ChatColor.WHITE + player.getAddress().toString());
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "/playerinfo <player>");
            }
        } else {
            sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient permissions to execute this command.");
        }
        return true;
    }
}
