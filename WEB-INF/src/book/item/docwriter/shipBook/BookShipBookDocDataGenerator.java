package book.item.docwriter.shipBook;

import java.util.List;

import book.item.bean.BookShipBook;

/**
 * 出庫台帳文書ファイルデータ作成クラス
 */
public class BookShipBookDocDataGenerator {

	public static BookShipBookDocData generateBookShipBookDocData(
			List<BookShipBook> bookShipBookList) {
		BookShipBookDocData docData = new BookShipBookDocData();

		int index = 0;
		for (BookShipBook bookShipBook : bookShipBookList) {

			docData.setNendo(bookShipBook.getNendo());
			docData.setFrom(bookShipBook.getFrom());
			docData.setTo(bookShipBook.getTo());

			docData.setVoucherDate(index, bookShipBook.getVoucherDate());
			docData.setVoucherNo(index, bookShipBook.getVoucherNo());
			docData.setClassName(index, bookShipBook.getClassName());
			docData.setKana(index,bookShipBook.getKana());
			docData.setName(index, bookShipBook.getName());
			docData.setCount(index, (bookShipBook.getCount() != null) ? bookShipBook.getCount().toString() : null);

			index++;
		}

		return docData;
	}
}
