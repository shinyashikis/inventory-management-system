package book.item.bl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.lang.SerializationUtils;

import prop.ViewProperties;

import book.item.BookItemForm;
import book.item.bean.BookShipSum;
import book.item.dao.BookShipSumDAO;
import book.item.dto.BookShipSumDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 出庫集計表ビジネスロジック
 */
public class BookShipSumBL {

	/**
	 * 出庫集計表リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookShipSum> getBookShipSumList(BookItemForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookShipSumDAO dao = new BookShipSumDAO(TransactionInfo.getConnection());
		List<BookShipSum> bookShipSumList = new ArrayList<BookShipSum>();

		String dispTargetDetail = form.getDispTargetDetail();
		String selectDateRange = form.getSelectDateRange();

		StringBuilder sql = new StringBuilder();

		if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_COUNT, ViewProperties.VALUE))) {
			// 数量表示

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				sql.append(SQLProperties.getInstance().getValue(BookShipSumDAO.SQLID00101));

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
				// 月次推移表

				sql.append(SQLProperties.getInstance().getValue(BookShipSumDAO.SQLID00103));

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				sql.append(SQLProperties.getInstance().getValue(BookShipSumDAO.SQLID00101));

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_PRICE, ViewProperties.VALUE))) {
			// 金額表示

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				sql.append(SQLProperties.getInstance().getValue(BookShipSumDAO.SQLID00102));

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
				// 月次推移表

				sql.append(SQLProperties.getInstance().getValue(BookShipSumDAO.SQLID00104));

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				sql.append(SQLProperties.getInstance().getValue(BookShipSumDAO.SQLID00102));

			} else {
				throw new IllegalArgumentException();
			}

		} else {
			throw new IllegalArgumentException();
		}

		// 表示順
		sql.append(BookItemCommonBL.makeSortSql(form));

		// TODO
		Integer from = Integer.parseInt("20120302");
		Integer to = Integer.parseInt("20130301");

		// TODO 締日、ORDER BYを可変に
		List<Object> params = new ArrayList<Object>();

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookShipSum bookShipSum = new BookShipSum();
			bookShipSum.setNendo(form.getNendo());
			bookShipSum.setFrom(from.toString());
			bookShipSum.setTo(to.toString());

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookShipSum, SerializationUtils.clone((BookShipSumDTO)dto));

			bookShipSumList.add(bookShipSum);
		}

		return bookShipSumList;
	}


}
