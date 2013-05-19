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
import book.sales.bean.BookUrikakeBook;
import book.sales.dao.BookUrikakeBookDAO;
import book.sales.dto.BookUrikakeBookDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 売掛台帳ビジネスロジック
 */
public class BookUrikakeBookBL {

	private static int DECIMAL_DIGIT = Integer.parseInt(
			ViewProperties.getInstance().getValue(ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

	/**
	 * 売掛台帳リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookUrikakeBook> getBookUrikakeBookList(BookSalesForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookUrikakeBookDAO dao = new BookUrikakeBookDAO(TransactionInfo.getConnection());
		List<BookUrikakeBook> bookUrikakeBookList = new ArrayList<BookUrikakeBook>();

		String dispTargetDetail = form.getDispTargetDetail();
		String selectDateRange = form.getSelectDateRange();

		String sql = null;
		if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_VOUCHER, ViewProperties.VALUE))) {
			// 伝票別

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				sql = SQLProperties.getInstance().getValue(BookUrikakeBookDAO.SQLID00101);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				sql = SQLProperties.getInstance().getValue(BookUrikakeBookDAO.SQLID00101);

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_DETAIL, ViewProperties.VALUE))) {
			// 詳細

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				sql = SQLProperties.getInstance().getValue(BookUrikakeBookDAO.SQLID00102);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				sql = SQLProperties.getInstance().getValue(BookUrikakeBookDAO.SQLID00102);

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
			BookUrikakeBookDTO bookUrikakeBookDTO = (BookUrikakeBookDTO)dto;

			BookUrikakeBook bookUrikakeBook = new BookUrikakeBook();

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookUrikakeBook, SerializationUtils.clone(bookUrikakeBookDTO));

			bookUrikakeBook.setNendo(form.getNendo());
			bookUrikakeBook.setFrom(from.toString());
			bookUrikakeBook.setTo(to.toString());

			// 売掛区分
			bookUrikakeBook.setDealDivision(
					(bookUrikakeBookDTO.getDealDivision() != null) ?
							CommonBL.convDealDivision(ViewProperties.DISP_VALUE, ViewProperties.VALUE,
									Integer.parseInt(bookUrikakeBook.getDealDivision())) : null);

			// 担当者
			bookUrikakeBook.setStaff(new StringBuilder().append(
					bookUrikakeBookDTO.getStaffSei()).append(
							bookUrikakeBookDTO.getStaffName()).toString());

			// 税抜金額
			BigDecimal sumTaxExcludedPrice = bookUrikakeBookDTO.getSumTaxExcludedPrice();
			bookUrikakeBook.setTaxExcludedAmount(
					(sumTaxExcludedPrice != null) ? sumTaxExcludedPrice :
						bookUrikakeBookDTO.getItemUnitPrice().multiply(
								BigDecimal.valueOf(bookUrikakeBookDTO.getItemCount())));

			// TODO 消費税(セッションからとる)
			BigDecimal tax = (bookUrikakeBook.getTaxExcludedAmount().multiply(
					BigDecimal.valueOf(0.05)).setScale(DECIMAL_DIGIT, RoundingMode.DOWN));
			bookUrikakeBook.setTax(tax);

			// 税込金額(税抜金額 + 消費税額)
			BigDecimal taxIncludedAmount = bookUrikakeBook.getTaxExcludedAmount().add(tax);
			bookUrikakeBook.setTaxIncludedAmount(taxIncludedAmount);

			// 売掛金額(税込金額 + 調整額)
			bookUrikakeBook.setSalesPrice(taxIncludedAmount.add(bookUrikakeBook.getDiscount()));

			bookUrikakeBookList.add(bookUrikakeBook);
		}

		return bookUrikakeBookList;
	}


}
