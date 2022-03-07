package krmcplugins.kokored.website.krguilds.util;

import java.util.Date;

public class TimeUtil {

    public static Long getMSTime() {
        Date date = new Date();
        return date.getTime();
    }
    
}
