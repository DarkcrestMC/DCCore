package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Website implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(GeneralMethods.prefix + "Go to the following link to access our website! http://darkcrestmc.net/");
        return true;
    }
}
