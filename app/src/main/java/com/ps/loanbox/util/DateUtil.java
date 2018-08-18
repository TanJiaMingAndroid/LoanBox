/**************************************************************************
 * Copyright (c) 2013-2023  浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.ps.loanbox.util;



import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description 日期处理util
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月24日 下午5:14:55
 * @version V1.0.1
 */
public class DateUtil {

	/**
	 * yyyy-MM-dd
	 */
	public static final String FORMAT_1 = "yyyy-MM-dd";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String FORMAT_3 = "yyyy-MM-dd HH:mm";
	/**
	 * yyyy年MM月dd日
	 */
	public static final String FORMAT_4 = "yyyy年MM月dd日";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String FORMAT_5 = "yyyyMMddHHmmss";
	/**
	 * MM-dd
	 */
	public static final String FORMAT_6 = "MM-dd";
	/**
	 * HH:mm:ss
	 */
	public static final String FORMAT_7 = "HH:mm";
	/**
	 * yyyyMMdd
	 */
	public static final String FORMAT_8 = "yyyyMMdd";
	/**
	 * HHmmss
	 */
	public static final String FORMAT_9 = "HHmmss";
	/**
	 * yyyy
	 */
	public static final String FORMAT_10 = "yyyy";
	/**
	 * 精确到毫秒
	 */
	public static final String FORMAT_11 = "yyyy-MM-dd HH:mm:ss.SSS";

	/* "MM/dd/yyyy"格式形式日期 */
	public static SimpleDateFormat DATE_FORMAT_10 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	public static SimpleDateFormat DATE_FORMAT_11 = new SimpleDateFormat("MM/dd/yyyy");

	/* "yyyy-MM-dd"格式形式日期 */
	public static SimpleDateFormat DATE_FORMAT_20 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat DATE_FORMAT_21 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat DATE_FORMAT_23 = new SimpleDateFormat("yyyy-MM-dd");

	/* "yyyyMMdd"格式形式日期 */
	public static SimpleDateFormat DATE_FORMAT_30 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat DATE_FORMAT_31 = new SimpleDateFormat("yyyyMMdd");

	public static SimpleDateFormat DATE_FORMAT_40 = new SimpleDateFormat(" HH:mm");

	/**
	 * get String value of time
	 * 
	 * @param d
	 * @param dateFormat
	 * @return
	 */
	public static String timeToString(Date d, DateFormat dateFormat) {
		if (d == null) {
			return null;
		}
		return dateFormat.format(d);
	}

	/**
	 * get String value of time
	 * 
	 * @param d
	 * @param pattern
	 * @return
	 */
	public static String timeToString(Date d, String pattern) {
		if (d == null) {
			return null;
		}
		return new SimpleDateFormat(pattern).format(d);
	}

