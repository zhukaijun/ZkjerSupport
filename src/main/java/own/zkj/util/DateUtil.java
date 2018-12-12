package own.zkj.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期格式化
 * 
 */
public final class DateUtil {
	/**
	 * yy-MM-dd
	 */
	public static final String yyMMdd = "yy-MM-dd";
	/**
	 * yyyy-MM-dd
	 */
	public static final String yyyyMMdd = "yyyy-MM-dd";
	/**
	 * HH:mm:ss
	 */
	public static final String HHmmss = "HH:mm:ss";
	/**
	 * HH:mm
	 */
	public static final String HHmm = "HH:mm";
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static final String yyyyMMddHHmmssSSS = "yyyy-MM-dd HH:mm:ss.SSS";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
	/**
	 * yy-MM-dd HH:mm:ss
	 */
	public static final String yyMMddHHmmss = "yy-MM-dd HH:mm:ss";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String yyyyMMddHHmmss_export = "yyyyMMddHHmmss";
	/**
	 * yyyyMMddHHmm
	 */
	public static final String yyyyMMddHHmm_version = "yyyyMMddHHmm";
	/**
	 * yyMMdd
	 */
	public static final String yyMMdd_version = "yyMMdd";

	/**
	 * yyyy/MM/dd HH:mm
	 */
	public static final String excelDate = "yyyy/MM/dd HH:mm";

	public static final int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
	/**
	 * yyyy
	 */
	public static final String yyyy = "yyyy";

	public static final String SolrFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String dollerFormat = "yyyy-MM-dd'$'HH:mm:ss";

	/**
	 * 
	 * @param s
	 * @param style
	 * @return
	 */
	public static Date parseToDate(String s, String style) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(style);
		Date date = null;
		if (s == null || s.length() < 8)
			return null;
		try {
			date = simpleDateFormat.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @param s
	 * @param style
	 * @return
	 */
	public static java.sql.Date parseToSQLDate(String s, String style) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(style);
		java.sql.Date date = null;
		if (s == null || s.length() < 8)
			return null;
		Date dd = DateUtil.parseToDate(s, style);
		date = new java.sql.Date(dd.getTime());
		return date;
	}

	/**
	 * 
	 * @param dat
	 * @param style
	 * @return
	 */
	public static java.sql.Date parseToSQLDate(java.util.Date dat, String style) {
		java.sql.Date date = null;
		if (dat == null || dat.toString().length() < 8)
			return null;
		date = new java.sql.Date(dat.getTime());
		return date;
	}

	public static String parseToString(String s, String DatePattern,
			String stringPattern) {
		Date date = parseToDate(s, DatePattern);
		return parseToString(date, stringPattern);
	}

	/**
	 * 
	 * @param s
	 * @param style
	 * @return
	 */
	public static String parseToString(String s, String style) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(style);
		Date date = null;
		String str = null;
		if (s == null || s.length() < 8)
			return null;
		try {
			date = simpleDateFormat.parse(s);
			str = simpleDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 
	 * @param date
	 * @param style
	 * @return
	 */
	public static String parseToString(Date date, String style) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(style);
		String str = null;
		if (date == null)
			return null;
		str = simpleDateFormat.format(date);
		return str;
	}

	/**
	 * 
	 * @param date
	 * @param style
	 * @return
	 */
	public static String parseSQLDateToString(java.sql.Date date, String style) {
		if (date == null)
			return null;
		java.util.Date d = new java.util.Date(date.getTime());
		return parseToString(d, style);
	}

	/**
	 * Getting java.sql.Date on this time.
	 * 
	 * @return
	 */
	public static java.sql.Date getJavaSqlDate() {
		java.sql.Date date = null;
		Date dd = new Date();
		date = new java.sql.Date(dd.getTime());
		return date;
	}

	/**
	 * 
	 * @param timestamp
	 * @param style
	 * @return
	 */
	public static String parseTimestampToString(java.sql.Timestamp timestamp,
			String style) {
		if (timestamp == null)
			return null;
		return DateUtil.parseToString(timestamp.toString(),
				DateUtil.yyyyMMddHHmmssSSS);
	}

	public static Timestamp parseToTimestamp(String s, String style) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(style);
		if (s == null || s.length() < 8)
			return null;
		Timestamp t = new Timestamp(parseToDate(s, style).getTime());
		return t;
	}

	/**
	 * @author zhukj
	 * @param style
	 * @return
	 */
	public static String getCurrentTime(String style) {
		return DateUtil.parseToString(new Date(),style);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static String getTimestampDate() {
		Timestamp ts = new Timestamp(new Date().getTime());
		return DateUtil.parseToString(ts.toString(), DateUtil.yyyyMMddHHmmssSSS);
	}

	public static Timestamp getLocalTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Calendar getWeekStartTimeC(Calendar cal) {
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		cal.add(Calendar.DATE, -day_of_week + 1);
		return cal;
	}

	public static Calendar getWeekEndTimeC(Calendar cal) {
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		cal.add(Calendar.DATE, -day_of_week + 7);
		return cal;
	}

	public static String getWeekStartTime(Calendar cal) {
		cal = getWeekStartTimeC(cal);
		String time = cal.get(Calendar.YEAR) + "-"
				+ (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH) + " 00:00:00";
		return time;
	}

	public static String getWeekEndTime(Calendar cal) {
		cal = getWeekEndTimeC(cal);
		String time = cal.get(Calendar.YEAR) + "-"
				+ (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH) + " 00:00:00";
		return time;
	}

	public static boolean isEffectiveDate(Date nowTime, Date startTime,
			Date endTime) {
		if (nowTime.getTime() == startTime.getTime()
				|| nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Date longToDate(long currentTime, String formatType)
 			throws ParseException {
 		Date dateOld = new Date(currentTime); 
 		String sDateTime = dateToString(dateOld, formatType); 
 		Date date = stringToDate(sDateTime, formatType); 
 		return date;
 	}
	
	public static String dateToString(Date data, String formatType) {
 		return new SimpleDateFormat(formatType).format(data);
 	}
	
	public static Date stringToDate(String strTime, String formatType)
 			throws ParseException {
 		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
 		Date date = null;
 		date = formatter.parse(strTime);
 		return date;
 	}
	public static String longToString(long currentTime, String formatType)
 			throws ParseException {
 		Date date = longToDate(currentTime, formatType);
 		String strTime = dateToString(date, formatType);
 		return strTime;
 	}

	public static Date getNextDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,1);
		date=calendar.getTime();
		return date;
	}
}
