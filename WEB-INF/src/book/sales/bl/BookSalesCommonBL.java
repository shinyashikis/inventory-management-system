package book.sales.bl;

import java.io.IOException;

import fw.common.db.SQLProperties;
import fw.common.util.CommonConstants;

import prop.ViewProperties;
import book.common.BookBL;
import book.item.BookItemForm;
import book.item.dao.BookItemCommonDAO;

/**
 * 帳簿(売上)共通ビジネスロジック
 */
public class BookSalesCommonBL {

	/**
	 * ソートSQL作成
	 *
	 * @param form
	 * @return
	 * @throws IOException
	 */
	public static String makeSortSql(BookItemForm form) throws IOException {
		StringBuilder sql = new StringBuilder();
		sql.append(CommonConstants.HALF_SPACE);

		String selectDispSort = form.getSelectDispSort();

		ViewProperties viewProp = ViewProperties.getInstance();
		if (viewProp.getValue(ViewProperties.BOOK_SELECT_DISP_SORT_CUSTOMER, ViewProperties.VALUE).equals(selectDispSort)) {
			// 得意先

			sql.append((BookBL.isSortCode(form) ?
					SQLProperties.getInstance().getValue(BookItemCommonDAO.SQLID002) :
						SQLProperties.getInstance().getValue(BookItemCommonDAO.SQLID001)));

			sql.append((BookBL.isSortDesc(form)) ? " DESC" : "");

		} else if (viewProp.getValue(ViewProperties.BOOK_SELECT_DISP_SORT_CUSTOMER_CLASS, ViewProperties.VALUE).equals(selectDispSort)) {
			// 得意先分類

			sql.append((BookBL.isSortCode(form) ?
					SQLProperties.getInstance().getValue(BookItemCommonDAO.SQLID004) :
						SQLProperties.getInstance().getValue(BookItemCommonDAO.SQLID003)));

			sql.append((BookBL.isSortDesc(form)) ? " DESC" : "");

		} else if (viewProp.getValue(ViewProperties.BOOK_SELECT_DISP_SORT_STAFF, ViewProperties.VALUE).equals(selectDispSort)) {
			// 担当者

			sql.append(SQLProperties.getInstance().getValue(BookItemCommonDAO.SQLID005));
			sql.append((BookBL.isSortDesc(form)) ? " DESC" : "");

		} else {
			throw new IllegalArgumentException();
		}

		return sql.toString();
	}

}
