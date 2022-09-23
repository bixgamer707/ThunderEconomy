package me.bixgamer707.thundereconomy.bank;

import me.bixgamer707.thundereconomy.user.UserData;

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
            data.setBalance(balance);
            return data;
        });
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

        user.removeBalance(balance);
        return true;
    }

    @Override
    public boolean depositBalance(UUID uuid, BigDecimal balance) {
        if(!userDataMap.containsKey(uuid)){
            return false;
        }

        UserData user = userDataMap.get(uuid);

        user.addBalance(balance);
        return true;
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
    public boolean hasBalance(UUID player, BigDecimal doubleBalance) {
        if (!userDataMap.containsKey(player)) {
            return false;
        }

        return userDataMap.get(player).getBalance().doubleValue() >= doubleBalance.doubleValue();
    }

    @Override
    public UserData getUser(UUID player) {
        return userDataMap.get(player);
    }

    @Override
    public void createAccount(UUID uuid, UserData user) {
        if(uuid == null){
            return;
        }

        userDataMap.put(uuid, user);
    }


    @Override
    public void removeAccount(UUID player) {
        if(player == null){
            return;
        }

        if(!userDataMap.containsKey(player)){
            return;
        }

        userDataMap.remove(player);
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
            user.removeBalance(balance);
            userTarget.addBalance(balance);
            return true;
        }
        return false;
    }

}
