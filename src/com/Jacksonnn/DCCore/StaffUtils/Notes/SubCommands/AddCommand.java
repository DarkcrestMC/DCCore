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
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
            Player player = Bukkit.getPlayer(args.get(0));
            Player staffMember = ((Player) sender).getPlayer();
            OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();

            for (OfflinePlayer oPlayer : offlinePlayers) {
                if (oPlayer.getName().equalsIgnoreCase(args.get(0))) {
                    player = oPlayer.getPlayer();
                }
            }

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
            embedBuilder.setAuthor(note.getStaffMember().getName());
            embedBuilder.setTitle("Note for " + note.getPlayer().getName());
            embedBuilder.setDescription(note.getNote() + " -" + note.getStaffMember().getName());
            //AQUA CHAT COLOR
            embedBuilder.setColor(new Color(85, 255, 255));

            notesChannel.sendMessage(embedBuilder.build()).queue();

            String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix")));
            String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor")));
            Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

            for (Player oPlayer : onlinePlayers) {
                if (oPlayer.hasPermission("DCCore.staffchats.Staff")) {
                    oPlayer.sendMessage(chatprefix + ChatColor.AQUA + "NEW PLAYER NOTE: " + msgColor + note.getStaffMember().getName() + " just noted about " + note.getPlayer().getName() + ": " + note.getNote() + ".");
                }
            }
        } else {
            sender.sendMessage(pdm.getNoteManager().notesPrefix + getProperUse());
        }
    }
}
