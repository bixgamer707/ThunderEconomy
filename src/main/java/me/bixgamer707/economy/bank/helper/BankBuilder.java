package me.bixgamer707.economy.bank.helper;

import me.bixgamer707.economy.api.bank.ProcessMethodEnum;
import me.bixgamer707.economy.bank.Bank;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BankBuilder {

    private static Map<String, Bank> banks;
    public BankBuilder(){
        banks = new HashMap<>();
    }

    public static Bank build(ProcessMethodEnum processMethod, String id){
        final Bank bank = banks.get(id);

        switch (processMethod){
            case SYNC: {
                if(bank == null){
                    banks.put(id, new Bank(id));
                }
                return banks.get(id);
            }
            case ASYNC: {
                CompletableFuture.supplyAsync(() -> {
                    if(bank == null){
                        banks.put(id, new Bank(id));
                    }
                    return banks.get(id);
                });
                break;
            }
        }
        return bank;
    }
    public void remove(ProcessMethodEnum processMethod, String id){
        switch (processMethod){
            case SYNC: {
                banks.remove(id);
            }
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    banks.remove(id);
                });
                break;
            }
        }
    }
}