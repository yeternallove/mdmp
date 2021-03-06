package com.eternallove.mdmp.util;

import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/5/1 16:10
 */
public class DateUtil {
    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();
    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf @param pattern @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);/* 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf*/
        if (tl == null) synchronized (lockObj) {
            tl = sdfMap.get(pattern);
            if (tl == null) {/* 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map*/
                tl = new ThreadLocal<SimpleDateFormat>() {
                    @Override
                    protected SimpleDateFormat initialValue() {
                        System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                        return new SimpleDateFormat(pattern, Locale.CHINA);
                    }
                };
                sdfMap.put(pattern, tl);
            }
        }
        return tl.get();
    }

    /**
     * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat @param date @param pattern @return
     */
    public static String format(Date date, String pattern) {
        if (date ==null){
            return "";
        }
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }

    public static String format(Date date) {
        return format(date,"yyyy-MM-dd HH:mm:ss");
    }
}
