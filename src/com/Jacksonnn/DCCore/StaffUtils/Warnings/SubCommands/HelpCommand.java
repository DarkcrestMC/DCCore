package com.Jacksonnn.DCCore.StaffUtils.Warnings.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.WarningSubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements WarningSubCommand {
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public HelpCommand(DCCore dccore) {
        plugin = dccore;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("?");
        aliases.add("wtf");
        aliases.add("whatthefuck");
        aliases.add("??");
        aliases.add("???");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/warnings help";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Warnings.CommandDescriptions.Help");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        pdm.getWarningManager().getHelp(sender);
    }
}
