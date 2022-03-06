package krmcplugins.kokored.website.krguilds.util;

import krmcplugins.kokored.website.krguilds.KrGuilds;

public class Message {

    KrGuilds krGuilds;

    public Message(KrGuilds krGuilds) {
        this.krGuilds = krGuilds;
    }

    public final String PREFIX = Config.get().getString("PREFIX");

    public final String COMMAND_NOT_FOUND = Config.get().getString("COMMAND_NOT_FOUND");
    public final String NO_PERMISSION = Config.get().getString("NO_PERMISSION");
    public final String REQUIRE_ARGS = Config.get().getString("REQUIRE_ARGS");

    // === Command ===
    public final String G_ERROR = Config.get().getString("G_ERROR");
    public final String G_INFO_USAGE = Config.get().getString("G_INFO_DESC");
    public final String G_CREATE_USAGE = Config.get().getString("G_CREATE_DESC");
    public final String G_CREATE_NAME_EMPTY = Config.get().getString("G_CREATE_NAME_EMPTY");
    public final String G_CREATE_DESC_EMPTY = Config.get().getString("G_CREATE_DESC_EMPTY");
    
}
