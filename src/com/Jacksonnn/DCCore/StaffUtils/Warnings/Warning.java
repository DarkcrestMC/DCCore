package com.Jacksonnn.DCCore.StaffUtils.Warnings;

import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Warning {
    private int id;
    private UUID player;
    private UUID staffMember;
    private String message;
    private PlayerDisciplineManager pdm;

    public Warning(UUID player, UUID staffMember, String message, PlayerDisciplineManager pdm) {
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

    public Warning(int id, UUID player, UUID staffMember, String message, PlayerDisciplineManager pdm) {
        this.id = id;
        this.player = player;
        this.staffMember = staffMember;
        this.message = message;
        this.pdm = pdm;
    }

    public int getID() {
        return id;
    }

    public UUID getPlayer() {
        return player;
    }

    public UUID getStaffMember() {
        return staffMember;
    }

    public String getReason() {
        return message;
    }

    public void saveWarning() {
        Bukkit.getLogger().info("Saving warning...");
        Bukkit.getPlayer(this.getPlayer()).sendMessage(pdm.getWarningManager().warningPrefix + "You have been warned by " + Bukkit.getPlayer(getStaffMember()).getName() + " for " + getReason() + ".");
        pdm.saveWarning(this);
        Bukkit.getLogger().info("Warning saved successfully!");
    }
}
