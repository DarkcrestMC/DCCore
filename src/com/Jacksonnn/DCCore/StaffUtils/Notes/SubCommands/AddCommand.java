package com.Jacksonnn.DCCore.StaffUtils.Notes.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.GeneralMethods;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AddCommand implements NotesSubCommand {
    private final DCCore plugin;
    private final PlayerDisciplineManager pdm;

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
        //notes add <player> <note>
        if (args.size() >= 2) {
            DCPlayer dcPlayer = GeneralMethods.getDCPlayer(args.get(0));

            if (dcPlayer == null) {
                sender.sendMessage(pdm.getNoteManager().notesPrefix + "That player does not exist, please try again.");
                return;
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage(pdm.getNoteManager().notesPrefix + "You must be a player to execute this command.");
                return;
            }

            args.remove(0);

            UUID staffMember = ((Player) sender).getPlayer().getUniqueId();

            Note note = new Note(dcPlayer, staffMember, String.join(" ", args), pdm);

            sender.sendMessage(pdm.getNoteManager().notesPrefix + "Success! Note Created!");

            TextChannel notesChannel = DiscordUtil.getTextChannelById(Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Notes.ChannelID")));

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor(sender.getName());
            embedBuilder.setTitle("Note for " + dcPlayer.getName());
            embedBuilder.setDescription(note.getNote() + " -" + sender.getName());

            //AQUA CHAT COLOR
            embedBuilder.setColor(new Color(85, 255, 255));

            notesChannel.sendMessage(embedBuilder.build()).queue();

            String chatprefix = GeneralMethods.translateColorCodes(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix")));
            String msgColor = GeneralMethods.translateColorCodes(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor")));

            Bukkit.broadcast(chatprefix + ChatColor.AQUA + "NEW PLAYER NOTE: " + msgColor + sender.getName() + " just noted about " + dcPlayer.getName() + ": " + note.getNote() + ".", GeneralMethods.ChatModes.STAFF.getChatPerm());
        } else {
            sender.sendMessage(pdm.getNoteManager().notesPrefix + getProperUse());
        }
    }
}
