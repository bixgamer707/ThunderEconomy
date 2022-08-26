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

public class PlayerTransactionEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;

    private final UUID uuid;
    private final UUID targetUuid;
    private double balancePlayer;
    private double send;
    private final Bank bank;

    public PlayerTransactionEvent(UUID playerUUID, UUID targetUUID, double balancePlayer, double send, Bank bank) {
        this.uuid = playerUUID;
        this.targetUuid = targetUUID;
        this.balancePlayer = balancePlayer;
        this.send = send;
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

    public Bank getBank() {
        return bank;
    }

    public double getSend() {
        return send;
    }

    public UUID getTargetUuid() {
        return targetUuid;
    }

    @Nullable
    public Player getTarget(){
        return Bukkit.getPlayer(targetUuid);
    }

    public double getBalancePlayer() {
        return balancePlayer;
    }

    public void setSend(double send) {
        this.send = send;
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
