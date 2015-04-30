package com.just.lollipop.bbs.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <p>实现日期操作的工具类</p>
 * @author zhongliwen
 * @email zhongliwen1981@163.com
 */
public abstract class DateUtil {
	
	/**
	 * 取得指定月份的第一天
	 * @param strdate
	 * @return
	 */
	public static String getMonthBegin(String strdate)
	{
		Date date = parseDate(strdate);
		return formatDateByFormat(date, "yyyy-MM") + "-01";
	}
	
	/**
	 * 取得指定月份的最后一天
	 * @param strdate
	 * @return
	 */
	public static String getMonthEnd(String strdate)
	{
		Date date = parseDate(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONDAY, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}
	public static String format(String date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.format(sdf.parse(date));
		} catch (ParseException e) {
		}
		return date;
	}
	/**
	 * 字符串转日期
	 * @param dataStr
	 * @return
	 */
	public static Date parseDate(String dataStr)
	{
		return parseDate(dataStr, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * java.sql.Date转java.util.Date
	 * @param date
	 * @return
	 */
	public static Date parseDate(java.sql.Date date)
	{
		return date;
	}
	
	/**
	 * java.util.Date转java.sql.Date
	 * @param date
	 * @return
	 */
	public static java.sql.Date parseSqlDate(Date date)
	{
		if (date != null)
			return new java.sql.Date(date.getTime());
		else 
			return null;
	}
	
	/**
	 * 字符串转日期
	 * @param dateStr 日期字符串
	 * @param format 格式
	 * @return
	 */
	public static java.sql.Date parseSqlDate(String dateStr, String format)
	{
		Date date = parseDate(dateStr, format);
		return parseSqlDate(date);
	}
	
	/**
	 * 字符串转日期
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date parseSqlDate(String dateStr)
	{
		return parseSqlDate(dateStr, "yyyy-MM-dd");
	}
	
	/**
	 * 字符串转时间截
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static java.sql.Timestamp parseTimestamp(String dateStr, String format)
	{
		Date date = parseDate(dateStr, format);
		if (date != null)
		{
			long t = date.getTime();
			return new java.sql.Timestamp(t);
		}else
			return null;
	}
	
	/**
	 * 字符串转时间截
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Timestamp parseTimestamp(String dateStr)
	{
		return parseTimestamp(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 常用的格式化日期
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		return formatDateByFormat(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * 以指定的格式来格式化日期
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateByFormat(Date date, String format)
	{
		String result = "";
		if (date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			result = sdf.format(date);
		}
		return result;
	}
	
	/**
	 * 格式化日期
	 * @param dateStr 字符型日期
	 * @param formatStr 格式
	 * @return 返回日期
	 */
	public static Date parseDate(String dateStr, String formatStr)
	{
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 返回年份
	 * @param date 日期
	 * @return
	 */
	public static int getYear(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 返回月份
	 * @param date 日期
	 * @return
	 */
	public static int getMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 返回日份
	 * @param date
	 * @return
	 */
	public  static int getDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 返回小时
	 * @param date
	 * @return
	 */
	public static int getHour(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 返回分钟
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}
	
	/**
	 * 返回秒钟
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}
	
	/**
	 * 返回毫秒
	 * @param date
	 * @return
	 */
	public static long getMillis(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}
	
	/**
	 * 返回字符串时间
	 * @param date
	 * @return
	 */
	public static String getTime(Date date)
	{
		return format(date,"HH:mm:ss");
	}
	
	/**
	 * 返回字符串日期
	 * @param date
	 * @return
	 */
	public static String getDate(Date date)
	{
		return format(date, "yyyy-MM-dd");
	}
	
	/**
	 * 返回字符串日期时间
	 * @param date
	 * @return
	 */
	public static String getDateTime(Date date)
	{
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 格式化输出日期
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format)
	{
		java.text.DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	/**
	 * 日期相加
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDate(Date date, int day)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long)day) * 24 * 3600 * 1000);
		return c.getTime();
	}
	
   /**
	* 日期相减
	* @param date 日期
	* @param date1 日期
	* @return 返回相减后的日期
	*/
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 获取一个关于时间的数组
	 * @return 0：year 1：date 2：month 3：hour 4：min 5：sec
	 */
	public static int[] getDateData(){
		int[] dateData = {0,0,0,0,0};
		Calendar now = Calendar.getInstance();
		dateData[0] = now.get(Calendar.YEAR);
		dateData[1] = now.get(Calendar.DAY_OF_MONTH);
		dateData[2] = now.get(Calendar.MONTH) + 1;
		dateData[3] = now.get(Calendar.HOUR);
		dateData[4] = now.get(Calendar.MINUTE);
		dateData[5] = now.get(Calendar.SECOND);
		return dateData;
	}
	
	/**
	 * 判断两个日期是否在同一周
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
			}
			else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
				// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
					return true;
			}
			else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
					return true;
		}
		return false;
	}
	
	/**
	 * 产生周序列
	 */
	public static String getSeqWeek(){
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if(week.length()==1)week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR)); 
		return year+week;
	}
	
	/**
	 * 获得周一的日期
	 * @param date
	 * @return
	 */
	public static String getMondayString(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
	/**
	 * 获得周五的日期
	 */
	public static String getFridayString(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY); 
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 字符串转 Calendar
	 * @param IDCardNum
	 * @return
	 */
	public static Calendar getDateFromIDCard(String IDCardNum){
		int year, month, day, idLength = IDCardNum.length();
		Calendar cal = Calendar.getInstance();

		if(idLength == 18){
			year = Integer.parseInt(IDCardNum.substring(6,10));
			month = Integer.parseInt(IDCardNum.substring(10,12));
			day = Integer.parseInt(IDCardNum.substring(12,14));
		}
		else if(idLength == 15){
			year = Integer.parseInt(IDCardNum.substring(6,8)) + 1900;
			month = Integer.parseInt(IDCardNum.substring(8,10));
			day = Integer.parseInt(IDCardNum.substring(10,12));
		}
		else {
			return null;
		}
		cal.set(year, month, day);
		return cal;
	}

	/**
	 * 取工作日天数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getWorkDay(Date d1, Date d2){
		int[] freeDays = {0, 6};//default: Sunday and Saturday are the free days.
		return getWorkDay(d1, d2, freeDays);
	}

	/**
	 * 取休息日天数
	 * @param date
	 * @param dNum
	 * @return
	 */
	public static int getFreeDay(Date date, int dNum){
		int[] freeDays = {0, 6};//default: Sunday and Saturday are the free days.
		return getFreeDay(date, dNum, freeDays);
	}

	/**
	 * 
	 * @param d1 日期
	 * @param d2 日期
	 * @param freeDays 休息日数组
	 * @return 工作日天数
	 */
	public static int getWorkDay(Date d1, Date d2, int[] freeDays){
		int dNum = 0;
		dNum = (int) ((d2.getTime() - d1.getTime()) / 1000 / 60 / 60 /24) + 1;
		return dNum - getFreeDay(d1, dNum, freeDays);
	}

	/**
	 * 
	 * @param date 日期
	 * @param dNum 偏移天数
	 * @param freeDays 休息日数组
	 * @return 休息日天数
	 */
	public static int getFreeDay(Date date, int dNum, int[] freeDays){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int start = cal.get(Calendar.DAY_OF_WEEK) - 1;
		int freeNum = 0;
		for(int i = 0; i < dNum; i++){
			for(int j = 0; j < freeDays.length; j++){
				if((start + i) % 7 == freeDays[j]){
					freeNum++;
				}
			}
		}
		return freeNum;
	}

	/**
	* 日期相加
	* 
	* @param date 日期
	* @param offset 天数
	* @return 返回相加后的日期
	*/
	public static Date changeDay(Date date, int offset){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar.getTime();
	}

	/**
	 * 日期相加
	 * @param calendar
	 * @param offset
	 * @return 返回相加后的Calendar
	 */
	public static Calendar changeDay(Calendar calendar, int offset){
		calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar;
	}

	/**
	 * 获得周一的日期
	 * @param date
	 * @return
	 */
	public static Date getMonday(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return c.getTime();
	}

	/**
	* 日期相减
	* 
	* @param date 日期
	* @param date1 日期
	* @return 返回相减后的天数
	*/
	public static int getDiffDate(Date date, Date date1) {
		return (int) ((date.getTime() - date1.getTime()) / (24 * 3600 * 1000));
	}

	/**
	 * 
	 * @param date 日期
	 * @param date1 日期
	 * @return 返回相减后的天数
	 */
	public static int getDiffDate(Calendar date, Calendar date1) {
		return getDiffDate(date.getTime(), date1.getTime());
	}

	/**
	 * 是否是闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, 2, 1);
		calendar.add(Calendar.DATE, -1);
		if (calendar.get(Calendar.DAY_OF_MONTH) == 29) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isToday(Date date){
	    Date now = new Date();
	    return getDiffDate(date, now) == 0 ? true : false;
	}
	
}
