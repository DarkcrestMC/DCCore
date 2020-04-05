package com.Jacksonnn.DCCore.StaffUtils.Warnings;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.SubCommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class WarningCommand implements CommandExecutor {
    private List<WarningSubCommand> subCommands = new ArrayList<>();
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public WarningCommand(DCCore dcCore) {
        this.plugin = dcCore;
        registerSubCommand();
        pdm = plugin.getPDM();
    }

    private void registerSubCommand() {
        subCommands.add(new HelpCommand(plugin));
        subCommands.add(new AddCommand(plugin));
        subCommands.add(new RemoveCommand(plugin));
        subCommands.add(new ListCommand(plugin));
        subCommands.add(new ClearCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("DCCore.WarningStaff")) {
            if (args.length >= 1) {
                for (WarningSubCommand subCommand : subCommands) {
                    if (subCommand.getAliases().contains(args[0]) || subCommand.getName().equalsIgnoreCase(args[0])) {
                        subCommand.execute(sender, buildArguments(args));
                        return true;
                    }
                }
                pdm.getWarningManager().getHelp(sender);
            } else {
                pdm.getWarningManager().getHelp(sender);
            }
        } else {
            sender.sendMessage(pdm.getWarningManager().warningPrefix + "Insufficient permission to execute these commands.");
        }
        return true;
    }

    private List<String> buildArguments(String[] args) {
        List<String> bArgs = new ArrayList<>();
        int i = 0;
        for (String arg : args) {
            if (i == 0) {

            } else {
                bArgs.add(arg);
            }
            i++;
        }
        return bArgs;
    }
}
