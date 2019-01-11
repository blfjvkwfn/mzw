package com.mzw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DateUtil {
    public static final long DAY_MILLI = 24 * 60 * 60 * 1000; // 一天的MilliSecond

    public static final long MINUTE_MILLI = 60 * 1000; // 一分钟的MilliSecond

    public static final long HOUR_MILLI = 60 * 60 * 1000; // 一小时的MilliSecond
    public static String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss"; // 年/月/日

    public static String getYearMonthDay() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int tempMonth = today.getMonthValue();
        int tempDay = today.getDayOfMonth();
        String month = "";
        String day = "";
        for (int i = 1; i < 31; ++i) {
            if (tempMonth == i && i < 10) {
                month = "0" + tempMonth;
            } else if (tempMonth == i) {
                month = "" + tempMonth;
            }
            if (tempDay == i && i < 10) {
                day = "0" + tempDay;
            } else if (tempDay == i) {
                day = "" + tempDay;
            }
        }
        return year + "-" + month + "-" + day;
    }
    public static String getTime(){
        String date = getYearMonthDay() + " " + LocalTime.now();
        return date;
    }
    public static Date getDate(){
        Date date = null;
        String dateString = getYearMonthDay() + " " + LocalTime.now();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static Date getDateTime(){
        return null;
    }
    /**
     * 在java.util.DateObject上增加/减少几分钟
     *
     * @param date   java.util.Date instance
     * @param minute 增加/减少的分钟数
     * @return java.util.Date Object
     * @history
     * @since 1.0
     */
    public static Date addMinute(Date date, int minute) {
        long temp = date.getTime();

        return new Date(temp + DateUtil.MINUTE_MILLI * minute);
    }
    /**
     * 将日期字符串转化为日期。失败返回null。
     * @param date 日期字符串
     * @param pattern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String pattern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(pattern).parse(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return myDate;
    }
    /**
     * 获取SimpleDateFormat
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    public static String formatDate(Date date, String pattern) {
        return getDateFormat(pattern).format(date);
    }
    public static String defaultFormatDate(Date date) {
        if (date == null) {
            return "";
        }
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
}