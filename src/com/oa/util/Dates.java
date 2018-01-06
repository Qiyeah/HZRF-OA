package com.oa.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Dates {
    private final static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    private final static String dateTimePattern2 = "yyyy/MM/dd HH:mm:ss";
    private final static String dateTimePattern3 = "yyyyMMddHHmmss";
    private final static String dateTimePattern4 = "yyyy-MM-dd HH:mm";
    private final static String datePattern = "yyyy-MM-dd";
    private final static String datePattern2 = "yyyy/MM/dd";
    private final static String datePattern3 = "yyyy-MM";
    private final static String datePattern4 = "yyyy";
    private final static String datePattern5 = "M";
    private final static String timePattern = "HH:mm:ss";
    private final static String stimePattern = "HH:mm";

    public static Date parse(String text) {
	try {
	    if (text.length() > dateTimePattern.length())
		text = text.substring(0, dateTimePattern.length());
	    if (text.length() == dateTimePattern.length()) {
		if (text.charAt(4) == '-' && text.charAt(7) == '-') {
		    return new SimpleDateFormat(dateTimePattern).parse(text);
		}
		if (text.charAt(4) == '/' && text.charAt(7) == '/') {
		    if (text.charAt(13) == ':' && text.charAt(16) == ':') {
			return new SimpleDateFormat(dateTimePattern2).parse(text);
		    }
		}
	    } else if (text.length() == dateTimePattern3.length()) {
		return new SimpleDateFormat(dateTimePattern3).parse(text);
	    } else if (text.length() == datePattern3.length()) {
		return new SimpleDateFormat(datePattern3).parse(text);
	    } else if (text.length() == datePattern.length()) {
		if (text.charAt(4) == '-' && text.charAt(7) == '-') {
		    return new SimpleDateFormat(datePattern).parse(text);
		}
		if (text.charAt(4) == '/' && text.charAt(7) == '/') {
		    return new SimpleDateFormat(datePattern2).parse(text);
		}
	    } else if (text.length() == timePattern.length()) {
		if (text.charAt(2) == ':' && text.charAt(5) == ':') {
		    return new SimpleDateFormat(timePattern).parse(text);
		}
	    } else if (text.length() == stimePattern.length()) {
		if (text.charAt(2) == ':') {
		    return new SimpleDateFormat(stimePattern).parse(text);
		}
	    } else if (text.length() == dateTimePattern4.length()) {
		return new SimpleDateFormat(dateTimePattern4).parse(text);
	    }
	    return new Date(Long.parseLong(text));
	} catch (Exception e) {
	    return null;
	}
    }

    public static Date changeType(Date date, Class<?> targetType) {
	if (date == null) {
	    return date;
	}
	if (java.sql.Date.class == targetType) {
	    date = new java.sql.Date(date.getTime());
	} else if (java.sql.Time.class == targetType) {
	    date = new java.sql.Time(date.getTime());
	} else if (java.sql.Timestamp.class == targetType) {
	    date = new java.sql.Timestamp(date.getTime());
	}
	return date;
    }

    /**
     * 判断给定日期是否为当天， 距离当前时间七天之内的日期，和七天之外的日期
     *
     * @param dt
     * @param type
     *            0--当天 1--7天之内的 2--7天之外的
     */
    public static boolean getDayDiffFromToday(Long dt, int type) {
	Calendar c = Calendar.getInstance();
	c.set(Calendar.HOUR_OF_DAY, 23);
	c.set(Calendar.MINUTE, 59);
	c.set(Calendar.SECOND, 59);
	long diff = c.getTimeInMillis() - dt;
	if (diff < 0)
	    diff = 0;
	long days = diff / (1000 * 60 * 60 * 24);
	if (type == 0 && days == 0)
	    return true;
	if (type == 1 && days > 0 && days <= 7)
	    return true;
	if (type == 2 && days > 7)
	    return true;
	return false;
    }

    public static String format(Date date, DateFormat format) {
	DateFormat dateFormat = (format != null ? format : new SimpleDateFormat("yyyyMMdd"));
	try {
	    return dateFormat.format(date);
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * 获取今天启始日期
     */
    public static Long getToday() {
	Calendar cal = Calendar.getInstance(Locale.CHINA);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	return cal.getTimeInMillis();
    }

    /**
     * 获取昨天启始日期
     */
    public static Long getYesterday() {
	Calendar cal = Calendar.getInstance(Locale.CHINA);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	cal.add(Calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
	return cal.getTimeInMillis();
    }

    /**
     * 获取今天日期
     */
    public static Long getToday(int hour) {
	Calendar cal = Calendar.getInstance(Locale.CHINA);
	cal.set(Calendar.HOUR_OF_DAY, hour);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	return cal.getTimeInMillis();
    }

    /**
     * 获取明天启始日期
     */
    public static Long getTomorrow() {
	Calendar cal = Calendar.getInstance(Locale.CHINA);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	cal.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
	return cal.getTimeInMillis();
    }

    /**
     * 获取本周的星期一
     */
    public static Date getThisMonday() {
	Calendar cal = Calendar.getInstance(Locale.CHINA);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	cal.setFirstDayOfWeek(Calendar.MONDAY); // 以周1为首日
	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	return cal.getTime();
    }

    /**
     * 获取本周的星期日
     */
    public static Date getThisSunday() {
	Calendar cal = Calendar.getInstance(Locale.CHINA);
	cal.set(Calendar.HOUR_OF_DAY, 23);
	cal.set(Calendar.MINUTE, 59);
	cal.set(Calendar.SECOND, 59);
	cal.set(Calendar.MILLISECOND, 0);
	cal.setFirstDayOfWeek(Calendar.MONDAY); // 以周1为首日
	cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	return cal.getTime();
    }

    /**
     * 获取某年某月第几周的第几天，以周日为首日
     *
     * @param year
     *            年份，如：2014
     * @param month
     *            月份，0-11
     * @param weekly
     *            当月第几周，1-6
     * @param day
     *            第几天，1-7
     * @return 目标日期的长整型数值
     */
    public static Long getWeeklyDay(int year, int month, int weekly, int day) {
	Calendar cal = Calendar.getInstance(Locale.CHINA);
	cal.set(Calendar.YEAR, year);
	cal.set(Calendar.MONTH, month);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	cal.setFirstDayOfWeek(Calendar.SUNDAY); // 以周日为首日
	cal.set(Calendar.WEEK_OF_MONTH, weekly);
	cal.set(Calendar.DAY_OF_WEEK, day);
	return cal.getTimeInMillis();
    }

    public static Integer getWeekly() {
	Calendar cal = Calendar.getInstance(Locale.CHINA);
	cal.setFirstDayOfWeek(Calendar.MONDAY); // 以周1为首日
	return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得当前年斜线月字串
     *
     * @param tune
     *            调整月份，当前月为 “1”
     * @return 年 + “/” + 月
     * @Author: jiacheng.yan@aliyun.com
     */
    public static String getYearObliqueMonth(String seperator, int tune) {
	StringBuffer sb = new StringBuffer();
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	month = month + tune;
	sb.append(year);
	sb.append(seperator);
	if (month > 9)
	    sb.append(month);
	else {
	    sb.append("0");
	    sb.append(month);
	}
	return sb.toString();

    }

    /**
     * 返回当前日期是星期几
     *
     * @param dateTime
     *            UTC时间
     * @return 星期N
     * @Author: jiacheng.yan@aliyun.com
     */
    public static int dayForWeek(long dateTime) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date(dateTime));
	int dayForWeek = 0;
	if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
	    dayForWeek = 7;
	} else {
	    dayForWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
	}
	return dayForWeek;
    }

    /**
     * 获得一个月的天数
     *
     * @param month
     *            月份，格式：yyyy-MM
     * @return 天数
     */
    public static int getDaysOfMonth(String month) {
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern3);
	try {
	    calendar.setTime(simpleDateFormat.parse(month));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取上个月字符串
     *
     * @param month
     *            月份
     * @return 上个月字符串
     */
    public static String getLastMonth(String month) {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern3);
	Calendar calendar = Calendar.getInstance();
	try {
	    calendar.setTime(simpleDateFormat.parse(month));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	// 取得上一个月时间
	calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
	String lastMonth = simpleDateFormat.format(calendar.getTime());
	return lastMonth;
    }

    /**
     * 获取下个月字符串
     *
     * @param month
     *            月份
     * @return 下个月字符串
     */
    public static String getNextMonth(String month) {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern3);
	Calendar calendar = Calendar.getInstance();
	try {
	    calendar.setTime(simpleDateFormat.parse(month));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	// 取得下一个月时间
	calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
	String lastMonth = simpleDateFormat.format(calendar.getTime());
	return lastMonth;
    }

    /**
     * 获取去年今月字符串
     *
     * @param month
     *            月份
     * @return 去年今月字符串
     */
    public static String getThisMonthLastYear(String month) {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern3);
	Calendar calendar = Calendar.getInstance();
	try {
	    calendar.setTime(simpleDateFormat.parse(month));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	// 取得上年时间
	calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
	String lastYearMonth = simpleDateFormat.format(calendar.getTime());
	return lastYearMonth;
    }

    /**
     * 获取一个月的最后一天的日期，格式yyyy-MM-dd
     *
     * @param month
     *            要查询的月份，格式yyyy-MM
     * @return 一个月的最后一天的日期
     */
    public static String getLastDayOfMonth(String month) {
	Date monthStart = parse(month);
	int days = getDaysOfMonth(month);
	Date lastDay = new Date(monthStart.getTime() + (days - 1) * BusinessConstant.INTERVAL_DAY);
	return new SimpleDateFormat(datePattern).format(lastDay);
    }

    /**
     * 判断所给月份是不是当月
     *
     * @param month
     *            要查询的月份，格式yyyy-MM
     * @return
     */
    public static boolean isThisMonth(String month) {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern3);
	Calendar calendar = Calendar.getInstance();
	try {
	    calendar.setTime(simpleDateFormat.parse(month));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	Calendar thisMonth = Calendar.getInstance();
	thisMonth.setTime(new Date());
	return calendar.get(Calendar.YEAR) == thisMonth.get(Calendar.YEAR)
		&& calendar.get(Calendar.MONTH) == thisMonth.get(Calendar.MONTH);
    }

    /**
     * 获取今年年份
     *
     * @return
     */
    public static String getThisYear() {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern4);
	Calendar calendar = Calendar.getInstance();
	String thisYear = simpleDateFormat.format(calendar.getTime());
	return thisYear;
    }

    /**
     * 获取当月月份
     *
     * @return
     */
    public static String getThisMonth() {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern5);
	Calendar calendar = Calendar.getInstance();
	String thisYear = simpleDateFormat.format(calendar.getTime());
	return thisYear;
    }

    public static String getNowDate() {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	return format.format(new Date());
    }

    /**
     * 判断指定日期是否今天
     * 
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
	String today = getNowDate();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String dateStr = format.format(date);
	return today.equals(dateStr);
    }

    public static void main(String[] agrs) {
	System.out.println(getThisYear());
    }

}