package me.bixgamer707.thundereconomy.bank.events;

import me.bixgamer707.thundereconomy.bank.helper.AbstractBank;
import me.bixgamer707.thundereconomy.bank.helper.ProcessMethodEnum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ServerTransactionEvent  extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;

    private final UUID uuid;
    private double balancePlayer;
    private double cost;
    private final AbstractBank bank;
    private final ProcessMethodEnum processMethod;

    public ServerTransactionEvent(UUID playerUUID, double balancePlayer, double cost, AbstractBank abstractBank,
                             ProcessMethodEnum processMethod) {
        this.uuid = playerUUID;
        this.balancePlayer = balancePlayer;
        this.cost = cost;
        this.bank = abstractBank;
        this.processMethod = processMethod;
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

    public double getCost() {
        return cost;
    }

    public double getBalancePlayer() {
        return balancePlayer;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setBalancePlayer(double balancePlayer) {
        bank.setBalance(processMethod, getUUID(), balancePlayer);
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
