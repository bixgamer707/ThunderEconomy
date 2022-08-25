package me.bixgamer707.thundereconomy.bank;

import me.bixgamer707.thundereconomy.bank.helper.ProcessMethodEnum;
import me.bixgamer707.thundereconomy.bank.helper.AbstractBank;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class Bank implements AbstractBank {

    private final Map<UUID, Double> balances;
    private final String id;
    public Bank(String id){
        this.id = id;
        this.balances = new HashMap<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Map<UUID, Double> getBalances() {
        return balances;
    }

    //Withdraw in
    //BankBuilder.build(
    //    ProcessMethodEnum.ASYNC,
    //    "global*"
    //).getActions()

    @Override
    public void setBalance(ProcessMethodEnum processMethod, UUID uuid, double balance){
        switch (processMethod){
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    if(uuid == null){
                        return;
                    }
                    balances.put(uuid, balance);
                });
                break;
            }
            case SYNC: {
                if(uuid == null){
                    return;
                }

                balances.put(uuid, balance);
            }
        }
    }

    @Override
    public void setBalance(ProcessMethodEnum processMethod, Player player, double balance){
        setBalance(processMethod, player.getUniqueId(), balance);
    }

    @Override
    public void setBalance(ProcessMethodEnum processMethod, OfflinePlayer player, double balance){
        setBalance(processMethod, player.getUniqueId(), balance);
    }

    @Deprecated
    @Override
    public void setBalance(OfflinePlayer player, double balance){
        setBalance(ProcessMethodEnum.ASYNC, player.getUniqueId(), balance);
    }

    @Deprecated
    @Override
    public void setBalance(Player player, double balance){
        setBalance(ProcessMethodEnum.ASYNC, player.getUniqueId(), balance);
    }

    @Deprecated
    @Override
    public void setBalance(UUID player, double balance){
        setBalance(ProcessMethodEnum.ASYNC, player, balance);
    }

    @Override
    public boolean withdrawBalance(ProcessMethodEnum processMethod, UUID uuid, double balance) {
        switch (processMethod){
            case ASYNC: {
                CompletableFuture.supplyAsync(() -> {
                    double afterBalance = 0;
                    if(getBalances().containsKey(uuid)){
                        afterBalance = getBalances().get(uuid);
                    }

                    if(afterBalance < balance){
                        return false;
                    }

                    getBalances().put(uuid, (afterBalance - balance));
                    return true;
                });
                break;
            }
            case SYNC: {
                double afterBalance = 0;
                if(getBalances().containsKey(uuid)){
                    afterBalance = getBalances().get(uuid);
                }

                if(balance >= afterBalance){
                    return false;
                }

                getBalances().put(uuid, (afterBalance - balance));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean withdrawBalance(ProcessMethodEnum processMethod, Player player, double balance){
        return withdrawBalance(processMethod, player.getUniqueId(), balance);
    }

    @Override
    public boolean withdrawBalance(ProcessMethodEnum processMethod, OfflinePlayer player, double balance) {
        return withdrawBalance(processMethod, player.getUniqueId(), balance);
    }

    @Override
    @Deprecated
    public boolean withdrawBalance(UUID player, double balance){
        return withdrawBalance(ProcessMethodEnum.ASYNC, player, balance);
    }

    @Override
    @Deprecated
    public boolean withdrawBalance(Player player, double balance){
        return withdrawBalance(ProcessMethodEnum.ASYNC, player.getUniqueId(), balance);
    }

    @Override
    @Deprecated
    public boolean withdrawBalance(OfflinePlayer player, double balance){
        return withdrawBalance(ProcessMethodEnum.ASYNC, player.getUniqueId(), balance);
    }

    @Override
    public boolean depositBalance(ProcessMethodEnum processMethod, UUID uuid, double balance) {
        switch (processMethod){
            case ASYNC: {
                CompletableFuture.supplyAsync(() -> {
                    double afterBalance = 0;
                    if(getBalances().containsKey(uuid)){
                        afterBalance = getBalances().get(uuid);
                    }

                    getBalances().put(uuid, (afterBalance + balance));
                    return true;
                });
                break;
            }
            case SYNC: {
                double afterBalance = 0;
                if(getBalances().containsKey(uuid)) {
                    afterBalance = getBalances().get(uuid);
                }
                getBalances().put(uuid, (afterBalance + balance));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean depositBalance(ProcessMethodEnum processMethod, Player player, double balance){
        return depositBalance(processMethod, player.getUniqueId(), balance);
    }

    @Override
    public boolean depositBalance(ProcessMethodEnum processMethod, OfflinePlayer player, double balance) {
        return depositBalance(processMethod, player.getUniqueId(), balance);
    }

    @Override
    @Deprecated
    public boolean depositBalance(UUID player, double balance){
        return depositBalance(ProcessMethodEnum.ASYNC, player, balance);
    }

    @Override
    @Deprecated
    public boolean depositBalance(Player player, double balance){
        return depositBalance(ProcessMethodEnum.ASYNC, player.getUniqueId(), balance);
    }

    @Override
    @Deprecated
    public boolean depositBalance(OfflinePlayer player, double balance){
        return depositBalance(ProcessMethodEnum.ASYNC, player.getUniqueId(), balance);
    }

    //Getters

    @Override
    public double getBalance(ProcessMethodEnum processMethod, UUID player){
        Map<UUID, Double> balances = getBalances();
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

    @Override
    public double getBalance(ProcessMethodEnum processMethod, Player player){
        return getBalance(processMethod, player.getUniqueId());
    }

    @Override
    public double getBalance(ProcessMethodEnum processMethod, OfflinePlayer player){
        return getBalance(processMethod, player.getUniqueId());
    }

    @Override
    @Deprecated
    public double getBalance(Player player){
        return getBalance(ProcessMethodEnum.ASYNC, player.getUniqueId());
    }

    @Override
    @Deprecated
    public double getBalance(OfflinePlayer player){
        return getBalance(ProcessMethodEnum.ASYNC, player.getUniqueId());
    }

    @Override
    @Deprecated
    public double getBalance(UUID player){
        return getBalance(ProcessMethodEnum.ASYNC, player);
    }

    @Override
    public boolean hasBalance(ProcessMethodEnum processMethod, UUID player, double doubleBalance) {
        Map<UUID, Double> balances = getBalances();
        switch (processMethod) {
            case ASYNC: {
                CompletableFuture.supplyAsync(() -> {
                    if (!balances.containsKey(player)) {
                        return false;
                    }

                    return balances.get(player) >= doubleBalance;
                });
            }
            case SYNC: {
                if (!balances.containsKey(player)) {
                    return false;
                }

                double balance = balances.get(player);
                if (balance >= doubleBalance) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasBalance(ProcessMethodEnum processMethod, Player player, double balance){
        return hasBalance(processMethod, player.getUniqueId(), balance);
    }

    @Override
    public boolean hasBalance(ProcessMethodEnum processMethod, OfflinePlayer player, double balance){
        return hasBalance(processMethod, player.getUniqueId(), balance);
    }

    @Override
    @Deprecated
    public boolean hasBalance(UUID player, double balance){
        return hasBalance(ProcessMethodEnum.ASYNC, player, balance);
    }

    @Override
    @Deprecated
    public boolean hasBalance(Player player, double balance){
        return hasBalance(ProcessMethodEnum.ASYNC, player.getUniqueId(), balance);
    }

    @Override
    @Deprecated
    public boolean hasBalance(OfflinePlayer player, double balance){
        return hasBalance(ProcessMethodEnum.ASYNC, player.getUniqueId(), balance);
    }

    @Override
    public void createOfflinePlayer(ProcessMethodEnum processMethod, OfflinePlayer player, double startBalance) {
        switch (processMethod){
            case SYNC: {
                if(player == null){
                    return;
                }

                balances.put(player.getUniqueId(), startBalance);
                break;
            }
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    if(player == null){
                        return;
                    }

                    if(player.isBanned()){
                        return;
                    }

                    balances.put(player.getUniqueId(), startBalance);
                });
                break;
            }
        }
    }

    @Override
    public void createPlayer(ProcessMethodEnum processMethod, Player player, double startBalance) {
        switch (processMethod){
            case SYNC: {
                if(player == null){
                    return;
                }

                balances.put(player.getUniqueId(), startBalance);
                break;
            }
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    if(player == null){
                        return;
                    }

                    if(player.isBanned()){
                        return;
                    }

                    balances.put(player.getUniqueId(), startBalance);
                });
                break;
            }
        }
    }

    @Override
    public void createPlayerWithUUID(ProcessMethodEnum processMethod, UUID uuid, double startBalance) {
        switch (processMethod){
            case SYNC: {
                balances.put(uuid, startBalance);
                break;
            }
            case ASYNC: {
                CompletableFuture.runAsync(() -> balances.put(uuid, startBalance));
                break;
            }
        }
    }

    @Override
    public void removePlayer(ProcessMethodEnum processMethod, Player player) {
        switch (processMethod){
            case SYNC: {
                if(player == null){
                    return;
                }

                balances.remove(player.getUniqueId());
                break;
            }
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    if(player == null){
                        return;
                    }

                    balances.remove(player.getUniqueId());
                });
                break;
            }
        }
    }

    @Override
    public void removePlayerWithUUID(ProcessMethodEnum processMethod, UUID player) {
        switch (processMethod){
            case SYNC: {
                if(player == null){
                    return;
                }

                balances.remove(player);
                break;
            }
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    if(player == null){
                        return;
                    }

                    balances.remove(player);
                });
                break;
            }
        }
    }

    @Override
    public void removeOfflinePlayer(ProcessMethodEnum processMethod, OfflinePlayer player) {
        switch (processMethod){
            case SYNC: {
                if(player == null){
                    return;
                }

                balances.remove(player.getUniqueId());
                break;
            }
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    if(player == null){
                        return;
                    }

                    balances.remove(player.getUniqueId());
                });
                break;
            }
        }
    }

    @Override
    @Deprecated
    public void removeOfflinePlayer(OfflinePlayer player) {
        removeOfflinePlayer(ProcessMethodEnum.ASYNC, player);
    }

    @Override
    @Deprecated
    public void removePlayer(Player player) {
        removePlayer(ProcessMethodEnum.ASYNC, player);
    }

    @Override
    @Deprecated
    public void removePlayerWithUUID(UUID player) {
        removePlayerWithUUID(ProcessMethodEnum.ASYNC, player);
    }

    @Override
    @Deprecated
    public void createOfflinePlayer(OfflinePlayer player, double startBalance) {
        createOfflinePlayer(ProcessMethodEnum.ASYNC, player, startBalance);
    }

    @Override
    public void createPlayer(Player player, double startBalance) {
        createPlayer(ProcessMethodEnum.ASYNC, player, startBalance);
    }

    @Override
    @Deprecated
    public void createPlayerWithUUID(UUID player, double startBalance) {
        createPlayerWithUUID(ProcessMethodEnum.ASYNC, player, startBalance);
    }

    @Override
    @Deprecated
    public void createPlayer(Player player) {
        createPlayer(ProcessMethodEnum.ASYNC, player, 0.0);
    }

    @Override
    @Deprecated
    public void createOfflinePlayer(OfflinePlayer player) {
        createPlayerWithUUID(player.getUniqueId());
    }

    @Override
    @Deprecated
    public void createPlayerWithUUID(UUID player) {
        createPlayerWithUUID(ProcessMethodEnum.ASYNC, player, 0.0);
    }

}
