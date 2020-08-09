package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Ranks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {
        FileConfiguration config = ConfigManager.defaultConfig.get();
        sender.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Here are is all of the ranking information!");
        sender.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Use " + ChatColor.AQUA + ChatColor.UNDERLINE +
                "/warp RankInfo" + ChatColor.RESET + ChatColor.of("#E5B100") + " to learn about each rank!");
//        sender.sendMessage(ChatColor.DARK_BLUE + "Guest " + ChatColor.WHITE + "-> " + ChatColor.GOLD + "Member " + ChatColor.WHITE + "- Complete Rules Check (Forced GUI)");
//        sender.sendMessage(ChatColor.GOLD + "Member " + ChatColor.WHITE + "-> " + ChatColor.AQUA + "Citizen " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Citizen"), config.getInt("Rankup.Prices.Citizen")));
//        sender.sendMessage(ChatColor.AQUA + "Citizen " + ChatColor.WHITE + "-> " + ChatColor.GREEN + "Merchant " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Merchant"), config.getInt("Rankup.Prices.Merchant")));
//        sender.sendMessage(ChatColor.GREEN + "Merchant " + ChatColor.WHITE + "-> " + ChatColor.DARK_AQUA + "Baron " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Baron"), config.getInt("Rankup.Prices.Baron")));
//        sender.sendMessage(ChatColor.DARK_AQUA + "Baron " + ChatColor.WHITE + "-> " + ChatColor.RED + "Official " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Official"), config.getInt("Rankup.Prices.Official")));
//        sender.sendMessage("");
//        sender.sendMessage(ChatColor.RED + "Official " + ChatColor.WHITE + "-> " + ChatColor.YELLOW + "N" + ChatColor.GOLD + "o" + ChatColor.YELLOW + "b" + ChatColor.GOLD + "l" + ChatColor.YELLOW + "e " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Noble"), config.getInt("Rankup.Prices.Noble")));
//        sender.sendMessage(ChatColor.YELLOW + "N" + ChatColor.GOLD + "o" + ChatColor.YELLOW + "b" + ChatColor.GOLD + "l" + ChatColor.YELLOW + "e " + ChatColor.WHITE + "-> " + ChatColor.LIGHT_PURPLE + "R" + ChatColor.DARK_PURPLE + "o" + ChatColor.LIGHT_PURPLE + "y" + ChatColor.DARK_PURPLE + "a" + ChatColor.LIGHT_PURPLE + "l " + ChatColor.WHITE + String.format("- %s hours & %s coins", config.getInt("Rankup.Hours.Royal"), config.getInt("Rankup.Prices.Royal")));
//        sender.sendMessage("");
        List<String> groupNames = ConfigManager.defaultConfig.get().getStringList("Rankup.Names.Ranks");
        List<Integer> groupPrices = ConfigManager.defaultConfig.get().getIntegerList("Rankup.Prices.Ranks");
        List<Integer> groupHours = ConfigManager.defaultConfig.get().getIntegerList("Rankup.Hours.Ranks");

        for (int i = 0; i < groupNames.size(); i++) {
            String oldGroup;
            String newGroup;
            int price;
            int hours;
            if (i == 0) {
                oldGroup = "Guest";
                newGroup = groupNames.get(0);
                price = -1;
                hours = -1;
            } else {
                oldGroup = groupNames.get(i-1);
                newGroup = groupNames.get(i);
                price = groupPrices.get(i);
                hours = groupHours.get(i);
            }
            if (i == groupNames.size()-2) {
                // if the second to last rank, i.e. first donor rank, which is noble
                sender.sendMessage("");
                sendRankInfo(sender, ChatColor.YELLOW.toString(), oldGroup, ChatColor.YELLOW.toString(), newGroup, ChatColor.GOLD.toString(), hours, price);
            } else if (i == groupNames.size()-1)
                sendRankInfo(sender, ChatColor.YELLOW.toString(), oldGroup, ChatColor.GOLD.toString(), newGroup, ChatColor.DARK_PURPLE.toString(), hours, price);
            else
                sendRankInfo(sender, ChatColor.WHITE.toString(), oldGroup, ChatColor.YELLOW.toString(), newGroup, ChatColor.YELLOW.toString(), hours, price);
        }

        sender.sendMessage("");
        int lightSpiritHours = config.getInt("Rankup.Hours.LightSpirit");
        int lightSpiritPrice = config.getInt("Rankup.Prices.LightSpirit");
        int darkSpiritHours = config.getInt("Rankup.Hours.DarkSpirit");
        int darkSpiritPrice = config.getInt("Rankup.Prices.DarkSpirit");
        sendRankInfo(sender, ChatColor.YELLOW.toString(), null, null, "Avatar",
                ChatColor.DARK_PURPLE.toString(), config.getInt("Rankup.Hours.Avatar"), config.getInt("Rankup.Prices.Avatar"));
        sendRankInfo(sender, ChatColor.YELLOW.toString(), null, null, "Light Spirit" +
                ChatColor.WHITE + "*", ChatColor.AQUA.toString(), lightSpiritHours, lightSpiritPrice);
        sendRankInfo(sender, ChatColor.YELLOW.toString(), null, null, "Dark Spirit" +
                ChatColor.WHITE + "*", ChatColor.BLUE.toString(), darkSpiritHours, darkSpiritPrice);
        sender.sendMessage("*Once you get one, you cannot switch, you must have " + (lightSpiritHours) +
                " additional hours (" + (lightSpiritHours + darkSpiritHours) + " total hrs) with " +
                String.format("%,d", lightSpiritPrice + darkSpiritPrice) + " coins.");
        sender.sendMessage("");
        sendRankInfo(sender, ChatColor.GOLD.toString(), null, null, "Artist",
                ChatColor.BLUE.toString(), -2, -2);
        sendRankInfo(sender, ChatColor.GOLD.toString(), null, null, "Staff",
                ChatColor.DARK_RED.toString(), -2, -2);
        return true;
    }

    private static void sendRankInfo(CommandSender sender, String baseColor, String oldGroup, String oldGroupColor, String newGroup, String newGroupColor, int hours, int price) {
        String first;
        if (oldGroup != null)
            first = String.format("%3$s%2$s%1$s -> %5$s%4$s%1$s - ", baseColor, oldGroup, oldGroupColor, newGroup, newGroupColor);
        else
            first = String.format("%3$s%2$s%1$s - ", baseColor, newGroup, newGroupColor);
        String second;
        if (hours == -1)
            second = "Complete Rules Check (Forced GUI)";
        else if (hours == -2)
            second = "Apply on the forums! " + ChatColor.AQUA + ChatColor.UNDERLINE + ChatColor.ITALIC + "https://darkcrestmc.net";
        else
            second = String.format(ChatColor.AQUA + "%1$d " + (hours == 1 ? "hour" : "hours") + "%3$s and " + ChatColor.GREEN + "$%2$,d", hours, price, baseColor);
        sender.sendMessage(first + second);
    }
}
