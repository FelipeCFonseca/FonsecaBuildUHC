package me.felipefonseca.plugins.manager.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import me.felipefonseca.plugins.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class LangConfiguration {

    private final Main plugin;

    public LangConfiguration(Main plugin) {
        this.plugin = plugin;
        langConfiguration = new YamlConfiguration();
    }

    File langFile;
    private final YamlConfiguration langConfiguration;

    public void init() throws IOException, FileNotFoundException,
            InvalidConfigurationException {
        langFile = new File(plugin.getDataFolder(), "lang.yml");
        if (!langFile.exists()) {
            plugin.saveResource("lang.yml", true);
        }
        langConfiguration.load(langFile);
    }

    public void save() {
        try {
            langConfiguration.save(langFile);
        } catch (IOException e) {
        }
    }

    public YamlConfiguration getLangConfiguration() {
        return langConfiguration;
    }

}
