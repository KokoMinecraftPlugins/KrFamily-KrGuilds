package krmcplugins.kokored.website.krguilds.api;

import krmcplugins.kokored.website.krguilds.database.datatable.GuildList;

public class DatabaseAPI {
    
    private static GuildList guildList = new GuildList();

    public static Boolean isGuildExist(String name) {
        return guildList.isGuildExist(name);
    }

    public static void createGuild(String name, String description, String owner_uuid, String owner_dpname, Integer max_member, Double guild_bank,
    Double guild_exp, Boolean public_join) {
        guildList.createNewGuild(name, description, owner_uuid, owner_dpname, max_member, guild_bank, guild_exp, public_join);
    }
    
}
