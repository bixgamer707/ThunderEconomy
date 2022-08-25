package me.bixgamer707.thundereconomy.bank.helper;

import me.bixgamer707.thundereconomy.api.bank.ProcessMethodEnum;
import me.bixgamer707.thundereconomy.bank.Bank;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BankGetter {

    public Bank bank;
    public BankGetter(Bank bank){
        this.bank = bank;
    }

    public static double getDouble(ProcessMethodEnum processMethod, Bank bank, OfflinePlayer player){
        return getDouble(processMethod, bank, player.getUniqueId());
    }

    public static double getDouble(ProcessMethodEnum processMethod, Bank bank, Player player) {
        return getDouble(processMethod, bank.getId(), player.getUniqueId());
    }

    public static double getDouble(ProcessMethodEnum processMethod, Bank bank, UUID player) {
        return getDouble(processMethod, bank.getId(), player);
    }

    public static double getDouble(ProcessMethodEnum processMethod, String bank, UUID player){
        Map<UUID, Double> balances = BankBuilder.build(
                processMethod,
                bank
        ).getBalances();
        switch (processMethod){
            case ASYNC: {
                CompletableFuture.supplyAsync(() -> {
                    if(!balances.containsKey(player)){
                        return 0.0;
                    }
                    return balances.get(player);
                });
                break;
            }
            case SYNC: {
                return balances.get(player);
            }
        }
        return 0.0;
    }

    public static double getDouble(ProcessMethodEnum processMethod, String bank, Player player){
        return getDouble(processMethod, bank, player.getUniqueId());
    }


    public static double getDouble(ProcessMethodEnum processMethod, String bank, OfflinePlayer player) {
        return getDouble(processMethod, bank, player.getUniqueId());
    }


    @Deprecated
    public static double getDouble(Bank bank, Player player){
        return getDouble(ProcessMethodEnum.ASYNC, bank, player.getUniqueId());
    }

    @Deprecated
    public static double getDouble(String bank, Player player){
        return getDouble(ProcessMethodEnum.ASYNC, bank, player.getUniqueId());
    }

    @Deprecated
    public static double getDouble(String bank, OfflinePlayer player){
        return getDouble(ProcessMethodEnum.ASYNC, bank, player.getUniqueId());
    }

    @Deprecated
    public static double getDouble(String bank, UUID player){
        return getDouble(ProcessMethodEnum.ASYNC, bank, player);
    }

    @Deprecated
    public static double getDouble(Bank bank, OfflinePlayer player){
        return getDouble(ProcessMethodEnum.ASYNC, bank, player.getUniqueId());
    }

    @Deprecated
    public static double getDouble(Bank bank, UUID player){
        return getDouble(ProcessMethodEnum.ASYNC, bank, player);
    }

    //Start local variables
    public double getDouble(ProcessMethodEnum processMethod, Player player){
        return getDouble(processMethod, bank, player);
    }

    public double getDouble(ProcessMethodEnum processMethod, OfflinePlayer player){
        return getDouble(processMethod, bank, player);
    }

    public double getDouble(ProcessMethodEnum processMethod, UUID player){
        return getDouble(processMethod, bank, player);
    }

    @Deprecated
    public double getDouble(Player player){
        return getDouble(ProcessMethodEnum.ASYNC, bank, player);
    }

    @Deprecated
    public double getDouble(OfflinePlayer player){
        return getDouble(ProcessMethodEnum.ASYNC, bank, player);
    }

    @Deprecated
    public double getDouble(UUID player){
        return getDouble(ProcessMethodEnum.ASYNC, bank, player);
    }
}
