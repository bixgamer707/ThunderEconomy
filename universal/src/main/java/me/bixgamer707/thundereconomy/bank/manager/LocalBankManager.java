package me.bixgamer707.thundereconomy.bank.manager;

import me.bixgamer707.thundereconomy.bank.BankData;

import java.util.HashMap;
import java.util.Map;

public class LocalBankManager implements BankManager {

    private final Map<String, BankData> banks;


    public LocalBankManager(){
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
    public boolean removeBank(String id) {
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
    public BankData getBankInServer(String id){
        return getBank(id);
    }
}
