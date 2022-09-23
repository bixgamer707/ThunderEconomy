package me.bixgamer707.thundereconomy.bank;

import me.bixgamer707.thundereconomy.user.UserData;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface BankData {

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
     * @param player The uuid of the player for whom the money will be defined
     *
     * @param balance Numerical amount which the player will have in his bank
     *
     */

    void setBalance(UUID player, BigDecimal balance);

    /**
     *
     * @param player The uuid of the player from whom the money will be removed
     *
     * @param balance Numerical amount to be removed from the player's bench
     *
     * @return Returns false if the player does not have the necessary money, or true if the transaction is successful.
     *
     */

    boolean withdrawBalance(UUID player, BigDecimal balance);

    /**
     *
     * @param player The uuid of the player to whom the money is to be given
     *
     * @param balance Numerical amount to be given to the player
     *
     * @return Returns false if the transaction is canceled by the event (ServerTransactionEvent),
     * or returns true if the transaction was successful.
     *
     */

    boolean depositBalance(UUID player, BigDecimal balance);

    /**
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


    boolean transferBalance(UUID player, UUID target, BigDecimal balance);

    /**
     *
     * @param player The uuid of the player from get balance.
     *
     * @return Returns the numerical amount of the player's bank.
     *
     */


    BigDecimal getBalance(UUID player);

    UserData getUser(UUID player);

    boolean hasBalance(UUID player, BigDecimal balance);

    void removeAccount(UUID player);

    void createAccount(UUID player, UserData user);

    void load();

    void save();
}
