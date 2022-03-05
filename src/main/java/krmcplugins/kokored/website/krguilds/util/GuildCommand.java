package krmcplugins.kokored.website.krguilds.util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class GuildCommand {
    
    private String command_name;
    private String permission;
    private String usage;
    private String description;

    public GuildCommand(String command_name, String permission, String usage, String description) {
        this.command_name = command_name;
        this.permission = permission;
        this.usage = usage;
        this.description = description;
    }
    
    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);

    public String getName() {
        return command_name;
    }

    public String getPermission() {
        return permission;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

}
