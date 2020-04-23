package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.Collection;
import java.util.Objects;

public class GeneralMethods {
	public static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + ChatColor.BOLD + "DarkcrestMC" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + " ";
	public static String errorColor = prefix + ChatColor.DARK_RED + "Error! " + ChatColor.RED;
	public static String successColor = prefix + ChatColor.GREEN + "";
	public static String disableColor = prefix + ChatColor.RED + "";
	public static String serverPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + ChatColor.BOLD + "DarkcrestMC" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + " ";
	private static int staffNotification = 0;

	public enum Elements {
		AIR,
		WATER,
		EARTH,
		FIRE,
		CHI
    }

	public static String milliToHours(long milli) {

		long hrs = milli / 3600000;
		milli %= 3600000;
		long min = milli / 60000;
		milli %= 60000;
		long sec = milli / 1000;

		return String.format("%02d hours, %02d minutes, and %02d seconds", hrs, min, sec);
	}

	public static Location toHighestBlock(Location l) {
		return new Location(l.getWorld(), l.getX(), l.getWorld().getHighestBlockYAt(l.getBlockX(), l.getBlockZ()), l.getZ());
	}

	public static void updateStaffCount(Player eventPlayer) {
		int onlineArtists = 0;
		int onlineAncients = 0;
		int onlineJMods = 0;
		int onlineModerators = 0;
		int onlineManagers = 0;
		int onlineCoOwners = 0;
		int onlineOwners = 0;
		int onlineStaff = 0;

		if (eventPlayer != null) {
			if (PermissionsEx.getUser(eventPlayer).inGroup("Owner")) {
				onlineOwners--;
				onlineStaff--;
			} else if (PermissionsEx.getUser(eventPlayer).inGroup("Ancient")) {
				onlineAncients--;
				onlineStaff--;
			} else if (PermissionsEx.getUser(eventPlayer).inGroup("Co-Owner")) {
				onlineCoOwners--;
				onlineStaff--;
			} else if (PermissionsEx.getUser(eventPlayer).inGroup("Manager")) {
				onlineManagers--;
				onlineStaff--;
			} else if (PermissionsEx.getUser(eventPlayer).inGroup("Moderator")) {
				onlineModerators--;
				onlineStaff--;
			} else if (PermissionsEx.getUser(eventPlayer).inGroup("JMod")) {
				onlineJMods--;
				onlineStaff--;
			} else if (PermissionsEx.getUser(eventPlayer).inGroup("Artist") || PermissionsEx.getUser(eventPlayer).inGroup("Architect")) {
				onlineArtists--;
				onlineStaff--;
			}
		}

		String staffColor = org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffNotification.StaffColor")));
		String ownerColor = org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffNotification.OwnerColor")));
		String coOwnerColor = org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffNotification.CoOwnerColor")));
		String managerColor = org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffNotification.ManagerColor")));
		String moderatorColor = org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffNotification.ModeratorColor")));
		String jModColor = org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffNotification.JModColor")));
		String ancientColor = org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffNotification.AncientColor")));
		String artistColor = org.bukkit.ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffNotification.ArtistColor")));

		Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
		for (Player player : onlinePlayers) {
			if (PermissionsEx.getUser(player).inGroup("Owner")) {
				onlineOwners++;
				onlineStaff++;
			} else if (PermissionsEx.getUser(player).inGroup("Ancient")) {
				onlineAncients++;
				onlineStaff++;
			} else if (PermissionsEx.getUser(player).inGroup("Co-Owner")) {
				onlineCoOwners++;
				onlineStaff++;
			} else if (PermissionsEx.getUser(player).inGroup("Manager")) {
				onlineManagers++;
				onlineStaff++;
			} else if (PermissionsEx.getUser(player).inGroup("Moderator")) {
				onlineModerators++;
				onlineStaff++;
			} else if (PermissionsEx.getUser(player).inGroup("JMod")) {
				onlineJMods++;
				onlineStaff++;
			} else if (PermissionsEx.getUser(player).inGroup("Artist") || PermissionsEx.getUser(player).inGroup("Architect")) {
				onlineArtists++;
				onlineStaff++;
			}
		}

		if (onlineStaff == 0) {
			TextChannel staffchat = DiscordUtil.getTextChannelById(Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.StaffChat.ChannelID")));
			String staffTag = DiscordSRV.getPlugin().getMainGuild().getRoleById(Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.StaffChat.StaffRoleID"))).getAsMention();
			staffNotification++;
			if (staffNotification % 4 == 0) {
				staffchat.sendMessage(staffTag + " server is staffless with ***" + (Bukkit.getServer().getOnlinePlayers().size() - 1) + "*** online players!!! (" + staffNotification + "/4)").queue();
			}
			staffchat.sendMessage( "DC Staff," + " server is staffless with ***" + (Bukkit.getServer().getOnlinePlayers().size() - 1) + "*** online players!!! (" + staffNotification + "/4)").queue();
		} else {
			for (Player player : onlinePlayers) {
				if (player.hasPermission("DCCore.staffchats.Staff")) {
					//player.sendMessage(GeneralMethods.serverPrefix + "Server has " + staffColor + onlineStaff + ChatColor.YELLOW + " online staff (" + ownerColor + onlineOwners + " Owners, " + coOwnerColor + onlineCoOwners + " Co-Owners, " + managerColor + onlineManagers + " Managers, " + moderatorColor + onlineModerators + " Moderators, " + jModColor + onlineJMods + " JMods, " + ancientColor + onlineAncients + " Ancients, " + artistColor + onlineArtists + " Artists" + ChatColor.YELLOW + ").");
					player.sendMessage("");
					player.sendMessage(GeneralMethods.serverPrefix + "Server has " + staffColor + onlineStaff + ChatColor.YELLOW + " online staff (" + ownerColor + onlineOwners + " " + coOwnerColor + onlineCoOwners + " " + managerColor + onlineManagers + " " + moderatorColor + onlineModerators + " " + jModColor + onlineJMods + " " + ancientColor + onlineAncients + " " + artistColor + onlineArtists + ChatColor.YELLOW + ").");
				}
			}
		}
	}
}
