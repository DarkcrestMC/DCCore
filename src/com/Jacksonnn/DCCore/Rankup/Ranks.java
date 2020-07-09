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
        sender.sendMessage(ChatColor.DARK_BLUE + "Guest " + ChatColor.WHITE + "-> " + ChatColor.GOLD + "Member " + ChatColor.WHITE + "- Complete Rules Check (Forced GUI)");
        sender.sendMessage(ChatColor.GOLD + "Member " + ChatColor.WHITE + "-> " + ChatColor.AQUA + "Citizen " + ChatColor.WHITE + "- 10 hours & 5,000 coins");
        sender.sendMessage(ChatColor.AQUA + "Citizen " + ChatColor.WHITE + "-> " + ChatColor.GREEN + "Merchant " + ChatColor.WHITE + "- 20 hours & 50,000 coins");
        sender.sendMessage(ChatColor.GREEN + "Merchant " + ChatColor.WHITE + "-> " + ChatColor.DARK_AQUA + "Baron " + ChatColor.WHITE + "- 30 hours & 500,000 coins");
        sender.sendMessage(ChatColor.DARK_AQUA + "Baron " + ChatColor.WHITE + "-> " + ChatColor.RED + "Official " + ChatColor.WHITE + "- 40 hours & 5,000,000 coins");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.RED + "Official " + ChatColor.WHITE + "-> " + ChatColor.YELLOW + "N" + ChatColor.GOLD + "o" + ChatColor.YELLOW + "b" + ChatColor.GOLD + "l" + ChatColor.YELLOW + "e " + ChatColor.WHITE + "- 60 hours & 50,000,000 coins");
        sender.sendMessage(ChatColor.YELLOW + "N" + ChatColor.GOLD + "o" + ChatColor.YELLOW + "b" + ChatColor.GOLD + "l" + ChatColor.YELLOW + "e " + ChatColor.WHITE + "-> " + ChatColor.LIGHT_PURPLE + "R" + ChatColor.DARK_PURPLE + "o" + ChatColor.LIGHT_PURPLE + "y" + ChatColor.DARK_PURPLE + "a" + ChatColor.LIGHT_PURPLE + "l " + ChatColor.WHITE + "- 75 hours & 500,000,000 coins");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.DARK_PURPLE + "Avatar " + ChatColor.WHITE + "- 60 hours & 225,000,000 coins");
        sender.sendMessage(ChatColor.AQUA + "LightSpirit* " + ChatColor.WHITE + "- 30 hours & 500,000 coins");
        sender.sendMessage(ChatColor.BLUE + "DarkSpirit* " + ChatColor.WHITE + "- 30 hours & 500,000 coins");
        sender.sendMessage("*Once you get one, you cannot switch, you must have 30 additional hours (60 total hrs) with 1,000,000 coins.");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GRAY + "Player " + ChatColor.WHITE + "-> " + ChatColor.BLUE + "Artist " + ChatColor.WHITE + "- Apply on the forums! http://darkcrestmc.net");
        sender.sendMessage(ChatColor.GRAY + "Player " + ChatColor.WHITE + "-> " + ChatColor.DARK_RED + "Staff " + ChatColor.WHITE + "- Apply on the forums! http://darkcrestmc.net");
        return true;
    }
}
