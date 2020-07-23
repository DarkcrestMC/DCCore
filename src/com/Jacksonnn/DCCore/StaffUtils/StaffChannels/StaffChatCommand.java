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

public class StaffChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("DCCore.staffchats.Staff")) {
            if (args.length == 0) {
                //toggle chat
                if (!(sender instanceof Player))
                    return false;
                Player player = (Player)sender;
                String currentChat = ConfigManager.defaultConfig.get().getString("DCStaffChat." + sender.getName());
                if (currentChat == null) {
                    ConfigManager.defaultConfig.get().set("DCStaffChat." + sender.getName(), "Staff");
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to STAFF CHAT.");
                    DCCore.permissions.playerAdd(player, "-discordsrv.chat");
                } else if (currentChat.equalsIgnoreCase("Staff")) {
                    ConfigManager.defaultConfig.get().set("DCStaffChat." + sender.getName(), null);
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to NORMAL.");
                    DCCore.permissions.playerRemove(player, "-discordsrv.chat");
                } else {
                    ConfigManager.defaultConfig.get().set("DCStaffChat." + sender.getName(), "Staff");
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to STAFF CHAT.");
                    DCCore.permissions.playerAdd(player, "-discordsrv.chat");
                }
                ConfigManager.defaultConfig.save();
            } else {
                //send message through command
                String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix")));
                String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor")));
                Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

                for (Player player : onlinePlayers) {
                    if (player.hasPermission("DCCore.staffchats.Staff")) {
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
