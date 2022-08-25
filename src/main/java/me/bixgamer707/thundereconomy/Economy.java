package me.bixgamer707.thundereconomy;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy extends JavaPlugin implements Listener {

    private static Economy instance;

    @Override
    public void onLoad(){
        instance = this;

        /*try {
            Class.forName("me.bixgamer707.thundereconomy.Economy");
            getServer().getServicesManager().register(Economy.class, new VaultEconomyProvider(this), this, ServicePriority.Normal);
        } catch (final ClassNotFoundException ignored) {

        }*/
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();
        registerPlaceholders();
        registerTabCompletions();
        registerListeners();


    }

    @Override
    public void onDisable() {
        instance = null;
        getServer().getServicesManager().unregisterAll(this);
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

    @EventHandler
    public void onTransaction(){

    }
}
