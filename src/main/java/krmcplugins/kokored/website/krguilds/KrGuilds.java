package krmcplugins.kokored.website.krguilds;

import java.io.File;
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
import krmcplugins.kokored.website.krguilds.util.ConfigUtil;
import krmcplugins.kokored.website.krguilds.util.Message;
import krmcplugins.kokored.website.krguilds.util.Metrics;
import me.leoko.advancedgui.manager.LayoutManager;

public final class KrGuilds extends JavaPlugin {

    public static Boolean adgui_enable;
    Connection connection;

    Guild guild_command;

    @Override
    public void onEnable() {
        if (!(new File(this.getDataFolder(), "config.yml")).exists()) {
            this.saveResource("config.yml", false);
        }

        if (!(new File(this.getDataFolder(), "message.yml")).exists()) {
            this.saveResource("message.yml", false);
        }
        new ConfigUtil();

        if (getConfig().getBoolean("AdvancedGUI.enable") && Bukkit.getPluginManager().getPlugin("AdvancedGUI") != null) {
            LayoutManager.getInstance().registerLayoutExtension(new AdGUI(), this);
            adgui_enable = true;
        }
        
        // bstats
        new Metrics(this, 14536);
        
        // init sql
        connection = KrCore.getMySQL();
        new TableCreate(this);

        // init plugin feature
        guild_command = new Guild(this);
        new GuildAPI(this);
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            GuildAPI.createGuildADGToggle(p, true);
        }
    }

    public static Plugin getPlug() {
        return getPlugin(KrGuilds.class);
    }
    public Connection getMySQL() {
        return connection;
    }

    public void sendMessage(Player player, String messagee) {
        if (player == null) return;
        if (!(player.isOnline())) return;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message.PREFIX + "&r" + messagee));
    }
    public void sendMessage(Player player, String message, Boolean prefix) {
        if (player == null) return;
        if (!(player.isOnline())) return;
        if (prefix) {
            sendMessage(player, message);
        }else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
    public void sendMessage(CommandSender sender, String messagee) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Message.PREFIX + "&r" + messagee));
    }
}
