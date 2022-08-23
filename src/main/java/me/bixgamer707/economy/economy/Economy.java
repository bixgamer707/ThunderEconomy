package me.bixgamer707.economy.economy;

import me.bixgamer707.economy.economy.bank.helper.BankHelper;
import me.bixgamer707.economy.economy.bank.api.bank.ProcessMethodEnum;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        BankHelper.build(
                ProcessMethodEnum.ASYNC,
                "global*"
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
