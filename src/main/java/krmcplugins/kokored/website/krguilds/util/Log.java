package krmcplugins.kokored.website.krguilds.util;

import java.util.logging.Level;

import org.bukkit.Bukkit;

public class Log {

    public static void info(String msg) {
        Bukkit.getLogger().log(Level.INFO, msg);
    }

    public static void warn(String msg) {
        Bukkit.getLogger().log(Level.WARNING, msg);
    }

    public static void error(String msg) {
        Bukkit.getLogger().log(Level.SEVERE, msg);
    }
    
}
