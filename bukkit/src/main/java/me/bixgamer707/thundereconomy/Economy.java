package me.bixgamer707.thundereconomy;

import me.bixgamer707.thundereconomy.commands.EconomyCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
    }

    @Override
    public void onDisable() {
        getServer().getServicesManager().unregisterAll(this);
    }

    public void registerCommands(){
        getCommand("thundereconomy").setExecutor(new EconomyCommand(this));
    }
}
