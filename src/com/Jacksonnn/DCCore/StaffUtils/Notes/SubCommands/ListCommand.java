package com.Jacksonnn.DCCore.StaffUtils.Notes.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.GeneralMethods;
import com.Jacksonnn.DCCore.StaffUtils.Notes.Note;
import com.Jacksonnn.DCCore.StaffUtils.Notes.NotesSubCommand;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements NotesSubCommand {
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public ListCommand(DCCore plugin) {
        this.plugin = plugin;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/notes list <player>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Notes.CommandDescriptions.List");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() == 1) {
            if (!args.get(0).equalsIgnoreCase("all")) {
                Player player = Bukkit.getPlayer(args.get(0));
                DCPlayer dcPlayer = GeneralMethods.getDCPlayer(player.getUniqueId());

                if (dcPlayer == null) {
                    sender.sendMessage(pdm.getNoteManager().notesPrefix + "Does that player exist?");
                    return;
                }

                List<Note> playerNotes = new ArrayList<>();
                playerNotes.addAll(dcPlayer.getNotes());

                if (playerNotes.size() == 0) {
                    sender.sendMessage(pdm.getNoteManager().notesPrefix + "Player has no notes.");
                } else {
                    sender.sendMessage(pdm.getNoteManager().notesPrefix + "Notes for player " + player.getName() + ":");
                    for (Note note : playerNotes) {
                        sender.sendMessage("(ID: " + note.getID() + ") " + ChatColor.YELLOW + note.getNote() + ChatColor.AQUA + " -" + Bukkit.getPlayer(note.getStaffMember()).getName());
                    }
                }
            } else {
                List<Note> playerNotes = new ArrayList<>();
                playerNotes.addAll(plugin.getPDM().getNoteManager().getAllNotes());

                if (playerNotes.size() == 0) {
                    sender.sendMessage(pdm.getNoteManager().notesPrefix + "There are no Player Notes.");
                } else {
                    sender.sendMessage(pdm.getNoteManager().notesPrefix + "All Player Notes:");
                    for (Note note : playerNotes) {
                        sender.sendMessage("(ID: " + note.getID() + ") " + ChatColor.YELLOW + note.getNote() + ChatColor.AQUA + " -" + GeneralMethods.getDCPlayer(note.getStaffMember()).getName());
                    }
                }
            }
        } else {
            sender.sendMessage(pdm.getNoteManager().notesPrefix + getProperUse());
        }
    }
}
