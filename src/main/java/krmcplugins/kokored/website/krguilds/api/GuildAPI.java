package krmcplugins.kokored.website.krguilds.api;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import krmcplugins.kokored.website.krguilds.KrGuilds;
import krmcplugins.kokored.website.krguilds.dependency.AdGUI;
import me.leoko.advancedgui.manager.GuiItemManager;
import me.leoko.advancedgui.utils.GuiItemInstance;

public class GuildAPI {

    private static KrGuilds krGuilds;
    private static Map<Player, ItemStack[]> adguiToggle = new HashMap<>();

    public GuildAPI(KrGuilds krGuilds) {
        this.krGuilds = krGuilds;
    }
    
    public static Boolean createGuild(Player owner, String gname, String description) {
        return false;
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