	/**
	 * 字符串转日期
	 * 
	 * @Description
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param s
	 * @param dateFormat
	 * @return
	 */
	public static Date stringToTime(String s, DateFormat dateFormat) {
		if (s == null) {
			return null;
		}
		try {
			return dateFormat.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 字符串转日期
	 * 
	 * @Description
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param s
	 * @param pattern
	 * @return
	 */
	public static Date stringToTime(String s, String pattern) {
		if (s == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(pattern).parse(s);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 时间转换为long值
	 * 
	 * @Description
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static long timeToLong(String time, String pattern) {
		if (time == null) {
			return 0;
		}
		try {
			return new SimpleDateFormat(pattern).parse(time).getTime();
		} catch (ParseException e) {
			return 0;
		}
	}

	/**
	 * 时间转换为long值
	 * 
	 * @Description
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param time
	 * @return
	 */
	public static long timeToLong(Date time) {
		if (time == null) {
			return 0;
		}
		return time.getTime();

	}

	/**
	 * long日期转换为字符串
	 * 
	 * @Description
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param dateFormat
	 * @param millSec
	 * @return
	 */
	public static String longToString(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}

	public static int getYear(Date d) {
		if (d != null) {
			final Calendar c = new GregorianCalendar();
			c.setTime(d);
			return c.get(Calendar.YEAR);
		}
		return 0;
	}

	/**
	 * 字符串转换日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateTime(String date, String format) throws ParseException {
		if (date == null || date.isEmpty()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}

	public static Date format(String date, String pattern) throws ParseException {
		if (StringUtils.isEmpty(date))
			return null;

		SimpleDateFormat tempDate = new SimpleDateFormat(pattern);
		return tempDate.parse(date);
	}

	public static String timestampToDate(Timestamp timestamp, String format) {
		String tsStr = null;
		DateFormat sdf = new SimpleDateFormat(format);
		try {
			// 方法一
			tsStr = sdf.format(timestamp);
			System.out.println(tsStr);
			// // 方法二
			// tsStr = timestamp.toString();
			System.out.println(tsStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}

	/**
	 * 时间间隔(秒) 永远为整数
	 * 
	 * @param current_time
	 * @param compare_time
	 * @return
	 */
	public static long getDateCompare(String current_time, String compare_time, String pattern) {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		long time = 0;
		try {
			Date c_tiem = sf.parse(current_time);
			Date com_time = sf.parse(compare_time);
			return getDateCompare(c_tiem, com_time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 时间间隔(秒) 永远为正数
	 * 
	 * @Description
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param currentTime
	 * @param compareTime
	 * @return
	 */
	public static long getDateCompare(Date currentTime, Date compareTime) {
		long time = 0;
		try {
			// long l = > 0 ? currentTime.getTime() - compareTime.getTime() :
			// compareTime.getTime() - currentTime.getTime();
			time = Math.abs(currentTime.getTime() - compareTime.getTime()) / 1000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 时间差 type:1天，2，小时，3，分钟，4秒
	 * 
	 * @Description
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param currentTime
	 * @param compareTime
	 * @param type
	 * @return
	 */
	public static long getDateDiff(Date currentTime, Date compareTime, int type) {
		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long diffTime = null;
		try {

			long diff = currentTime.getTime() - compareTime.getTime();
			switch (type) {
			case 1:
				diffTime = diff / (1000 * 60 * 60 * 24);
				break;
			case 2:
				diffTime = diff / (1000 * 60 * 60);
				break;
			case 3:
				diffTime = diff / (1000 * 60);
				break;
			case 4:
				diffTime = diff / 1000;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffTime;
	}

	/**
	 * 时间差 type:1天，2，小时，3，分钟，4秒
	 * 
	 * @return
	 */
	public static long getDateDiff(String strDate, String endDate, int type) {
		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long diffTime = null;
		try {
			// 毫秒ms
			Date c_tiem = DATE_FORMAT_20.parse(endDate);
			Date com_time = DATE_FORMAT_20.parse(strDate);

			diffTime = getDateDiff(c_tiem, com_time, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffTime;
	}

	/**
	 * 当前日期加减n天
	 * 
	 * @param n
	 * @return
	 */
	public static String nowDateAddDays(int n) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, n);
		return DATE_FORMAT_23.format(now.getTime());
	}

	/**
	 * 当前时间加几个月
	 * 
	 * @param date
	 *            设置的时间
	 * @param
	 * @return
	 */
	public static String nowDateAddMonths(int n, Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MONTH, n);
		return DATE_FORMAT_23.format(now.getTime());
	}

	public static String nowDateAddDays(int n, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, n);
		return df.format(now.getTime());
	}

	/**
	 * date加减n天
	 * 
	 * @param date
	 * @param n
	 * @return
	 * @throws ParseException
	 */
	public static String dateAddDays(String date, int n) throws ParseException {
		Date dt = DATE_FORMAT_23.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DAY_OF_MONTH, n);
		return DATE_FORMAT_23.format(calendar.getTime());
	}

	/**
	 * 获取两个日期相隔年数
	 * 
	 * @param startDate
	 * @param enddate
	 * @return
	 */
	public static double getDiffYears(Date startDate, Date enddate) {
		Calendar starCal = Calendar.getInstance();
		starCal.setTime(startDate);

		int sYear = starCal.get(Calendar.YEAR);
		int sMonth = starCal.get(Calendar.MONTH);
		int sDay = starCal.get(Calendar.DATE);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(enddate);
		int eYear = endCal.get(Calendar.YEAR);
		int eMonth = endCal.get(Calendar.MONTH);
		int eDay = endCal.get(Calendar.DATE);

		double year = eYear - sYear;
		if (year != 0) {
			if (eMonth > sMonth) {
				year = year + 0.5;
			} else if (eMonth == sMonth) {
				if (eDay > sDay) {
					year = year + 0.5;
				} else if (eDay < sDay) {
					year = year - 1;
				}
			} else {
				year = year - 1;
			}
		}
		return year;
	}

	/**
	 * @param startdate
	 *            开始日期
	 * @param enddate
	 *            结束日期
	 * @return 两个日期相隔天数
	 */
	public static int dayBet2Date(Date startdate, Date enddate) {
		if (null == startdate || null == enddate) {
			return 0;
		}
		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(enddate);

		int endday = aCalendar.get(Calendar.DAY_OF_YEAR);

		int endyear = aCalendar.get(Calendar.YEAR);

		aCalendar.setTime(startdate);

		int startday = aCalendar.get(Calendar.DAY_OF_YEAR);

		int startyear = aCalendar.get(Calendar.YEAR);

		int year = endyear - startyear;

		int result = year * 365 + (endday - startday);

		return result;
	}

	// 由出生日期获得年龄
	public static int getAge(Date birthDay) {
		if (birthDay == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			return -1;
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;
			} else {
				age--;
			}
		}
		return age;
	}

	/**
	 * 获取指定日期所在周的第一天
	 * 
	 * @Description
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param dateStr
	 * @return
	 */
	public static String getWeekFirstDay(String dateStr) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(stringToTime(dateStr, FORMAT_1));

		int d = 0;
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			d = -6;
		} else {
			d = 2 - cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);

		return DATE_FORMAT_23.format(cal.getTime());
	}

	public static String getWeekFirstDay(Date date) {
		return getWeekFirstDay(timeToString(date, DATE_FORMAT_23));
	}

	/**
	 * 获取指定日期所在周的最后一天
	 * 
	 * @Description
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param dateStr
	 * @return
	 */
	public static String getWeekLastDay(String dateStr) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(stringToTime(dateStr, FORMAT_1));
		int d = 0;
		if (cal.get(Calendar.DAY_OF_WEEK) != 1) {
			d = 8 - cal.get(Calendar.DAY_OF_WEEK);
		}

		cal.add(Calendar.DAY_OF_WEEK, d);

		return DATE_FORMAT_23.format(cal.getTime());
	}

	public static String getWeekLastDay(Date date) {
		return getWeekLastDay(timeToString(date, DATE_FORMAT_23));
	}

	/**
	 * 获取任意时间的月第一天
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getMonthFirstDate(String dateStr) {
		Calendar calendar = Calendar.getInstance();
		try {
			if (StringUtils.isNotBlank(dateStr)) {
				calendar.setTime(DATE_FORMAT_23.parse(dateStr));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return DATE_FORMAT_23.format(calendar.getTime());
	}

	public static String getMonthFirstDate(Date date) {
		return getMonthFirstDate(timeToString(date, DATE_FORMAT_23));
	}

	/**
	 * 获取任意时间的月的最后一天
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getMonthLastDate(String dateStr) {
		Calendar calendar = Calendar.getInstance();
		try {
			if (StringUtils.isNotBlank(dateStr)) {
				calendar.setTime(DATE_FORMAT_23.parse(dateStr));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return DATE_FORMAT_23.format(calendar.getTime());
	}

	public static String getMonthLastDate(Date date) {
		return getMonthLastDate(timeToString(date, DATE_FORMAT_23));
	}

	/**
	 * 获取当前0点 修改时间
	 * 
	 * @Description
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @return
	 */
	public static long getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}

	/**
	 * @Description 获取当天结束时间
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @return
	 */
	public static long getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime();
	}

}
