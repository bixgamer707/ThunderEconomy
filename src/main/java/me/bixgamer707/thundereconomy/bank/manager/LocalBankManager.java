package me.bixgamer707.thundereconomy.bank.manager;

import me.bixgamer707.thundereconomy.bank.Bank;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public abstract class LocalBankManager implements BankManager {

    private final Map<String, Bank> banks;
    private final Map<Plugin, Map<String, Bank>> banksPlugin;


    public LocalBankManager(){
        banksPlugin = new HashMap<>();
        banks = new HashMap<>();
    }

    @Override
    public Map<String, Bank> getBanks() {
        return banks;
    }

    @Override
    public boolean createBank(String id, Bank bank) {
        if(banks.containsKey(id)){
            return false;
        }

        banks.put(id, bank);
        return true;
    }

    @Override
    public boolean createBank(String id, Bank bank, Plugin plugin) {
        if(banks.containsKey(id)){
            return false;
        }
        Map<String, Bank> banks = banksPlugin.get(plugin);

        if(banks == null){
            banks = new HashMap<>();
        }

        banks.put(id, bank);

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
    public Bank getBank(String id) {
        return banks.get(id);
    }

    @Override
    public Bank getBank(String id, Plugin plugin) {
        Map<String, Bank> banks = banksPlugin.get(plugin);

        if(banks == null){
            return null;
        }

        return banks.get(id);
    }

    @Override
    public Bank getBankInServer(String id){
        for(Plugin plugin : banksPlugin.keySet()){
            Map<String, Bank> banks = banksPlugin.get(plugin);

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
