package me.bixgamer707.thundereconomy.bank.events;

import me.bixgamer707.thundereconomy.bank.Bank;
import me.bixgamer707.thundereconomy.bank.helper.TransactionType;
import me.bixgamer707.thundereconomy.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.UUID;

public class ServerTransactionEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;

    private final UUID uuid;
    private UserData user;
    private BigDecimal money;
    private final Bank bank;
    private final TransactionType type;

    public ServerTransactionEvent(UUID playerUUID, UserData user, BigDecimal money, Bank bank,
                                  TransactionType type) {
        this.uuid = playerUUID;
        this.user = user;
        this.money = money;
        this.bank = bank;
        this.type = type;
        this.isCancelled = false;
    }

    @Contract(pure = true)
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public UUID getUUID() {
        return uuid;
    }

    @Nullable
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public BigDecimal getMoney() {
        return money;
    }

    public UserData getUser() {
        return user;
    }

    public Bank getBank() {
        return bank;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.isCancelled=cancelled;
    }
}
