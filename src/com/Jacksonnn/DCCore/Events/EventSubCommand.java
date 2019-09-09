package com.Jacksonnn.DCCore.Events;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface EventSubCommand {
    String getName();

    List<String> getAliases();

    String getProperUse();

    String getDescription();

    void execute(CommandSender sender, List<String> args);
}
