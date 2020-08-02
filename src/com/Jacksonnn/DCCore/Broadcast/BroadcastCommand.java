package com.Jacksonnn.DCCore.Broadcast;

import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		String message = GeneralMethods.translateColorCodes(StringUtils.join(args, ' '));
		
		if (sender instanceof Player) {
			if (sender.hasPermission("DCCore.broadcast")) {
				if (args.length == 0) {
					sender.sendMessage(GeneralMethods.prefix + "Please type message: /broadcast <message>");
				} else {
					Bukkit.broadcastMessage(GeneralMethods.prefix + ChatColor.of("#81E500") + message + ChatColor.of("#81E500") + " -" + sender.getName());
				}
			} else {
				sender.sendMessage(GeneralMethods.errorPrefix + "Insufficient permission!");
			}
		} else {
			if (args.length == 0) {
				sender.sendMessage(GeneralMethods.errorPrefix + "Please type message: /broadcast <message>");
			} else {
				Bukkit.broadcastMessage(GeneralMethods.errorPrefix + message + ChatColor.of("#81e500") + " -Console");
			}
		}
		return true;
	}
}


