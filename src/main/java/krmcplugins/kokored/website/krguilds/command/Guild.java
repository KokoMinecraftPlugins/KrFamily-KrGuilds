package krmcplugins.kokored.website.krguilds.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import krmcplugins.kokored.website.krguilds.KrGuilds;
import krmcplugins.kokored.website.krguilds.command.guilds.Info;
import krmcplugins.kokored.website.krguilds.util.GuildCommand;
import krmcplugins.kokored.website.krguilds.util.Message;

public class Guild implements TabExecutor {

    KrGuilds krGuilds;
    Plugin plugin;
    Message msgc;
    
    List<GuildCommand> guilds_commands = new ArrayList<>();

    public Guild(KrGuilds krGuilds) {
        this.krGuilds = krGuilds;
        this.plugin = krGuilds.getIns();
        this.msgc = krGuilds.getMessageConfig();

        Bukkit.getPluginCommand("guilds").setExecutor(this);
        Bukkit.getPluginCommand("guilds").setTabCompleter(this);
        
        guilds_commands.add(new Info("info", "krfamily.krguilds.commands.guilds.info", "/g info", msgc.G_INFO_USAGE, plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            krGuilds.sendMessage(sender, msgc.REQUIRE_ARGS);
            return true;
        }
        
        GuildCommand gcmds = getCommand(args[0]);

        if (gcmds == null) {
            krGuilds.sendMessage(sender, msgc.COMMAND_NOT_FOUND);
            return true;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!(player.hasPermission(gcmds.getPermission()))) {
                krGuilds.sendMessage(sender, msgc.NO_PERMISSION);
                return true;
            }
            if (gcmds.onCommand(sender, command, label, args) == false) {
                krGuilds.sendMessage(sender, msgc.G_ERROR);
                krGuilds.sendMessage(sender, gcmds.getUsage());
                return true;
            }
        }else if (sender instanceof ConsoleCommandSender) {
            if (gcmds.onCommand(sender, command, label, args) == false) {
                krGuilds.sendMessage(sender, msgc.G_ERROR);
                krGuilds.sendMessage(sender, gcmds.getUsage());
                return true;
            }
        }

        return false;
    }
    
    public GuildCommand getCommand(String name) {
        for (GuildCommand gcmds : guilds_commands) {
            if (gcmds.getName().equals(name)) {
                return gcmds;
            }
        }
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> args0 = new ArrayList<>();
            for (GuildCommand gcmds : guilds_commands) {
                if (sender.hasPermission(gcmds.getPermission())) {
                    args0.add(gcmds.getName());
                }
            }
            return args0;
        }
        return null;
    }
    
}
