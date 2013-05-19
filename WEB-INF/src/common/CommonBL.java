package common;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import prop.ViewProperties;

import fw.common.date.DateFormatPattern;
import fw.common.date.DateUtility;
import fw.common.util.CommonConstants;

public class CommonBL {

	public static final int SHIMEBI = 0;
	public static final int KESSAI = 1;

	/**
	 * 締日計算
	 *
	 * @param targetDate
	 * @param shimebiMonthly
	 * @param shimebiKessai
	 * @return
	 * @throws ParseException
	 */
	public static String calcShimebi(String targetDate, String shimebiMonthly, String shimebiKessai) throws ParseException {

		if (targetDate == null || "".equals(targetDate)) {
			return "";
		}

		Date date = DateUtility.string2Date(targetDate, DateFormatPattern.PATTERN_YYYYMMDD);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 締日の日計算
		calcShimebiDate(cal, shimebiMonthly);

		return DateUtility.date2String(cal.getTime(), DateFormatPattern.PATTERN_YYYYMMDD);
	}

	/**
	 * 決済日計算
	 *
	 * @param targetDate
	 * @param kessaiMonthly
	 * @param shimebiKessai
	 * @return
	 * @throws ParseException
	 */
	public static String calcKessai(String targetDate, String kessaiMonthly, String shimebiKessai) throws ParseException {

		if (targetDate == null || "".equals(targetDate)) {
			return "";
		}

		Date date = DateUtility.string2Date(targetDate, DateFormatPattern.PATTERN_YYYYMMDD);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 決済日の月計算
		calcKessaiMonth(cal, shimebiKessai);
		// 決済日の日計算
		calcKessaiDate(cal, kessaiMonthly);

		return DateUtility.date2String(cal.getTime(), DateFormatPattern.PATTERN_YYYYMMDD);
	}

