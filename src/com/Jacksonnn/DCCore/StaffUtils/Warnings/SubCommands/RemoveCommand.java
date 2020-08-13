package com.Jacksonnn.DCCore.StaffUtils.Warnings.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.Warning;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.WarningSubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class RemoveCommand implements WarningSubCommand {
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public RemoveCommand(DCCore plugin) {
        this.plugin = plugin;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("delete");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/warning remove <id>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Warnings.CommandDescriptions.Remove");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() == 1) {
            try {
                int id = Integer.valueOf(args.get(0));
                Warning warning = pdm.getWarningManager().getWarning(id);

                pdm.deleteWarning(warning);
                GeneralMethods.getDCPlayer(warning.getPlayer()).removeWarning(warning);

            } catch (Exception e) {
                sender.sendMessage(pdm.getWarningManager().warningPrefix + "Something went wrong... did you enter the correct ID?");
                return;
            }

            sender.sendMessage(pdm.getWarningManager().warningPrefix + "Success! Deleted warning!");
        } else {
            sender.sendMessage(pdm.getWarningManager().warningPrefix + getProperUse());
        }
    }
}
