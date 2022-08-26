package me.bixgamer707.thundereconomy;

import me.bixgamer707.thundereconomy.bank.events.ServerTransactionEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        registerCommands();
        registerPlaceholders();
        registerTabCompletions();

        Bukkit.getPluginManager().registerEvents(this,this);

    }

    @Override
    public void onDisable() {
        getServer().getServicesManager().unregisterAll(this);
    }

    public void registerCommands(){
        //getCommand("economy").setExecutor(new EconomyCommand(plugin));
    }

    public void registerTabCompletions(){

    }

    public void registerPlaceholders(){

    }


    @EventHandler
    public void onTransaction(ServerTransactionEvent event){
        if(event.getBank().getId().equals("global*")){
            double balance = event.getMoney() * getConfig().getInt("multiplier");

            event.setMoney(balance);
        }
    }
}
