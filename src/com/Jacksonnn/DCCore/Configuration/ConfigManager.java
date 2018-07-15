package com.Jacksonnn.DCCore.Configuration;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class ConfigManager {
    public static Config defaultConfig;

    public ConfigManager() {
        defaultConfig = new Config(new File("config.yml"));
        configCheck(ConfigType.DEFAULT);
    }

    public static void configCheck(ConfigType type) {
        FileConfiguration config;
        if (type == ConfigType.DEFAULT) {
            config = defaultConfig.get();

           config.addDefault("QuickDeposit", "");
            defaultConfig.save();
        }
    }
}