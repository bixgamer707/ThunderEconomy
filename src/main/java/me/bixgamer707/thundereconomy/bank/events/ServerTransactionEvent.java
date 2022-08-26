package me.bixgamer707.thundereconomy.bank.events;

import me.bixgamer707.thundereconomy.bank.helper.Bank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ServerTransactionEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;

    private final UUID uuid;
    private double balancePlayer;
    private double money;
    private final Bank bank;

    public ServerTransactionEvent(UUID playerUUID, double balancePlayer, double money, Bank bank) {
        this.uuid = playerUUID;
        this.balancePlayer = balancePlayer;
        this.money = money;
        this.bank = bank;
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

    public double getMoney() {
        return money;
    }

    public double getBalancePlayer() {
        return balancePlayer;
    }

    public Bank getBank() {
        return bank;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setBalancePlayer(double balancePlayer) {
        bank.setBalance(getUUID(), balancePlayer);
        this.balancePlayer = balancePlayer;
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
