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
import book.item.bean.BookGross;
import book.item.dao.BookGrossDAO;
import book.item.dto.BookGrossDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 粗利益ビジネスロジック
 */
public class BookGrossBL {

	/**
	 * 粗利益リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookGross> getBookGrossList(BookItemForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookGrossDAO dao = new BookGrossDAO(TransactionInfo.getConnection());
		List<BookGross> bookGrossList = new ArrayList<BookGross>();

		String selectDateRange = form.getSelectDateRange();

		StringBuilder sql = new StringBuilder();

		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			sql.append(SQLProperties.getInstance().getValue(BookGrossDAO.SQLID00101));

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
			// 月次推移表

			sql.append(SQLProperties.getInstance().getValue(BookGrossDAO.SQLID00102));

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			sql.append(SQLProperties.getInstance().getValue(BookGrossDAO.SQLID00101));

		} else {
			throw new IllegalArgumentException();
		}

		// 表示順
		sql.append(BookItemCommonBL.makeSortSql(form));

		// TODO
		Integer from = Integer.parseInt("20120302");
		Integer to = Integer.parseInt("20130301");

		// TODO 締日を可変に
		List<Object> params = new ArrayList<Object>();

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookGross bookGross = new BookGross();
			bookGross.setNendo(form.getNendo());
			bookGross.setFrom(from.toString());
			bookGross.setTo(to.toString());

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookGross, SerializationUtils.clone((BookGrossDTO)dto));

			bookGrossList.add(bookGross);
		}

		return bookGrossList;
	}


}
