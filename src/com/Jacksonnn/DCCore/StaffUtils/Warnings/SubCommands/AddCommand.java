package com.Jacksonnn.DCCore.StaffUtils.Warnings.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.GeneralMethods;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.Warning;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.WarningSubCommand;
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

public class AddCommand implements WarningSubCommand {
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
        return "/warnings new <player> <message>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Warnings.CommandDescriptions.AddCommand");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        //warning add <player> <note>
        //              arg(0)  arg(1)
        if (args.size() >= 2) {
            DCPlayer dcPlayer = GeneralMethods.getDCPlayer(args.get(0));
            Player staffMember = ((Player) sender).getPlayer();

            if (dcPlayer == null) {
                sender.sendMessage(pdm.getWarningManager().warningPrefix + "That player does not exist, please try again.");
                return;
            }

            if (staffMember == null) {
                sender.sendMessage(pdm.getWarningManager().warningPrefix + "You must be a player to execute this command.");
            }

            args.remove(0);

            Warning warning = new Warning(dcPlayer, staffMember.getUniqueId(), String.join(" ", args), pdm);

            sender.sendMessage(pdm.getWarningManager().warningPrefix + "Success! Warning created!");

            TextChannel notesChannel = DiscordUtil.getTextChannelById(Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Warnings.ChannelID")));

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor(staffMember.getName());
            embedBuilder.setTitle("Warning against " + dcPlayer.getName());
            embedBuilder.setDescription(warning.getReason() + " -" + sender.getName());
            //GOLD CHAT COLOR
            embedBuilder.setColor(new Color(255, 170, 0));

            notesChannel.sendMessage(embedBuilder.build()).queue();

            String chatprefix = GeneralMethods.translateColorCodes(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix")));
            String msgColor = GeneralMethods.translateColorCodes(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor")));

            Bukkit.broadcast(chatprefix + ChatColor.GOLD + "NEW WARNING: " + msgColor + staffMember.getName() + " just warned " + dcPlayer.getName() + " for " + warning.getReason() + ".", GeneralMethods.ChatModes.STAFF.getChatPerm());
        } else {
            sender.sendMessage(pdm.getWarningManager().warningPrefix + getProperUse());
        }
    }
}
