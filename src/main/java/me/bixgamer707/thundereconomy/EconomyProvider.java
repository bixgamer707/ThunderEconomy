package me.bixgamer707.thundereconomy;

import me.bixgamer707.thundereconomy.bank.Bank;
import me.bixgamer707.thundereconomy.bank.helper.BankGetter;
import me.bixgamer707.thundereconomy.bank.helper.actions.BankActions;

public class EconomyProvider {
    public Economy plugin;

    public EconomyProvider(Economy plugin){
        this.plugin = plugin;
    }

    public BankGetter getBankGetter(Bank bank){
        return new BankGetter(bank);
    }

    public BankActions getBankActions(Bank bank){
        return new BankActions(bank);
    }
}
