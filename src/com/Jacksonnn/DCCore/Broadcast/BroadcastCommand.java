package com.Jacksonnn.DCCore.Broadcast;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jacksonnn.DCCore.GeneralMethods;

public class BroadcastCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		String message = StringUtils.join(args, " ");
		
		if (sender instanceof Player) {
			
			String player = sender.getName();
			
			if (sender.hasPermission("DCCore.broadcast.use")) {
				if (args.length == 0) {
					sender.sendMessage(GeneralMethods.prefix + GeneralMethods.errorColor + "Please type message: /broadcast <message>");
				} else if (args.length >= 1) {
					Bukkit.broadcastMessage(GeneralMethods.serverPrefix + message + "- " + player);
				}
			} else {
				sender.sendMessage(GeneralMethods.prefix + GeneralMethods.errorColor + "Insufficient permission!");
			}
		} else {
			if (args.length == 0) {
				sender.sendMessage(GeneralMethods.prefix + GeneralMethods.errorColor + "Please type message: /broadcast <message>");
			} else if (args.length >= 1) {
				Bukkit.broadcastMessage(GeneralMethods.serverPrefix + message + "- Console");
			}
		}
		return false;
	}
	
}
