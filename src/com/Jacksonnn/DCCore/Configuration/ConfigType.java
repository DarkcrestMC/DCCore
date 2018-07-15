package com.Jacksonnn.DCCore.Configuration;

import java.util.HashMap;

public class ConfigType {

    private static final HashMap<String, ConfigType> ALL_TYPES = new HashMap<>();

    public static final ConfigType DEFAULT = new ConfigType("Default");
    public static final ConfigType LANGUAGE = new ConfigType("Language");
    public static final ConfigType[] CORE_TYPES = { DEFAULT, LANGUAGE };

    private String string;

    public ConfigType(String string) {
        this.string = string;
        ALL_TYPES.put(string, this);
    }
}