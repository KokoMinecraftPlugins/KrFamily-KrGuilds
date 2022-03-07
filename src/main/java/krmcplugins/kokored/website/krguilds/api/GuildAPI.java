package krmcplugins.kokored.website.krguilds.api;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import krmcplugins.kokored.website.krguilds.KrGuilds;
import krmcplugins.kokored.website.krguilds.dependency.AdGUI;
import krmcplugins.kokored.website.krguilds.util.Message;
import me.leoko.advancedgui.manager.GuiItemManager;
import me.leoko.advancedgui.utils.GuiItemInstance;

public class GuildAPI {

    private static KrGuilds krGuilds;
    private static Map<Player, ItemStack[]> adguiToggle = new HashMap<>();

    public GuildAPI(KrGuilds krGuilds) {
        GuildAPI.krGuilds = krGuilds;
    }
    
    public static String createGuild(Player owner, String gname, String description) {
        gname = gname.replace(" ", "");
        description = description.replace(" ", "");

        if (gname.isEmpty()) {
            krGuilds.sendMessage(owner, Message.G_CREATE_NAME_EMPTY);
            return "G_CREATE_NAME_EMPTY";
        }
        if (description.isEmpty()) {
            krGuilds.sendMessage(owner, Message.G_CREATE_DESC_EMPTY);
            return "G_CREATE_DESC_EMPTY";
        }
        if (gname.length() > 12) {
            krGuilds.sendMessage(owner, Message.G_CREATE_NAME_TOO_LONG);
            return "G_CREATE_NAME_TOO_LONG";
        }
        if (gname.length() > 30) {
            krGuilds.sendMessage(owner, Message.G_CREATE_DESC_TOO_LONG);
            return "G_CREATE_DESC_TOO_LONG";
        }

        DatabaseAPI.createGuild(gname, description, owner.getUniqueId().toString(), owner.getName(),
        krGuilds.getConfig().getInt("Setting.GuildDefault.maxmember"), krGuilds.getConfig().getDouble("Setting.GuildDefault.guildbank"),
        krGuilds.getConfig().getDouble("Setting.GuildDefault.guildexp"), krGuilds.getConfig().getBoolean("Setting.GuildDefault.publicjoin"));

        krGuilds.sendMessage(owner, "&aDone!");
        return "DONE";
    }

    public static Boolean createGuildADGToggle(Player player, Boolean isCancel) {
        if (adguiToggle.containsKey(player)) {
            player.getInventory().setContents(adguiToggle.get(player));
            adguiToggle.remove(player);
            return true;
        }else {
            if (isCancel) {
                return true;
            }
            final GuiItemInstance itemInstance = GuiItemManager.getInstance().getItemInstance(AdGUI.GUILD_CREATE_ID);
            if(itemInstance != null){
                adguiToggle.put(player, player.getInventory().getContents());
                player.getInventory().clear();
                player.getInventory().setItem(0, itemInstance.createItem());
                player.getInventory().setItem(1, itemInstance.createItem());
                player.getInventory().setItem(2, itemInstance.createItem());
                player.getInventory().setItem(3, itemInstance.createItem());
                player.getInventory().setItem(4, itemInstance.createItem());
                player.getInventory().setItem(5, itemInstance.createItem());
                player.getInventory().setItem(6, itemInstance.createItem());
                player.getInventory().setItem(7, itemInstance.createItem());
                player.getInventory().setItem(8, itemInstance.createItem());
                player.getInventory().setHeldItemSlot(4);
                return true;
            }else {
                krGuilds.sendMessage(player, "[KrGuilds] &cKrGuild_CreateGuild.json not found!");
                return false;
            }
        }
    }
    
}
