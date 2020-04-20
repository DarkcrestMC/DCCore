package com.Jacksonnn.DCCore.Configuration;

import java.util.HashMap;

public class ConfigType {
    private static final HashMap<String, ConfigType> ALL_TYPES = new HashMap<>();

    static final ConfigType DEFAULT = new ConfigType("Default");

    static final ConfigType LANGUAGE = new ConfigType("Language");

    static final ConfigType ANTICURSE = new ConfigType("AntiCurse");

    static final ConfigType ANNOUNCER = new ConfigType("Announcer");

    public static final ConfigType[] CORE_TYPES = new ConfigType[] { DEFAULT, LANGUAGE, ANTICURSE, ANNOUNCER };

    private String string;

    public ConfigType(String string) {
        this.string = string;
        ALL_TYPES.put(string, this);
    }
}
