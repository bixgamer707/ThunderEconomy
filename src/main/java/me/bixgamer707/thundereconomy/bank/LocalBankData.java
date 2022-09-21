package me.bixgamer707.thundereconomy.bank;

import me.bixgamer707.thundereconomy.Economy;
import me.bixgamer707.thundereconomy.bank.events.AccountCreateEvent;
import me.bixgamer707.thundereconomy.bank.events.AccountRemoveEvent;
import me.bixgamer707.thundereconomy.bank.events.PlayerTransactionEvent;
import me.bixgamer707.thundereconomy.bank.events.ServerTransactionEvent;
import me.bixgamer707.thundereconomy.bank.helper.TransactionType;
import me.bixgamer707.thundereconomy.bank.manager.BankManager;
import me.bixgamer707.thundereconomy.user.UserData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class LocalBankData implements BankData {

    private final Map<UUID, UserData> userDataMap;
    private final String id;
    public LocalBankData(String id){
        this.id = id;
        this.userDataMap = new HashMap<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Map<UUID, UserData> getUserDataMap() {
        return userDataMap;
    }

    @Override
    public void setBalance(UUID uuid, BigDecimal balance){
        userDataMap.computeIfPresent(uuid, (key, data) -> {
            ServerTransactionEvent serverEvent = new ServerTransactionEvent(key, data, balance,
                    this, TransactionType.SET_BALANCE);
            Bukkit.getPluginManager().callEvent(serverEvent);
            if(!serverEvent.isCancelled()){
                data.setBalance(balance);
            }
            return data;
        });
    }

    @Override
    public void setBalance(OfflinePlayer player, BigDecimal balance){
        setBalance(player.getUniqueId(), balance);
    }

    @Override
    public void setBalance(Player player, BigDecimal balance){
        setBalance(player.getUniqueId(), balance);
    }

    @Override
    public boolean withdrawBalance(UUID uuid, BigDecimal balance) {
        if(!userDataMap.containsKey(uuid)){
            return false;
        }

        UserData user = userDataMap.get(uuid);
        if(balance.doubleValue() > user.getBalance().doubleValue()){
            return false;
        }

        ServerTransactionEvent serverEvent = new ServerTransactionEvent(
                uuid, user, balance, this, TransactionType.WITHDRAW_SERVER
        );
        Bukkit.getPluginManager().callEvent(serverEvent);

        if(!serverEvent.isCancelled()) {
            user.removeBalance(balance);
            userDataMap.put(uuid, user);
            return true;
        }
        return false;
    }

    @Override
    public boolean withdrawBalance(Player player, BigDecimal balance){
        return withdrawBalance(player.getUniqueId(), balance);
    }

    @Override
    public boolean withdrawBalance(OfflinePlayer player, BigDecimal balance) {
        return withdrawBalance(player.getUniqueId(), balance);
    }

    @Override
    public boolean depositBalance(UUID uuid, BigDecimal balance) {
        if(!userDataMap.containsKey(uuid)){
            return false;
        }

        UserData user = userDataMap.get(uuid);
        ServerTransactionEvent serverEvent = new ServerTransactionEvent(
                uuid, user, balance, this, TransactionType.DEPOSIT_SERVER
        );
        Bukkit.getPluginManager().callEvent(serverEvent);
        if(!serverEvent.isCancelled()){
            user.addBalance(balance);
            userDataMap.put(uuid, user);
            return true;
        }
        return false;
    }

    @Override
    public boolean depositBalance(Player player, BigDecimal balance){
        return depositBalance(player.getUniqueId(), balance);
    }

    @Override
    public boolean depositBalance(OfflinePlayer player, BigDecimal balance) {
        return depositBalance(player.getUniqueId(), balance);
    }

    //Getters

    @Override
    public BigDecimal getBalance(UUID player){
        if(!userDataMap.containsKey(player)){
            return new BigDecimal(0);
        }
        return userDataMap.get(player).getBalance();
    }

    @Override
    public BigDecimal getBalance(Player player){
        return getBalance(player.getUniqueId());
    }

    @Override
    public BigDecimal getBalance(OfflinePlayer player){
        return getBalance(player.getUniqueId());
    }

    @Override
    public boolean hasBalance(UUID player, BigDecimal doubleBalance) {
        if (!userDataMap.containsKey(player)) {
            return false;
        }

        return userDataMap.get(player).getBalance().doubleValue() >= doubleBalance.doubleValue();
    }

    @Override
    public boolean hasBalance(Player player, BigDecimal balance){
        return hasBalance(player.getUniqueId(), balance);
    }

    @Override
    public boolean hasBalance(OfflinePlayer player, BigDecimal balance){
        return hasBalance(player.getUniqueId(), balance);
    }

    @Override
    public void createAccount(OfflinePlayer player, UserData userData) {
        if(player == null){
            return;
        }

        if(player.isBanned()){
            return;
        }

        AccountCreateEvent event = new AccountCreateEvent(
                player.getUniqueId(),
                userData,
                this
        );
        Bukkit.getPluginManager().callEvent(event);

        if(event.isCancelled()){
            return;
        }

        userDataMap.put(event.getUUID(), event.getUser());
    }

    @Override
    public UserData getUser(UUID player) {
        return userDataMap.get(player);
    }

    @Override
    public UserData getUser(Player player) {
        return getUser(player.getUniqueId());
    }

    @Override
    public UserData getUser(OfflinePlayer player) {
        return getUser(player.getUniqueId());
    }

    @Override
    public void createAccount(Player player, UserData user) {
        if(player == null){
            return;
        }

        if(player.isBanned()){
            return;
        }

        AccountCreateEvent event = new AccountCreateEvent(
                player.getUniqueId(),
                user,
                this
        );
        Bukkit.getPluginManager().callEvent(event);

        if(event.isCancelled()){
            return;
        }

        userDataMap.put(event.getUUID(), event.getUser());
    }

    @Override
    public void createAccount(UUID uuid, UserData user) {
        if(uuid == null){
            return;
        }

        AccountCreateEvent event = new AccountCreateEvent(
                uuid,
                user,
                this
        );
        Bukkit.getPluginManager().callEvent(event);

        if(event.isCancelled()){
            return;
        }

        userDataMap.put(event.getUUID(), event.getUser());
    }

    @Override
    public void removeAccount(Player player) {
        removeAccount(player.getUniqueId());
    }


    @Override
    public void removeAccount(UUID player) {
        if(player == null){
            return;
        }

        if(!userDataMap.containsKey(player)){
            return;
        }

        AccountRemoveEvent event = new AccountRemoveEvent(
                player,
                getUser(player),
                this
        );
        Bukkit.getPluginManager().callEvent(event);

        if(event.isCancelled()){
            return;
        }

        userDataMap.remove(event.getUUID());
    }

    @Override
    public void removeAccount(OfflinePlayer player) {
        removeAccount(player.getUniqueId());
    }

    @Override
    @Deprecated
    public void removeAllBanks(Server server){
        if(server == null){
            return;
        }
        RegisteredServiceProvider<BankManager> econ = server.getServicesManager().getRegistration(BankManager.class);
        if(econ == null){
            return;
        }

        econ.getProvider().getBanks().clear();
    }

    @Override
    public boolean transferBalance(UUID player, UUID target, BigDecimal balance){
        if(player == null || target == null){
            return false;
        }
        UserData user = userDataMap.get(player);
        UserData userTarget = userDataMap.get(target);
        if(user == null || userTarget == null){
            return false;
        }

        if(getBalance(player).doubleValue() >= balance.doubleValue()){
            PlayerTransactionEvent playerEvent = new PlayerTransactionEvent(
                    player, target, user, balance, this, TransactionType.TRANSFER_PLAYER
            );
            Bukkit.getPluginManager().callEvent(playerEvent);

            if(!playerEvent.isCancelled()){
                user.removeBalance(balance);
                userTarget.addBalance(balance);
                userDataMap.put(player, user);
                userDataMap.put(target, userTarget);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean transferBalance(Player player, Player target, BigDecimal balance){
        return transferBalance(player.getUniqueId(), target.getUniqueId(), balance);
    }

    @Override
    public boolean transferBalance(OfflinePlayer player, OfflinePlayer target, BigDecimal balance){
        return transferBalance(player.getUniqueId(), target.getUniqueId(), balance);
    }

}
