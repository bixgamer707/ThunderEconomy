package me.bixgamer707.economy;

import me.bixgamer707.economy.api.bank.ProcessMethodEnum;
import me.bixgamer707.economy.bank.helper.BankBuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy extends JavaPlugin {

    private static Economy instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        new BankBuilder();
        registerCommands();
        registerPlaceholders();
        registerTabCompletions();
        registerListeners();

        BankBuilder.build(
                ProcessMethodEnum.ASYNC,
                "global*"
        );
        Bukkit.getServicesManager().register(Economy.class, this, this, ServicePriority.Highest);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void registerCommands(){
        //getCommand("economy").setExecutor(new EconomyCommand(plugin));
    }

    public void registerTabCompletions(){

    }

    public void registerListeners(Listener... listeners){
        for(Listener listener : listeners){
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    public void registerPlaceholders(){

    }

    public static Economy getInstance() {
        return instance;
    }
}
