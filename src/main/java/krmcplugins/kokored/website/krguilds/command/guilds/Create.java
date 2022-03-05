package krmcplugins.kokored.website.krguilds.command.guilds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import krmcplugins.kokored.website.krguilds.api.GuildAPI;
import krmcplugins.kokored.website.krguilds.util.GuildCommand;

public class Create extends GuildCommand {

    public Create(String command_name, String permission, String usage, String description, Plugin plugin) {
        super(command_name, permission, usage, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GuildAPI.createGuildADGToggle((Player) sender, false);
        return true;
    }
    
}
