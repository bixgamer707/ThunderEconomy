package me.bixgamer707.economy;

import me.bixgamer707.economy.api.bank.ProcessMethodEnum;
import me.bixgamer707.economy.bank.helper.BankHelper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        BankHelper.build(
                ProcessMethodEnum.ASYNC,
                "global*"
        );
        Bukkit.getServicesManager().register(Economy.class, this, this, ServicePriority.Highest);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
