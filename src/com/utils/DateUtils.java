package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * 转换字符串到日期类型
	 * @param date 字符串格式 yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateWithHyphen(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.parse(date);
	}
	
	/**
	 * 转换字符串到日期类型
	 * @param date 字符串格式 yyyyMMdd
	 * @return
	 * @throws ParseException
	 */
	public static Date getSimpleDate(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.parse(date);
	}
	
	/**
	 * 获取当前日期的年月, 格式: yyyyMM
	 * @return
	 */
	public static String getCurrentyyyyMM() {
		return getYearMonth("yyyyMM", 0);
	}
	
	/**
	 * 获取与当前日期相差一定月份的年月
	 * @param format 日期格式
	 * @param amount 与当前日期月份数偏移量
	 * @return
	 */
	public static String getYearMonth(String format, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, amount);
		return new SimpleDateFormat(format).format(calendar.getTime());
	}
	
	/**
	 * 获取上月的第一天
	 * @param format 日期格式
	 * @return
	 */
	public static String getFirstDayOfLastMonth(String format) {
		return getFirstDayOfDate(new Date(), format, -1);
	}
	
	/**
	 * 获取特定日期相差一定月份的第一天
	 * @param date 指定日期
	 * @param format 日期格式
	 * @param amount 月份偏移量
	 * @return
	 */
	public static String getFirstDayOfDate(Date date, String format, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat(format).format(calendar.getTime());
	}
	
	/**
	 * 获取上月的最后一天
	 * @param format 日期格式
	 * @return
	 */
	public static String getLastDayOfLastMonth(String format) {
		return getLastDayOfDate(new Date(), format, -1);
	}
	
	/**
	 * 获取特定日期相差一定月份的最后一天
	 * @param date 指定日期
	 * @param format 日期格式
	 * @param amount 月份偏移量
	 * @return
	 */
	public static String getLastDayOfDate(Date date, String format, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return new SimpleDateFormat(format).format(calendar.getTime());
	}
}