package me.bixgamer707.thundereconomy.bank;

import me.bixgamer707.thundereconomy.bank.events.PlayerTransactionEvent;
import me.bixgamer707.thundereconomy.bank.events.ServerTransactionEvent;
import me.bixgamer707.thundereconomy.bank.manager.Bank;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class LocalBank implements Bank {

    private final Map<UUID, Double> balances;
    private final String id;
    public LocalBank(String id){
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

    @Override
    public void setBalance(UUID uuid, double balance){
        if(uuid == null){
            return;
        }
        balances.put(uuid, balance);
    }

    @Override
    public void setBalance(OfflinePlayer player, double balance){
        setBalance(player.getUniqueId(), balance);
    }

    @Override
    public void setBalance(Player player, double balance){
        setBalance(player.getUniqueId(), balance);
    }

    @Override
    public boolean withdrawBalance(UUID uuid, double balance) {
        ServerTransactionEvent serverEvent = new ServerTransactionEvent(
                uuid, getBalance(uuid), balance, this
        );
        Bukkit.getPluginManager().callEvent(serverEvent);

        if(!serverEvent.isCancelled()){
            double afterBalance = 0;

            if(getBalances().containsKey(uuid)){
                afterBalance = getBalances().get(uuid);
            }

            if(afterBalance < balance){
                return false;
            }

            getBalances().put(uuid, (afterBalance - balance));
            return true;
        }
        return false;
    }

    @Override
    public boolean withdrawBalance(Player player, double balance){
        return withdrawBalance(player.getUniqueId(), balance);
    }

    @Override
    public boolean withdrawBalance(OfflinePlayer player, double balance) {
        return withdrawBalance(player.getUniqueId(), balance);
    }

    @Override
    public boolean depositBalance(UUID uuid, double balance) {
        ServerTransactionEvent serverEvent = new ServerTransactionEvent(
                uuid, getBalance(uuid), balance, this
        );
        Bukkit.getPluginManager().callEvent(serverEvent);
        if(!serverEvent.isCancelled()){
            double afterBalance = 0;
            if(getBalances().containsKey(uuid)){
                afterBalance = getBalances().get(uuid);
            }

            getBalances().put(uuid, (afterBalance + serverEvent.getMoney()));
            return true;
        }
        return false;
    }

    @Override
    public boolean depositBalance(Player player, double balance){
        return depositBalance(player.getUniqueId(), balance);
    }

    @Override
    public boolean depositBalance(OfflinePlayer player, double balance) {
        return depositBalance(player.getUniqueId(), balance);
    }

    //Getters

    @Override
    public double getBalance(UUID player){
        Map<UUID, Double> balances = getBalances();

        if(!balances.containsKey(player)){
            return 0.0;
        }
        return balances.get(player);
    }

    @Override
    public double getBalance(Player player){
        return getBalance(player.getUniqueId());
    }

    @Override
    public double getBalance(OfflinePlayer player){
        return getBalance(player.getUniqueId());
    }

    @Override
    public boolean hasBalance(UUID player, double doubleBalance) {
        if (!balances.containsKey(player)) {
            return false;
        }

        return balances.get(player) >= doubleBalance;
    }

    @Override
    public boolean hasBalance(Player player, double balance){
        return hasBalance(player.getUniqueId(), balance);
    }

    @Override
    public boolean hasBalance(OfflinePlayer player, double balance){
        return hasBalance(player.getUniqueId(), balance);
    }

    @Override
    public void createAccount(OfflinePlayer player, double startBalance) {
        if(player == null){
            return;
        }

        if(player.isBanned()){
            return;
        }

        balances.put(player.getUniqueId(), startBalance);
    }

    @Override
    public void createAccount(Player player, double startBalance) {
        if(player == null){
            return;
        }

        if(player.isBanned()){
            return;
        }

        balances.put(player.getUniqueId(), startBalance);    }

    @Override
    public void createAccount(UUID uuid, double startBalance) {
        balances.put(uuid, startBalance);
    }

    @Override
    public void removeAccount(Player player) {
        removeAccount(player.getUniqueId());
    }


    @Override
    public void removeAccount(UUID player) {
        if(player == null){
            return;
        }

        balances.remove(player);
    }

    @Override
    public void removeAccount(OfflinePlayer player) {
        removeAccount(player.getUniqueId());
    }

    @Override
    @Deprecated
    public void createAccount(Player player) {
        createAccount(player.getUniqueId(), 0.0);
    }

    @Override
    @Deprecated
    public void createAccount(OfflinePlayer player) {
        createAccount(player.getUniqueId());
    }

    @Override
    @Deprecated
    public void createAccount(UUID player) {
        createAccount(player, 0.0);
    }

    @Override
    @Deprecated
    public void removeAllAccounts(Server server){
        RegisteredServiceProvider<Bank> econ = server.getServicesManager().getRegistration(Bank.class);
        if(econ == null){
            return;
        }

        server.getOnlinePlayers().forEach(players -> econ.getProvider().removeAccount(players.getUniqueId()));
        Arrays.stream(server.getOfflinePlayers()).forEach(players -> econ.getProvider().removeAccount(players));

    }

    @Override
    public boolean transferBalance(UUID player, UUID target, double balance){
        if(player == null || target == null){
            return false;
        }

        if(getBalance(player) >= balance){
            PlayerTransactionEvent playerEvent = new PlayerTransactionEvent(
                    player, target, getBalance(player), balance, this
            );
            Bukkit.getPluginManager().callEvent(playerEvent);

            if(!playerEvent.isCancelled()){
                double afterBalance = 0;

                if(getBalances().containsKey(player)){
                    afterBalance = getBalances().get(player);
                }

                if(afterBalance < balance){
                    return false;
                }

                getBalances().put(player, (afterBalance - balance));
                getBalances().put(target, balance);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean transferBalance(Player player, Player target, double balance){
        return transferBalance(player.getUniqueId(), target.getUniqueId(), balance);
    }

    @Override
    public boolean transferBalance(OfflinePlayer player, OfflinePlayer target, double balance){
        return transferBalance(player.getUniqueId(), target.getUniqueId(), balance);
    }

}
