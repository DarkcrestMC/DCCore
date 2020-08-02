package com.Jacksonnn.DCCore.OverrideCommands;

import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BendingHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(GeneralMethods.prefix + "Bending Introduction:");
        sender.sendMessage(ChatColor.of("#E5B100") + "DarkcrestMC uses the " + GeneralMethods.accentColor + ChatColor.BOLD + "ProjectKorra" + ChatColor.of("#E5B100") + " plugin to allow for A:TLA and A:LOK in-game play. As a " + ChatColor.ITALIC + "member" + ChatColor.of("#E5B100") + ", you can choose between the elements (" + ChatColor.GRAY + "air" + ChatColor.of("#E5B100") + ", " + ChatColor.AQUA + "water" + ChatColor.of("#E5B100") + ", " + ChatColor.GREEN + "earth" + ChatColor.of("#E5B100") + ", " + ChatColor.RED + "fire" + ChatColor.of("#E5B100") + ", and " + ChatColor.GOLD + "chi" + ChatColor.of("#E5B100") + ").");
        sender.sendMessage(ChatColor.of("#E5B100") + "When you become a " + ChatColor.of("#62636e") + "[" + ChatColor.of("#c018d6") + "Merchant" + ChatColor.of("#62636e") + "]" + ChatColor.of("#E5B100") + " rank or higher, you can also start using the " + ChatColor.DARK_AQUA + "Spirit" + ChatColor.of("#E5B100") + " element.");
        sender.sendMessage(ChatColor.of("#E5B100") + "On DarkcrestMC, you are also able to donate for " + ChatColor.of("#AA00E5") + "Avatar" + ChatColor.of("#E5B100") + "! In order to do so, type " + GeneralMethods.accentColor + ChatColor.BOLD + "/donate" + ChatColor.of("#E5B100") + ". You can also obtain it as a regular player according to " + GeneralMethods.accentColor + ChatColor.BOLD + "/ranks" + ChatColor.of("#E5B100") + ".");
        sender.sendMessage(ChatColor.of("#E5B100") + "In order to use the " + ChatColor.ITALIC + "SpiritTypes" + ChatColor.of("#E5B100") + ", it works the same as avatar. You can donate for either " + ChatColor.WHITE + "LightSpirit" + ChatColor.of("#E5B100") + " or " + ChatColor.DARK_GRAY + "DarkSpirit" + ChatColor.of("#E5B100") + " through " + GeneralMethods.accentColor + ChatColor.BOLD + "/donate" + ChatColor.of("#E5B100") + ". Otherwise, you could obtain it according to " + GeneralMethods.accentColor + ChatColor.BOLD + "/ranks" + ChatColor.of("#E5B100") + ".");
        return true;
    }
}
