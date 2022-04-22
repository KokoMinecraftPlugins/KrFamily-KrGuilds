package krmcplugins.kokored.website.krguilds.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import krmcplugins.kokored.website.krguilds.KrGuilds;

public class ConfigUtil {

    private static FileConfiguration message;

    public ConfigUtil() {
        message = YamlConfiguration.loadConfiguration(new File(KrGuilds.getPlug().getDataFolder() + File.separator + "message.yml"));
    }

    public static FileConfiguration getMessage() {
        return message;
    }

    public static void migrateConfigs(String oldName, File newF) throws IOException {
        File oldF = new File(KrGuilds.getPlug().getDataFolder(), oldName);

        List<String> oldConfigLines = Arrays.stream(FileUtils.readFileToString(oldF, StandardCharsets.UTF_8).split(System.lineSeparator() + "|\n")).collect(Collectors.toList());
        List<String> newConfigLines = Arrays.stream(FileUtils.readFileToString(newF, StandardCharsets.UTF_8).split(System.lineSeparator() + "|\n")).collect(Collectors.toList());

    }
    
}
