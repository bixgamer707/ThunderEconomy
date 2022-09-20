package me.bixgamer707.thundereconomy.file;

import me.bixgamer707.thundereconomy.Economy;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class YamlFile extends YamlConfiguration {

    private final Economy plugin;
    private final String fileName;
    private final File folder;

    public YamlFile(Economy plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }

    public YamlFile(Economy plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }

    public YamlFile(Economy plugin, String fileName, String fileExtension, File folder) {
        this.folder = folder;
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(fileExtension) ? "" : fileExtension);
        this.create();
    }

    @Override
    public String getString(String path) {
        // Return the specified path in case the result is null
        return super.getString(path, path);
    }

    public void create() {
        try {
            File file = new File(this.folder, this.fileName);
            if (file.exists()) {
                this.load(file);
                this.save(file);
            } else {
                if (this.plugin.getResource(this.fileName) != null) {
                    this.plugin.saveResource(this.fileName, false);
                } else {
                    this.save(file);
                }

                this.load(file);
            }
        } catch (IOException | InvalidConfigurationException exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Unable to create '" + this.fileName + "'.", exception);
        }
    }

    public void save() {
        File file = new File(this.folder, this.fileName);

        try {
            this.save(file);
        } catch (IOException exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Unable to save file '" + this.fileName + "'.", exception);
        }

    }

    public void reload() {
        File file = new File(folder, this.fileName);

        try {
            this.load(file);
        } catch (InvalidConfigurationException | IOException exception) {
            this.plugin.getLogger().log(Level.SEVERE, "Unable to reload file '" + this.fileName + "'.", exception);
        }
    }
}
