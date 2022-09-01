package me.bixgamer707.thundereconomy.bank.manager;

import me.bixgamer707.thundereconomy.bank.Bank;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public interface BankManager {

    Map<String, Bank> getBanks();

    /*
     *
     *  @param id, The id of the bank you are looking for
     *
     *  @return Returns a boolean, false if it could not create the bank (it already exists) or true if it could create it.
     *
     */

    boolean createBank(String id, Bank bank);

    /*
     *
     *  @param id, The id of the bank you are looking for
     *
     *  @param plugin, Which plugin created the bank
     *
     *  @return Returns a boolean, false if it could not create the bank (it already exists) or true if it could create it.
     *
     */

    boolean createBank(String id, Bank bank, Plugin plugin);

    /*
     *
     *  @param id, The id of the bank you are looking for
     *
     *  @return Returns a boolean, false if it could not delete the bank (it does not exist) or true if it could delete it.
     *
     */

    boolean removeBank(String id);

    /*
     *
     *  @param id, The id of the bank you are looking for
     *
     *  @return Returns a bank interface
     *
     */

    Bank getBank(String id);

    /*
     *
     *  @param id, The id of the bank you are looking for
     *
     *  @param plugin, Which plugin created the bank
     *
     *  @return Returns a bank interface
     *
     */

    Bank getBank(String id, Plugin plugin);

    /*
     *
     *   @param id, The id of the bank you are looking for
     *
     *   @info Search in all possible banks no matter which plugin it is.
     *
     */

    Bank getBankInServer(String id);
}
