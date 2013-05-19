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
import book.purchase.bean.BookKaikakeBook;
import book.purchase.dao.BookKaikakeBookDAO;
import book.purchase.dto.BookKaikakeBookDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 買掛台帳ビジネスロジック
 */
public class BookKaikakeBookBL {

	private static int DECIMAL_DIGIT = Integer.parseInt(
			ViewProperties.getInstance().getValue(ViewProperties.DECIMAL_DIGIT, ViewProperties.VALUE));

	/**
	 * 買掛台帳リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookKaikakeBook> getBookKaikakeBookList(BookPurchaseForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookKaikakeBookDAO dao = new BookKaikakeBookDAO(TransactionInfo.getConnection());
		List<BookKaikakeBook> bookKaikakeBookList = new ArrayList<BookKaikakeBook>();

		String dispTargetDetail = form.getDispTargetDetail();
		String selectDateRange = form.getSelectDateRange();

		String sql = null;
		if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_VOUCHER, ViewProperties.VALUE))) {
			// 伝票別

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				sql = SQLProperties.getInstance().getValue(BookKaikakeBookDAO.SQLID00101);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				sql = SQLProperties.getInstance().getValue(BookKaikakeBookDAO.SQLID00101);

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_DETAIL, ViewProperties.VALUE))) {
			// 詳細

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				sql = SQLProperties.getInstance().getValue(BookKaikakeBookDAO.SQLID00102);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				sql = SQLProperties.getInstance().getValue(BookKaikakeBookDAO.SQLID00102);

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
			BookKaikakeBookDTO bookKaikakeBookDTO = (BookKaikakeBookDTO)dto;

			BookKaikakeBook bookKaikakeBook = new BookKaikakeBook();

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookKaikakeBook, SerializationUtils.clone(bookKaikakeBookDTO));

			bookKaikakeBook.setNendo(form.getNendo());
			bookKaikakeBook.setFrom(from.toString());
			bookKaikakeBook.setTo(to.toString());

			// 買掛区分
			bookKaikakeBook.setDealDivision(
					(bookKaikakeBookDTO.getDealDivision() != null) ?
							CommonBL.convDealDivision(ViewProperties.DISP_VALUE, ViewProperties.VALUE,
									Integer.parseInt(bookKaikakeBook.getDealDivision())) : null);

			// 担当者
			bookKaikakeBook.setStaff(new StringBuilder().append(
					bookKaikakeBookDTO.getStaffSei()).append(
							bookKaikakeBookDTO.getStaffName()).toString());

			// 税抜金額
			BigDecimal sumTaxExcludedPrice = bookKaikakeBookDTO.getSumTaxExcludedPrice();
			bookKaikakeBook.setTaxExcludedAmount(
					(sumTaxExcludedPrice != null) ? sumTaxExcludedPrice :
						bookKaikakeBookDTO.getItemPurchasePrice().multiply(
								BigDecimal.valueOf(bookKaikakeBookDTO.getItemCount())));

			// TODO 消費税(セッションからとる)
			BigDecimal tax = (bookKaikakeBook.getTaxExcludedAmount().multiply(
					BigDecimal.valueOf(0.05)).setScale(DECIMAL_DIGIT, RoundingMode.DOWN));
			bookKaikakeBook.setTax(tax);

			// 税込金額(税抜金額 + 消費税額)
			BigDecimal taxIncludedAmount = bookKaikakeBook.getTaxExcludedAmount().add(tax);
			bookKaikakeBook.setTaxIncludedAmount(taxIncludedAmount);

			// 買掛金額(税込金額 + 調整額)
			bookKaikakeBook.setPurchasePrice(taxIncludedAmount.add(bookKaikakeBook.getDiscount()));

			bookKaikakeBookList.add(bookKaikakeBook);
		}

		return bookKaikakeBookList;
	}


}
