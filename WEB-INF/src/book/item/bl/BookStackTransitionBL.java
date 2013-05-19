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
import book.item.bean.BookStockTransition;
import book.item.dao.BookStockTransitionDAO;
import book.item.dto.BookStockTransitionDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 在庫推移表ビジネスロジック
 */
public class BookStackTransitionBL {

	/**
	 * 在庫推移表リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookStockTransition> getBookStockTransitionList(BookItemForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookStockTransitionDAO dao = new BookStockTransitionDAO(TransactionInfo.getConnection());
		List<BookStockTransition> bookStockTransitionList = new ArrayList<BookStockTransition>();

		String dispTargetDetail = form.getDispTargetDetail();

		StringBuilder sql = new StringBuilder();
		if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_COUNT, ViewProperties.VALUE))) {
			sql.append(SQLProperties.getInstance().getValue(BookStockTransitionDAO.SQLID00101));
		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_PRICE, ViewProperties.VALUE))) {
			sql.append(SQLProperties.getInstance().getValue(BookStockTransitionDAO.SQLID00102));
		} else {
			throw new IllegalArgumentException();
		}

		List<Object> params = new ArrayList<Object>();

//		// TODO 年度によって取得月が替わってくる
//		// ⇒ 期首月が4月の場合、取得月は2012年度の4月度から2012年度の3月度
//		for (int i = 3; i <= 14; i++) {
//			List<Integer> fromTo = BookItemCommonBL.getFromTo(form, i);
//			params.add(fromTo.get(0));
//			params.add(fromTo.get(1));
//			params.add(fromTo.get(0));
//			params.add(fromTo.get(1));
//		}
//
//		Integer from = BookItemCommonBL.getFromTo(form, 3).get(0);
//		Integer to = Integer.parseInt(new StringBuilder().append(
//				DateUtility.getSysDate(DateFormatPattern.PATTERN_YYYYMD)).toString());
		Integer from = Integer.parseInt("20130101");
		Integer to = Integer.parseInt("20131231");

		// 表示順
		sql.append(BookItemCommonBL.makeSortSql(form));

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookStockTransition bookStockTransition = new BookStockTransition();
			// TODO 期首日も元に年度を求める(期首月が4月なら年度開始は4月から)
			bookStockTransition.setNendo(form.getNendo());
			bookStockTransition.setFrom(from.toString());
			bookStockTransition.setTo(to.toString());

			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(bookStockTransition, SerializationUtils.clone((BookStockTransitionDTO)dto));

			bookStockTransitionList.add(bookStockTransition);
		}

		return bookStockTransitionList;
	}

}
