package com.Jacksonnn.DCCore.StaffUtils.Warnings.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.Warning;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.WarningSubCommand;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AddCommand implements WarningSubCommand {
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public AddCommand(DCCore plugin) {
        this.plugin = plugin;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("create");
        aliases.add("new");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/warnings new <player> <message>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Warnings.CommandDescriptions.AddCommand");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() >= 2) {
            Player player = Bukkit.getPlayer(args.get(0));
            Player staffMember = ((Player) sender).getPlayer();
            OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();

            for (OfflinePlayer oPlayer : offlinePlayers) {
                if (oPlayer.getName().equalsIgnoreCase(args.get(0))) {
                    player = oPlayer.getPlayer();
                }
            }

            if (player == null) {
                sender.sendMessage(pdm.getWarningManager().warningPrefix + "That player does not exist, please try again.");
                return;
            }

            if (staffMember == null) {
                sender.sendMessage(pdm.getWarningManager().warningPrefix + "You must be a player to execute this command.");
            }

            args.remove(0);

            Warning warning = new Warning(player, staffMember, String.join(" ", args), pdm);

            sender.sendMessage(pdm.getWarningManager().warningPrefix + "Success! Warning created!");

            TextChannel notesChannel = DiscordUtil.getTextChannelById(Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Warnings.ChannelID")));

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor(warning.getStaffMember().getName());
            embedBuilder.setTitle("Warning against " + warning.getPlayer().getName());
            embedBuilder.setDescription(warning.getReason() + " -" + warning.getStaffMember().getName());
            //GOLD CHAT COLOR
            embedBuilder.setColor(new Color(255, 170, 0));

            notesChannel.sendMessage(embedBuilder.build()).queue();

            String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix")));
            String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor")));
            Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

            for (Player oPlayer : onlinePlayers) {
                if (oPlayer.hasPermission("DCCore.staffchats.Staff")) {
                    oPlayer.sendMessage(chatprefix + ChatColor.GOLD + "NEW WARNING: " + msgColor + warning.getStaffMember().getName() + " just warned " + warning.getPlayer().getName() + " for " + warning.getReason() + ".");
                }
            }
        } else {
            sender.sendMessage(pdm.getWarningManager().warningPrefix + getProperUse());
        }
    }
}
