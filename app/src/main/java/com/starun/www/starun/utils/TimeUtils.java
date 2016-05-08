package com.starun.www.starun.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TPIAN on 16/5/5.
 */
public class TimeUtils {

    public static String long2DateStr(long time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateFormatted = dateFormat.format(new Date(time));
        return dateFormatted;
    }

    public static String long2MS(long time){
        int min = (int) (time/60000);
        int sec = (int) ((time%60000)/1000);
        return min+":"+sec;
    }

}
