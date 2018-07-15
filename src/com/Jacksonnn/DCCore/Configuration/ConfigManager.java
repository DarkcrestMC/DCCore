package com.Jacksonnn.DCCore.Configuration;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class ConfigManager {
    private static Config langConfig;
    private static Config defaultConfig;
    private static Config killConfig;

    public ConfigManager() {
        defaultConfig = new Config(new File("config.yml"));
        langConfig = new Config(new File("language.yml"));
        killConfig = new Config(new File("killMoney.yml"));
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
        }
    }
}