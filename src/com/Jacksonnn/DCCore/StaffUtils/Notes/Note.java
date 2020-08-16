package com.Jacksonnn.DCCore.StaffUtils.Notes;

import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Note {
    private int id;
    private UUID player;
    private UUID staffMember;
    private String message;
    private PlayerDisciplineManager pdm;

    public Note(DCPlayer player, UUID staffMember, String message, PlayerDisciplineManager pdm) {
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
        this.player = player.getUuid();
        this.staffMember = staffMember;
        this.message = message;
        this.pdm = pdm;

        player.addNote(this);

        saveNote();
    }

    public Note(int id, UUID player, UUID staffMember, String message, PlayerDisciplineManager pdm) {
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

    public String getNote() {
        return message;
    }

    public void saveNote() {
        Bukkit.getLogger().info("Saving note...");
        pdm.saveNote(this);
        Bukkit.getLogger().info("Note saved successfully!");
    }
}
