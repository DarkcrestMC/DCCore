package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BendingHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(GeneralMethods.serverPrefix + "Bending Introduction:");
        sender.sendMessage(ChatColor.YELLOW + "DarkcrestMC uses the " + ChatColor.BOLD + "ProjectKorra" + ChatColor.YELLOW + " plugin to allow for A:TLA and A:LOK in-game play. As a " + ChatColor.ITALIC + "member" + ChatColor.YELLOW + ", you can choose between the elements (" + ChatColor.GRAY + "air" + ChatColor.YELLOW + ", " + ChatColor.AQUA + "water" + ChatColor.YELLOW + ", " + ChatColor.GREEN + "earth" + ChatColor.YELLOW + ", " + ChatColor.RED + "fire" + ChatColor.YELLOW + ", and " + ChatColor.GOLD + "chi" + ChatColor.YELLOW + ").");
        sender.sendMessage(ChatColor.YELLOW + "When you become a " + ChatColor.WHITE + "[" + ChatColor.GREEN + "Merchant" + ChatColor.WHITE + "]" + ChatColor.YELLOW + " rank or higher, you can also start using the " + ChatColor.DARK_AQUA + "Spirit" + ChatColor.YELLOW + " element.");
        sender.sendMessage(ChatColor.YELLOW + "On DarkcrestMC, you are also able to donate for " + ChatColor.DARK_PURPLE + "Avatar" + ChatColor.YELLOW + "! In order to do so, type " + ChatColor.BOLD + "/donate" + ChatColor.YELLOW + ". You can also obtain it as a regular player according to " + ChatColor.BOLD + "/ranks" + ChatColor.YELLOW + ".");
        sender.sendMessage(ChatColor.YELLOW + "In order to use the " + ChatColor.ITALIC + "SpiritTypes" + ChatColor.YELLOW + ", it works the same as avatar. You can donate for either " + ChatColor.WHITE + "LightSpirit" + ChatColor.YELLOW + " or " + ChatColor.DARK_GRAY + "DarkSpirit" + ChatColor.YELLOW + " through " + ChatColor.BOLD + "/donate" + ChatColor.YELLOW + ". Otherwise, you could obtain it according to " + ChatColor.BOLD + "/ranks" + ChatColor.YELLOW + ".");
        return true;
    }
}
