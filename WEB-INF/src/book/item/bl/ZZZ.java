package book.item.bl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import prop.ViewProperties;
import book.item.BookItemForm;

public class ZZZ {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BookItemForm form = new BookItemForm();
		form.setShimebi("6");
		form.setNendo("2013");

		for (int i = 3; i <= 14; i++) {
			System.out.println(getFromTo(form, i));
		}
	}

	/**
	 * @param form
	 * @return
	 */
	private static List<Integer> getFromTo(BookItemForm form, int baseMonth) {
		ViewProperties viewProp = ViewProperties.getInstance();

		String shimebi = form.getShimebi();
		String nendo = calcNendo(baseMonth, form.getNendo());
		baseMonth = (baseMonth > 12) ? baseMonth - 12 : baseMonth;
		String baseMonthStr = attachZero(baseMonth);

		if (viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_1, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(1日)

			// baseMonthStr/2 ～ baseMonthStr/1
			return getFromTo(nendo, baseMonthStr, viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_1, ViewProperties.CALC_VALUE));

		} else if (viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_5, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(5日)

			// baseMonthStr/6 ～ baseMonthStr/5
			return getFromTo(nendo, baseMonthStr, viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_5, ViewProperties.CALC_VALUE));

		} else if (viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_10, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(10日)

			// baseMonthStr/11 ～ baseMonthStr/10
			return getFromTo(nendo, baseMonthStr, viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_10, ViewProperties.CALC_VALUE));

		} else if (viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_15, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(15日)

			// baseMonthStr/16 ～ baseMonthStr/15
			return getFromTo(nendo, baseMonthStr, viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_15, ViewProperties.CALC_VALUE));

		} else if (viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_20, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(20日)

			// baseMonthStr/21 ～ baseMonthStr/20
			return getFromTo(nendo, baseMonthStr, viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_20, ViewProperties.CALC_VALUE));

		} else if (viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_25, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(25日)

			// baseMonthStr/26 ～ baseMonthStr/25
			return getFromTo(nendo, baseMonthStr, viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_25, ViewProperties.CALC_VALUE));

		} else if (viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.VALUE).equals(shimebi)) {
			// 締日(末日)

			int nextMonth = baseMonth + 1;
			nendo = calcNendo(nextMonth, nendo);
			nextMonth =  (nextMonth > 12) ? 1 : nextMonth;
			String nextMonthStr = attachZero(nextMonth);

			return getFromTo(nendo, nextMonthStr, viewProp.getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.CALC_VALUE));

		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * @param nendo
	 * @param month
	 * @param shimebiCalcValue
	 * @return
	 */
	private static List<Integer> getFromTo(String nendo, String month, String day) {
		List<Integer> fromTo = new ArrayList<Integer>();

		int fromDay = 1;
		int toDay = 1;

		if (day.equals(
				ViewProperties.getInstance().getValue(ViewProperties.SHIMEBI_MONTHLY_END, ViewProperties.CALC_VALUE))) {
			fromDay = 1;

			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(nendo), Integer.parseInt(month) + 1, 0);
			toDay = cal.getActualMaximum(Calendar.DATE);

		} else {
			fromDay = Integer.parseInt(day) + 1;
			toDay = Integer.parseInt(day);
		}

		fromTo.add(
			Integer.parseInt(new StringBuilder().append(nendo)
				.append(month)
				.append(attachZero(fromDay)).toString()));

		int nextMonth = Integer.parseInt(month) + 1;
		fromTo.add(
			Integer.parseInt(new StringBuilder().append(calcNendo(nextMonth, nendo))
				.append(attachZero((nextMonth > 12) ? 1 : nextMonth))
				.append(attachZero(toDay)).toString()));

		return fromTo;
	}

	/**
	 * @param month
	 * @param nendo
	 * @return
	 */
	private static String calcNendo(int month, String nendo) {
		return (month > 12) ? Integer.toString(Integer.parseInt(nendo) + 1) : nendo;
	}

	/**
	 * @param month
	 * @return
	 */
	private static String attachZero(int i) {
		return (i < 10) ?
				new StringBuilder().append("0").append(i).toString() : Integer.toString(i);
	}

}
