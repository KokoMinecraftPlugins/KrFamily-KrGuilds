package krmcplugins.kokored.website.krguilds.database;

import java.sql.SQLException;
import java.sql.Statement;

import krmcplugins.kokored.website.krcore.KrCore;
import krmcplugins.kokored.website.krguilds.KrGuilds;
import krmcplugins.kokored.website.krguilds.util.Log;

public class TableCreate {

    KrGuilds krGuilds;
    String databaseName = KrCore.getSQLName();

    public TableCreate(KrGuilds krGuilds) {
        this.krGuilds = krGuilds;
        Log.info("Loading KrGuilds mysql data tables");
        
        try {
            guilds_list();

            Log.info("Data table loaded!");
        } catch (SQLException e) {
            Log.error("Fail to load Data table: " + e.getMessage());
        }
    }
    
    private void guilds_list() throws SQLException {
        Statement table = krGuilds.getMySQL().createStatement();
        String table_code = "CREATE TABLE IF NOT EXISTS " + databaseName + "." + "guilds_list (" +
            "name CHAR(12) PRIMARY KEY," +
            "description CHAR(30) NOT NULL," +
            "owner_uuid CHAR(36) NOT NULL," +
            "owner_dpname CHAR(16) NOT NULL," +
            "now_member INT(10) unsigned NOT NULL," +
            "max_member INT(10) unsigned NOT NULL," +
            "guild_bank DOUBLE NOT NULL," +
            "guild_exp DOUBLE NOT NULL," +
            "public_join BOOLEAN NOT NULL DEFAULT 0," +
            "create_date BIGINT unsigned NOT NULL" +
            ") CHARSET=utf8;";
        table.execute(table_code);
    }
    
}
