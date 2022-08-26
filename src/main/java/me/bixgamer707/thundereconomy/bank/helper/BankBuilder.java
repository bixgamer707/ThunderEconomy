package me.bixgamer707.thundereconomy.bank.helper;

import me.bixgamer707.thundereconomy.bank.manager.Bank;

import java.util.Map;

public interface BankBuilder {

    Map<String, Bank> getBanks();

    // @param id Id of the bank

    boolean createBank(String id, Bank bank);

    // @param id Id of the bank

    boolean removeBank(String id);

    // @param id Id of the bank

    Bank getBank(String id);
}
