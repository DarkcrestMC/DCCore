package com.Jacksonnn.DCCore;

import net.md_5.bungee.api.ChatColor;

public class GeneralMethods {
	public static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "DCCore" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + " ";
	public static String errorColor = prefix + ChatColor.DARK_RED + "Error! " + ChatColor.RED;
	public static String successColor = prefix + ChatColor.GREEN + "";
	public static String disableColor = prefix + ChatColor.RED + "";
	public static String serverPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Darkcrest" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + " ";

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
}
