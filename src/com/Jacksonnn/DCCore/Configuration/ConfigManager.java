package com.Jacksonnn.DCCore.Configuration;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;

public class ConfigManager {
    private static Config langConfig;
    private static Config defaultConfig;
    private static Config killConfig;
    private static Config bannedWords;

    public ConfigManager() {
        defaultConfig = new Config(new File("config.yml"));
        langConfig = new Config(new File("language.yml"));
        killConfig = new Config(new File("killMoney.yml"));
        bannedWords = new Config(new File("bannedWords.yml"));
        configCheck(ConfigType.DEFAULT);
    }

    public static void configCheck(ConfigType type) {
        FileConfiguration config;
        if (type == ConfigType.DEFAULT) {
            config = defaultConfig.get();

           config.addDefault("QuickDeposit", "");
            defaultConfig.save();
        } else if (type == ConfigType.LANGUAGE) {
            config = langConfig.get();

            config.addDefault("Language", "");
        } else if (type == ConfigType.KILLMONEY) {
            config = killConfig.get();

            config.addDefault("KillMoney", "");
        } else if (type == ConfigType.ANTICURSE) {
            config = bannedWords.get();

            ArrayList<String> bannedWords = new ArrayList<>();
            bannedWords.add("bitch");
            bannedWords.add("fuck");
            bannedWords.add("shit");

            config.addDefault("AntiCurse.bannedWords", bannedWords);
        }
    }
}