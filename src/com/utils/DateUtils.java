package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * ת���ַ�������������
	 * @param date �ַ�����ʽ yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateWithHyphen(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.parse(date);
	}
	
	/**
	 * ת���ַ�������������
	 * @param date �ַ�����ʽ yyyyMMdd
	 * @return
	 * @throws ParseException
	 */
	public static Date getSimpleDate(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat.parse(date);
	}
	
	/**
	 * ��ȡ��ǰ���ڵ�����, ��ʽ: yyyyMM
	 * @return
	 */
	public static String getCurrentyyyyMM() {
		return getYearMonth("yyyyMM", 0);
	}
	
	/**
	 * ��ȡ�뵱ǰ�������һ���·ݵ�����
	 * @param format ���ڸ�ʽ
	 * @param amount �뵱ǰ�����·���ƫ����
	 * @return
	 */
	public static String getYearMonth(String format, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, amount);
		return new SimpleDateFormat(format).format(calendar.getTime());
	}
	
	/**
	 * ��ȡ���µĵ�һ��
	 * @param format ���ڸ�ʽ
	 * @return
	 */
	public static String getFirstDayOfLastMonth(String format) {
		return getFirstDayOfDate(new Date(), format, -1);
	}
	
	/**
	 * ��ȡ�ض��������һ���·ݵĵ�һ��
	 * @param date ָ������
	 * @param format ���ڸ�ʽ
	 * @param amount �·�ƫ����
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
	 * ��ȡ���µ����һ��
	 * @param format ���ڸ�ʽ
	 * @return
	 */
	public static String getLastDayOfLastMonth(String format) {
		return getLastDayOfDate(new Date(), format, -1);
	}
	
	/**
	 * ��ȡ�ض��������һ���·ݵ����һ��
	 * @param date ָ������
	 * @param format ���ڸ�ʽ
	 * @param amount �·�ƫ����
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