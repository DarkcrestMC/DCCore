package com.Jacksonnn.DCCore.StaffUtils.Notes;

import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Note {
    private int id;
    private Player player;
    private Player staffMember;
    private String message;
    private PlayerDisciplineManager pdm;

    public Note(Player player, Player staffMember, String message, PlayerDisciplineManager pdm) {
        Bukkit.getLogger().info("Creating note...");
        int i = 1;
        for (Note note : pdm.getNoteManager().getAllNotes()) {
            if (note.getID() >= i) {
                i = note.getID();
                i++;
            }
        }

        this.id = i;
        Bukkit.getLogger().info("Getting id... id=" + this.id);
        this.player = player;
        this.staffMember = staffMember;
        this.message = message;
        this.pdm = pdm;

        saveNote();
    }

    public Note(int id, Player player, Player staffMember, String message, PlayerDisciplineManager pdm) {
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

    public String getNote() {
        return message;
    }

    public void saveNote() {
        Bukkit.getLogger().info("Saving note...");
        pdm.saveNote(this);
        Bukkit.getLogger().info("Note saved successfully!");
    }
}
