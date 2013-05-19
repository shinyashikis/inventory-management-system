package fw.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

// TODO Calendar、SimpleDateFormatを毎回newはコストが高い
public class DateUtility {

	/**
	 * @param pattern
	 * @return
	 */
	public static String getSysDate(DateFormatPattern pattern) {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern.getPattern());
		return format.format(date);
	}

	/**
	 * @param pattern
	 * @return
	 */
	public static String getWarekiSysDate(DateFormatPattern pattern) {
		Locale.setDefault(new Locale("ja","JP","JP"));
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern.getPattern());
		return format.format(cal.getTime());
	}

	/**
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String getDate(DateFormatPattern pattern, long date) {
		Date d = new Date(date);
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern.getPattern());
		return format.format(d);
	}

//	/**
//	 * @param day
//	 * @param regex
//	 * @return
//	 * @throws IllegalArgumentException
//	 */
//	public static String getDayFiled(String day, String regex) throws IllegalArgumentException {
//		Pattern p = Pattern.compile(regex);
//		Matcher matcher = p.matcher(day);
//		if (matcher.find()) {
//			return matcher.group(1);
//		} else {
//			throw new IllegalArgumentException();
//		}
//	}
//
//	/**
//	 * @param cal
//	 * @param regex
//	 * @return
//	 * @throws IllegalArgumentException
//	 */
//	public static String getDayFiled(Calendar cal, String regex) throws IllegalArgumentException {
//		StringBuilder sb = new StringBuilder()
//			.append(cal.get(Calendar.YEAR))
//			.append(cal.get(Calendar.MONTH) + 1)
//			.append(cal.get(Calendar.DATE));
//		Pattern p = Pattern.compile(regex);
//		Matcher matcher = p.matcher(sb.toString());
//		if (matcher.find()) {
//			return matcher.group(1);
//		} else {
//			throw new IllegalArgumentException();
//		}
//	}

	/**
	 * @param day
	 * @return
	 */
	public static String getLastMonth(String day) {
		Calendar cal = Calendar.getInstance();
//		int month = Integer.valueOf(getDayFiled(day, DAY_REGEX_MONTH)) - 1;
		int month = Integer.valueOf(day.substring(4, 6));
		cal.set(Calendar.MONTH, month);
		return Integer.toString(cal.get(Calendar.MONTH));
	}

	/**
	 * @param date
	 * @return
	 */
	public static Calendar getLastMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar last = Calendar.getInstance();
		last.setTime(date);
		last.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		return last;
	}

	/**
	 * @param date
	 * @return
	 */
	public static boolean isEndOfDate(int date) {
		int finalDate = Calendar.getInstance().getActualMaximum(Calendar.DATE);
		return finalDate == date;
	}

	/**
	 * @param source
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date(String source,
			DateFormatPattern pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern.getPattern());
		return format.parse(source);
	}

	public static String date2String(Date source,
			DateFormatPattern pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern.getPattern());
		return format.format(source);
	}

	/**
	 * @param date
	 * @return
	 */
	public static int getEndOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DATE);
	}
}
