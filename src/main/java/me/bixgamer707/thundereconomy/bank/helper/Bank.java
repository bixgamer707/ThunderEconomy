package me.bixgamer707.thundereconomy.bank.helper;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface Bank {

    String getId();

    Map<UUID, Double> getBalances();


    void setBalance(UUID player, double balance);

    void setBalance(OfflinePlayer player, double balance);

    void setBalance(Player player, double balance);

    boolean withdrawBalance(UUID player, double balance);

    boolean withdrawBalance(Player player, double balance);

    boolean withdrawBalance(OfflinePlayer player, double balance);

    boolean depositBalance(UUID player, double balance);

    boolean depositBalance(Player player, double balance);

    boolean depositBalance(OfflinePlayer player, double balance);

    boolean transferBalance(UUID player, UUID target, double balance);

    boolean transferBalance(Player player, Player target, double balance);

    boolean transferBalance(OfflinePlayer player, OfflinePlayer target, double balance);

    double getBalance(Player player);

    double getBalance(OfflinePlayer player);

    double getBalance(UUID player);

    boolean hasBalance(Player player, double balance);

    boolean hasBalance(UUID player, double balance);

    boolean hasBalance(OfflinePlayer player, double balance);

    void removeAccount(OfflinePlayer player);

    void removeAccount(Player player);

    void removeAccount(UUID player);

    void createAccount(OfflinePlayer player, double startBalance);

    void createAccount(Player player, double startBalance);

    void createAccount(UUID player, double startBalance);

    @Deprecated
    void createAccount(Player player);

    @Deprecated
    void createAccount(OfflinePlayer player);

    @Deprecated
    void createAccount(UUID player);

    void removeAllAccounts(Server server);
}
