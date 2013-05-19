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

import common.CommonBL;

import prop.ViewProperties;

import book.purchase.BookPurchaseForm;
import book.purchase.bean.BookPurchaseBook;
import book.purchase.dao.BookPurchaseBookDAO;
import book.purchase.dto.BookPurchaseBookDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 仕入台帳ビジネスロジック
 */
public class BookPurchaseBookBL {

	private static int DECIMAL_DIGIT = Integer.parseInt(
			ViewProperties.getInstance().getValue(ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

	/**
	 * 仕入台帳リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookPurchaseBook> getBookPurchaseBookList(BookPurchaseForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookPurchaseBookDAO dao = new BookPurchaseBookDAO(TransactionInfo.getConnection());
		List<BookPurchaseBook> bookPurchaseBookList = new ArrayList<BookPurchaseBook>();

		String dispTargetDetail = form.getDispTargetDetail();
		String selectDateRange = form.getSelectDateRange();

		String sql = null;
		if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_VOUCHER, ViewProperties.VALUE))) {
			// 伝票別

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				sql = SQLProperties.getInstance().getValue(BookPurchaseBookDAO.SQLID00101);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				sql = SQLProperties.getInstance().getValue(BookPurchaseBookDAO.SQLID00101);

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_DETAIL, ViewProperties.VALUE))) {
			// 詳細

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				sql = SQLProperties.getInstance().getValue(BookPurchaseBookDAO.SQLID00102);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				sql = SQLProperties.getInstance().getValue(BookPurchaseBookDAO.SQLID00102);

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
			BookPurchaseBookDTO bookPurchaseBookDTO = (BookPurchaseBookDTO)dto;

			BookPurchaseBook bookPurchaseBook = new BookPurchaseBook();

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookPurchaseBook, SerializationUtils.clone(bookPurchaseBookDTO));

			bookPurchaseBook.setNendo(form.getNendo());
			bookPurchaseBook.setFrom(from.toString());
			bookPurchaseBook.setTo(to.toString());

			// 仕入区分
			bookPurchaseBook.setDealDivision(
					(bookPurchaseBookDTO.getDealDivision() != null) ?
							CommonBL.convDealDivision(ViewProperties.DISP_VALUE, ViewProperties.VALUE,
									Integer.parseInt(bookPurchaseBook.getDealDivision())) : null);

			// 担当者
			bookPurchaseBook.setStaff(new StringBuilder().append(
					bookPurchaseBookDTO.getStaffSei()).append(
							bookPurchaseBookDTO.getStaffName()).toString());

			// 税抜金額
			BigDecimal sumTaxExcludedPrice = bookPurchaseBookDTO.getSumTaxExcludedPrice();
			bookPurchaseBook.setTaxExcludedAmount(
					(sumTaxExcludedPrice != null) ? sumTaxExcludedPrice :
						bookPurchaseBookDTO.getItemPurchasePrice().multiply(
								BigDecimal.valueOf(bookPurchaseBookDTO.getItemCount())));

			// TODO 消費税(セッションからとる)
			BigDecimal tax = (bookPurchaseBook.getTaxExcludedAmount().multiply(
					BigDecimal.valueOf(0.05)).setScale(DECIMAL_DIGIT, RoundingMode.DOWN));
			bookPurchaseBook.setTax(tax);

			// 税込金額(税抜金額 + 消費税額)
			BigDecimal taxIncludedAmount = bookPurchaseBook.getTaxExcludedAmount().add(tax);
			bookPurchaseBook.setTaxIncludedAmount(taxIncludedAmount);

			// 仕入金額(税込金額 + 調整額)
			bookPurchaseBook.setPurchasePrice(taxIncludedAmount.add(bookPurchaseBook.getDiscount()));

			bookPurchaseBookList.add(bookPurchaseBook);
		}

		return bookPurchaseBookList;
	}


}
