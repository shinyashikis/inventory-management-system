package book.item.docwriter.storageBook;

import java.util.List;

import book.item.bean.BookStorageBook;

/**
 * 入庫台帳文書ファイルデータ作成クラス
 */
public class BookStorageBookDocDataGenerator {

	public static BookStorageBookDocData generateBookStorageBookDocData(
			List<BookStorageBook> bookStorageBookList) {
		BookStorageBookDocData docData = new BookStorageBookDocData();

		int index = 0;
		for (BookStorageBook bookStorageBook : bookStorageBookList) {

			docData.setNendo(bookStorageBook.getNendo());
			docData.setFrom(bookStorageBook.getFrom());
			docData.setTo(bookStorageBook.getTo());

			docData.setVoucherDate(index, bookStorageBook.getVoucherDate());
			docData.setVoucherNo(index, bookStorageBook.getVoucherNo());
			docData.setClassName(index, bookStorageBook.getClassName());
			docData.setKana(index,bookStorageBook.getKana());
			docData.setName(index, bookStorageBook.getName());
			docData.setCount(index, (bookStorageBook.getCount() != null) ? bookStorageBook.getCount().toString() : null);

			index++;
		}

		return docData;
	}
}
