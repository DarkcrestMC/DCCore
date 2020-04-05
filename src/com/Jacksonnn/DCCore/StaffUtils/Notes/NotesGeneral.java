package com.Jacksonnn.DCCore.StaffUtils.Notes;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class NotesGeneral {
    public String notesPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "DC Notes" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + " ";
    private ArrayList<Note> notes = new ArrayList<>();

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
        Bukkit.getLogger().info("Successfully added note!");
    }

    public void removeNote(Note note) {
        Bukkit.getLogger().info("Removing warning from overall warnings...");
        notes.remove(note);
        Bukkit.getLogger().info("Successfully removed notes!");
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
