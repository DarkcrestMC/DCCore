package com.Jacksonnn.DCCore.StaffUtils.StaffChannels;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DeveloperCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission(GeneralMethods.ChatModes.DEVELOPER.getChatPerm())) {
            if (args.length == 0) {
                //toggle chat
                if (!(sender instanceof Player))
                    return false;
                Player player = (Player)sender;
                DCPlayer dcPlayer = GeneralMethods.getDCPlayer(player.getUniqueId());

                GeneralMethods.ChatModes currentChat = dcPlayer.getChatMode();

                if (currentChat != GeneralMethods.ChatModes.DEVELOPER) {
                    dcPlayer.setChatMode(GeneralMethods.ChatModes.DEVELOPER);
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to DEVELOPER.");
                    DCCore.permissions.playerAdd(player, "-discordsrv.chat");

                } else if (currentChat == GeneralMethods.ChatModes.DEVELOPER) {
                    dcPlayer.setChatMode(GeneralMethods.ChatModes.GENERAL);
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to GENERAL.");
                    DCCore.permissions.playerRemove(player, "-discordsrv.chat");
                }

            } else {
                //send message through command
                String chatprefix = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Developer.Prefix"))));
                String msgColor = ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Developer.msgColor"))));

                Bukkit.broadcast(chatprefix + sender.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateHEXColorCode(StringUtils.join(args, ' '))), GeneralMethods.ChatModes.DEVELOPER.getChatPerm());
            }
        } else {
            sender.sendMessage(GeneralMethods.errorColor + "You do not have permission to perform this command.");
        }
        return true;
    }
}
