package me.hypews.coldtimer.core.utils;

import me.hypews.coldtimer.ColdTimer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigUtils {
    String name;

    public ConfigUtils(String s) {
        this.name = s;
    }

    public FileConfiguration getConfig() {
        File file = new File(ColdTimer.getInstance().getDataFolder(), this.name + ".yml");
        return YamlConfiguration.loadConfiguration(file);
    }
}