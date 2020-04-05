package com.Jacksonnn.DCCore.StaffUtils.Reports;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface ReportSubCommand {
    String getName();

    List<String> getAliases();

    String getProperUse();

    String getDescription();

    void execute(CommandSender sender, List<String> args);
}
