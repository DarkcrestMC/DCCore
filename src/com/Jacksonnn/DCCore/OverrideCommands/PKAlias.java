package com.Jacksonnn.DCCore.OverrideCommands;

import com.projectkorra.projectkorra.command.PKCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PKAlias implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            final List<String> sendingArgs = Arrays.asList(args).subList(1, args.length);
            for (final PKCommand command : PKCommand.instances.values()) {
                if (Arrays.asList(command.getAliases()).contains(args[0].toLowerCase())) {
                    command.execute(sender, sendingArgs);
                    return true;
                }
            }
        }

        PKCommand.instances.get("help").execute(sender, new ArrayList<String>());
        return true;
    }
}
