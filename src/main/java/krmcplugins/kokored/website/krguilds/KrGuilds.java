package krmcplugins.kokored.website.krguilds;

import java.sql.Connection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import krmcplugins.kokored.website.krcore.KrCore;
import krmcplugins.kokored.website.krguilds.command.Guild;
import krmcplugins.kokored.website.krguilds.dependency.AdGUI;
import krmcplugins.kokored.website.krguilds.util.Log;
import krmcplugins.kokored.website.krguilds.util.Message;
import krmcplugins.kokored.website.krguilds.util.Metrics;
import me.leoko.advancedgui.manager.LayoutManager;

public final class KrGuilds extends JavaPlugin {

    Boolean adgui_enable;
    Connection connection;
    Guild guild_command;
    Log log;
    Message message;

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        if (getConfig().getBoolean("AdvancedGUI.enable") && Bukkit.getPluginManager().getPlugin("AdvancedGUI") != null) {
            LayoutManager.getInstance().registerLayoutExtension(new AdGUI(), this);
            adgui_enable = true;
        }

        new Metrics(this, 14536);
        connection = KrCore.getMySQL();
        log = new Log();
        message = new Message();

        guild_command = new Guild(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Plugin getIns() {
        return this;
    }
    public Message getMessageConfig() {
        return message;
    }
    public void sendMessage(Player player, String messagee) {
        if (player == null) return;
        if (!(player.isOnline())) return;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.PREFIX + "&r" + messagee));
    }
    public void sendMessage(CommandSender sender, String messagee) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message.PREFIX + "&r" + messagee));
    }
}
