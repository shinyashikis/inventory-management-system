package book.item.docwriter.itemStock;

import java.util.List;

import book.item.bean.BookItemStock;

public class BookItemStockDocDataGenerator {

	public static BookItemStockDocData generateBookItemStockDocData(List<BookItemStock> bookItemStockList) {
		BookItemStockDocData docData = new BookItemStockDocData();
		docData.setBookItemStockList(bookItemStockList);
		return docData;
	}
}
