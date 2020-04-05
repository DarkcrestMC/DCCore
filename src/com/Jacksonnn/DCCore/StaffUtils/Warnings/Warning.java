package com.Jacksonnn.DCCore.StaffUtils.Warnings;

import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Warning {
    private int id;
    private Player player;
    private Player staffMember;
    private String message;
    private PlayerDisciplineManager pdm;

    public Warning(Player player, Player staffMember, String message, PlayerDisciplineManager pdm) {
        Bukkit.getLogger().info("Creating warning...");
        this.pdm = pdm;
        int i = 1;
        for (Warning warning : pdm.getWarningManager().getAllWarnings()) {
            if (warning.getID() >= i) {
                i = warning.getID();
                i++;
            }
        }

        this.id = i;
        Bukkit.getLogger().info("Getting id... id=" + this.id);
        this.player = player;
        this.staffMember = staffMember;
        this.message = message;

        saveWarning();
    }

    public Warning(int id, Player player, Player staffMember, String message, PlayerDisciplineManager pdm) {
        this.id = id;
        this.player = player;
        this.staffMember = staffMember;
        this.message = message;
        this.pdm = pdm;
    }

    public int getID() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getStaffMember() {
        return staffMember;
    }

    public String getReason() {
        return message;
    }

    public void saveWarning() {
        Bukkit.getLogger().info("Saving warning...");
        player.sendMessage(pdm.getWarningManager().warningPrefix + "You have been warned by " + getStaffMember().getName() + " for " + getReason() + ".");
        pdm.saveWarning(this);
        Bukkit.getLogger().info("Warning saved successfully!");
    }
}
