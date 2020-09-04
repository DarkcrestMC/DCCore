package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralMethods {

	public final static String accentColor = ChatColor.of("#C9C9C9").toString();
	public final static String prefix = translateColorCodes("&8[&#E500C3&lD&#DB00C2&la&#D200C2&lr&#C900C1&lk&#BF00C1&lc&#AD00C0&lr&#A400C0&le&#9A00C0&ls&#9100BF&lt&#8800BF&lM&#7F00BF&lC&8]" + accentColor + " ");
	public final static String errorPrefix = ChatColor.of("#660000") + "[" + ChatColor.of("#D6221E") + ChatColor.BOLD + "DCCore" + ChatColor.RESET + ChatColor.of("#660000") + "]" + accentColor + " ";
	public final static String successPrefix = ChatColor.of("#1E5C26") + "[" + ChatColor.of("#24D530") + ChatColor.BOLD + "DCCore" + ChatColor.RESET + ChatColor.of("#1E5C26") + "]" + accentColor + " ";

	private static final HashMap<UUID, DCPlayer> dcPlayers = new HashMap<>();

	public static HashMap<UUID, DCPlayer> getAllDCPlayers() {
		return dcPlayers;
	}

	public static DCPlayer getDCPlayer(UUID uuid) {
		return dcPlayers.getOrDefault(uuid, null);
	}
	public static DCPlayer getDCPlayer(String name) {
		for (DCPlayer dcPlayer : dcPlayers.values())
			if (dcPlayer.getName().equalsIgnoreCase(name))
				return dcPlayer;
		return null;
	}

	//FROM DATABASE
	public static void addDCPlayer(DCPlayer dcPlayer) {
		dcPlayers.put(dcPlayer.getUuid(), dcPlayer);
	}

	//NEW DCPLAYER
	public static void createDCPlayer(Player player) {
		dcPlayers.put(player.getUniqueId(), new DCPlayer(player));
	}

	public static void removeDCPlayer(DCPlayer dcPlayer) {
		dcPlayers.remove(dcPlayer.getUuid());
	}

	public static void removeDCPlayer(Player player) {
		dcPlayers.remove(player.getUniqueId());
	}

	public static String milliToHours(long milli) {

		long hrs = milli / 3600000;
		milli %= 3600000;
		long min = milli / 60000;
		milli %= 60000;
		long sec = milli / 1000;

		return String.format("%02d hrs, %02d min, and %02d sec", hrs, min, sec);
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
			if (DCCore.permissions.playerInGroup(null, eventPlayer, "Owner")) {
				onlineOwners--;
				onlineStaff--;
			} else if (DCCore.permissions.playerInGroup(null, eventPlayer, "Ancient")) {
				onlineAncients--;
				onlineStaff--;
			} else if (DCCore.permissions.playerInGroup(null, eventPlayer, "Co-Owner")) {
				onlineCoOwners--;
				onlineStaff--;
			} else if (DCCore.permissions.playerInGroup(null, eventPlayer, "Manager")) {
				onlineManagers--;
				onlineStaff--;
			} else if (DCCore.permissions.playerInGroup(null, eventPlayer, "Moderator")) {
				onlineModerators--;
				onlineStaff--;
			} else if (DCCore.permissions.playerInGroup(null, eventPlayer, "JMod")) {
				onlineJMods--;
				onlineStaff--;
			} else if (DCCore.permissions.playerInGroup(null, eventPlayer, "Artist") ||
					DCCore.permissions.playerInGroup(null, eventPlayer, "Architect")) {
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
			if (DCCore.permissions.playerInGroup(null, player, "Owner")) {
				onlineOwners++;
				onlineStaff++;
			} else if (DCCore.permissions.playerInGroup(null, player, "Ancient")) {
				onlineAncients++;
				onlineStaff++;
			} else if (DCCore.permissions.playerInGroup(null, player, "Co-Owner")) {
				onlineCoOwners++;
				onlineStaff++;
			} else if (DCCore.permissions.playerInGroup(null, player, "Manager")) {
				onlineManagers++;
				onlineStaff++;
			} else if (DCCore.permissions.playerInGroup(null, player, "Moderator")) {
				onlineModerators++;
				onlineStaff++;
			} else if (DCCore.permissions.playerInGroup(null, player, "JMod")) {
				onlineJMods++;
				onlineStaff++;
			} else if (DCCore.permissions.playerInGroup(null, player, "Artist") ||
					DCCore.permissions.playerInGroup(null, player, "Architect")) {
				onlineArtists++;
				onlineStaff++;
			}
		}

		if (onlineStaff == 0) {
			if (!Bukkit.getOnlinePlayers().isEmpty()) {
				TextChannel staffchat = DiscordUtil.getTextChannelById(Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.StaffChat.ChannelID")));
				EmbedBuilder embed = new EmbedBuilder();

				embed.setTitle("Staffless");
				embed.setColor(new Color(204, 102, 255));
				embed.setDescription("Server is **staffless** with ***" + (Bukkit.getServer().getOnlinePlayers().size() - 1) + "*** online players!!!");
				embed.setAuthor("DC Staff Chat Notification", "http://darkcrestmc.net", "http://darkcrestmc.net/wp-content/uploads/2019/10/Orange.png");

				staffchat.sendMessage(embed.build()).queue();
			}
		} else {
			for (Player player : onlinePlayers) {
				if (player.hasPermission("DCCore.staffchats.Staff")) {
					player.sendMessage("");
					player.sendMessage(GeneralMethods.prefix + "Server has " + staffColor + onlineStaff + GeneralMethods.accentColor + " online staff (" + ownerColor + onlineOwners + " " + coOwnerColor + onlineCoOwners + " " + managerColor + onlineManagers + " " + moderatorColor + onlineModerators + " " + jModColor + onlineJMods + " " + ancientColor + onlineAncients + " " + artistColor + onlineArtists + GeneralMethods.accentColor + ").");
				}
			}
		}
	}

	public static String locToString(Location loc) {
		// 10 13 531 (Events)
		return loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " (" + loc.getWorld().getName() + ")";
	}
	public static Location stringToLoc(String loc) {
		String[] locArgs = loc.split(" ");

		int x = Integer.parseInt(locArgs[0]);
		int y = Integer.parseInt(locArgs[1]);
		int z = Integer.parseInt(locArgs[2]);

		int parenthesesChar = locArgs[3].indexOf(')');
		String worldName = locArgs[3].substring(1, parenthesesChar - 1);

		return new Location(Bukkit.getWorld(worldName), x, y, z);
	}

	public enum ChatModes {
		GENERAL("general", null),
		ARTIST("artists", "DCCore.staffchats.Artists"),
		EVENTHOSTS("eventhosts", "DCCore.staffchats.EventHosts"),
		STAFF("staff", "DCCore.staffchats.Staff"),
		MODERATORS("moderators", "DCCore.staffchats.Moderators"),
		MANAGERS("managers", "DCCore.staffchats.Managers"),
		DEVELOPER("developer", "DCCore.staffchats.Developer"),
		HOS("HOS", "DCCore.staffchats.HOS");

		private final String name;
		private final String perm;

		ChatModes(String name, String perm) {
			this.name = name;
			this.perm = perm;
		}

		public String getChatName() {
			return name;
		}

		public String getChatPerm() {
			return perm;
		}
	}

	public static String booleanToString(boolean bool) {
		if (bool) {
			return "true";
		}
		return "false";
	}

	public static boolean stringToBool(String string) {
		return string.equalsIgnoreCase("true");
	}

	public static String translateColorCodes(String message) {

		ArrayList<String> allMatches = new ArrayList<>();
		Matcher m = Pattern.compile("&#[0-9A-Fa-f]{6}")
				.matcher(message);
		while (m.find())
			allMatches.add(m.group());

		for (String hex : allMatches)
			message = message.replace(hex, ChatColor.of(hex.substring(1)).toString());

		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static String stripColor(String message) {
		return ChatColor.stripColor(message);
	}

	public static void broadcast(String message) {
		for (Player player : Bukkit.getOnlinePlayers())
			player.sendMessage(message);
		Bukkit.getConsoleSender().sendMessage("[DC] " + stripColor(message));
	}
}
