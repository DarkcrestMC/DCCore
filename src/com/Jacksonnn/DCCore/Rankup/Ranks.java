package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Ranks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        /*
                Guest -> Member - Complete Rules Check (Forced GUI)
                Member -> Citizen - 10 hours & some amount of money
                Citizen -> Merchant - 30 hours & some amount of money
                Merchant -> Baron - 60 hours & some amount of money
                Baron -> Official - 100 hours & some amount of money
         */
        sender.sendMessage(GeneralMethods.serverPrefix + "Here are is all of the ranking information!");
        sender.sendMessage(ChatColor.YELLOW + "Guest -> Member - Complete Rules Check (Forced GUI)");
        sender.sendMessage(ChatColor.YELLOW + "Member -> Citizen - 10 hours & 5,000 coins");
        sender.sendMessage(ChatColor.YELLOW + "Citizen -> Merchant - 30 hours & 50,000 coins");
        sender.sendMessage(ChatColor.YELLOW + "Merchant -> Baron - 60 hours & 500,000 coins");
        sender.sendMessage(ChatColor.YELLOW + "Baron -> Official - 100 hours & 5,000,000 coins");
        return true;
    }
}
