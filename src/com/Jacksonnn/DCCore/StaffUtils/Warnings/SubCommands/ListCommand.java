package com.Jacksonnn.DCCore.StaffUtils.Warnings.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.Warning;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.WarningSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements WarningSubCommand {
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public ListCommand(DCCore plugin) {
        this.plugin = plugin;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/warnings list <player>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Warnings.CommandDescriptions.List");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() == 1) {
            if (!args.get(0).equalsIgnoreCase("all")) {
                Player player = Bukkit.getPlayer(args.get(0));

                if (player == null) {
                    OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();
                    for (OfflinePlayer offlinePlayer : offlinePlayers) {
                        if (offlinePlayer.getName() == args.get(0)) {
                            player = offlinePlayer.getPlayer();
                            break;
                        } else {
                            continue;
                        }
                    }
                }

                if (player == null) {
                    sender.sendMessage(pdm.getWarningManager().warningPrefix + "Does that player exist?");
                    return;
                }

                List<Warning> playerWarnings = new ArrayList<>();
                for (Warning warning : pdm.getWarningManager().getAllWarnings()) {
                    if (warning.getPlayer() == player) {
                        playerWarnings.add(warning);
                    }
                }

                if (playerWarnings.size() == 0) {
                    sender.sendMessage(pdm.getWarningManager().warningPrefix + "Player has no warnings.");
                } else {
                    sender.sendMessage(pdm.getWarningManager().warningPrefix + "Warnings for player " + player.getName() + ":");
                    for (Warning warning : playerWarnings) {
                        sender.sendMessage("(ID: " + warning.getID() + ") " + ChatColor.YELLOW + warning.getReason() + ChatColor.GOLD + " -" + warning.getStaffMember().getName());
                    }
                }
            } else {
                List<Warning> playerWarnings = new ArrayList<>();
                for (Warning warning : pdm.getWarningManager().getAllWarnings()) {
                    playerWarnings.add(warning);
                }

                if (playerWarnings.size() == 0) {
                    sender.sendMessage(pdm.getWarningManager().warningPrefix + "There are no Player Warnings.");
                } else {
                    sender.sendMessage(pdm.getWarningManager().warningPrefix + "All player warnings:");
                    for (Warning warning : playerWarnings) {
                        sender.sendMessage("(ID: " + warning.getID() + ") " + ChatColor.YELLOW + warning.getReason() + ChatColor.GOLD + " -" + warning.getStaffMember().getName());
                    }
                }
            }
        } else {
            sender.sendMessage(pdm.getWarningManager().warningPrefix + getProperUse());
        }
    }
}
