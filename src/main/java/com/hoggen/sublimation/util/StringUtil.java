package com.hoggen.sublimation.util;

import org.springframework.lang.Nullable;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class StringUtil {

	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	public static int getStringLen(String str) {
		int reLn = 0;
		if (str == null || str.equals("")) {
			return reLn;
		}
		try {
			byte[] t_utf8 = str.getBytes("UTF-8");
			return t_utf8.length;
		} catch (Exception e) {
			return reLn;
			// TODO: handle exception
		}

//		String teString = "asdf";
//		String teString1 = "中文中文";
//		String teString2 = "1234";
//		String teString3 = "q4啊";
//		System.out.println("teString:" + StringUtil.getStringLen(teString) + "teString1:"
//				+ StringUtil.getStringLen(teString1) + "teString2:" + StringUtil.getStringLen(teString2) + "teString3:"
//				+ StringUtil.getStringLen(teString3));
	}

	/**
	 * 获取昨天的时间 yyyy-MM-dd HH:mm:ss
	 *
	 * @param
	 * @return
	 */
	public static Date getYesterday(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return strToDateLong(dateString);
	}


	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 *
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		if (strDate == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 *
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		if (dateDate == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd
	 *
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLongForFileName(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd
	 *
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrYMD(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM
	 *
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrYM(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 获取当天0点0分0秒（00:00:00）
	 *
	 * @return
	 */
	public static Date getMidnightZero(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 获取当天23点59分59秒
	 *
	 * @return
	 */
	public static Date getDataEndTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		return cal.getTime();
	}


	public static boolean isEmpty(@Nullable CharSequence str) {
		return str == null || str.length() == 0;
	}

	public static Date getTwodayAgo(Date date) {
		Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
		ca.setTime(date);
		;// 月份是从0开始的，
		ca.add(Calendar.DAY_OF_YEAR, -2); // 日期减2
		Date lastDay = ca.getTime(); // 结果
		return lastDay;
	}
}
