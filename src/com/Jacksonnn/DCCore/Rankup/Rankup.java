package com.Jacksonnn.DCCore.Rankup;

import com.Jacksonnn.DCCore.GeneralMethods;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import static org.bukkit.Bukkit.getServer;

public class Rankup implements CommandExecutor {

    private static Economy econ = getServer().getServicesManager().getRegistration(Economy.class).getProvider();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String playerName = sender.getName();
        Player player = sender.getServer().getPlayer(playerName);
        long playTime = PlayTime.getPlayTime(player);
        rankupCheck(player, sender, playTime);
        return true;
    }

    public void rankupCheck(Player player, CommandSender sender, long playTime) {
        PermissionUser pexUser = PermissionsEx.getUser(player);

        long tenHours = 36000000;
        long thirtyHours = 108000000;
        long sixtyHours = 216000000;
        long hundredHours = 360000000;


        if (player.hasPermission("DCCore.rankup.Guest")) { //GUEST GROUP RANKUP
            //GUI SHIT
            pexUser.addGroup("Member");
            pexUser.removeGroup("Guest");
            sender.sendMessage(GeneralMethods.successColor + "You are now a Member.");
        } else if (player.hasPermission("DCCore.rankup.Member")) { //MEMBER GROUP RANKUP
            if (tenHours - playTime <= 0) {
                if (econ.has(player, 5000)) {
                    pexUser.addGroup("Citizen");
                    pexUser.removeGroup("Member");
                    econ.bankWithdraw(player.getName(), 5000);
                    sender.sendMessage(GeneralMethods.successColor + "You are now a Citizen.");
                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient funds! You need " + (5000 - econ.bankBalance(player.getName()).amount) + " more coins to rankup.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You need to wait " + GeneralMethods.milliToHours(tenHours - playTime) + " before you can rankup.");
            }
        } else if (player.hasPermission("DCCore.rankup.Citizen")) {
            if (thirtyHours - playTime <= 0) {
                if (econ.has(player, 50000)) {
                    pexUser.addGroup("Merchant");
                    pexUser.removeGroup("Citizen");
                    econ.bankWithdraw(player.getName(), 50000);
                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient funds! You need " + (50000 - econ.bankBalance(player.getName()).amount) + " more coins to rankup.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You need to wait " + GeneralMethods.milliToHours(thirtyHours - playTime) + " before you can rankup.");
            }
        } else if (player.hasPermission("DCCore.rankup.Merchant")) {
            if (sixtyHours - playTime <= 0) {
                if (econ.has(player, 500000)) {
                    pexUser.addGroup("Baron");
                    pexUser.removeGroup("Merchant");
                    econ.bankWithdraw(player.getName(), 500000);
                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient funds! You need " + (500000 - econ.bankBalance(player.getName()).amount) + " more coins to rankup.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You need to wait " + GeneralMethods.milliToHours(thirtyHours - playTime) + " before you can rankup.");
            }
        } else if (player.hasPermission("DCCore.rankup.Baron")) {
            if (hundredHours - playTime <= 0) {
                if (econ.has(player, 5000000)) {
                    pexUser.addGroup("Official");
                    pexUser.removeGroup("Baron");
                    econ.bankWithdraw(player.getName(), 5000000);
                } else {
                    sender.sendMessage(GeneralMethods.errorColor + "You do not have sufficient funds! You need " + (5000000 - econ.bankBalance(player.getName()).amount) + " more coins to rankup.");
                }
            } else {
                sender.sendMessage(GeneralMethods.errorColor + "You need to wait " + GeneralMethods.milliToHours(thirtyHours - playTime) + " before you can rankup.");
            }
        } else if (player.hasPermission("DCCore.rankup.Official")) {
            sender.sendMessage(GeneralMethods.serverPrefix + "Congratulations! You have surpassed all those peons with the lower ranks and achieved the highest player ranks possible. Have you considered donating or applying for staff?");
        } else if (player.hasPermission("DCCore.rankup.Noble") || player.hasPermission("DCCore.rankup.Royalty")) {
            sender.sendMessage(GeneralMethods.serverPrefix + "Thank you for donating for the server, if you would like to gain a higher rank, please donate or apply for staff. Everyone has a fair chance when applying for staff, so good luck~");
        } else if (player.hasPermission("DCCore.rankup.Staff")) {
            sender.sendMessage(GeneralMethods.serverPrefix + "Silly Goose! You're staff. Don't stress about ranking up, just user your sooper cule powerz on those peon players.");
        } else if (player.hasPermission("DCCore.rankup.Ancient")) {
            sender.sendMessage(GeneralMethods.serverPrefix + "Thank you for your time as a staff member! Don't stress about ranking up, just user your sooper cule powerz on those peon players.");
        } else {
            sender.sendMessage(GeneralMethods.serverPrefix + "Wtf, are you even a player?!?! Contact staff immediately!");
        }
    }
}
