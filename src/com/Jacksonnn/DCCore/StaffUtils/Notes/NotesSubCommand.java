package com.Jacksonnn.DCCore.StaffUtils.Notes;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface NotesSubCommand {
    String getName();

    List<String> getAliases();

    String getProperUse();

    String getDescription();

    void execute(CommandSender sender, List<String> args);
}
