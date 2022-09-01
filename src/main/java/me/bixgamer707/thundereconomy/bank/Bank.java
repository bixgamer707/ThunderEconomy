package me.bixgamer707.thundereconomy.bank;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface Bank {

    /*
     *
     * @return Returns a string containing the bank ID
     *
     */

    String getId();

    /*
     *
     * @return Returns the numerical amounts of the players in the bank
     *
     */

    Map<UUID, Double> getBalances();

    /*
     *
     * @param player The uuid of the player for whom the money will be defined
     *
     * @param balance Numerical amount which the player will have in his bank
     *
     */

    void setBalance(UUID player, double balance);

    /*
     *
     * @param player The OfflinePlayer of the player for whom the money will be defined
     *
     * @param balance Numerical amount which the player will have in his bank
     *
     */

    void setBalance(OfflinePlayer player, double balance);

    /*
     *
     * @param player The Player of the player for whom the money will be defined
     *
     * @param balance Numerical amount which the player will have in his bank
     *
     */

    void setBalance(Player player, double balance);

    /*
     *
     * @param player The uuid of the player from whom the money will be removed
     *
     * @param balance Numerical amount to be removed from the player's bench
     *
     * @return Returns false if the player does not have the necessary money, or true if the transaction is successful.
     *
     */

    boolean withdrawBalance(UUID player, double balance);

    /*
     *
     * @param player The Player of the player from whom the money will be removed
     *
     * @param balance Numerical amount to be removed from the player's bench
     *
     * @return Returns false if the player does not have the necessary money, or true if the transaction is successful.
     *
     */

    boolean withdrawBalance(Player player, double balance);

    /*
     *
     * @param player The OfflinePlayer of the player from whom the money will be removed
     *
     * @param balance Numerical amount to be removed from the player's bench
     *
     * @return Returns false if the player does not have the necessary money, or true if the transaction is successful.
     *
     */

    boolean withdrawBalance(OfflinePlayer player, double balance);

    /*
     *
     * @param player The uuid of the player to whom the money is to be given
     *
     * @param balance Numerical amount to be given to the player
     *
     * @return Returns false if the transaction is canceled by the event (ServerTransactionEvent),
     * or returns true if the transaction was successful.
     *
     */

    boolean depositBalance(UUID player, double balance);

    /*
     *
     * @param player The Player of the player to whom the money is to be given
     *
     * @param balance Numerical amount to be given to the player
     *
     * @return Returns false if the transaction is canceled by the event (ServerTransactionEvent),
     * or returns true if the transaction was successful.
     *
     */


    boolean depositBalance(Player player, double balance);

    /*
     *
     * @param player The OfflinePlayer of the player to whom the money is to be given
     *
     * @param balance Numerical amount to be given to the player
     *
     * @return Returns false if the transaction is canceled by the event (ServerTransactionEvent),
     * or returns true if the transaction was successful.
     *
     */

    boolean depositBalance(OfflinePlayer player, double balance);

    /*
     *
     * @param player The uuid of the player from whom the money will be subtracted from the bank to give it to the "target".
     *
     * @param target The uuid of the player to whom the player money will be given.
     *
     * @param balance Numerical amount to be given to the target
     *
     * @return Returns false if the transaction is canceled by the event (ServerTransactionEvent),
     * or returns true if the transaction was successful.
     *
     */


    boolean transferBalance(UUID player, UUID target, double balance);

    /*
     *
     * @param player The Player of the player from whom the money will be subtracted from the bank to give it to the "target".
     *
     * @param target The uuid of the player to whom the player money will be given.
     *
     * @param balance Numerical amount to be given to the target
     *
     * @return Returns false if the transaction is canceled by the event (ServerTransactionEvent),
     * or returns true if the transaction was successful.
     *
     */

    boolean transferBalance(Player player, Player target, double balance);

    /*
     *
     * @param player The OfflinePlayer of the player from whom the money will be subtracted from the bank to give it to the "target".
     *
     * @param target The uuid of the player to whom the player money will be given.
     *
     * @param balance Numerical amount to be given to the target
     *
     * @return Returns false if the transaction is canceled by the event (ServerTransactionEvent),
     * or returns true if the transaction was successful.
     *
     */

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
