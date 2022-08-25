package me.bixgamer707.economy.bank.helper.actions;

import me.bixgamer707.economy.api.bank.ProcessMethodEnum;
import me.bixgamer707.economy.bank.Bank;
import me.bixgamer707.economy.bank.helper.BankBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BankActions {

    private Bank bank;
    public BankActions(){}

    public BankActions(Bank bank){
        this.bank = bank;
    }

    public static void withdrawBalance(ProcessMethodEnum processMethod, Bank bank, UUID uuid, double balance) {
        switch (processMethod){
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    double afterBalance = 0;
                    if(bank.getBalances().containsKey(uuid)){
                        afterBalance = bank.getBalances().get(uuid);
                    }

                    if(balance >= afterBalance){
                        bank.getBalances().put(uuid, 0.0);
                        return;
                    }

                    bank.getBalances().put(uuid, (afterBalance - balance));
                });
                break;
            }
            case SYNC: {
                double afterBalance = 0;
                if(bank.getBalances().containsKey(uuid)){
                    afterBalance = bank.getBalances().get(uuid);
                }

                if(balance >= afterBalance){
                    bank.getBalances().put(uuid, 0.0);
                    return;
                }

                bank.getBalances().put(uuid, (afterBalance - balance));
                break;
            }
        }
    }

    public static void withdrawBalance(ProcessMethodEnum processMethod, Bank bank, Player player, double balance){
        withdrawBalance(processMethod, bank, player.getUniqueId(), balance);
    }

    public static void withdrawBalance(ProcessMethodEnum processMethod, Bank bank, OfflinePlayer player, double balance) {
        withdrawBalance(processMethod, bank, player.getUniqueId(), balance);
    }

    public static void withdrawBalance(ProcessMethodEnum processMethod, String bankId, UUID uuid, double balance){
        Bank bank = BankBuilder.build(
                processMethod,
                bankId
        );
        switch (processMethod){
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    double afterBalance = 0;
                    if(bank.getBalances().containsKey(uuid)){
                        afterBalance = bank.getBalances().get(uuid);
                    }

                    if(balance >= afterBalance){
                        bank.getBalances().put(uuid, 0.0);
                        return;
                    }

                    bank.getBalances().put(uuid, (afterBalance - balance));
                });
                break;
            }
            case SYNC: {
                double afterBalance = 0;
                if(bank.getBalances().containsKey(uuid)){
                    afterBalance = bank.getBalances().get(uuid);
                }

                if(balance >= afterBalance){
                    bank.getBalances().put(uuid, 0.0);
                    return;
                }

                bank.getBalances().put(uuid, (afterBalance - balance));
                break;
            }
        }
    }

    public static void withdrawBalance(ProcessMethodEnum processMethod, String bank, Player player, double balance){
        withdrawBalance(processMethod, bank, player.getUniqueId(), balance);
    }

    public static void withdrawBalance(ProcessMethodEnum processMethod, String bank, OfflinePlayer player, double balance){
        withdrawBalance(processMethod, bank, player.getUniqueId(), balance);
    }

    @Deprecated
    public static void withdrawBalance(String bank, OfflinePlayer player, double balance){
        withdrawBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    @Deprecated
    public static void withdrawBalance(String bank, Player player, double balance){
        withdrawBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    @Deprecated
    public static void withdrawBalance(String bank, UUID player, double balance){
        withdrawBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    @Deprecated
    public static void withdrawBalance(Bank bank, OfflinePlayer player, double balance){
        withdrawBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    @Deprecated
    public static void withdrawBalance(Bank bank, Player player, double balance){
        withdrawBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    @Deprecated
    public static void withdrawBalance(Bank bank, UUID player, double balance){
        withdrawBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    //Deposit in

    public static void depositBalance(ProcessMethodEnum processMethod, Bank bank, UUID uuid, double balance) {
        switch (processMethod){
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    double afterBalance = 0;
                    if(bank.getBalances().containsKey(uuid)){
                        afterBalance = bank.getBalances().get(uuid);
                    }

                    bank.getBalances().put(uuid, (afterBalance + balance));
                });
                break;
            }
            case SYNC: {
                double afterBalance = 0;
                if(bank.getBalances().containsKey(uuid)){
                    afterBalance = bank.getBalances().get(uuid);
                }

                bank.getBalances().put(uuid, (afterBalance + balance));
                break;
            }
        }
    }

    public static void depositBalance(ProcessMethodEnum processMethod, Bank bank, Player player, double balance){
        depositBalance(processMethod, bank, player.getUniqueId(), balance);
    }

    public static void depositBalance(ProcessMethodEnum processMethod, Bank bank, OfflinePlayer player, double balance) {
        depositBalance(processMethod, bank, player.getUniqueId(), balance);
    }

    public static void depositBalance(ProcessMethodEnum processMethod, String bankId, UUID uuid, double balance){
        Bank bank = BankBuilder.build(
                processMethod,
                bankId
        );
        switch (processMethod){
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    double afterBalance = 0;
                    if(bank.getBalances().containsKey(uuid)){
                        afterBalance = bank.getBalances().get(uuid);
                    }

                    bank.getBalances().put(uuid, (afterBalance + balance));
                });
                break;
            }
            case SYNC: {
                double afterBalance = 0;
                if(bank.getBalances().containsKey(uuid)){
                    afterBalance = bank.getBalances().get(uuid);
                }

                if(balance >= afterBalance){
                    bank.getBalances().put(uuid, 0.0);
                    return;
                }

                bank.getBalances().put(uuid, (afterBalance - balance));
                break;
            }
        }
    }

    public static void depositBalance(ProcessMethodEnum processMethod, String bank, Player player, double balance){
        depositBalance(processMethod, bank, player.getUniqueId(), balance);
    }

    public static void depositBalance(ProcessMethodEnum processMethod, String bank, OfflinePlayer player, double balance){
        depositBalance(processMethod, bank, player.getUniqueId(), balance);
    }

    @Deprecated
    public static void depositBalance(String bank, OfflinePlayer player, double balance){
        depositBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    @Deprecated
    public static void depositBalance(String bank, Player player, double balance){
        depositBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    @Deprecated
    public static void depositBalance(String bank, UUID player, double balance){
        depositBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    @Deprecated
    public static void depositBalance(Bank bank, OfflinePlayer player, double balance){
        depositBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    @Deprecated
    public static void depositBalance(Bank bank, Player player, double balance){
        depositBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    @Deprecated
    public static void depositBalance(Bank bank, UUID player, double balance){
        depositBalance(ProcessMethodEnum.ASYNC, bank, player, balance);
    }

    //Withdraw in
    //BankBuilder.build(
    //    ProcessMethodEnum.ASYNC,
    //    "global*"
    //).getActions()

    public void withdrawBalance(ProcessMethodEnum processMethod, UUID uuid, double balance) {
        Bank bank = this.bank;
        switch (processMethod){
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    double afterBalance = 0;
                    if(bank.getBalances().containsKey(uuid)){
                        afterBalance = bank.getBalances().get(uuid);
                    }

                    if(balance >= afterBalance){
                        bank.getBalances().put(uuid, 0.0);
                        return;
                    }

                    bank.getBalances().put(uuid, (afterBalance - balance));
                });
                break;
            }
            case SYNC: {
                double afterBalance = 0;
                if(bank.getBalances().containsKey(uuid)){
                    afterBalance = bank.getBalances().get(uuid);
                }

                if(balance >= afterBalance){
                    bank.getBalances().put(uuid, 0.0);
                    return;
                }

                bank.getBalances().put(uuid, (afterBalance - balance));
                break;
            }
        }
    }

    public void withdrawBalance(ProcessMethodEnum processMethod, Player player, double balance){
        withdrawBalance(processMethod, player.getUniqueId(), balance);
    }

    public void withdrawBalance(ProcessMethodEnum processMethod, OfflinePlayer player, double balance) {
        withdrawBalance(processMethod, bank, player.getUniqueId(), balance);
    }

    @Deprecated
    public void withdrawBalance(UUID player, double balance){
        withdrawBalance(ProcessMethodEnum.ASYNC, player, balance);
    }

    @Deprecated
    public void withdrawBalance(Player player, double balance){
        withdrawBalance(ProcessMethodEnum.ASYNC, bank, player.getUniqueId(), balance);
    }

    @Deprecated
    public void withdrawBalance(OfflinePlayer player, double balance){
        withdrawBalance(ProcessMethodEnum.ASYNC, bank, player.getUniqueId(), balance);
    }

    //Deposit local

    public void depositBalance(ProcessMethodEnum processMethod, UUID uuid, double balance) {
        Bank bank = this.bank;
        switch (processMethod){
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    double afterBalance = 0;
                    if(bank.getBalances().containsKey(uuid)){
                        afterBalance = bank.getBalances().get(uuid);
                    }

                    bank.getBalances().put(uuid, (afterBalance + balance));
                });
                break;
            }
            case SYNC: {
                double afterBalance = 0;
                if(bank.getBalances().containsKey(uuid)){
                    afterBalance = bank.getBalances().get(uuid);
                }

                bank.getBalances().put(uuid, (afterBalance + balance));
                break;
            }
        }
    }

    public void depositBalance(ProcessMethodEnum processMethod, Player player, double balance){
        depositBalance(processMethod, player.getUniqueId(), balance);
    }

    public void depositBalance(ProcessMethodEnum processMethod, OfflinePlayer player, double balance) {
        depositBalance(processMethod, bank, player.getUniqueId(), balance);
    }

    @Deprecated
    public void depositBalance(UUID player, double balance){
        depositBalance(ProcessMethodEnum.ASYNC, player, balance);
    }

    @Deprecated
    public void depositBalance(Player player, double balance){
        depositBalance(ProcessMethodEnum.ASYNC, bank, player.getUniqueId(), balance);
    }

    @Deprecated
    public void depositBalance(OfflinePlayer player, double balance){
        depositBalance(ProcessMethodEnum.ASYNC, bank, player.getUniqueId(), balance);
    }
}
