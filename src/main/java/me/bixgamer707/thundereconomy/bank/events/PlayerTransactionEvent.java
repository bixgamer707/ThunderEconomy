package me.bixgamer707.thundereconomy.bank.events;

import me.bixgamer707.thundereconomy.bank.BankData;
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

import java.math.BigDecimal;
import java.util.UUID;

public class PlayerTransactionEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;

    private final UUID uuid;
    private final UUID targetUuid;
    private final UserData user;
    private BigDecimal money;
    private final BankData bank;
    private final TransactionType type;

    public PlayerTransactionEvent(UUID playerUUID, UUID targetUUID, UserData user, BigDecimal money, BankData bankData,
                                  TransactionType type) {
        this.uuid = playerUUID;
        this.targetUuid = targetUUID;
        this.user = user;
        this.money = money;
        this.bank = bankData;
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
        if(Bukkit.getPlayer(uuid) != null){
            return Bukkit.getPlayer(uuid);
        }

        return Bukkit.getOfflinePlayer(uuid).getPlayer();
    }

    public BankData getBank() {
        return bank;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public UUID getTargetUuid() {
        return targetUuid;
    }

    @Nullable
    public Player getTarget(){
        if(Bukkit.getPlayer(targetUuid) != null){
            return Bukkit.getPlayer(targetUuid);
        }

        return Bukkit.getOfflinePlayer(targetUuid).getPlayer();
    }

    public UserData getUser() {
        return user;
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
