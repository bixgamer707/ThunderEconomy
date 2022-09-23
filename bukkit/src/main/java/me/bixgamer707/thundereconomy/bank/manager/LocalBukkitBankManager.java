package me.bixgamer707.thundereconomy.bank.manager;

import me.bixgamer707.thundereconomy.bank.BukkitBankData;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class LocalBukkitBankManager extends LocalBankManager implements BukkitBankManager {

    private final Map<Plugin, Map<String, BukkitBankData>> banksPlugin;

    public LocalBukkitBankManager(){
        banksPlugin = new HashMap<>();
    }

    @Override
    public Map<Plugin, Map<String, BukkitBankData>> getBanksPlugin() {
        return banksPlugin;
    }

    @Override
    public boolean createBank(String id, BukkitBankData bukkitBankData, Plugin plugin) {
        if(banksPlugin.containsKey(plugin)){
            return false;
        }
        Map<String, BukkitBankData> banks = banksPlugin.get(plugin);

        if(banks == null){
            banks = new HashMap<>();
        }

        banks.put(id, bukkitBankData);

        banksPlugin.put(plugin, banks);
        return true;
    }

    @Override
    public boolean removeBank(String id, Plugin plugin) {
        if(!banksPlugin.containsKey(plugin)){
            return false;
        }

        Map<String, BukkitBankData> banks = banksPlugin.get(plugin);

        if(!banks.containsKey(id)){
            return false;
        }

        banks.remove(id);
        return true;
    }

    @Override
    public BukkitBankData getBank(String id, Plugin plugin) {
        Map<String, BukkitBankData> banks = banksPlugin.get(plugin);

        if(banks == null){
            return null;
        }

        return banks.get(id);
    }

    @Override
    public BukkitBankData getBankInServer(String id){
        for(Plugin plugin : banksPlugin.keySet()){
            Map<String, BukkitBankData> banks = banksPlugin.get(plugin);

            if(banks == null){
                continue;
            }

            if(banks.get(id) == null){
                continue;
            }

            return banks.get(id);
        }
        return (BukkitBankData) super.getBank(id);
    }

}
