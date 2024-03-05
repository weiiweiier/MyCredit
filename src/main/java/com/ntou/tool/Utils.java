package com.ntou.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getDateTime(){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        return sdFormat.format(new Date());
    }
}
