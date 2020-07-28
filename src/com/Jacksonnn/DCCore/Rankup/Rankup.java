package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Bukkit.getServer;

public class Rankup implements CommandExecutor, Listener {

    private static Economy econ = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
    public static boolean guestRankup = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String playerName = sender.getName();
        Player player = sender.getServer().getPlayer(playerName);
        long playTime = PlayTime.getPlayTime(player);
        rankupCheck(player, sender, playTime);
        return true;
    }

    public void rankupCheck(Player player, CommandSender sender, long playTime) {
        long tenHours = 36000000;
        long twentyHours = 72000000;
        long thirtyHours = 108000000;
        long fourtyHours = 144000000;
        long sixtyHours = 216000000;
        long seventyFiveHours = 270000000;

        if (DCCore.permissions.playerInGroup(player, "JMod") || DCCore.permissions.playerInGroup(player, "Artist") || DCCore.permissions.playerInGroup(player, "Architect") || DCCore.permissions.playerInGroup(player, "Moderator") || DCCore.permissions.playerInGroup(player, "Manager") || DCCore.permissions.playerInGroup(player, "Co-Owner") || DCCore.permissions.playerInGroup(player, "Owner")) { //STAFF GROUP RANKUP
            sender.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Silly Goose! You're staff. Don't stress about ranking up, just user your sooper cule powerz on those peon players.");
        }  else if (DCCore.permissions.playerInGroup(player, "Ancient")) { //RETIRED STAFF RANKUP
            sender.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Thank you for your time as a staff member! Don't stress about ranking up, just user your sooper cule powerz on those peon players.");
        } else if (DCCore.permissions.playerInGroup(player, "Noble") || DCCore.permissions.playerInGroup(player, "Royal")) { //DONOR GROUPS RANKUP
            sender.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Thank you for donating for the server, if you would like to gain a higher rank, please donate or apply for staff. Everyone has a fair chance when applying for staff, so good luck~");
        } else if (DCCore.permissions.playerInGroup(player, "Official")) { //OFFICIAL
            sender.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Congratulations! You have surpassed all those peons with the lower ranks and achieved the highest player ranks possible. Have you considered donating or applying for staff?");
        } else if (DCCore.permissions.playerInGroup(player, "Baron")) { //BARON GROUP RANKUP
            if (fourtyHours - playTime <= 0) {
                if (econ.has(player, 5000000)) {
                    DCCore.permissions.playerRemoveGroup(player, "Baron");
                    DCCore.permissions.playerAddGroup(player, "Official");
                    try {
                        DCCore.economy.withdrawPlayer(player, 5000000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Bukkit.broadcastMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Congratulations, " + player.getName() + ", on becoming an Official! -Console");
                    player.sendMessage(GeneralMethods.successColor + ChatColor.of("#E5B100") + "Congratulations on achieving the Official rank!");
                } else {
                    try {
                        sender.sendMessage(GeneralMethods.errorColor + ChatColor.of("#E5B100") + "You do not have sufficient funds! You need " + (5000000 - DCCore.economy.getBalance(player)) + " more coins to rankup.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + ChatColor.of("#E5B100") + "You need to wait " + GeneralMethods.milliToHours(fourtyHours - playTime) + " before you can rankup.");
            }
        } else if (DCCore.permissions.playerInGroup(player, "Merchant")) { //MERCHANT GROUP RANKUP
            if (thirtyHours - playTime <= 0) {
                if (econ.has(player, 500000)) {
                    DCCore.permissions.playerRemoveGroup(player, "Merchant");
                    DCCore.permissions.playerAddGroup(player, "Baron");
                    try {
                        DCCore.economy.withdrawPlayer(player, 500000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Bukkit.broadcastMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Congratulations, " + player.getName() + ", on becoming a Baron! -Console");
                    player.sendMessage(GeneralMethods.successColor + ChatColor.of("#E5B100") + "Congratulations on achieving the Baron rank!");
                } else {
                    try {
                        sender.sendMessage(GeneralMethods.errorColor + ChatColor.of("#E5B100") + "You do not have sufficient funds! You need " + (500000 - DCCore.economy.getBalance(player)) + " more coins to rankup.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + ChatColor.of("#E5B100") + "You need to wait " + GeneralMethods.milliToHours(thirtyHours - playTime) + " before you can rankup.");
            }
        } else if (DCCore.permissions.playerInGroup(player, "Citizen")) { //CITIZEN GROUP RANKUP
            if (twentyHours - playTime <= 0) {
                if (econ.has(player, 50000)) {
                    DCCore.permissions.playerRemoveGroup(player, "Citizen");
                    DCCore.permissions.playerAddGroup(player, "Merchant");
                    try {
                        DCCore.economy.withdrawPlayer(player, 50000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Bukkit.broadcastMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Congratulations, " + player.getName() + ", on becoming a Merchant! -Console");
                    player.sendMessage(GeneralMethods.successColor + ChatColor.of("#E5B100") + "Congratulations on achieving the Merchant rank!");
                } else {
                    try {
                        sender.sendMessage(GeneralMethods.errorColor + ChatColor.of("#E5B100") + "You do not have sufficient funds! You need " + (50000 - DCCore.economy.getBalance(player)) + " more coins to rankup.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + ChatColor.of("#E5B100") + "You need to wait " + GeneralMethods.milliToHours(twentyHours - playTime) + " before you can rankup.");
            }
        } else if (DCCore.permissions.playerInGroup(player, "Member")) { //MEMBER GROUP RANKUP
            if (tenHours - playTime <= 0) {
                if (econ.has(player, 5000)) {
                    DCCore.permissions.playerRemoveGroup(player, "Member");
                    DCCore.permissions.playerAddGroup(player, "Citizen");
                    try {
                        DCCore.economy.withdrawPlayer(player, 5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Bukkit.broadcastMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Congratulations, " + player.getName() + ", on becoming a Citizen! -Console");
                    player.sendMessage(GeneralMethods.successColor + ChatColor.of("#E5B100") + "Congratulations on achieving the Citizen rank!");
                } else {
                    try {
                        sender.sendMessage(GeneralMethods.errorColor + ChatColor.of("#E5B100") + "You do not have sufficient funds! You need " + (5000 - DCCore.economy.getBalance(player)) + " more coins to rankup.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + ChatColor.of("#E5B100") + "You need to wait " + GeneralMethods.milliToHours(tenHours - playTime) + " before you can rankup.");
            }
        } else if (DCCore.permissions.playerInGroup(player, "Guest")) { //GUEST GROUP RANKUP
            guestCheck(Bukkit.getPlayer(sender.getName()), 1);
        } else { //NO GROUP RANKUP
            sender.sendMessage(GeneralMethods.prefix + ChatColor.of("#E5B100") + "Wtf, are you even a player?!?! Contact staff immediately!");
        }
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

