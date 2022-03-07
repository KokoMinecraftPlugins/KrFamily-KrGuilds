package krmcplugins.kokored.website.krguilds.util;

public class TimeUtil {

    public static Integer getMSTime() {
        return (int) (System.currentTimeMillis() % 1000);
    }
    
}
