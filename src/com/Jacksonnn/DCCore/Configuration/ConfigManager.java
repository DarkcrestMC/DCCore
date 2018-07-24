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
            defaultConfig.save();
        } else if (type == ConfigType.LANGUAGE) {
            config = langConfig.get();

            config.addDefault("Language", "");
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