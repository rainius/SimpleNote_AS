package com.jing.app.sn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jing on 2018/3/13.
 */

public class Utils {

    public static String formatTime(long time) {
        // 按照给定的格式串（"yyyy/MM/dd"）转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(new Date(time));
    }

}
