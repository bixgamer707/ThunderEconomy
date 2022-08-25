package me.bixgamer707.thundereconomy.api.bank;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class BankProcess {

    public abstract void createOfflinePlayer(ProcessMethodEnum processMethod, OfflinePlayer player, double startBalance);

    public abstract void createPlayer(ProcessMethodEnum processMethod, Player player, double startBalance);

    public abstract void createPlayerWithUUID(ProcessMethodEnum processMethod, UUID uuid, double startBalance);

    public abstract void removeOfflinePlayer(ProcessMethodEnum processMethod, OfflinePlayer player);

    @Deprecated public abstract void removeOfflinePlayer(OfflinePlayer player);

    @Deprecated public abstract void removePlayer(Player player);

    @Deprecated public abstract void removePlayerWithUUID(UUID player);

    @Deprecated public abstract void createOfflinePlayer(OfflinePlayer player, double startBalance);

    @Deprecated public abstract void createPlayer(Player player, double startBalance);

    @Deprecated public abstract void createPlayerWithUUID(UUID player, double startBalance);

    @Deprecated public abstract void createPlayer(Player player);

    @Deprecated public abstract void createOfflinePlayer(OfflinePlayer player);

    @Deprecated public abstract void createPlayerWithUUID(UUID player);

    public abstract void removePlayer(ProcessMethodEnum processMethod, Player player);

    public abstract void removePlayerWithUUID(ProcessMethodEnum processMethod, UUID uuid);
}
