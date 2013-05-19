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

import common.CommonBL;

import prop.ViewProperties;

import book.sales.BookSalesForm;
import book.sales.bean.BookSalesBook;
import book.sales.dao.BookSalesBookDAO;
import book.sales.dto.BookSalesBookDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 売上台帳ビジネスロジック
 */
public class BookSalesBookBL {

	private static int DECIMAL_DIGIT = Integer.parseInt(
			ViewProperties.getInstance().getValue(ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

	/**
	 * 売上台帳リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookSalesBook> getBookSalesBookList(BookSalesForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookSalesBookDAO dao = new BookSalesBookDAO(TransactionInfo.getConnection());
		List<BookSalesBook> bookSalesBookList = new ArrayList<BookSalesBook>();

		String dispTargetDetail = form.getDispTargetDetail();
		String selectDateRange = form.getSelectDateRange();

		String sql = null;
		if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_VOUCHER, ViewProperties.VALUE))) {
			// 伝票別

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				sql = SQLProperties.getInstance().getValue(BookSalesBookDAO.SQLID00101);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				sql = SQLProperties.getInstance().getValue(BookSalesBookDAO.SQLID00101);

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_DETAIL, ViewProperties.VALUE))) {
			// 詳細

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				sql = SQLProperties.getInstance().getValue(BookSalesBookDAO.SQLID00102);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				sql = SQLProperties.getInstance().getValue(BookSalesBookDAO.SQLID00102);

			} else {
				throw new IllegalArgumentException();
			}

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
			BookSalesBookDTO bookSalesBookDTO = (BookSalesBookDTO)dto;

			BookSalesBook bookSalesBook = new BookSalesBook();

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookSalesBook, SerializationUtils.clone(bookSalesBookDTO));

			bookSalesBook.setNendo(form.getNendo());
			bookSalesBook.setFrom(from.toString());
			bookSalesBook.setTo(to.toString());

			// 売上区分
			bookSalesBook.setDealDivision(
					(bookSalesBookDTO.getDealDivision() != null) ?
							CommonBL.convDealDivision(ViewProperties.DISP_VALUE, ViewProperties.VALUE,
									Integer.parseInt(bookSalesBook.getDealDivision())) : null);

			// 担当者
			bookSalesBook.setStaff(new StringBuilder().append(
					bookSalesBookDTO.getStaffSei()).append(
							bookSalesBookDTO.getStaffName()).toString());

			// 税抜金額
			BigDecimal sumTaxExcludedPrice = bookSalesBookDTO.getSumTaxExcludedPrice();
			bookSalesBook.setTaxExcludedAmount(
					(sumTaxExcludedPrice != null) ? sumTaxExcludedPrice :
						bookSalesBookDTO.getItemUnitPrice().multiply(
								BigDecimal.valueOf(bookSalesBookDTO.getItemCount())));

			// TODO 消費税(セッションからとる)
			BigDecimal tax = (bookSalesBook.getTaxExcludedAmount().multiply(
					BigDecimal.valueOf(0.05)).setScale(DECIMAL_DIGIT, RoundingMode.DOWN));
			bookSalesBook.setTax(tax);

			// 税込金額(税抜金額 + 消費税額)
			BigDecimal taxIncludedAmount = bookSalesBook.getTaxExcludedAmount().add(tax);
			bookSalesBook.setTaxIncludedAmount(taxIncludedAmount);

			// 売上金額(税込金額 + 調整額)
			bookSalesBook.setSalesPrice(taxIncludedAmount.add(bookSalesBook.getDiscount()));

			bookSalesBookList.add(bookSalesBook);
		}

		return bookSalesBookList;
	}


}
