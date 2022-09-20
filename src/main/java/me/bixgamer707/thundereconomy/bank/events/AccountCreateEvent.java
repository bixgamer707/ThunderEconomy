package me.bixgamer707.thundereconomy.bank.events;

import me.bixgamer707.thundereconomy.bank.BankData;
import me.bixgamer707.thundereconomy.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class AccountCreateEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;

    private final UUID uuid;
    private final UserData user;
    private final BankData bankData;

    public AccountCreateEvent(UUID playerUUID, UserData user, BankData bankData) {
        this.uuid = playerUUID;
        this.user = user;
        this.bankData = bankData;
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
        if(Bukkit.getPlayer(uuid) != null){
            return Bukkit.getPlayer(uuid);
        }

        return Bukkit.getOfflinePlayer(uuid).getPlayer();
    }

    public BankData getBank() {
        return bankData;
    }

    public UserData getUser() {
        return user;
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
