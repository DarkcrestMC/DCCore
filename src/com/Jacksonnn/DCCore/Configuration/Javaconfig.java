package com.Jacksonnn.DCCore.Configuration;

import java.io.IOException;
import java.util.logging.Level;
import java.io.InputStream;
import org.bukkit.configuration.Configuration;
import java.io.Reader;
import java.io.InputStreamReader;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import com.Jacksonnn.DCCore


public class Javaconfig
{
    private DCCore plugin;
    private FileConfiguration dataConfig;
    private File configFile;
    
    public Javaconfig(final DCCore plugin) {
        this.dataConfig = null;
        this.configFile = null;
        this.plugin = plugin;
        this.reloadConfig();
    }
    
    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "webhook.yml");
        }
        this.dataConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.configFile);
        final InputStream defaultStream = this.plugin.getResource("webhook.yml");
        if (defaultStream != null) {
            final YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration((Reader)new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults((Configuration)defaultConfig);
        }
    }
    
    public FileConfiguration getConfig() {
        if (this.dataConfig == null) {
            this.reloadConfig();
        }
        return this.dataConfig;
    }
    
    public void saveConfig() {
        if (this.dataConfig != null && this.configFile != null) {
            try {
                this.getConfig().save(this.configFile);
            }
            catch (IOException var2) {
                this.plugin.getLogger().log(Level.SEVERE, "could not save config" + this.configFile, var2);
            }
        }
    }
    
    public void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "webhook.yml");
        }
        if (!this.configFile.exists()) {
            this.plugin.saveResource("webhook.yml", false);
        }
    }
}
