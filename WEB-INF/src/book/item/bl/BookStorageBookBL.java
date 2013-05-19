package book.item.bl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.SerializationUtils;

import book.item.BookItemForm;
import book.item.bean.BookStorageBook;
import book.item.dao.BookStorageBookDAO;
import book.item.dto.BookStorageBookDTO;
import fw.common.db.SQLProperties;
import fw.common.db.dto.CommonDTO;
import fw.core.base.TransactionInfo;

/**
 * 入庫台帳ビジネスロジック
 */
public class BookStorageBookBL {

	/**
	 * 入庫台帳リスト取得処理
	 * @param form
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static List<BookStorageBook> getBookStorageBookList(BookItemForm form)
		throws IOException, SQLException, IllegalAccessException, InvocationTargetException {

		BookStorageBookDAO dao = new BookStorageBookDAO(TransactionInfo.getConnection());
		List<BookStorageBook> bookStorageBookList = new ArrayList<BookStorageBook>();

		StringBuilder sql = new StringBuilder();
		sql.append(SQLProperties.getInstance().getValue(BookStorageBookDAO.SQLID00101));

		// 表示順
		sql.append(BookItemCommonBL.makeSortSql(form));

		// TODO
		Integer from = Integer.parseInt("20120302");
		Integer to = Integer.parseInt("20130301");

		// TODO 締日、ORDER BYを可変に
		List<Object> params = new ArrayList<Object>();

		List<CommonDTO> retList = dao.select(sql.toString(), params);
		for (CommonDTO dto : retList) {
			BookStorageBook bookStorageBook = new BookStorageBook();
			bookStorageBook.setNendo(form.getNendo());
			bookStorageBook.setFrom(from.toString());
			bookStorageBook.setTo(to.toString());

			BeanUtils.copyProperties(bookStorageBook, SerializationUtils.clone((BookStorageBookDTO)dto));

			bookStorageBookList.add(bookStorageBook);
		}

		return bookStorageBookList;
	}


}
