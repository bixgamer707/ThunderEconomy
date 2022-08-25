package me.bixgamer707.thundereconomy.bank;

import me.bixgamer707.thundereconomy.api.bank.BankProcess;
import me.bixgamer707.thundereconomy.api.bank.ProcessMethodEnum;
import me.bixgamer707.thundereconomy.bank.helper.actions.BankActions;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Bank extends BankProcess {

    private final String id;
    private final Map<UUID, Double> balances;
    private final BankActions bankActions;

    public Bank(String id){
        this.id = id;
        this.balances = new HashMap<>();
        this.bankActions = new BankActions(this);
    }

    public String getId() {
        return id;
    }

    public Map<UUID, Double> getBalances() {
        return balances;
    }

    public BankActions actions() {
        return bankActions;
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
                CompletableFuture.runAsync(() -> {
                    balances.put(uuid, startBalance);
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
        CompletableFuture.runAsync(() -> {
            if(player == null){
                return;
            }

            balances.remove(player.getUniqueId());
        });
    }

    @Override
    @Deprecated
    public void removePlayer(Player player) {
        CompletableFuture.runAsync(() -> {
            if(player == null){
                return;
            }

            balances.remove(player.getUniqueId());
        });
    }

    @Override
    @Deprecated
    public void removePlayerWithUUID(UUID player) {
        CompletableFuture.runAsync(() -> {
            if (player == null) {
                return;
            }

            balances.remove(player);
        });
    }

    @Override
    @Deprecated
    public void createOfflinePlayer(OfflinePlayer player, double startBalance) {
        CompletableFuture.runAsync(() -> {
            if(player == null){
                return;
            }

            if(player.isBanned()){
                return;
            }

            balances.put(player.getUniqueId(), startBalance);
        });
    }

    @Override
    public void createPlayer(Player player, double startBalance) {
        CompletableFuture.runAsync(() -> {
            if(player == null){
                return;
            }

            if(player.isBanned()){
                return;
            }

            balances.put(player.getUniqueId(), startBalance);
        });
    }

    @Override
    @Deprecated
    public void createPlayerWithUUID(UUID player, double startBalance) {
        CompletableFuture.runAsync(() -> {
            if(player == null){
                return;
            }

            balances.put(player, 0.0);
        });
    }

    @Override
    @Deprecated
    public void createPlayer(Player player) {
        CompletableFuture.runAsync(() -> {
            if(player == null){
                return;
            }

            if(player.isBanned()){
                return;
            }

            balances.put(player.getUniqueId(), 0.0);
        });
    }

    @Override
    @Deprecated
    public void createOfflinePlayer(OfflinePlayer player) {
        CompletableFuture.runAsync(() -> {
            if(player == null){
                return;
            }

            if(player.isBanned()){
                return;
            }

            balances.put(player.getUniqueId(), 0.0);
        });
    }

    @Override
    @Deprecated
    public void createPlayerWithUUID(UUID player) {
        CompletableFuture.runAsync(() -> {
            if(player == null){
                return;
            }

            balances.put(player, 0.0);
        });
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
    public void removePlayerWithUUID(ProcessMethodEnum processMethod, UUID uuid) {
        switch (processMethod){
            case SYNC: {
                if(uuid == null){
                    return;
                }

                balances.remove(uuid);
                break;
            }
            case ASYNC: {
                CompletableFuture.runAsync(() -> {
                    if(uuid == null){
                        return;
                    }

                    balances.remove(uuid);
                });
                break;
            }
        }
    }
}
