package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.GeneralMethods;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import static org.bukkit.Bukkit.getServer;

public class Rankup implements CommandExecutor, Listener {

    private static Economy econ = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
    public static boolean guestRankup = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String playerName = sender.getName();
        Player player = sender.getServer().getPlayer(playerName);
        long playTime = 1;//PlayTime.getPlayTime(player);
        rankupCheck(player, sender, playTime);
        return true;
    }

    public void rankupCheck(Player player, CommandSender sender, long playTime) {
        PermissionUser pexUser = PermissionsEx.getUser(player);

        long tenHours = 36000000;
        long twentyHours = 72000000;
        long thirtyHours = 108000000;
        long fourtyHours = 144000000;
        long sixtyHours = 216000000;
        long seventyFiveHours = 270000000;

        if (pexUser.inGroup("JMod") || pexUser.inGroup("Artist") || pexUser.inGroup("Architect") || pexUser.inGroup("Moderator") || pexUser.inGroup("Manager") || pexUser.inGroup("Co-Owner") || pexUser.inGroup("Owner")) { //STAFF GROUP RANKUP
            sender.sendMessage(GeneralMethods.serverPrefix + "Silly Goose! You're staff. Don't stress about ranking up, just user your sooper cule powerz on those peon players.");
        }  else if (pexUser.inGroup("Ancient")) { //RETIRED STAFF RANKUP
            sender.sendMessage(GeneralMethods.serverPrefix + "Thank you for your time as a staff member! Don't stress about ranking up, just user your sooper cule powerz on those peon players.");
        } else if (pexUser.inGroup("Noble") || pexUser.inGroup("Royal")) { //DONOR GROUPS RANKUP
            sender.sendMessage(GeneralMethods.serverPrefix + "Thank you for donating for the server, if you would like to gain a higher rank, please donate or apply for staff. Everyone has a fair chance when applying for staff, so good luck~");
        } else if (pexUser.inGroup("Official")) { //OFFICIAL
            sender.sendMessage(GeneralMethods.serverPrefix + "Congratulations! You have surpassed all those peons with the lower ranks and achieved the highest player ranks possible. Have you considered donating or applying for staff?");
        } else if (pexUser.inGroup("Baron")) { //BARON GROUP RANKUP
            if (fourtyHours - playTime <= 0) {
                if (econ.has(player, 5000000)) {
                    pexUser.addGroup("Official");
                    pexUser.removeGroup("Baron");
                    econ.bankWithdraw(player.getName(), 5000000);
                    Bukkit.broadcastMessage(GeneralMethods.serverPrefix + "Congratulations, " + player.getName() + ", on becoming an Official! -Console");
                    player.sendMessage(GeneralMethods.successColor + "Congratulations on achieving the Official rank!");
                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient funds! You need " + (5000000 - econ.bankBalance(player.getName()).amount) + " more coins to rankup.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You need to wait " + GeneralMethods.milliToHours(fourtyHours - playTime) + " before you can rankup.");
            }
        } else if (pexUser.inGroup("Merchant")) { //MERCHANT GROUP RANKUP
            if (thirtyHours - playTime <= 0) {
                if (econ.has(player, 500000)) {
                    pexUser.addGroup("Baron");
                    pexUser.removeGroup("Merchant");
                    econ.bankWithdraw(player.getName(), 500000);
                    Bukkit.broadcastMessage(GeneralMethods.serverPrefix + "Congratulations, " + player.getName() + ", on becoming a Baron! -Console");
                    player.sendMessage(GeneralMethods.successColor + "Congratulations on achieving the Baron rank!");
                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient funds! You need " + (500000 - econ.bankBalance(player.getName()).amount) + " more coins to rankup.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You need to wait " + GeneralMethods.milliToHours(thirtyHours - playTime) + " before you can rankup.");
            }
        } else if (pexUser.inGroup("Citizen")) { //CITIZEN GROUP RANKUP
            if (twentyHours - playTime <= 0) {
                if (econ.has(player, 50000)) {
                    pexUser.addGroup("Merchant");
                    pexUser.removeGroup("Citizen");
                    econ.bankWithdraw(player.getName(), 50000);
                    Bukkit.broadcastMessage(GeneralMethods.serverPrefix + "Congratulations, " + player.getName() + ", on becoming a Merchant! -Console");
                    player.sendMessage(GeneralMethods.successColor + "Congratulations on achieving the Merchant rank!");
                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient funds! You need " + (50000 - econ.bankBalance(player.getName()).amount) + " more coins to rankup.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You need to wait " + GeneralMethods.milliToHours(twentyHours - playTime) + " before you can rankup.");
            }
        } else if (pexUser.inGroup("Member")) { //MEMBER GROUP RANKUP
            if (tenHours - playTime <= 0) {
                if (econ.has(player, 5000)) {
                    pexUser.addGroup("Citizen");
                    pexUser.removeGroup("Member");
                    econ.bankWithdraw(player.getName(), 5000);
                    Bukkit.broadcastMessage(GeneralMethods.serverPrefix + "Congratulations, " + player.getName() + ", on becoming a Citizen! -Console");
                    player.sendMessage(GeneralMethods.successColor + "Congratulations on achieving the Citizen rank!");
                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient funds! You need " + (5000 - econ.bankBalance(player.getName()).amount) + " more coins to rankup.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You need to wait " + GeneralMethods.milliToHours(tenHours - playTime) + " before you can rankup.");
            }
        } else if (pexUser.inGroup("Guest")) { //GUEST GROUP RANKUP
            guestCheck(Bukkit.getPlayer(sender.getName()), 1);
        } else { //NO GROUP RANKUP
            sender.sendMessage(GeneralMethods.serverPrefix + "Wtf, are you even a player?!?! Contact staff immediately!");
        }
    }

    public static void guestCheck(Player player, int questionNum) {
        if (questionNum == 1) {
            newQuestion(player, "01. Spamming chat is allowed.", false);
        } else if (questionNum == 2) {
            newQuestion(player, "02. There is no griefing or raiding.", true);
        } else if (questionNum == 3) {
            newQuestion(player, "03. You may swear in chat.", true);
        } else if (questionNum == 4) {
            newQuestion(player, "04. If you know about exploits, you should use them until they're discovered.", false);
        } else if (questionNum == 5) {
            newQuestion(player, "05. You are allowed to use mods (hacks).", false);
        } else if (questionNum == 6) {
            newQuestion(player, "06. Racial slurs are allowed only if they're funny.", false);
        } else if (questionNum == 7) {
            newQuestion(player, "07. Using all caps excessively is allowed.", false);
        } else if (questionNum == 8) {
            newQuestion(player, "08. You can talk back to staff without disciplinary action.", false);
        } else if (questionNum == 9) {
            newQuestion(player, "09. Advertising is not allowed.", true);
        } else if (questionNum == 10) {
            newQuestion(player, "10. You can talk like you are attempting self-harm.", false);
        } else if (questionNum == 11) {
            newQuestion(player, "11. You may use your donor ranks/perms to give you friends anything.", false);
        } else if (questionNum == 12) {
            newQuestion(player, "12. Asking for staff is prohibited.", true);
        } else if (questionNum == 13) {
            newQuestion(player, "13. There is not allowed to be any fun on this server.", false);
        }
    }

    private static void newQuestion(Player player, String question, boolean answer) {
        Inventory i = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "DarkcrestMC Rules Quiz");

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

