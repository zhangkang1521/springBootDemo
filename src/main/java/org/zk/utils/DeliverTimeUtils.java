package org.zk.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * 应送时间工具类
 * @author zhangkang
 * @date 2023/1/5 16:39
 */
public class DeliverTimeUtils {

    /**
     * 是否在时间点范围
     * @param date
     * @param timeBeginPattern 格式 HH:mm:ss
     * @param timeEndPattern 格式 HH:mm:ss
     * @return
     */
    public static boolean isIn(Date date, String timeBeginPattern, String timeEndPattern) {
        String pattern = DateUtil.format(date, "yyyy-MM-dd");
        Date begin = DateUtil.parse(pattern + " " + timeBeginPattern);
        Date end = DateUtil.parse(pattern + " " + timeEndPattern + ".999");
        return DateUtil.isIn(date, begin, end);
    }

    /**
     * 当日时间点
     * @param date
     * @param time
     * @return
     */
    public static Date todayTime(Date date, String time) {
        String pattern = DateUtil.format(date, "yyyy-MM-dd");
        return DateUtil.parse(pattern + " " + time);
    }

    /**
     * 明日时间点
     * @param date
     * @param time
     * @return
     */
    public static Date tomorrowTime(Date date, String time) {
        Date tomorrow = DateUtil.offsetDay(date, 1);
        String pattern = DateUtil.format(tomorrow, "yyyy-MM-dd");
        return DateUtil.parse(pattern + " " + time);
    }

}
