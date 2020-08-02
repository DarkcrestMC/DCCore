package com.Jacksonnn.DCCore.StaffUtils.Notes.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.Notes.Note;
import com.Jacksonnn.DCCore.StaffUtils.Notes.NotesSubCommand;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;
import java.util.*;

public class AddCommand implements NotesSubCommand {
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public AddCommand(DCCore plugin) {
        this.plugin = plugin;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("create");
        aliases.add("new");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/notes new <player> <message>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Notes.CommandDescriptions.AddCommand");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() >= 2) {
            UUID player = Bukkit.getPlayer(args.get(0)).getUniqueId();
            UUID staffMember = ((Player) sender).getPlayer().getUniqueId();

            if (player == null) {
                sender.sendMessage(pdm.getNoteManager().notesPrefix + "That player does not exist, please try again.");
                return;
            }

            if (staffMember == null) {
                sender.sendMessage(pdm.getNoteManager().notesPrefix + "You must be a player to execute this command.");
            }

            args.remove(0);

            Note note = new Note(player, staffMember, String.join(" ", args), pdm);

            sender.sendMessage(pdm.getNoteManager().notesPrefix + "Success! Note Created!");

            TextChannel notesChannel = DiscordUtil.getTextChannelById(Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Notes.ChannelID")));

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor(Bukkit.getPlayer(note.getStaffMember()).getName());
            embedBuilder.setTitle("Note for " + Bukkit.getPlayer(note.getPlayer()).getName());
            embedBuilder.setDescription(note.getNote() + " -" + Bukkit.getPlayer(note.getStaffMember()).getName());

            //AQUA CHAT COLOR
            embedBuilder.setColor(new Color(85, 255, 255));

            notesChannel.sendMessage(embedBuilder.build()).queue();

            String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix")));
            String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor")));
            Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

            for (Player oPlayer : onlinePlayers) {
                if (oPlayer.hasPermission("DCCore.staffchats.Staff")) {
                    oPlayer.sendMessage(chatprefix + ChatColor.AQUA + "NEW PLAYER NOTE: " + msgColor + Bukkit.getPlayer(note.getStaffMember()).getName() + " just noted about " + Bukkit.getPlayer(note.getPlayer()).getName() + ": " + note.getNote() + ".");
                }
            }
        } else {
            sender.sendMessage(pdm.getNoteManager().notesPrefix + getProperUse());
        }
    }
}
