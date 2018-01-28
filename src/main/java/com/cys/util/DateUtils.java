package com.cys.util;

/**
 * Created by liyuan on 2018/1/29.
 */
/**
 * 文件名：DateUtil.java
 *
 * 版本信息：v1.0
 * 日期：2012-10-10
 * Copyright  Corporation 2012
 * 版权所有 E-vada
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    final static Logger log = LoggerFactory.getLogger(DateUtils.class);
    private static DateUtils instance = new DateUtils();
    private static String DEFAULT_PATTERN = "yyyy-MM-dd";
    private static Map<String, SimpleDateFormat> formats;

    private DateUtils() {
        resetFormats();
    }

    public static DateUtils getInstance() {
        return instance;
    }

    /** Reset the supported formats to the default set. */
    public void resetFormats() {
        formats = new HashMap<String, SimpleDateFormat>();

        // alternative formats
        formats.put("yyyy-MM-dd HH:mm:ss", new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss"));
        formats.put("yyyyMMddHHmmssms",
                new SimpleDateFormat("yyyyMMddHHmmssms"));
        formats.put("yyyyMMddHHmmss", new SimpleDateFormat("yyyyMMddHHmmss"));

        // alternative formats
        formats.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
        formats.put("yyyy-MM-dd HH:mm", new SimpleDateFormat("yyyy-MM-dd HH:mm"));

        formats.put("yyyyMMdd", new SimpleDateFormat("yyyyMMdd"));
        formats.put("yyyyMMddHHmm", new SimpleDateFormat("yyyyMMddHHmm"));

        // XPDL examples format
        formats.put("MM/dd/yyyy HH:mm:ss a", new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss a"));

        // alternative formats
        formats.put("yyyy-MM-dd HH:mm:ss a", new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss a"));

        // ISO formats
        formats.put("yyyy-MM-dd'T'HH:mm:ss'Z'", new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'"));
        formats.put("yyyy-MM-dd'T'HH:mm:ssZ", new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ"));
        formats.put("yyyy-MM-dd'T'HH:mm:ssz", new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssz"));
        formats.put("yyyy/MM/dd HH:mm:ss", new SimpleDateFormat(
                "yyyy/MM/dd HH:mm:ss"));
    }

    /**
     *
     * @param date
     *
     * @param pattern
     *
     * @return
     */
    public static String format(Date date, String pattern) {
        if (!formats.containsKey(pattern))
            pattern = DEFAULT_PATTERN;
        DateFormat format = formats.get(pattern);
        return format.format(date);
    }

    /**
     * @param date
     * @return String
     */
    public static String format(Date date) {
        Iterator<SimpleDateFormat> iter = formats.values().iterator();
        while (iter.hasNext()) {
            return iter.next().format(date);
        }
        return null;
    }

    /**
     * @param date
     * @param pattern
     * @return
     */
    public static Date parse(String date, String pattern) {
        Date resultDate = null;
        try {
            if (!formats.containsKey(pattern))
                pattern = DEFAULT_PATTERN;
            resultDate = formats.get(pattern).parse(date);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return resultDate;
    }

    /**
     *
     * @param dateString
     * @return Date
     */
    public static Date parse(String dateString) {
        Iterator<SimpleDateFormat> iter = formats.values().iterator();
        while (iter.hasNext()) {
            try {
                return iter.next().parse(dateString);
            } catch (ParseException e) {
                log.error(e.getMessage(),e);
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * @param offsetYear
     * @return
     */
    public static Timestamp getTimestampExpiredYear(int offsetYear) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, offsetYear);
        return new Timestamp(now.getTime().getTime());
    }

    /**
     * @param offsetMonth
     * @return + offsetMonth
     */
    public static Timestamp getCurrentTimestampExpiredMonth(int offsetMonth) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MONTH, offsetMonth);
        return new Timestamp(now.getTime().getTime());
    }

    /**
     * @param offsetDay
     * @return + offsetDay
     */
    public static Timestamp getCurrentTimestampExpiredDay(int offsetDay) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, offsetDay);
        return new Timestamp(now.getTime().getTime());
    }

    /**
     * @param offsetSecond
     * @return + offsetSecond
     */
    public static Timestamp getCurrentTimestampExpiredSecond(int offsetSecond) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, offsetSecond);
        return new Timestamp(now.getTime().getTime());
    }

    /**
     * @param offsetDay
     * @return + offsetDay
     */
    public static Timestamp getTimestampExpiredDay(Date givenDate, int offsetDay) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.DATE, offsetDay);
        return new Timestamp(date.getTime().getTime());
    }

    /**
     *
     * @param offsetMonth
     * @return + offsetMonth
     */
    public static Timestamp getTimestampExpiredMonth(Date givenDate,
                                                     int offsetMonth) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.MONTH, offsetMonth);
        return new Timestamp(date.getTime().getTime());
    }

    /**
     * @param offsetSecond
     * @return + offsetSecond
     */
    public static Timestamp getTimestampExpiredSecond(Date givenDate,
                                                      int offsetSecond) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.SECOND, offsetSecond);
        return new Timestamp(date.getTime().getTime());
    }

    /**
     *
     * @param givenDate
     * @param offsetHour
     * @return
     */
    public static Timestamp getTimestampExpiredHour(Date givenDate,
                                                    int offsetHour) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.HOUR, offsetHour);
        return new Timestamp(date.getTime().getTime());
    }

    /**
     * @return yyyy-MM-dd
     */
    public static String getCurrentDay() {
        return format(new Date(), DEFAULT_PATTERN);
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getNowTime() {
        return format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @return
     */
    public static Date getMonthFirstDay(Date givenDate) {
        Date date = DateUtils.parse(DateUtils.format(givenDate, "yyyy-MM"),
                "yyyy-MM");
        return date;
    }

    /**
     * @return
     */
    public static Date getMonthLastDay(Date givenDate) {
        Date firstDay = getMonthFirstDay(givenDate);
        Date lastMonthFirstDay = DateUtils.getTimestampExpiredMonth(firstDay, 1);
        Date lastDay = DateUtils.getTimestampExpiredDay(lastMonthFirstDay, -1);
        return lastDay;
    }

    /**
     * 获取两个日期间的天数
     * @param startDate 起始时间
     * @param endDate 终止时间
     * @return 两个日期间的天数
     */
    public static int daysBetween(Date startDate, Date endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(startDate);
        startDate = parse(dateStr);
        dateStr = format.format(endDate);
        endDate = parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long startTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long endTime = cal.getTimeInMillis();
        long between_days = (endTime - startTime) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

}

