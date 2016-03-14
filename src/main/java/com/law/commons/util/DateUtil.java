package com.law.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	private static final Map<Integer, Integer> TIME_HOLDERS = new HashMap<Integer, Integer>();
	public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String NO_DIV_FORMAT = "yyyyMMddHHmmss";
	public static final String X_G_FORMAT = "yyyy/MM/dd";
	public static final String DATE_FORMAT_HOUR = "yyyy-MM-dd HH";
	public static final String DATE_FORMAT_MI = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
	
	
	
	
	static {
		TIME_HOLDERS.put(1, Calendar.YEAR);
		TIME_HOLDERS.put(2, Calendar.MONTH);
		TIME_HOLDERS.put(3, Calendar.DAY_OF_MONTH);
		TIME_HOLDERS.put(4, Calendar.HOUR_OF_DAY);
		TIME_HOLDERS.put(5, Calendar.MINUTE);
		TIME_HOLDERS.put(6, Calendar.SECOND);
		TIME_HOLDERS.put(7, Calendar.MILLISECOND);
	}

	
	public static String getNowDateMS(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");// 设置日期格式
		return  df.format(new Date());
	}
	public static String getNowMS(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
		return  df.format(new Date());
	}
	
	public static String getNowDate(){
		SimpleDateFormat df = new SimpleDateFormat(DATE_DEFAULT_FORMAT);// 设置日期格式
		return  df.format(new Date());
	}
	
	public static String getDefaultDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
		return sdf.format(date);
	}
	
	public static Date getDefaultDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
		return sdf.parse(date);
	}

//	public static String getDefaultDate(String date) {
//		Date d = getCalendar(date).getTime();
//		return getDefaultDate(d);
//	}

	public static String getNODIVDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(NO_DIV_FORMAT);
		return sdf.format(date);
	}
	public static String getXGDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(X_G_FORMAT);
		return sdf.format(date);
	}
	public static String getNODIVDate(String date) {
		Date d = getCalendar(date).getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(NO_DIV_FORMAT);
		return sdf.format(d);
	}
	
	
	public static String getDateHour(String date) {
		Date d = getCalendar(date).getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_HOUR);
		return sdf.format(d);
	}
	public static String getDateHour(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_HOUR);
		return sdf.format(date);
	}
	public static String getDateMi(String date) {
		Date d = getCalendar(date).getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_MI);
		return sdf.format(d);
	}
	public static String getDateMi(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_MI);
		return sdf.format(date);
	}
	public static String getDay(String date) {
		Date d = getCalendar(date).getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DAY);
		return sdf.format(d);
	}

	public static String getNowOfNODIV() {
		SimpleDateFormat sdf = new SimpleDateFormat(NO_DIV_FORMAT);
		return sdf.format(new Date());
	}

	public static Calendar getCalendar(String date) {

		Calendar cal = Calendar.getInstance();

		Pattern pattern = Pattern.compile("(\\d{4})?[-/]?(\\d{0,2})[-/]?(\\d{0,2})\\s*(\\d{0,2}):?(\\d{0,2}):?(\\d{0,2})\\.?(\\d{0,3})");
		Matcher matcher = pattern.matcher(date);

		if (matcher.find()) {
			int i = 1;
			while (i <= matcher.groupCount()) {
				if (!StringUtils.isBlank(matcher.group(i))) {
					if (TIME_HOLDERS.get(i) == Calendar.MONTH) {
						cal.set(TIME_HOLDERS.get(i), Integer.valueOf(matcher.group(i)) - 1);
					} else {
						cal.set(TIME_HOLDERS.get(i), Integer.valueOf(matcher.group(i)));
					}
				} else {
					if (TIME_HOLDERS.get(i) == Calendar.DAY_OF_MONTH) {
						cal.set(TIME_HOLDERS.get(i), 1);
					} else {
						cal.set(TIME_HOLDERS.get(i), 0);
					}
				}
				i++;
			}
		}

		return cal;

	}

	
//	public static void main(String[] args){
//	String time = getDefaultDate("2014/8/13 9:00");	
//	System.out.println(time);
//	}
	
	
	public  static  String getCurDateString() {
		Date dt = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS z");
		return format.format(dt);
	}
	
	
	public static void main(String[] args){
		Date dt = new Date();
		String d =  getXGDate(dt) ;
		System.out.println(d);
	}
}
