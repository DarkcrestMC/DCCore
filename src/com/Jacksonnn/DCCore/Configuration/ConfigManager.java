package com.Jacksonnn.DCCore.Configuration;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;

public class ConfigManager {
    public static Config langConfig;
    public static Config defaultConfig;
    public static Config bannedWords;
    public static Config announcer;

    public ConfigManager() {
        defaultConfig = new Config(new File("config.yml"));
        langConfig = new Config(new File("language.yml"));
        bannedWords = new Config(new File("bannedWords.yml"));
        announcer = new Config(new File("announcements.yml"));
        configCheck(ConfigType.DEFAULT);
        configCheck(ConfigType.ANTICURSE);
        configCheck(ConfigType.LANGUAGE);
        configCheck(ConfigType.ANNOUNCER);
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

            config.addDefault("StaffNotification.Notes.ChannelID", "688899801697615873");
            config.addDefault("StaffNotification.Notes.StaffRoleID", "376739311896363008");

            config.addDefault("StaffNotification.Warnings.ChannelID", "688899801697615873");
            config.addDefault("StaffNotification.Warnings.StaffRoleID", "376739311896363008");

            config.addDefault("StaffNotification.Reports.PlayerReport.ChannelID", "688899801697615873");
            config.addDefault("StaffNotification.Reports.PlayerReport.StaffRoleID", "376739311896363008");

            config.addDefault("StaffNotification.Reports.BugReport.ChannelID", "688899801697615873");
            config.addDefault("StaffNotification.Reports.BugReport.StaffRoleID", "376739311896363008");

            config.addDefault("StaffNotification.Reports.StaffReport.ChannelID", "684852294541377536");
            config.addDefault("StaffNotification.Reports.StaffReport.StaffRoleID", "440567726839431178");

            config.addDefault("StaffNotification.Reports.ToDoReport.ChannelID", "684852294541377536");
            config.addDefault("StaffNotification.Reports.ToDoReport.StaffRoleID", "440567726839431178");

            config.addDefault("RandomTP.enabled", false);
            config.addDefault("RandomTP.SetRadius", true);
            config.addDefault("RandomTP.Radius", 5000000);
            config.addDefault("RandomTP.WorldSizeDividedBy", 8);
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

            config.addDefault("Reports.CommandDescriptions.Help", "Shows all possible commands and their arguments.");
            config.addDefault("Reports.CommandDescriptions.Add", "Creates a new report for the specified type.");
            config.addDefault("Reports.CommandDescriptions.Remove", "Removes/Deletes a Report");
            config.addDefault("Reports.CommandDescriptions.List", "Lists all reports for a certain type.");
            config.addDefault("Reports.CommandDescriptions.Clear", "Clears all reports for a certain type.");

            config.addDefault("Notes.CommandDescriptions.Help", "Shows all possible commands and their arguments.");
            config.addDefault("Notes.CommandDescriptions.Add", "Creates a new note for a player with the designated message.");
            config.addDefault("Notes.CommandDescriptions.Clear", "Clears all notes for a player.");
            config.addDefault("Notes.CommandDescriptions.List", "Lists all notes for a player.");
            config.addDefault("Notes.CommandDescriptions.Remove", "Removes a note from a player.");

            config.addDefault("Warnings.CommandDescriptions.Help", "Shows all possible commands and their arguments.");
            config.addDefault("Warnings.CommandDescriptions.Add", "Creates a new warning against a player with a reason.");
            config.addDefault("Warnings.CommandDescriptions.Clear", "Clears all warnings for a player.");
            config.addDefault("Warnings.CommandDescriptions.List", "Lists all warnings for a player.");
            config.addDefault("Warnings.CommandDescriptions.Remove", "Removes a warning from a player.");

            langConfig.save();
        } else if (type == ConfigType.ANTICURSE) {
            config = bannedWords.get();

            ArrayList<String> bannedWordsList = new ArrayList<>();

            bannedWordsList.add("bitch");
            bannedWordsList.add("fuck");
            bannedWordsList.add("shit");

            config.addDefault("AntiCurse.bannedWords", bannedWordsList);
            bannedWords.save();
        } else if (type == ConfigType.ANNOUNCER) {
            config = announcer.get();
            ArrayList<String> messages = new ArrayList<>();
            messages.add("&aTest1");
            messages.add("&bTest2");
            messages.add("&cTest3");
            config.addDefault("announcements.enabled", Boolean.valueOf(true));
            config.addDefault("announcements.prefix", "&8[&7&lDC Announcer&8]&b ");
            config.addDefault("announcements.interval", Long.valueOf(300L));
            config.addDefault("announcements.messages", messages);
            announcer.save();
        }
    }
}