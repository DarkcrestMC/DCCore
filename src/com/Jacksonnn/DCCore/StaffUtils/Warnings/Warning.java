package com.Jacksonnn.DCCore.StaffUtils.Warnings;

import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Warning {
    private final int id;
    private final UUID player;
    private final UUID staffMember;
    private final String message;
    private final PlayerDisciplineManager pdm;

    public Warning(DCPlayer player, UUID staffMember, String message, PlayerDisciplineManager pdm) {
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
        this.player = player.getUuid();
        this.staffMember = staffMember;
        this.message = message;

//        player.addWarning(this);

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
        if (Bukkit.getPlayer(this.getPlayer()) != null) {
            Bukkit.getPlayer(this.getPlayer()).sendMessage(pdm.getWarningManager().warningPrefix + "You have been warned by " + Bukkit.getPlayer(getStaffMember()).getName() + " for " + getReason() + ".");
        }
        pdm.saveWarning(this);
        Bukkit.getLogger().info("Warning saved successfully!");
    }
}
