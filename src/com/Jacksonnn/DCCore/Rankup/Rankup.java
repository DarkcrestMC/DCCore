package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Rankup implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {
        if (!(sender instanceof Player))
            return true;
        rankupCheck((Player)sender);
        return true;
    }

    public void rankupCheck(Player player) {
        if (isPlayerInGroup(player, new String[] {"JMod", "Artist", "Architect", "Moderator", "Manager", "Co-Owner", "Owner"}))
            player.sendMessage(GeneralMethods.prefix + "Silly Goose! You're staff. Don't stress about ranking up, just user your sooper cule powerz on those peon players.");
        else if (isPlayerInGroup(player, "Ancient"))
            player.sendMessage(GeneralMethods.prefix + "Thank you for your time as a staff member! Don't stress about ranking up, just user your sooper cule powerz on those peon players.");
        // highest rank .sendMessage(GeneralMethods.serverPrefix + "Congratulations! You have surpassed all those peons with the lower ranks and achieved the highest player ranks possible. Have you considered donating or applying for staff?");
        else if (isPlayerInGroup(player, "Guest"))
            guestCheck(player, 1);
        else {
            FileConfiguration config = ConfigManager.defaultConfig.get();
            List<String> groupNames = config.getStringList("Rankup.Names.Ranks");
            List<Integer> groupPrices = config.getIntegerList("Rankup.Prices.Ranks");
            List<Integer> groupHours = config.getIntegerList("Rankup.Hours.Ranks");
            if (groupNames.size() != groupPrices.size() || groupNames.size() != groupHours.size()) {
                player.sendMessage(GeneralMethods.errorPrefix + "The rankup config is not setup correctly." +
                        "The Rankup.Names.Ranks config, Rankup.Prices.Ranks config, and Rankup.Hours.Ranks config have different sizes." +
                        groupNames.size() + ", " + groupPrices.size() + ", " + groupHours.size());
                return;
            }
            for (int i = 0; i < groupNames.size(); i++) {
                if (isPlayerInGroup(player, groupNames.get(i))) {
                    if (i == groupNames.size() - 1)
                        player.sendMessage(GeneralMethods.prefix + "Thank you for donating for the server, if you would like to gain a higher rank, please donate or apply for staff. Everyone has a fair chance when applying for staff, so good luck~");
                    else
                        attemptRankup(player, groupNames.get(i), groupNames.get(i+1),
                                groupPrices.get(i+1), groupHours.get(i+1));
                    return;
                }
            }
            player.sendMessage(GeneralMethods.errorPrefix + "You are not in a group! You have been added to the Guest group so you can continue. "
                    + ChatColor.BOLD + ChatColor.UNDERLINE + "Contact staff immediately!");
            DCCore.permissions.playerAddGroup(player, "Guest");
        }
    }

    public static void attemptRankup(Player player, String oldGroup, String newGroup, double price, double hoursNeeded) {

        double curBalance = DCCore.economy.getBalance(player);
        if (curBalance < price) {
            player.sendMessage(String.format(GeneralMethods.errorPrefix + "You do not have sufficient funds! You need " +
                    "%.2f more coins to rankup.", price - curBalance));
            return;
        }
        double curHours = PlayTime.getPlayTimeHours(player);
        if (curHours < hoursNeeded) {
            player.sendMessage(String.format(GeneralMethods.errorPrefix + "You need to wait %.2f before you can rankup!",
                    hoursNeeded - curHours));
            return;
        }
        DCCore.economy.withdrawPlayer(player, price);
        DCCore.permissions.playerRemoveGroup(player, oldGroup);
        DCCore.permissions.playerAddGroup(player, newGroup);
        Bukkit.broadcastMessage(GeneralMethods.prefix + "Congratulations, " + player.getName() +
                ", on ranking up from " + oldGroup + " to " + newGroup + "!");
    }

    private static boolean isPlayerInGroup(Player player, String group) {
        return DCCore.permissions.playerInGroup(player, group);
    }
    private static boolean isPlayerInGroup(Player player, String[] groups) {
        for (String group : groups)
            if (isPlayerInGroup(player, group))
                return true;
        return false;
    }

    public static void guestCheck(Player player, int questionNum) {
        if (questionNum == 1) {
            newQuestion(player, "01. I have read the rules and agree to them.", true);
        /*
        if (questionNum == 1) {
            newQuestion(player, "01. Spamming chat is allowed.", false);
        } else if (questionNum == 2) {
            newQuestion(player, "02. There is no griefing or raiding.", true);
        } else if (questionNum == 3) {
            newQuestion(player, "03. You may swear in chat.", true);
        } else if (questionNum == 4) {
            newQuestion(player, "04. You may use exploits until discovered by staff.", false);
        } else if (questionNum == 5) {
            newQuestion(player, "05. You are allowed to use mods (hacks).", false);
        } else if (questionNum == 6) {
            newQuestion(player, "06. Racial slurs are allowed only if they're funny.", false);
        } else if (questionNum == 7) {
            newQuestion(player, "07. Using all caps excessively is allowed.", false);
        } else if (questionNum == 8) {
            newQuestion(player, "08. Talking back to staff results in disciplinary action.", true);
        } else if (questionNum == 9) {
            newQuestion(player, "09. Advertising is not allowed.", true);
        } else if (questionNum == 10) {
            newQuestion(player, "10. You can talk like you are attempting self-harm.", false);
        } else if (questionNum == 11) {
            newQuestion(player, "11. You may use your donor perks to give people items.", false);
        } else if (questionNum == 12) {
            newQuestion(player, "12. Asking for staff is prohibited.", true);
        } else if (questionNum == 13) {
            newQuestion(player, "13. There is not allowed to be any fun on this server.", false);
            */
        }
    }

    private static void newQuestion(Player player, String question, boolean answer) {
        Inventory i = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "DarkcrestMC Vibe Check");

        ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName("");
        empty.setItemMeta(emptyMeta);

        ItemStack questionItem = new ItemStack(Material.END_CRYSTAL, 1);
        ItemMeta questionMeta = questionItem.getItemMeta();
        questionMeta.setDisplayName(ChatColor.GOLD + question);
        questionItem.setItemMeta(questionMeta);

        ItemStack trueAnswer = new ItemStack(Material.GREEN_CONCRETE, 1);
        ItemMeta trueAnswerMeta = trueAnswer.getItemMeta();
        trueAnswerMeta.setDisplayName(ChatColor.GREEN + "True!");
        if (answer) {
            trueAnswerMeta.setLocalizedName("correct");
        }
        trueAnswer.setItemMeta(trueAnswerMeta);

        ItemStack falseAnswer = new ItemStack(Material.RED_CONCRETE, 1);
        ItemMeta falseAnswerMeta = falseAnswer.getItemMeta();
        falseAnswerMeta.setDisplayName(ChatColor.RED + "False!");
        if (!answer) {
            falseAnswerMeta.setLocalizedName("correct");
        }
        falseAnswer.setItemMeta(falseAnswerMeta);

        i.setItem(0, empty);
        i.setItem(1, questionItem);
        i.setItem(2, empty);
        i.setItem(3, empty);
        i.setItem(4, empty);
        i.setItem(5, empty);
        i.setItem(6, trueAnswer);
        i.setItem(7, empty);
        i.setItem(8, falseAnswer);

        player.openInventory(i);
    }

}

