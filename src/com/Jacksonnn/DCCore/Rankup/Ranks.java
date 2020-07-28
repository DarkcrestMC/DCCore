package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Ranks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        FileConfiguration config = ConfigManager.defaultConfig.get();
        /*
                Guest -> Member - Complete Rules Check (Forced GUI)
                Member -> Citizen - 10 hours & some amount of money
                Citizen -> Merchant - 30 hours & some amount of money
                Merchant -> Baron - 60 hours & some amount of money
                Baron -> Official - 100 hours & some amount of money
         */
        sender.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Here are is all of the ranking information!");
        sender.sendMessage(ChatColor.DARK_BLUE + "Guest " + ChatColor.WHITE + "-> " + ChatColor.GOLD + "Member " + ChatColor.WHITE + "- Complete Rules Check (Forced GUI)");
        sender.sendMessage(ChatColor.GOLD + "Member " + ChatColor.WHITE + "-> " + ChatColor.AQUA + "Citizen " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Citizen"), config.getInt("Rankup.Prices.Citizen")));
        sender.sendMessage(ChatColor.AQUA + "Citizen " + ChatColor.WHITE + "-> " + ChatColor.GREEN + "Merchant " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Merchant"), config.getInt("Rankup.Prices.Merchant")));
        sender.sendMessage(ChatColor.GREEN + "Merchant " + ChatColor.WHITE + "-> " + ChatColor.DARK_AQUA + "Baron " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Baron"), config.getInt("Rankup.Prices.Baron")));
        sender.sendMessage(ChatColor.DARK_AQUA + "Baron " + ChatColor.WHITE + "-> " + ChatColor.RED + "Official " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Official"), config.getInt("Rankup.Prices.Official")));
        sender.sendMessage("");
        sender.sendMessage(ChatColor.RED + "Official " + ChatColor.WHITE + "-> " + ChatColor.YELLOW + "N" + ChatColor.GOLD + "o" + ChatColor.YELLOW + "b" + ChatColor.GOLD + "l" + ChatColor.YELLOW + "e " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Noble"), config.getInt("Rankup.Prices.Noble")));
        sender.sendMessage(ChatColor.YELLOW + "N" + ChatColor.GOLD + "o" + ChatColor.YELLOW + "b" + ChatColor.GOLD + "l" + ChatColor.YELLOW + "e " + ChatColor.WHITE + "-> " + ChatColor.LIGHT_PURPLE + "R" + ChatColor.DARK_PURPLE + "o" + ChatColor.LIGHT_PURPLE + "y" + ChatColor.DARK_PURPLE + "a" + ChatColor.LIGHT_PURPLE + "l " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Royal"), config.getInt("Rankup.Prices.Royal")));
        sender.sendMessage("");
        sender.sendMessage(ChatColor.DARK_PURPLE + "Avatar " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Avatar"), config.getInt("Rankup.Prices.Avatar")));
        sender.sendMessage(ChatColor.AQUA + "LightSpirit* " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.LightSpirit"), config.getInt("Rankup.Prices.LightSpirit")));
        sender.sendMessage(ChatColor.BLUE + "DarkSpirit* " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.DarkSpirit"), config.getInt("Rankup.Prices.DarkSpirit")));
        sender.sendMessage("*Once you get one, you cannot switch, you must have 30 additional hours (60 total hrs) with 1,000,000 coins.");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GRAY + "Player " + ChatColor.WHITE + "-> " + ChatColor.BLUE + "Artist " + ChatColor.WHITE + "- Apply on the forums! http://darkcrestmc.net");
        sender.sendMessage(ChatColor.GRAY + "Player " + ChatColor.WHITE + "-> " + ChatColor.DARK_RED + "Staff " + ChatColor.WHITE + "- Apply on the forums! http://darkcrestmc.net");
        return true;
    }
}
