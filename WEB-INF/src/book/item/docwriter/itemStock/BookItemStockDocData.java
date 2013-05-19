package book.item.docwriter.itemStock;

import java.util.ArrayList;
import java.util.List;

import book.item.bean.BookItemStock;
import fw.common.docwriter.DocData;

public class BookItemStockDocData implements DocData {
	private static final long serialVersionUID = -1811861542804427523L;

	private List<BookItemStock> bookItemStockList = new ArrayList<BookItemStock>();

	public List<BookItemStock> getBookItemStockList() {
		return bookItemStockList;
	}

	public void setBookItemStockList(List<BookItemStock> bookItemStockList) {
		this.bookItemStockList = bookItemStockList;
	}

}