	/**
	 * 締日(日)計算
	 *
	 * @param cal
	 * @param shimebiMonthly
	 */
	private static void calcShimebiDate(Calendar cal, String shimebiMonthly) {
		ViewProperties prop = ViewProperties.getInstance();
		if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_1, ViewProperties.VALUE).equals(shimebiMonthly)) {
			// 締日(毎月) 1日

			cal.set(Calendar.DATE, 1);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_5, ViewProperties.VALUE).equals(shimebiMonthly)) {
			// 締日(毎月) 5日

			cal.set(Calendar.DATE, 5);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_10, ViewProperties.VALUE).equals(shimebiMonthly)) {
			// 締日(毎月) 10日

			cal.set(Calendar.DATE, 10);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_15, ViewProperties.VALUE).equals(shimebiMonthly)) {
			// 締日(毎月) 15日

			cal.set(Calendar.DATE, 15);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_20, ViewProperties.VALUE).equals(shimebiMonthly)) {
			// 締日(毎月) 20日

			cal.set(Calendar.DATE, 20);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_25, ViewProperties.VALUE).equals(shimebiMonthly)) {
			// 締日(毎月) 25日

			cal.set(Calendar.DATE, 25);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.VALUE).equals(shimebiMonthly)) {
			// 締日(毎月) 末日

			cal.set(Calendar.DATE, DateUtility.getEndOfDate(cal.getTime()));

		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 決済日(月)計算
	 *
	 * @param cal
	 * @param shimebiKessai
	 */
	private static void calcKessaiMonth(Calendar cal, String shimebiKessai) {
		ViewProperties prop = ViewProperties.getInstance();

		if (prop.getValue(ViewProperties.SHIMEBI_KESSAI_THIS, ViewProperties.VALUE).equals(shimebiKessai)) {
			// 締日・決済日月数 当月

		} else if (prop.getValue(ViewProperties.SHIMEBI_KESSAI_NEXT, ViewProperties.VALUE).equals(shimebiKessai)) {
			// 締日・決済日月数 翌月

			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);

		} else if (prop.getValue(ViewProperties.SHIMEBI_KESSAI_TWO, ViewProperties.VALUE).equals(shimebiKessai)) {
			// 締日・決済日月数 2月後

			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 2);

		} else if (prop.getValue(ViewProperties.SHIMEBI_KESSAI_THREE, ViewProperties.VALUE).equals(shimebiKessai)) {
			// 締日・決済日月数 3月後

			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 3);

		} else if (prop.getValue(ViewProperties.SHIMEBI_KESSAI_FOUR, ViewProperties.VALUE).equals(shimebiKessai)) {
			// 締日・決済日月数 4月後

			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 4);

		} else if (prop.getValue(ViewProperties.SHIMEBI_KESSAI_FIVE, ViewProperties.VALUE).equals(shimebiKessai)) {
			// 締日・決済日月数 5月後

			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 5);

		} else if (prop.getValue(ViewProperties.SHIMEBI_KESSAI_SIX, ViewProperties.VALUE).equals(shimebiKessai)) {
			// 締日・決済日月数 6月後

			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 6);

		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * 決済日(日)計算
	 *
	 * @param cal
	 * @param kessaiMonthly
	 */
	private static void calcKessaiDate(Calendar cal, String kessaiMonthly) {
		ViewProperties prop = ViewProperties.getInstance();
		if (prop.getValue(ViewProperties.KESSAI_MONTHLY_1, ViewProperties.VALUE).equals(kessaiMonthly)) {
			// 決済(毎月) 1日

			cal.set(Calendar.DATE, 1);

		} else if (prop.getValue(ViewProperties.KESSAI_MONTHLY_5, ViewProperties.VALUE).equals(kessaiMonthly)) {
			// 決済(毎月) 5日

			cal.set(Calendar.DATE, 5);

		} else if (prop.getValue(ViewProperties.KESSAI_MONTHLY_10, ViewProperties.VALUE).equals(kessaiMonthly)) {
			// 決済(毎月) 10日

			cal.set(Calendar.DATE, 10);

		} else if (prop.getValue(ViewProperties.KESSAI_MONTHLY_15, ViewProperties.VALUE).equals(kessaiMonthly)) {
			// 決済(毎月) 15日

			cal.set(Calendar.DATE, 15);

		} else if (prop.getValue(ViewProperties.KESSAI_MONTHLY_20, ViewProperties.VALUE).equals(kessaiMonthly)) {
			// 決済(毎月) 20日

			cal.set(Calendar.DATE, 20);

		} else if (prop.getValue(ViewProperties.KESSAI_MONTHLY_25, ViewProperties.VALUE).equals(kessaiMonthly)) {
			// 決済(毎月) 25日

			cal.set(Calendar.DATE, 25);

		} else if (prop.getValue(ViewProperties.KESSAI_MONTHLY_END, ViewProperties.VALUE).equals(kessaiMonthly)) {
			// 決済(毎月) 末日

			cal.set(Calendar.DATE, DateUtility.getEndOfDate(cal.getTime()));

		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @param tax
	 * @param calc
	 * @param calcHasu
	 * @return
	 */
	public static String makeCalcCalcHasu(Integer tax, Integer calc, Integer calcHasu) {
		if (calc == null || calcHasu == null) {
			return null;
		}

		StringBuilder calcCalcHasu = new StringBuilder();
		ViewProperties p = ViewProperties.getInstance();

		int hikazei = Integer.valueOf(p.getValue(ViewProperties.TAX_SETTING_HIKAZEI, ViewProperties.VALUE));
		int kazei = Integer.valueOf(p.getValue(ViewProperties.TAX_SETTING_KAZEI, ViewProperties.VALUE));

		if (tax == hikazei) {
			// 税設定「非課税」
			return p.getValue(ViewProperties.TAX_SETTING_HIKAZEI, ViewProperties.DISP_VALUE);

		} else if (tax == kazei) {
			// 税設定「課税」

			int uchizei = Integer.valueOf(p.getValue(ViewProperties.CALC_UCHIZEI, ViewProperties.VALUE));
			int sotozei = Integer.valueOf(p.getValue(ViewProperties.CALC_SOTOZEI, ViewProperties.VALUE));

			if (calc == uchizei) {
				// 計算方法「内税」
				calcCalcHasu.append(p.getValue(ViewProperties.CALC_UCHIZEI, ViewProperties.DISP_VALUE));

			} else if (calc == sotozei) {
				// 計算方法「外税」
				calcCalcHasu.append(p.getValue(ViewProperties.CALC_SOTOZEI, ViewProperties.DISP_VALUE));
			}

			calcCalcHasu.append(CommonConstants.HALF_SPACE);

			int shisyaGonyu = Integer.valueOf(p.getValue(ViewProperties.CALC_HASU_SHISYAGONYU, ViewProperties.VALUE));
			int kiriage = Integer.valueOf(p.getValue(ViewProperties.CALC_HASU_KIRIAGE, ViewProperties.VALUE));
			int kirisute = Integer.valueOf(p.getValue(ViewProperties.CALC_HASU_KIRISUTE, ViewProperties.VALUE));

			if (calcHasu == shisyaGonyu) {
				// 端数計算「四捨五入」
				calcCalcHasu.append(p.getValue(ViewProperties.CALC_HASU_SHISYAGONYU, ViewProperties.DISP_VALUE));
			} else if (calcHasu == kiriage) {
				// 端数計算「切上げ」
				calcCalcHasu.append(p.getValue(ViewProperties.CALC_HASU_KIRIAGE, ViewProperties.DISP_VALUE));
			} else if (calcHasu == kirisute) {
				// 端数計算「切捨て」
				calcCalcHasu.append(p.getValue(ViewProperties.CALC_HASU_KIRISUTE, ViewProperties.DISP_VALUE));
			} else {
				throw new IllegalArgumentException();
			}

		} else {
			throw new IllegalArgumentException();
		}

		return calcCalcHasu.toString();

	}

	/**
	 * 「敬称」変換処理
	 * @param targetName
	 * @param fromValue
	 * @param fromName
	 * @return
	 */
	public static String convKeisyo(String targetName, String fromName, int fromValue) {
		List<String> keys = new ArrayList<String>();
		keys.add(ViewProperties.KEISYO_ONCYU);
		keys.add(ViewProperties.KEISYO_SAMA);
		keys.add(ViewProperties.KEISYO_TONO);
		return ViewProperties.getInstance().getValue(
				keys, ViewProperties.DISP_VALUE, fromName, Integer.toString(fromValue));
	}

	/**
	 * 「取引区分」変換処理
	 * @param targetName
	 * @param fromValue
	 * @param fromName
	 * @return
	 */
	public static String convDealDivision(String targetName, String fromName, int fromValue) {
		List<String> keys = new ArrayList<String>();
		keys.add(ViewProperties.DEAL_DIVISION_CASH);
		keys.add(ViewProperties.DEAL_DIVISION_KAKE);
		return ViewProperties.getInstance().getValue(
				keys, ViewProperties.DISP_VALUE, fromName, Integer.toString(fromValue));
	}

	/**
	 * 名前作成
	 * @param sei
	 * @param name
	 * @param isSpace
	 * @return
	 */
	public static String makeName(String sei, String name, boolean isSpace) {
		String strSei = (sei == null) ? "" : sei;
		String strName = (name == null) ? "" : name;
		String space = (isSpace) ? CommonConstants.HALF_SPACE : "";
		return new StringBuilder(strSei).append(space).append(strName).toString();
	}

	/**
	 * 売上(or仕入)対象月度取得
	 * @param shimebi 締日
	 * @param date 売上(or仕入)日
	 * @return 売上(or仕入)対象月度
	 */
	public static String getTargetSalesPurchaseMonth(String shimebi, String date) {
		String targetMonth = null;

		if (date == null) {
			return null;
		}

		Date voucherDate;
		try {
			voucherDate = DateUtility.string2Date(date, DateFormatPattern.PATTERN_YYYYMMDD);
		} catch (ParseException e) {
			throw new RuntimeException();
		}

		Calendar from = DateUtility.getLastMonth(voucherDate);
		Calendar to = Calendar.getInstance();
		to.setTime(voucherDate);

		ViewProperties prop = ViewProperties.getInstance();
		if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_1, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(1日)
			// MM/02～MM/01
			from.set(Calendar.DATE, 2);
			to.set(Calendar.DATE, 1);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_5, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(5日)
			// MM/06～MM/05
			from.set(Calendar.DATE, 6);
			to.set(Calendar.DATE, 5);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_10, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(10日)
			// MM/11～MM/10
			from.set(Calendar.DATE, 11);
			to.set(Calendar.DATE, 10);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_15, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(15日)
			// MM/16～MM/15
			from.set(Calendar.DATE, 16);
			to.set(Calendar.DATE, 15);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_20, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(20日)
			// MM/21～MM/20
			from.set(Calendar.DATE, 21);
			to.set(Calendar.DATE, 20);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_25, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(25日)
			// MM/26～MM/25
			from.set(Calendar.DATE, 26);
			to.set(Calendar.DATE, 25);

		} else if (prop.getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(末日)
			// MM/01～MM/末日
			from.setTime(voucherDate);
			from.set(Calendar.DATE, 1);
			to.set(Calendar.DATE, to.getActualMaximum(Calendar.DATE));

		} else {
			throw new IllegalArgumentException();
		}

		// 売上(仕入)前月/?日 <= 売上(仕入)日 <= 売上(仕入)当月?日
		if (from.getTime().compareTo(voucherDate) <= 0 && to.getTime().compareTo(voucherDate) >= 0) {
			// 当月度
			targetMonth = Integer.toString(to.get(Calendar.MONTH) + 1);
		} else {
			// 次月度
			targetMonth = Integer.toString(to.get(Calendar.MONTH) + 2);
		}

		return targetMonth;
	}
}
