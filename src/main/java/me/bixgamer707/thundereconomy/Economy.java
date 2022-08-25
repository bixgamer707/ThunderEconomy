package me.bixgamer707.thundereconomy;

import me.bixgamer707.thundereconomy.api.bank.ProcessMethodEnum;
import me.bixgamer707.thundereconomy.bank.helper.BankBuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy extends JavaPlugin {

    private static Economy instance;
    private EconomyProvider provider;

    @Override
    public void onLoad(){
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();
        registerPlaceholders();
        registerTabCompletions();
        registerListeners();

        provider = new EconomyProvider(this);
        BankBuilder.build(
                ProcessMethodEnum.ASYNC,
                "global*"
        );
        getServer().getServicesManager().register(EconomyProvider.class, provider, this, ServicePriority.Normal);
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

    public EconomyProvider getProvider() {
        return provider;
    }
}
