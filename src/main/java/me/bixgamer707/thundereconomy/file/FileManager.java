package me.bixgamer707.thundereconomy.file;

import me.bixgamer707.thundereconomy.Economy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private final Economy plugin;

    private File[] files;
    private final Map<String, YamlFile> filesMap = new HashMap<>();

    public FileManager(Economy plugin){
        this.plugin = plugin;
    }

    public void register(){
        filesMap.put("config", new YamlFile(plugin, "config", ".yml"));
        filesMap.put("banks", new YamlFile(plugin, "banks", ".yml"));
        files = plugin.getDataFolder().listFiles();
        loadFiles();
    }

    private void loadFiles() {
        if (files.length == 0) {
            filesMap.put("config", new YamlFile(plugin, "config", ".yml"));
            filesMap.put("banks", new YamlFile(plugin, "banks", ".yml"));
        }

        for (File file : files) {
            if (file.getName().endsWith(".yml")) {
                filesMap.put(file.getName(), new YamlFile(plugin, file.getName(), ".yml"));
            }
        }
    }

    public void reloadFiles() {
        filesMap.clear();
        register();
    }

    public YamlFile getFile(String name) {
        return filesMap.get(name);
    }

    public void loadBanks(){
        YamlFile banks = getFile("banks");
        banks.getConfigurationSection(
                "banks"
        ).getKeys(false).forEach(id -> {

        });

    }

    public void stop() {
        filesMap.forEach((key, value) -> value.save());
        filesMap.clear();
    }
}
