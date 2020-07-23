package com.Jacksonnn.DCCore.StaffUtils.StaffChannels;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Objects;

public class ModeratorsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("DCCore.staffchats.Moderators")) {
            if (args.length == 0) {
                //toggle chat
                if (!(sender instanceof Player))
                    return false;
                Player player = (Player)sender;
                String currentChat = ConfigManager.defaultConfig.get().getString("DCStaffChat." + sender.getName());
                if (currentChat == null) {
                    ConfigManager.defaultConfig.get().set("DCStaffChat." + sender.getName(), "Moderators");
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to MODERATORS.");
                    DCCore.permissions.playerAdd(player, "-discordsrv.chat");
                } else if (currentChat.equalsIgnoreCase("Moderators")) {
                    ConfigManager.defaultConfig.get().set("DCStaffChat." + sender.getName(), null);
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to NORMAL.");
                    DCCore.permissions.playerRemove(player, "-discordsrv.chat");
                } else {
                    ConfigManager.defaultConfig.get().set("DCStaffChat." + sender.getName(), "Moderators");
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to MODERATORS.");
                    DCCore.permissions.playerAdd(player, "-discordsrv.chat");
                }
                ConfigManager.defaultConfig.save();
            } else {
                //send message through command
                String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Moderators.Prefix")));
                String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.Moderators.msgColor")));
                Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

                for (Player player : onlinePlayers) {
                    if (player.hasPermission("DCCore.staffchats.Moderators")) {
                        player.sendMessage(chatprefix + sender.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, ' ')));
                    }
                }

                Bukkit.getLogger().info(chatprefix + sender.getName() + ": " + msgColor + ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, ' ')));
            }
        } else {
            sender.sendMessage(GeneralMethods.errorColor + "You do not have permission to perform this command.");
        }
        return true;
    }
}
