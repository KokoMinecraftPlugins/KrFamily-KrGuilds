package krmcplugins.kokored.website.krguilds;

import java.io.IOException;
import java.sql.Connection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import krmcplugins.kokored.website.krcore.KrCore;
import krmcplugins.kokored.website.krguilds.api.GuildAPI;
import krmcplugins.kokored.website.krguilds.command.Guild;
import krmcplugins.kokored.website.krguilds.database.TableCreate;
import krmcplugins.kokored.website.krguilds.dependency.AdGUI;
import krmcplugins.kokored.website.krguilds.util.Config;
import krmcplugins.kokored.website.krguilds.util.ConfigUtil;
import krmcplugins.kokored.website.krguilds.util.Log;
import krmcplugins.kokored.website.krguilds.util.Message;
import krmcplugins.kokored.website.krguilds.util.Metrics;
import me.leoko.advancedgui.manager.LayoutManager;

public final class KrGuilds extends JavaPlugin {

    Boolean adgui_enable;
    Connection connection;
    Config messageConfig;
    Guild guild_command;
    GuildAPI guildAPI;
    Log log;

    @Override
    public void onEnable() {

        new Metrics(this, 14536);

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        messageConfig = new Config();
        log = new Log();

        if (getConfig().getBoolean("AdvancedGUI.enable") && Bukkit.getPluginManager().getPlugin("AdvancedGUI") != null) {
            LayoutManager.getInstance().registerLayoutExtension(new AdGUI(), this);
            adgui_enable = true;
        }

        try {
            ConfigUtil.migrateConfigs("message.yml", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection = KrCore.getMySQL();
        new TableCreate(this);

        guild_command = new Guild(this);
        guildAPI = new GuildAPI(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return getPlugin(KrGuilds.class);
    }
    public Connection getMySQL() {
        return connection;
    }
    public Log getLog() {
        return log;
    }

    public void sendMessage(Player player, String messagee) {
        if (player == null) return;
        if (!(player.isOnline())) return;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message.PREFIX + "&r" + messagee));
    }
    public void sendMessage(CommandSender sender, String messagee) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Message.PREFIX + "&r" + messagee));
    }
}
