package com.Jacksonnn.DCCore.StaffUtils.Warnings.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.Warning;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.WarningSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ClearCommand implements WarningSubCommand {
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public ClearCommand(DCCore plugin) {
        this.plugin = plugin;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("deleteall");
        aliases.add("clearall");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/warnings clear <player>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Warnings.CommandDescriptions.Clear");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() == 1) {
            Player player = Bukkit.getPlayer(args.get(0));

            if (player == null) {
                sender.sendMessage(pdm.getWarningManager().warningPrefix + "Does that player exist?");
                return;
            }

            ArrayList<Warning> allWarnings = new ArrayList<>();
            allWarnings.addAll(pdm.getWarningManager().getAllWarnings());

            for (Warning warning : allWarnings) {
                if (warning.getPlayer() == player.getUniqueId()) {
                    pdm.deleteWarning(warning);
                }
            }

            sender.sendMessage("Success! Cleared player's warnings!");
        } else {
            sender.sendMessage(pdm.getWarningManager().warningPrefix + getProperUse());
        }
    }
}
