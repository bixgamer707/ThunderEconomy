package me.bixgamer707.thundereconomy.bank.helper;

import me.bixgamer707.thundereconomy.api.bank.ProcessMethodEnum;
import me.bixgamer707.thundereconomy.bank.Bank;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BankBuilder {

    private static final Map<String, Bank> banks = new HashMap<>();

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
