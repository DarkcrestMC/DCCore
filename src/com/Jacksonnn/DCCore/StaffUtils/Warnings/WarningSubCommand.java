package com.Jacksonnn.DCCore.StaffUtils.Warnings;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface WarningSubCommand {
    String getName();

    List<String> getAliases();

    String getProperUse();

    String getDescription();

    void execute(CommandSender sender, List<String> args);
}
