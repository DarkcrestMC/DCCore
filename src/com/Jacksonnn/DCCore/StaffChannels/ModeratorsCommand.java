package com.Jacksonnn.DCCore.StaffChannels;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.Collection;
import java.util.Objects;

public class ModeratorsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("DCCore.staffchats.Moderators")) {
            if (args.length == 0) {
                //toggle chat
                String currentChat = ConfigManager.defaultConfig.get().getString("DCStaffChat." + sender.getName());
                if (currentChat == null) {
                    ConfigManager.defaultConfig.get().set("DCStaffChat." + sender.getName(), "Moderators");
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to MODERATORS.");
                    PermissionUser pexUser = PermissionsEx.getUser((Player)sender);
                    pexUser.addPermission("-discordsrv.chat");
                } else if (currentChat.equalsIgnoreCase("Moderators")) {
                    ConfigManager.defaultConfig.get().set("DCStaffChat." + sender.getName(), null);
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to NORMAL.");
                    PermissionUser pexUser = PermissionsEx.getUser((Player)sender);
                    pexUser.removePermission("-discordsrv.chat");
                } else {
                    ConfigManager.defaultConfig.get().set("DCStaffChat." + sender.getName(), "Moderators");
                    sender.sendMessage(GeneralMethods.prefix + "Chat channel set to MODERATORS.");
                    PermissionUser pexUser = PermissionsEx.getUser((Player)sender);
                    pexUser.addPermission("-discordsrv.chat");
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
