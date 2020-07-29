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
		
		String message = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(StringUtils.join(args, ' ')));
		
		if (sender instanceof Player) {
			
			String player = sender.getName();
			
			if (sender.hasPermission("DCCore.broadcast")) {
				if (args.length == 0) {
					sender.sendMessage(GeneralMethods.errorColor + "Please type message: /broadcast <message>");
				} else {
					Bukkit.broadcastMessage(GeneralMethods.prefix + ChatColor.of("#81E500") + message + ChatColor.of("#81E500") + " -" + player);
				}
			} else {
				sender.sendMessage(GeneralMethods.errorColor + "Insufficient permission!");
			}
		} else {
			if (args.length == 0) {
				sender.sendMessage(GeneralMethods.errorColor + "Please type message: /broadcast <message>");
			} else {
				Bukkit.broadcastMessage(GeneralMethods.prefix + ChatColor.of("#81E500") + message + ChatColor.of("#81E500") + " -Console");
			}
		}
		return true;
	}
}


