package com.Jacksonnn.DCCore.StaffUtils.Notes;

import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.GeneralMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class NotesGeneral {
    public String notesPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "DC Notes" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + " ";
    private static ArrayList<Note> notes = new ArrayList<>();

    public Note getNote(int id) {
        for (Note note : notes) {
            if (note.getID() == id) {
                return note;
            }
        }

        return null;
    }

    public ArrayList<Note> getAllNotes() {
        return notes;
    }

    public void addNote(Note note) {
        Bukkit.getLogger().info("Adding note to overall notes...");

        notes.add(note);

        DCPlayer dcPlayer = GeneralMethods.getDCPlayer(note.getPlayer());
        if (dcPlayer != null)
            dcPlayer.addNote(note);

        Bukkit.getLogger().info("Successfully added note!");
    }

    public void removeNote(Note note) {
        Bukkit.getLogger().info("Removing note from overall notes...");

        DCPlayer dcPlayer = GeneralMethods.getDCPlayer(note.getPlayer());
        if (dcPlayer != null)
            dcPlayer.removeNote(note);

        notes.remove(note);
        Bukkit.getLogger().info("Successfully removed note!");
    }

    public static ArrayList<Note> getPlayerNotes(Player player) {
        ArrayList<Note> playersNotes = new ArrayList<>();
        for (Note note : notes) {
            if (note.getPlayer() == player.getUniqueId()) {
                playersNotes.add(note);
            }
        }

        return playersNotes;
    }

    public void getHelp(CommandSender sender) {
        sender.sendMessage(notesPrefix + "Notes Commands: ");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/notes help");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/notes add <player> <note>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/notes delete <id>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/notes list <player>");
        sender.sendMessage(org.bukkit.ChatColor.YELLOW + "/notes clear <player>");
    }
}
