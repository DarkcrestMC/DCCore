package com.Jacksonnn.DCCore.StaffUtils;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Collection;
import java.util.Objects;

public class BanishHackFix implements CommandExecutor {

    private DCCore plugin;

    public BanishHackFix(DCCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            String sendReportTo = Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Reports.PlayerReport.ChannelID"));

            TextChannel todoReportChannel = DiscordUtil.getTextChannelById(sendReportTo);

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor("CONSOLE");
            embedBuilder.setTitle("Player Report: " + commandSender.getName());
            embedBuilder.setDescription("Malicious activity... using /banish..." + " (resolved: true-permbanned) -CONSOLE");
            //RED CHAT COLOR
            embedBuilder.setColor(new Color(170, 0, 0));

            todoReportChannel.sendMessage(embedBuilder.build()).queue();

            String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix")));
            String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor")));

            Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

            for (Player player : onlinePlayers) {
                if (player.hasPermission("DCCore.staffchats.Staff")) {
                    player.sendMessage(chatprefix + ChatColor.DARK_RED + "NEW PLAYER REPORT: " + msgColor + "CONSOLE just submitted a new player report against " + commandSender.getName() + ".");
                }
            }

            Player player = (Player) commandSender;

            String banReason = "Malicious activity. Make a ban appeal at http://darkcrestmc.net/forums or on our discord.";

            player.kickPlayer(banReason);
            Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(player.getName(), banReason, null, null);

            for (Player oPlayer :onlinePlayers) {
                if (oPlayer.hasPermission("essentials.ban.notify")) {
                    oPlayer.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + "CONSOLE " + ChatColor.GOLD + "banned " + ChatColor.RED + commandSender.getName() + ChatColor.GOLD + " for: " + ChatColor.RED + banReason + ChatColor.GOLD + ".");
                }
            }

            return true;
        }

        return true;
    }
}
