package com.eternallove.mdmp.util;

import java.util.regex.Pattern;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/20 14:50
 */
public class RegexUtil {
    // IP正则表达式
    private final static String IP_REGEX = "^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$";
    private final static String IP_PORT = "^([1-9]d{0,3}|[1-5]d{4}|6[0-5]{2}[0-3][0-5])(-([1-9]d{0,3}|[1-5]d{4}|6[0-5]{2}[0-3][0-5]))?$";

    public static boolean isIP(String str) {
        Pattern pattern = Pattern.compile(IP_REGEX);
        return pattern.matcher(str).find();
    }

    public static boolean isPort(String str) {
        Pattern pattern = Pattern.compile(IP_PORT);
        return pattern.matcher(str).find();
    }
}
