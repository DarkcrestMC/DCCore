package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Vote implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Go to the following link to vote for our server! http://darkcrestmc.net/mc-vote");
        return true;
    }
}
