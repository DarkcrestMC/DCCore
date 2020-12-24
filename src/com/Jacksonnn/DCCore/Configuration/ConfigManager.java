package com.Jacksonnn.DCCore.Configuration;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;

public class ConfigManager {
    public static Config langConfig;
    public static Config defaultConfig;
    public static Config bannedWords;
    public static Config announcer;
    public static Config webhook;

    public static void setupConfigManager() {
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

            config.addDefault("StaffNotification.enabled", false);

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

            config.addDefault("Suggestions.ChannelID", "0");
            config.addDefault("Suggestions.lifespanInTicks", 1200);

            config.addDefault("RandomTP.enabled", false);
            config.addDefault("RandomTP.SetRadius", true);
            config.addDefault("RandomTP.Radius", 5000000);
            config.addDefault("RandomTP.WorldSizeDividedBy", 8);



            ArrayList<Integer> defaultPrices = new ArrayList<>();
            defaultPrices.add(0);
            defaultPrices.add(5000);
            defaultPrices.add(50000);
            defaultPrices.add(500000);
            defaultPrices.add(5000000);
            defaultPrices.add(50000000);
            defaultPrices.add(500000000);
            config.addDefault("Rankup.Prices.Ranks", defaultPrices);
            ArrayList<Integer> defaultHours = new ArrayList<>();
            defaultHours.add(0);
            defaultHours.add(10);
            defaultHours.add(20);
            defaultHours.add(30);
            defaultHours.add(40);
            defaultHours.add(60);
            defaultHours.add(75);
            config.addDefault("Rankup.Hours.Ranks", defaultHours);
            config.addDefault("Rankup.Names.Ranks", new String[] {"Member", "Citizen", "Merchant", "Baron", "Official", "Noble", "Royal"});

            config.addDefault("Rankup.Prices.Avatar", 225000000);
            config.addDefault("Rankup.Hours.Avatar", 60);

            config.addDefault("Rankup.Prices.LightSpirit", 500000);
            config.addDefault("Rankup.Hours.LightSpirit", 30);

            config.addDefault("Rankup.Prices.DarkSpirit", 500000);
            config.addDefault("Rankup.Hours.DarkSpirit", 30);
            config.addDefault("Webhook", "insert link here");
            config.addDefault("Enabled", true);
            config.addDefault("Format","%player% has vote for us on %Service_name%");
            defaultConfig.save();
        } else if (type == ConfigType.LANGUAGE) {
            config = langConfig.get();

            config.addDefault("Language.StaffChats.HOS.Prefix", "&8[&5&lHOS&8]&d ");
            config.addDefault("Language.StaffChats.HOS.msgColor", "&e&o");
            config.addDefault("Language.StaffChats.Developer.Prefix", "&8[&3&lDeveloper&8]&d ");
            config.addDefault("Language.StaffChats.Developer.msgColor", "&7&o");
            config.addDefault("Language.StaffChats.Managers.Prefix", "&8[&4&lMANAGERS&8]&c ");
            config.addDefault("Language.StaffChats.Managers.msgColor", "&e&o");
            config.addDefault("Language.StaffChats.Moderators.Prefix", "&8[&2&lMODERATORS&8]&a ");
            config.addDefault("Language.StaffChats.Moderators.msgColor", "&e&o");
            config.addDefault("Language.StaffChats.StaffChat.Prefix", "&8[&3&lSTAFF CHAT&8]&b ");
            config.addDefault("Language.StaffChats.StaffChat.msgColor", "&e&o");
            config.addDefault("Language.StaffChats.EventHosts.Prefix", "&8[&9&lEVENT HOSTS&8]&b ");
            config.addDefault("Language.StaffChats.EventHosts.msgColor", "&a&o");
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

            config.addDefault("Language.BendingHelp.Description", "&#E5B100DarkcrestMC uses the &#C9C9C9&lProjectKorra&#E5B100 plugin to allow for A:TLA and A:LOK in-game play. As a &omember&#E5B100, you can choose between the elements (&7air&#E5B100, &bwater&#E5B100, &aearth&#E5B100, &cfire&#E5B100, and &6chi&#E5B100).§" +
                    "&#E5B100When you become a &#62636e[&#c018d6Merchant&#62636e]&#E5B100 rank or higher, you can also start using the &3Spirit&#E5B100 element.§" +
                    "&#E5B100On DarkcrestMC, you are also able to donate for &#AA00E5Avatar&#E5B100! In order to do so, type &#C9C9C9&l/donate&#E5B100. You can also obtain it as a regular player according to &#C9C9C9&l/ranks&#E5B100.§" +
                    "&#E5B100In order to use the &oSpiritTypes&#E5B100, it works the same as avatar. You can donate for either &fLightSpirit&#E5B100 or &#E5B100&8DarkSpirit&#E5B100 through &#C9C9C9&l/donate&#E5B100. Otherwise, you could obtain it according to &#C9C9C9&l/ranks&#E5B100.§");

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