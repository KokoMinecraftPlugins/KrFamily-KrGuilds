package krmcplugins.kokored.website.krguilds.util;

public class Message {

    public static final String PREFIX = ConfigUtil.getMessage().getString("PREFIX");

    public static final String COMMAND_NOT_FOUND = ConfigUtil.getMessage().getString("COMMAND_NOT_FOUND");
    public static final String NO_PERMISSION = ConfigUtil.getMessage().getString("NO_PERMISSION");
    public static final String REQUIRE_ARGS = ConfigUtil.getMessage().getString("REQUIRE_ARGS");

    // === Command ===
    public static final String G_ERROR = ConfigUtil.getMessage().getString("G_ERROR");
    public static final String G_INFO_USAGE = ConfigUtil.getMessage().getString("G_INFO_DESC");
    public static final String G_CREATE_USAGE = ConfigUtil.getMessage().getString("G_CREATE_DESC");
    public static final String G_CREATE_DONE = ConfigUtil.getMessage().getString("G_CREATE_DONE");
    public static final String G_CREATE_ALREADY_HAVE_GUILD = ConfigUtil.getMessage().getString("G_CREATE_ALREADY_HAVE_GUILD");
    public static final String G_CREATE_NAME_EMPTY = ConfigUtil.getMessage().getString("G_CREATE_NAME_EMPTY");
    public static final String G_CREATE_NAME_TOO_LONG = ConfigUtil.getMessage().getString("G_CREATE_NAME_TOO_LONG");
    public static final String G_CREATE_DESC_EMPTY = ConfigUtil.getMessage().getString("G_CREATE_DESC_EMPTY");
    public static final String G_CREATE_DESC_TOO_LONG = ConfigUtil.getMessage().getString("G_CREATE_DESC_TOO_LONG");
    
}
