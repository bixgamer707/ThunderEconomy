package me.bixgamer707.thundereconomy.commands;

import me.bixgamer707.thundereconomy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EconomyCommand implements CommandExecutor {

    private final Economy plugin;
    StringBuilder infoMsg = new StringBuilder();
    public EconomyCommand(Economy plugin){
        this.plugin = plugin;
        infoMsg.append("&9ThunderEconomy info\n");
        infoMsg.append("&7\n");
        infoMsg.append("&7Version: &a").append(plugin.getDescription().getVersion()).append("\n");
        infoMsg.append("&7Author: &a").append(plugin.getDescription().getAuthors()).append("\n");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            plugin.getLogger().info(
                    colorize(infoMsg.toString())
            );
            return true;
        }
        sender.sendMessage(colorize(infoMsg.toString()));
        return true;
    }

    public String colorize(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
