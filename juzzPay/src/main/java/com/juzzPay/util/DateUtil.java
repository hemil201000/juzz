package com.juzzPay.util;

//import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DateUtil {
private static Log logger = LogFactory.getLog(DateUtil.class);
	
	private static String SIMPLE_FORMAT =  "MMM dd, yyyy hh:mm:ss aaa";
	protected static String ISO_FORMAT =  "yyyy-MM-dd'T'HH:mm:ssXXX";
	public static String SIMPLE_DATETIME_FORMAT =  "yyyy-MM-dd HH:mm:ss";
	
	public static String getFormattedDateMMddyyyyHHmmss(Date date){
		return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
	}
	
	public static String getCurrentTimeStampString(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	public static String getFormattedDateYYYYmmddHHmmss(LocalDateTime localDateTime) {
//		return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(localDateTime);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		return localDateTime.format(formatter);
	}
	

	/*for example: Thu Jun 24 16:19:41 EDT 2010*/
	public static String getFormattedDateEMMMdHmszzyyyy(Date date){
		if(date == null){
			return null;
		}
		return new SimpleDateFormat("E MMM dd HH:mm:ss zz yyyy").format(date);
	}
	
	public static String getDateString(){
		return (new java.util.Date()).toString();
	}

//	/*return Yesterday*/
//	public static Date getYesterday() {
//        return getDateSOD(DateUtils.addDays(new Date(), -1));
//        }

	
	/*return start of the day 00:00:00*/
	public static Date getCurrentDateSOD(){
		Calendar calendar = new GregorianCalendar();
		return getDateSOD(calendar.getTime());
	}
	
	/*return end of the day 23:59:59*/
	public static Date getCurrentDateEOD(){
		Calendar calendar = new GregorianCalendar();
		return getDateEOD(calendar.getTime());
	}
	
	public static Date getCurrentDateEODWithMillis(){
		Calendar calendar = new GregorianCalendar();
		return getDateEODWithMillis(calendar.getTime());
	}
	
	public static Date getNextDateEOD(){
		Calendar calendar = new GregorianCalendar();
		return getNextDateEOD(calendar.getTime());
	}
	
	/*return start of the day 00:00:00*/
	public static Date getDateSOD(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getDateSODWithMillis(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/*return end of the day 23:59:59*/
	public static Date getDateEOD(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	public static Date getDateEODWithMillis(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getNextDateEOD(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/*return current year */
	public static int getCurrentYear(){
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR);
	}

//	public static boolean isSameDay(Date date1, Date date2) {
//		if (date1 == null && date2 == null) {
//			return true;
//		}
//		if (date1 == null || date2 == null) {
//			return false;
//		}
//		return DateUtils.isSameDay(date1, date2);
//	}

//	public static boolean isSameInstant(Date date1, Date date2) {
//		if (date1 == null && date2 == null) {
//			return true;
//		}
//		if (date1 == null || date2 == null) {
//			return false;
//		}
//		return DateUtils.isSameInstant(date1, date2);
//	}
	
	public static String simpleFormat(String dateStr) {
		DateFormat fromFormat = new SimpleDateFormat(ISO_FORMAT);
		DateFormat toFormat = new SimpleDateFormat(SIMPLE_DATETIME_FORMAT);
		Date d = null;
		try {
			d = fromFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("ParseException", e);
		}
		return toFormat.format(d);
	}
	
	public static String simpleFormat(Date date) {
		return new SimpleDateFormat(SIMPLE_FORMAT).format(date);
	}
	
	public static String simpleFormat(long time) {
		Date date = new Date(time);
		return new SimpleDateFormat(ISO_FORMAT).format(date);
	}

	public static Date simpleParse(String dateStr) {
		Date d = null;
		try {
			d = new SimpleDateFormat(ISO_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			logger.error("error while parsing string date " + d);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("error in simpleParse for string date " + d);
			e.printStackTrace();
		}
		return d;
	}
	
	public static Date simpleDateTimeParse(String dateStr) {
		Date d = null;
		try {
			d = new SimpleDateFormat(SIMPLE_DATETIME_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static String getFormattedCurrentYYYYMMDD(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	public static boolean isCurrent(Date startDate, Date endDate) {
		Date date = Calendar.getInstance().getTime();
		return date.after(startDate) && date.before(endDate);
	}
	
	public static long convertMinutesToMilliseconds(int min) {
		long milliseconds = TimeUnit.MINUTES.toMillis((long) min);
		return milliseconds;
	}
	
	public static long convertMillisecondsToMinutes(long millis) {
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		return minutes;
	}
	
	public static long convertMillisecondsToDays(long millis) {
		long days = TimeUnit.MILLISECONDS.toDays(millis);
		return days;
	}
	
	public static String getISOFormattedDateTimeInUTC(Date date){
		TimeZone tz = TimeZone.getTimeZone("UTC");
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    df.setTimeZone(tz);
	    String nowAsISO = df.format(date);
	    return nowAsISO.replace("Z", "+00:00");
	}
	
	public static Date getFormattedDate(String date) throws ParseException
	{
		DateFormat extended = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
	    DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
	    Date yourDate = simple.parse(date);
		String simpaleformat = extended.format(yourDate);
	    return new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(simpaleformat);
	}
	
	public static Date getFormattedDateForDbComparison(Date date) throws ParseException
	{		
		 DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		 Date formatedCreditDate = (Date)formatter.parse(date.toString());
		 
	     return formatedCreditDate;
	}
	
	public static String simpleFormatYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(cal.getTime());
		return formatted;
	
	}	
	
	public static String getFormattedMMDDYYYY(Date date){
		return new SimpleDateFormat("MM/dd/yyyy").format(date);
	}
	public static String getFormattedDate(Date d){
		return new SimpleDateFormat("yyyy-MM-dd").format(d);
	}
	
	public static String getFormattedDateForOldSystem(Date d){
		return new SimpleDateFormat("MM/dd/yyyy").format(d);
	}
	
	public static Date getInfiniteEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 100);
		return cal.getTime();
	}
	
	public static String convertDateToString(Date value, String formatString) {
		SimpleDateFormat fmt = null;
		if (value != null) {
			fmt = new SimpleDateFormat(formatString);
			return fmt.format(value);
		} else {
			return null;
		}
	}
	
	public static Date convertStringToDate(String value, String formatString) {
		SimpleDateFormat fmt = null;
		if (value != null) {
			fmt = new SimpleDateFormat(formatString);
			//fmt.setLenient(true);
			try {
				return fmt.parse(value);				
			} catch (Exception e) {
				throw new RuntimeException("Error parsing date.", e);
			}
		} else {
			return null;
		}
	}

	public static String getFormattedDateMMddyyyy(Date date) {
		return new SimpleDateFormat("MM-dd-yyyy").format(date);
	}

//	public static Date getYesterdayEOD() {
//		Calendar calendar = new GregorianCalendar();
//		calendar.setTime(getYesterday());
//		calendar.set(Calendar.HOUR_OF_DAY, 23);
//		calendar.set(Calendar.MINUTE, 59);
//		calendar.set(Calendar.SECOND, 59);
//		calendar.set(Calendar.MILLISECOND, 0); // for mysql
//		return calendar.getTime();
//	}
	
	public static String getFormattedDateYYYYmmddHHmmssIn24HrFormat(Date date) {
		return new SimpleDateFormat(SIMPLE_DATETIME_FORMAT).format(date);
	}

	public static String getFormattedDateYYYYmmddHHmmssIn24HrFormat(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern(SIMPLE_DATETIME_FORMAT));
	}
	
	public static String getFormattedDateHHmmss(Date date) {
		return new SimpleDateFormat("hh:mm:ss").format(date);
	}

	public static String formatTime(Date date) {
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}

	public static Date parse(String date, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(date);
	}

	public static Date parse(String dateString, String dateFormat, String... dateFormats) throws ParseException {
		try {
			return new SimpleDateFormat(dateFormat).parse(dateString);
		} catch (Exception e) {
		}
		for (String format : dateFormats) {
			try {
				return new SimpleDateFormat(format).parse(dateString);
			} catch (Exception e2) {
			}
		}
		throw new ParseException("None of these formats: [" + dateFormat + ", " + Arrays.toString(dateFormats).substring(1), -1);
	}
	
	public static String getISOFormattedDateTime(Date date) {
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
	    df.setTimeZone(TimeZone.getDefault());
	    return df.format(date);
	}
	
	public static Long getStringDateToLongDate(String date) {
		SimpleDateFormat f = new SimpleDateFormat(SIMPLE_DATETIME_FORMAT);
		try {
			return f.parse(date).getTime();
		} catch (Exception e) {
			throw new RuntimeException("Error while parsing date ", e);
		}
	}
	
	/** Adds days to a date.
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	/** Adds hours to a date.
	 * @param date
	 * @return
	 */
	public static Date addHours(Date date, int hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hours);
		return cal.getTime();
	}
	
	/** Adds minutes to a date.
	 * @param date
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}
	
	/** Adds seconds to a date.
	 * @param date
	 * @return
	 */
	public static Date addSeconds(Date date, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}
}
