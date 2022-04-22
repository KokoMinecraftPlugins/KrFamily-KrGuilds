package krmcplugins.kokored.website.krguilds.dependency;

import org.bukkit.Bukkit;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import krmcplugins.kokored.website.krguilds.KrGuilds;
import krmcplugins.kokored.website.krguilds.api.GuildAPI;
import me.leoko.advancedgui.utils.Layout;
import me.leoko.advancedgui.utils.LayoutExtension;
import me.leoko.advancedgui.utils.components.CheckComponent;
import me.leoko.advancedgui.utils.components.GroupComponent;
import me.leoko.advancedgui.utils.components.TextInputComponent;
import me.leoko.advancedgui.utils.events.LayoutLoadEvent;

public class AdGUI implements LayoutExtension {

    public static final String GUILD_CREATE_ID = "KrGuild_CreateGuild";
    public final String GUILD_CREATE_INPUT_GUILD_NAME_ID = "LqvLGgZE";
    public final String GUILD_CREATE_INPUT_GUILD_DESCRIPTION_ID = "xBYCOZQc";
    public final String GUILD_CREATE_BUTTON_CONFIRM_ID = "T6m1sO3T";
    public final String GUILD_CREATE_BUTTON_CANCEL_ID = "vaSBB5Hw";

    private GroupComponent GUILD_CREATE_TREE;

    TextInputComponent
        GUILD_CREATE_INPUT_GUILD_NAME,
        GUILD_CREATE_INPUT_GUILD_DESCRIPTION;

    CheckComponent
        GUILD_CREATE_BUTTON_CONFIRM,
        GUILD_CREATE_BUTTON_CANCEL;
    
    public AdGUI() {
        Bukkit.getPluginManager().registerEvents(this, KrGuilds.getPlug());
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (GuildAPI.adguiToggle.containsKey(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player && GuildAPI.adguiToggle.containsKey((Player) event.getEntity())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onInventoryClickE(InventoryClickEvent event) {
        if (GuildAPI.adguiToggle.containsKey(event.getWhoClicked())) {
            event.setCancelled(true);
            event.getWhoClicked().closeInventory();
        }
    }
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame && GuildAPI.adguiToggle.containsKey(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (GuildAPI.adguiToggle.containsKey(event.getEntity())) {
            event.setKeepInventory(true);
        }
    }

    @Override
    @EventHandler
    public void onLayoutLoad(LayoutLoadEvent event) {
        Layout layout = event.getLayout();
        if (layout.getName().equals(GUILD_CREATE_ID)) {
            GUILD_CREATE_TREE = layout.getTemplateComponentTree();
            GUILD_CREATE_INPUT_GUILD_NAME = GUILD_CREATE_TREE.locate(GUILD_CREATE_INPUT_GUILD_NAME_ID, TextInputComponent.class);
            GUILD_CREATE_INPUT_GUILD_DESCRIPTION = GUILD_CREATE_TREE.locate(GUILD_CREATE_INPUT_GUILD_DESCRIPTION_ID, TextInputComponent.class);
            GUILD_CREATE_BUTTON_CONFIRM = GUILD_CREATE_TREE.locate(GUILD_CREATE_BUTTON_CONFIRM_ID, CheckComponent.class);
            GUILD_CREATE_BUTTON_CANCEL = GUILD_CREATE_TREE.locate(GUILD_CREATE_BUTTON_CANCEL_ID, CheckComponent.class);

            GUILD_CREATE_TREE.locate(GUILD_CREATE_BUTTON_CONFIRM_ID).setClickAction((context, player, primaryTrigger) -> {
                GuildAPI.createGuild(player, GUILD_CREATE_INPUT_GUILD_NAME.locateOn(context).getInput(), GUILD_CREATE_INPUT_GUILD_DESCRIPTION.locateOn(context).getInput());
            });
            GUILD_CREATE_TREE.locate(GUILD_CREATE_BUTTON_CANCEL_ID).setClickAction((context, player, primaryTrigger) -> {
                GuildAPI.createGuildADGToggle(player, true);
            });
        }
    }
    
}
