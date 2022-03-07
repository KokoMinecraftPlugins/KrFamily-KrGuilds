package krmcplugins.kokored.website.krguilds.util;

public class Message {

    public static final String PREFIX = Config.get().getString("PREFIX");

    public static final String COMMAND_NOT_FOUND = Config.get().getString("COMMAND_NOT_FOUND");
    public static final String NO_PERMISSION = Config.get().getString("NO_PERMISSION");
    public static final String REQUIRE_ARGS = Config.get().getString("REQUIRE_ARGS");

    // === Command ===
    public static final String G_ERROR = Config.get().getString("G_ERROR");
    public static final String G_INFO_USAGE = Config.get().getString("G_INFO_DESC");
    public static final String G_CREATE_USAGE = Config.get().getString("G_CREATE_DESC");
    public static final String G_CREATE_NAME_EMPTY = Config.get().getString("G_CREATE_NAME_EMPTY");
    public static final String G_CREATE_DESC_EMPTY = Config.get().getString("G_CREATE_DESC_EMPTY");
    
}
