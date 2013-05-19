package book.purchase.bl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.lang.SerializationUtils;

import prop.ViewProperties;

import book.purchase.BookPurchaseForm;
import book.purchase.bean.BookPurchaseTotal;
import book.purchase.dao.BookPurchaseTotalDAO;
import book.purchase.dto.BookPurchaseTotalDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 仕入集計表ビジネスロジック
 */
public class BookPurchaseTotalBL {

	private static int DECIMAL_DIGIT = Integer.parseInt(
			ViewProperties.getInstance().getValue(ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

	/**
	 * 仕入集計表リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookPurchaseTotal> getBookPurchaseTotalList(BookPurchaseForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookPurchaseTotalDAO dao = new BookPurchaseTotalDAO(TransactionInfo.getConnection());
		List<BookPurchaseTotal> bookPurchaseTotalList = new ArrayList<BookPurchaseTotal>();

		String selectDateRange = form.getSelectDateRange();

		String sql = null;

		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			sql = SQLProperties.getInstance().getValue(BookPurchaseTotalDAO.SQLID00101);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
			// 月次推移表

			sql = SQLProperties.getInstance().getValue(BookPurchaseTotalDAO.SQLID00102);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			sql = SQLProperties.getInstance().getValue(BookPurchaseTotalDAO.SQLID00101);

		} else {
			throw new IllegalArgumentException();
		}

		// TODO
		Integer from = Integer.parseInt("20120302");
		Integer to = Integer.parseInt("20130301");

		// TODO 締日、ORDER BYを可変に
		List<Object> params = new ArrayList<Object>();

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookPurchaseTotal bookPurchaseTotal = new BookPurchaseTotal();
			bookPurchaseTotal.setNendo(form.getNendo());
			bookPurchaseTotal.setFrom(from.toString());
			bookPurchaseTotal.setTo(to.toString());

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookPurchaseTotal, SerializationUtils.clone((BookPurchaseTotalDTO)dto));

			// TODO 消費税(セッションからとる)
			BigDecimal tax = (bookPurchaseTotal.getTaxExcludedAmount() == null) ? null :
				bookPurchaseTotal.getTaxExcludedAmount().multiply(
					BigDecimal.valueOf(0.05)).setScale(
							DECIMAL_DIGIT, RoundingMode.DOWN);

			bookPurchaseTotal.setTax(tax);
			bookPurchaseTotal.setTaxIncludedAmount(
					(bookPurchaseTotal.getTaxExcludedAmount() == null) ? null :
						bookPurchaseTotal.getTaxExcludedAmount().add(tax));

			bookPurchaseTotalList.add(bookPurchaseTotal);
		}

		return bookPurchaseTotalList;
	}


}
