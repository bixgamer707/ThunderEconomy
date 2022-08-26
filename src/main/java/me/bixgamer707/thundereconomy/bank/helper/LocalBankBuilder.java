package me.bixgamer707.thundereconomy.bank.helper;

import me.bixgamer707.thundereconomy.bank.manager.Bank;

import java.util.HashMap;
import java.util.Map;

public abstract class LocalBankBuilder implements BankBuilder{

    private final Map<String, Bank> banks;
    public LocalBankBuilder(){
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
    public boolean removeBank(String id) {
        if(!banks.containsKey(id)){
            return false;
        }

        banks.remove(id);
        return true;
    }

    @Override
    public Bank getBank(String id) {
        return null;
    }
}
