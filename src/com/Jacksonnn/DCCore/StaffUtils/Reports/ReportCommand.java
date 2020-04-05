package com.Jacksonnn.DCCore.StaffUtils.Reports;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Reports.SubCommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ReportCommand implements CommandExecutor {
    private List<ReportSubCommand> subCommands = new ArrayList<>();
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public ReportCommand(DCCore dcCore) {
        this.plugin = dcCore;
        registerSubCommand();
        pdm = plugin.getPDM();
    }

    private void registerSubCommand() {
        subCommands.add(new HelpCommand(plugin));
        subCommands.add(new AddCommand(plugin));
        subCommands.add(new RemoveCommand(plugin));
        subCommands.add(new ClearCommand(plugin));
        subCommands.add(new ListCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("DCCore.ReportStaff")) {
            if (args.length >= 1) {
                for (ReportSubCommand subCommand : subCommands) {
                    if (subCommand.getAliases().contains(args[0]) || subCommand.getName().equalsIgnoreCase(args[0])) {
                        subCommand.execute(sender, buildArguments(args));
                        return true;
                    }
                }
                pdm.getReportManager().getHelp(sender);
            } else {
                pdm.getReportManager().getHelp(sender);
            }
        } else {
            sender.sendMessage(pdm.getReportManager().reportsPrefix + "Insufficient permission to execute these commands.");
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
