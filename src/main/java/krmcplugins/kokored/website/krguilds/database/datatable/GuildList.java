package krmcplugins.kokored.website.krguilds.database.datatable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import krmcplugins.kokored.website.krcore.KrCore;
import krmcplugins.kokored.website.krguilds.util.Log;
import krmcplugins.kokored.website.krguilds.util.TimeUtil;

public class GuildList {
    
    String table = "guilds_list";
    
    public Boolean isGuildExist(String name) {
        try {
            PreparedStatement psSelectCount = KrCore.getMySQL().prepareStatement("SELECT COUNT(*) FROM " + KrCore.getSQLName() + "." + table +
            " WHERE name=?;");
            psSelectCount.setString(1, name);
            ResultSet result = psSelectCount.executeQuery();
            result.next();
            Integer resultInt = result.getInt("COUNT(*)");
            if (resultInt == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createNewGuild(String name, String description, String owner_uuid, String owner_dpname, Integer max_member, Double guild_bank,
    Double guild_exp, Boolean public_join) {
        try {
            PreparedStatement psSet = KrCore.getMySQL().prepareStatement(
            "INSERT INTO " + KrCore.getSQLName() + "." + table +
            "(name, description, owner_uuid, owner_dpname, now_member, max_member, guild_bank, guild_exp, public_join, create_date) VALUES (?,?,?,?,?,?,?,?,?,?);");
            psSet.setString(1, name);
            psSet.setString(2, description);
            psSet.setString(3, owner_uuid);
            psSet.setString(4, owner_dpname);
            psSet.setInt(5, 1);
            psSet.setInt(6, max_member);
            psSet.setDouble(7, guild_bank);
            psSet.setDouble(8, guild_exp);
            psSet.setBoolean(9, public_join);
            psSet.setLong(10, TimeUtil.getMSTime());
            psSet.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
