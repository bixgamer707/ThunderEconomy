package me.bixgamer707.thundereconomy.bank;

import me.bixgamer707.thundereconomy.user.UserData;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface BukkitBankData extends BankData {

    /**
     *
     * @return Returns a string containing the bank ID
     *
     */

    String getId();

    /**
     *
     * @return Returns the numerical amounts of the players in the bank
     *
     */

    Map<UUID, UserData> getUserDataMap();

    /**
     *
     * @param player The OfflinePlayer of the player for whom the money will be defined
     *
     * @param balance Numerical amount which the player will have in his bank
     *
     */

    void setBalance(OfflinePlayer player, BigDecimal balance);

    /**
     *
     * @param player The Player of the player for whom the money will be defined
     *
     * @param balance Numerical amount which the player will have in his bank
     *
     */

    void setBalance(Player player, BigDecimal balance);

    /**
     *
     * @param player The Player of the player from whom the money will be removed
     *
     * @param balance Numerical amount to be removed from the player's bench
     *
     * @return Returns false if the player does not have the necessary money, or true if the transaction is successful.
     *
     */

    boolean withdrawBalance(Player player, BigDecimal balance);

    /**
     *
     * @param player The OfflinePlayer of the player from whom the money will be removed
     *
     * @param balance Numerical amount to be removed from the player's bench
     *
     * @return Returns false if the player does not have the necessary money, or true if the transaction is successful.
     *
     */

    boolean withdrawBalance(OfflinePlayer player, BigDecimal balance);

    /**
     *
     * @param player The Player of the player to whom the money is to be given
     *
     * @param balance Numerical amount to be given to the player
     *
     * @return Returns false if the transaction is canceled by the event (ServerTransactionEvent),
     * or returns true if the transaction was successful.
     *
     */


    boolean depositBalance(Player player, BigDecimal balance);

    /**
     *
     * @param player The OfflinePlayer of the player to whom the money is to be given
     *
     * @param balance Numerical amount to be given to the player
     *
     * @return Returns false if the transaction is canceled by the event (ServerTransactionEvent),
     * or returns true if the transaction was successful.
     *
     */

    boolean depositBalance(OfflinePlayer player, BigDecimal balance);

    /**
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

    boolean transferBalance(Player player, Player target, BigDecimal balance);

    /**
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


    boolean transferBalance(OfflinePlayer player, OfflinePlayer target, BigDecimal balance);

    /**
     *
     * @param player The player from get balance.
     *
     * @return Returns the numerical amount of the player's bank.
     *
     */

    BigDecimal getBalance(Player player);

    /**
     *
     * @param player The OfflinePlayer of the player from get balance.
     *
     * @return Returns the numerical amount of the player's bank.
     *
     */


    BigDecimal getBalance(OfflinePlayer player);

    /**
     *
     * @param player The uuid of the player from get balance.
     *
     * @return Returns the numerical amount of the player's bank.
     *
     */

    UserData getUser(Player player);

    UserData getUser(OfflinePlayer player);

    boolean hasBalance(Player player, BigDecimal balance);

    boolean hasBalance(OfflinePlayer player, BigDecimal balance);

    void removeAccount(OfflinePlayer player);

    void removeAccount(Player player);

    void createAccount(OfflinePlayer player, UserData user);

    void createAccount(Player player, UserData user);

    void removeAllBanks(Server server);

    void load();

    void save();
}
