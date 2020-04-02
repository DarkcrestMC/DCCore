package com.Jacksonnn.DCCore.Configuration;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;

public class ConfigManager {
    public static Config langConfig;
    public static Config defaultConfig;
    public static Config killConfig;
    public static Config bannedWords;


    public ConfigManager() {
        defaultConfig = new Config(new File("config.yml"));
        langConfig = new Config(new File("language.yml"));
        killConfig = new Config(new File("killMoney.yml"));
        bannedWords = new Config(new File("bannedWords.yml"));
        configCheck(ConfigType.DEFAULT);
        configCheck(ConfigType.ANTICURSE);
        configCheck(ConfigType.LANGUAGE);
        configCheck(ConfigType.KILLMONEY);
    }

    public static void configCheck(ConfigType type) {
        FileConfiguration config;
        if (type == ConfigType.DEFAULT) {
            config = defaultConfig.get();

           config.addDefault("QuickDeposit.players", "");

            config.addDefault("Storage.engine", "sqlite");

            config.addDefault("Storage.settings.host", "localhost");
            config.addDefault("Storage.settings.port", 3306);
            config.addDefault("Storage.settings.password", "");
            config.addDefault("Storage.settings.database", "minecraft");
            config.addDefault("Storage.settings.username", "root");
            config.addDefault("Storage.settings.ssl", false);

            config.addDefault("Storage.settings.path", "db.sql");

            config.addDefault("StaffNotification.StaffChat.ChannelName", "staff-chat");
            config.addDefault("StaffNotification.StaffChat.ChannelID", "354051234338045952");
            config.addDefault("StaffNotification.StaffChat.StaffRoleID", "376739311896363008");
            defaultConfig.save();
        } else if (type == ConfigType.LANGUAGE) {
            config = langConfig.get();

            config.addDefault("Language.StaffChats.HoS.Prefix", "&8[&5&lHOS&8]&d ");
            config.addDefault("Language.StaffChats.HoS.msgColor", "&e&o");
            config.addDefault("Language.StaffChats.Managers.Prefix", "&8[&4&lMANAGERS&8]&c ");
            config.addDefault("Language.StaffChats.Managers.msgColor", "&e&o");
            config.addDefault("Language.StaffChats.Moderators.Prefix", "&8[&2&lMODERATORS&8]&a ");
            config.addDefault("Language.StaffChats.Moderators.msgColor", "&e&o");
            config.addDefault("Language.StaffChats.StaffChat.Prefix", "&8[&3&lSTAFF CHAT&8]&b ");
            config.addDefault("Language.StaffChats.StaffChat.msgColor", "&e&o");
            config.addDefault("Language.StaffChats.Artists.Prefix", "&8[&c&lA&6&lR&e&lT&a&lI&9&lS&d&lT&f&lS&8]&b ");
            config.addDefault("Language.StaffChats.Artists.msgColor", "&e&o");

            config.addDefault("Language.Vanish.PlayerJoinMessage", "&8&l[&a&l+&8&l]&7");
            config.addDefault("Language.Vanish.PlayerLeaveMessage", "&8&l[&c&l-&8&l]&7");

            config.addDefault("Language.StaffNotification.StaffColor", "&3&l");
            config.addDefault("Language.StaffNotification.OwnerColor", "&d&l");
            config.addDefault("Language.StaffNotification.CoOwnerColor", "&7&l");
            config.addDefault("Language.StaffNotification.ManagerColor", "&c&l");
            config.addDefault("Language.StaffNotification.ModeratorColor", "&2&l");
            config.addDefault("Language.StaffNotification.JModColor", "&9&l");
            config.addDefault("Language.StaffNotification.AncientColor", "&b&l");
            config.addDefault("Language.StaffNotification.ArtistColor", "&a&l");

            //  /dcevents add <event> <player(s)>
            //  /dcevents remove <event> <player(s)>
            //  /dcevents startEvent <event>
            //  /dcevents endEvent <event>
            //  /dcevents eventList
            //  /dcevents playerList <event>
            //  /dcevents broadcast <message>
            //  /dcevents teleport

            config.addDefault("Events.CommandDescriptions.AddCommand", "Adds player(s) to a certain eventlist.");
            config.addDefault("Events.CommandDescriptions.RemoveCommand", "Removes player(s) from a certain eventlist.");
            config.addDefault("Events.CommandDescriptions.StartEventCommand", "Creates an event.");
            config.addDefault("Events.CommandDescriptions.EndEventCommand", "Ends an event.");
            config.addDefault("Events.CommandDescriptions.EventList", "Lists all current events.");
            config.addDefault("Events.CommandDescriptions.PlayerList", "Lists all players participating in a certain event.");
            config.addDefault("Events.CommandDescriptions.Broadcast", "Uses the [EventBroadcast] chat function.");
            config.addDefault("Events.CommandDescriptions.Help", "Shows all possible commands and their arguments.");
            config.addDefault("Events.CommandDescriptions.Teleport", "Teleports all active players in an event to the executor.");

            langConfig.save();
        } else if (type == ConfigType.KILLMONEY) {
            config = killConfig.get();

            config.addDefault("KillMoney", "");
            killConfig.save();
        } else if (type == ConfigType.ANTICURSE) {
            config = bannedWords.get();

            ArrayList<String> bannedWordsList = new ArrayList<>();

            bannedWordsList.add("bitch");
            bannedWordsList.add("fuck");
            bannedWordsList.add("shit");

            config.addDefault("AntiCurse.bannedWords", bannedWordsList);
            bannedWords.save();
        }
    }
}