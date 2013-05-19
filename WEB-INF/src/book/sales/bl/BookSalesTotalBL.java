package book.sales.bl;

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

import book.sales.BookSalesForm;
import book.sales.bean.BookSalesTotal;
import book.sales.dao.BookSalesTotalDAO;
import book.sales.dto.BookSalesTotalDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 売上集計表ビジネスロジック
 */
public class BookSalesTotalBL {

	private static int DECIMAL_DIGIT = Integer.parseInt(
			ViewProperties.getInstance().getValue(ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

	/**
	 * 売上集計表リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookSalesTotal> getBookSalesTotalList(BookSalesForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookSalesTotalDAO dao = new BookSalesTotalDAO(TransactionInfo.getConnection());
		List<BookSalesTotal> bookSalesTotalList = new ArrayList<BookSalesTotal>();

		String selectDateRange = form.getSelectDateRange();

		String sql = null;

		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			sql = SQLProperties.getInstance().getValue(BookSalesTotalDAO.SQLID00101);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
			// 月次推移表

			sql = SQLProperties.getInstance().getValue(BookSalesTotalDAO.SQLID00102);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			sql = SQLProperties.getInstance().getValue(BookSalesTotalDAO.SQLID00101);

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
			BookSalesTotal bookSalesTotal = new BookSalesTotal();
			bookSalesTotal.setNendo(form.getNendo());
			bookSalesTotal.setFrom(from.toString());
			bookSalesTotal.setTo(to.toString());

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookSalesTotal, SerializationUtils.clone((BookSalesTotalDTO)dto));

			// TODO 消費税(セッションからとる)
			BigDecimal tax = (bookSalesTotal.getTaxExcludedAmount() == null) ? null :
				bookSalesTotal.getTaxExcludedAmount().multiply(
					BigDecimal.valueOf(0.05)).setScale(
							DECIMAL_DIGIT, RoundingMode.DOWN);

			bookSalesTotal.setTax(tax);
			bookSalesTotal.setTaxIncludedAmount(
					(bookSalesTotal.getTaxExcludedAmount() == null) ? null :
						bookSalesTotal.getTaxExcludedAmount().add(tax));

			bookSalesTotalList.add(bookSalesTotal);
		}

		return bookSalesTotalList;
	}


}
