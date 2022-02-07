package com.hzg.crawler.utils;

import cn.hutool.core.date.DatePattern;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Package: com.hzg.crawler.utils
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-11-15 11:20
 */
public class DateUtil {

    /**
     * 默认输出的时间格式 yyyy-MM-dd HH:mm:ss
     */
    private static final String DEFAULT_OUTPUT_TIME_FORMAT = DatePattern.NORM_DATETIME_PATTERN;


    /**
     * 根据输入的目标时间格式转换时间字符串
     * <p/>
     * 默认输出 yyyy-MM-dd HH:mm:ss 格式的时间字符串
     * <p/>
     *
     * @param inputTime        时间字符串
     * @param inputTimeFormat  时间格式
     * @param outputTimeFormat 目标时间格式
     * @return java.lang.String 目标时间格式的时间字符串
     * @author HuangZhiGao
     * @date 2021/11/15/015 12:01
     */
    public static String parseStringTime(String inputTime, String inputTimeFormat, String outputTimeFormat) throws Exception {
        if (StringUtils.isBlank(inputTime)) {
            throw new IllegalArgumentException("");
        } else if (StringUtils.isBlank(inputTimeFormat)) {
            throw new IllegalArgumentException("");
        }
        if (StringUtils.isBlank(outputTimeFormat)) {
            outputTimeFormat = DEFAULT_OUTPUT_TIME_FORMAT;
        }
        Date date = (new SimpleDateFormat(inputTimeFormat)).parse(inputTime);
        String formatDate = (new SimpleDateFormat(outputTimeFormat)).format(date);
        return formatDate;
    }

    /**
     * 根据输入的目标时间格式获取当前时间字符串
     * <p/>
     * 默认输出 yyyy-MM-dd HH:mm:ss 格式的时间字符串
     * <p/>
     *
     * @param outputTimeFormat 目标时间格式
     * @return java.lang.String 目标时间格式的时间字符串
     * @author HuangZhiGao
     * @date 2021/11/15/015 12:22
     */
    public static String getCurrentDateTimeByFormat(String outputTimeFormat) {
        if (StringUtils.isBlank(outputTimeFormat)) {
            outputTimeFormat = DEFAULT_OUTPUT_TIME_FORMAT;
        }
        String formatDate = (new SimpleDateFormat(outputTimeFormat)).format(new Date());
        return formatDate;
    }

    /**
     * 根据输入的目标时间格式转换UNIX时间戳
     * <p/>
     * 默认输出 yyyy-MM-dd HH:mm:ss 格式的时间字符串
     * <p/>
     *
     * @param unixStr          UNIX时间戳
     * @param outputTimeFormat 目标时间格式
     * @return java.lang.String 目标时间格式的时间字符串
     * @author HuangZhiGao
     * @date 2021/11/15/015 12:27
     */
    public static String unixToDateTimeByFormat(String unixStr, String outputTimeFormat) {
        if (StringUtils.isBlank(unixStr)) {
            throw new IllegalArgumentException("");
        }
        if (StringUtils.isBlank(outputTimeFormat)) {
            outputTimeFormat = DEFAULT_OUTPUT_TIME_FORMAT;
        }
        long unix = Long.parseLong(unixStr);
        return unixToDateTimeByFormat(unix, outputTimeFormat);
    }

    /**
     * 根据输入的目标时间格式转换UNIX时间戳
     * <p/>
     * 默认输出 yyyy-MM-dd HH:mm:ss 格式的时间字符串
     * <p/>
     *
     * @param unix             UNIX时间戳
     * @param outputTimeFormat 目标时间格式
     * @return java.lang.String 目标时间格式的时间字符串
     * @author HuangZhiGao
     * @date 2021/11/15/015 12:27
     */
    public static String unixToDateTimeByFormat(long unix, String outputTimeFormat) {
        if (StringUtils.isBlank(outputTimeFormat)) {
            outputTimeFormat = DEFAULT_OUTPUT_TIME_FORMAT;
        }
        long timestamp = unix * 1000;
        String formatDate = (new SimpleDateFormat(outputTimeFormat, Locale.CHINA)).format(timestamp);
        return formatDate;
    }

}
