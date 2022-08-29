package me.bixgamer707.thundereconomy.bank.helper;

import me.bixgamer707.thundereconomy.bank.manager.Bank;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public interface BankManager {

    Map<String, Bank> getBanks();

    // @param id, The id of the bank you are looking for

    boolean createBank(String id, Bank bank);

    // @param id, The id of the bank you are looking for
    // @param plugin, Which plugin created the bank

    boolean createBank(String id, Bank bank, Plugin plugin);

    // @param id, The id of the bank you are looking for

    boolean removeBank(String id);

    // @param id, The id of the bank you are looking for

    Bank getBank(String id);

    // @param id, The id of the bank you are looking for
    // @param plugin, Which plugin created the bank

    Bank getBank(String id, Plugin plugin);

    // @param id, The id of the bank you are looking for
    // @info Search in all possible banks no matter which plugin it is.

    Bank getBankInServer(String id);
}
