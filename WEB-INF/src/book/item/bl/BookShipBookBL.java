package book.item.bl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.SerializationUtils;

import book.item.BookItemForm;
import book.item.bean.BookShipBook;
import book.item.dao.BookShipBookDAO;
import book.item.dto.BookShipBookDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 出庫台帳ビジネスロジック
 */
public class BookShipBookBL {

	/**
	 * 出庫台帳リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookShipBook> getBookShipBookList(BookItemForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookShipBookDAO dao = new BookShipBookDAO(TransactionInfo.getConnection());
		List<BookShipBook> bookShipBookList = new ArrayList<BookShipBook>();

		StringBuilder sql = new StringBuilder();
		sql.append(SQLProperties.getInstance().getValue(BookShipBookDAO.SQLID00101));

		// 表示順
		sql.append(BookItemCommonBL.makeSortSql(form));

		// TODO
		Integer from = Integer.parseInt("20120302");
		Integer to = Integer.parseInt("20130301");

		// TODO 締日、ORDER BYを可変に
		List<Object> params = new ArrayList<Object>();

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookShipBook bookShipBook = new BookShipBook();
			bookShipBook.setNendo(form.getNendo());
			bookShipBook.setFrom(from.toString());
			bookShipBook.setTo(to.toString());

			BeanUtils.copyProperties(bookShipBook, SerializationUtils.clone((BookShipBookDTO)dto));

			bookShipBookList.add(bookShipBook);
		}

		return bookShipBookList;
	}


}
