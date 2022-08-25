package me.bixgamer707.thundereconomy.api.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //
    //@param versions format write is; 1.9,1.10,1.11....
    //
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public static boolean versionEquals(String... versions) {
        String ver = Bukkit.getServer().getVersion();
        for(String v : versions){
            if(ver.contains(v)) return true;
        }
        return false;
    }

    public static String formatNumber(Number number) {
        char[] suffix = {' ', 'K', 'M', 'B', 'T', 'P', 'E'};
        long numValue = number.longValue();
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0,0").format(numValue / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }

    public static String hexColors(String text) {
        text = text.replaceAll(
                "%prefix%", "ThunderEconomy");
        Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");
        if (Bukkit.getVersion().contains("1.16")) {
            Matcher match = HEX_PATTERN.matcher(text);
            while (match.find()) {
                String color = text.substring(match.start(), match.end());
                text = text.replace(color, ChatColor.of(color) + "");
                match = HEX_PATTERN.matcher(text);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
