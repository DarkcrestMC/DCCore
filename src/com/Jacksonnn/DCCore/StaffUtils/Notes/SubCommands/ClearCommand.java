package com.Jacksonnn.DCCore.StaffUtils.Notes.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.Notes.Note;
import com.Jacksonnn.DCCore.StaffUtils.Notes.NotesSubCommand;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ClearCommand implements NotesSubCommand {
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public ClearCommand(DCCore plugin) {
        this.plugin = plugin;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("deleteall");
        aliases.add("clearall");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/notes clear <player>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Notes.CommandDescriptions.Clear");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() == 1) {
            Player player = Bukkit.getPlayer(args.get(0));

            if (player == null) {
                OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();
                for (OfflinePlayer offlinePlayer : offlinePlayers) {
                    if (offlinePlayer.getName() == args.get(0)) {
                        player = offlinePlayer.getPlayer();
                        break;
                    } else {
                        continue;
                    }
                }
            }

            if (player == null) {
                sender.sendMessage(pdm.getNoteManager().notesPrefix + "Does that player exist?");
                return;
            }

            ArrayList<Note> allNotes = new ArrayList<>();
            allNotes.addAll(pdm.getNoteManager().getAllNotes());

            for (Note note : allNotes) {
                if (note.getPlayer() == player) {
                    pdm.deleteNote(note);
                }
            }

            sender.sendMessage("Success! Cleared player's notes!");
        } else {
            sender.sendMessage(pdm.getNoteManager().notesPrefix + getProperUse());
        }
    }
}
