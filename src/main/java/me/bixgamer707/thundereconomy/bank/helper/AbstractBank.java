package me.bixgamer707.thundereconomy.bank.helper;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface AbstractBank {

    String getId();

    Map<UUID, Double> getBalances();

    boolean withdrawBalance(ProcessMethodEnum processMethod, UUID uuid, double balance);

    boolean withdrawBalance(ProcessMethodEnum processMethod, Player player, double balance);

    boolean withdrawBalance(ProcessMethodEnum processMethod, OfflinePlayer player, double balance);

    void setBalance(ProcessMethodEnum processMethod, UUID player, double balance);

    void setBalance(ProcessMethodEnum processMethod, OfflinePlayer player, double balance);

    void setBalance(ProcessMethodEnum processMethod, Player player, double balance);

    @Deprecated
    void setBalance(UUID player, double balance);

    @Deprecated
    void setBalance(OfflinePlayer player, double balance);

    @Deprecated
    void setBalance(Player player, double balance);

    @Deprecated
    boolean withdrawBalance(UUID player, double balance);

    @Deprecated
    boolean withdrawBalance(Player player, double balance);

    @Deprecated
    boolean withdrawBalance(OfflinePlayer player, double balance);

    boolean depositBalance(ProcessMethodEnum processMethod, UUID uuid, double balance);

    boolean depositBalance(ProcessMethodEnum processMethod, Player player, double balance);

    boolean depositBalance(ProcessMethodEnum processMethod, OfflinePlayer player, double balance);

    @Deprecated
    boolean depositBalance(UUID player, double balance);

    @Deprecated
    boolean depositBalance(Player player, double balance);

    @Deprecated
    boolean depositBalance(OfflinePlayer player, double balance);

    double getBalance(ProcessMethodEnum processMethod, UUID player);

    double getBalance(ProcessMethodEnum processMethod, Player player);

    double getBalance(ProcessMethodEnum processMethod, OfflinePlayer player);

    @Deprecated
    double getBalance(Player player);

    @Deprecated
    double getBalance(OfflinePlayer player);

    @Deprecated
    double getBalance(UUID player);

    boolean hasBalance(ProcessMethodEnum processMethod, UUID player, double doubleBalance);

    @Deprecated
    boolean hasBalance(Player player, double balance);

    boolean hasBalance(ProcessMethodEnum processMethod, Player player, double balance);

    boolean hasBalance(ProcessMethodEnum processMethod, OfflinePlayer player, double balance);

    @Deprecated
    boolean hasBalance(UUID player, double balance);

    @Deprecated
    boolean hasBalance(OfflinePlayer player, double balance);

    void createOfflinePlayer(ProcessMethodEnum processMethod, OfflinePlayer player, double startBalance);

    void createPlayer(ProcessMethodEnum processMethod, Player player, double startBalance);

    void createPlayerWithUUID(ProcessMethodEnum processMethod, UUID uuid, double startBalance);

    void removeOfflinePlayer(ProcessMethodEnum processMethod, OfflinePlayer player);

    @Deprecated
    void removeOfflinePlayer(OfflinePlayer player);

    @Deprecated
    void removePlayer(Player player);

    @Deprecated
    void removePlayerWithUUID(UUID player);

    @Deprecated
    void createOfflinePlayer(OfflinePlayer player, double startBalance);

    @Deprecated
    void createPlayer(Player player, double startBalance);

    @Deprecated
    void createPlayerWithUUID(UUID player, double startBalance);

    @Deprecated
    void createPlayer(Player player);

    @Deprecated
    void createOfflinePlayer(OfflinePlayer player);

    @Deprecated
    void createPlayerWithUUID(UUID player);

    void removePlayer(ProcessMethodEnum processMethod, Player player);

    void removePlayerWithUUID(ProcessMethodEnum processMethod, UUID uuid);
}
