package me.bixgamer707.thundereconomy.bank.manager;

import me.bixgamer707.thundereconomy.bank.BankData;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class LocalBankManager implements BankManager {

    private final Map<String, BankData> banks;
    private final Map<Plugin, Map<String, BankData>> banksPlugin;


    public LocalBankManager(){
        banksPlugin = new HashMap<>();
        banks = new HashMap<>();
    }

    @Override
    public Map<String, BankData> getBanks() {
        return banks;
    }

    @Override
    public boolean createBank(String id, BankData bankData) {
        if(banks.containsKey(id)){
            return false;
        }

        banks.put(id, bankData);
        return true;
    }

    @Override
    public boolean createBank(String id, BankData bankData, Plugin plugin) {
        if(banksPlugin.containsKey(plugin)){
            return false;
        }
        Map<String, BankData> banks = banksPlugin.get(plugin);

        if(banks == null){
            banks = new HashMap<>();
        }

        banks.put(id, bankData);

        banksPlugin.put(plugin, banks);
        return true;
    }

    @Override
    public boolean removeBank(String id) {
        if(!banks.containsKey(id)){
            return false;
        }

        banks.remove(id);
        return true;
    }

    @Override
    public boolean removeBank(String id, Plugin plugin) {
        if(!banksPlugin.containsKey(plugin)){
            return false;
        }

        Map<String, BankData> banks = banksPlugin.get(plugin);

        if(!banks.containsKey(id)){
            return false;
        }

        banks.remove(id);
        return true;
    }


    @Override
    public BankData getBank(String id) {
        return banks.get(id);
    }

    @Override
    public BankData getBank(String id, Plugin plugin) {
        Map<String, BankData> banks = banksPlugin.get(plugin);

        if(banks == null){
            return null;
        }

        return banks.get(id);
    }

    @Override
    public BankData getBankInServer(String id){
        for(Plugin plugin : banksPlugin.keySet()){
            Map<String, BankData> banks = banksPlugin.get(plugin);

            if(banks == null){
                continue;
            }

            if(banks.get(id) == null){
                continue;
            }

            return banks.get(id);
        }
        return banks.get(id);
    }

}
