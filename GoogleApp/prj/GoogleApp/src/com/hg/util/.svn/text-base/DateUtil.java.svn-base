package com.hg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

    private DateUtil() {}

    public static Date getCurrentTime() {
        return new Date();
    }

    public static Calendar getCurrentCal() {
        return Calendar.getInstance();
    }

    /**
     * format java.util.Date to java.lang.String<br>
     * 取得日期的字符串表示形式<br>
     *
     * @param format "example:yyyy/MM/dd"
     */
    public static String format(final Date date, final String format) {
        if (date == null || format == null) {
            throw new IllegalArgumentException("InParam error!");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * format java.lang.String to java.util.Date<br>
     * 取得日期的日期表示形式<br>
     *
     * @param format "example:yyyy/MM/dd"
     */
    public static Date parse(final String date, final String format) {
        if (date == null || format == null) {
            throw new IllegalArgumentException("InParam error!");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @return 24 o'clock
     */
    public static Date getTwentyFourClock(final Date date) {
        Calendar cal = getCurrentCal();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * @return zero o'clock
     */
    public static Date getZeroClock(final Date date) {
        Calendar cal = getCurrentCal();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * return true if the parameter 'compare' is today<br>
     * 判断该日期是否和今天是同一天<br>
     */
    public static boolean isToday(final Date compare) {
        Calendar calToday = getCurrentCal();

        Calendar calCompare = getCurrentCal();
        calCompare.setTime(compare);

        return calToday.get(Calendar.DATE) == calCompare.get(Calendar.DATE);
    }
}
