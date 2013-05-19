package book.sales.docwriter.salesBook;

import java.util.List;

import book.sales.bean.BookSalesBook;

/**
 * 売上台帳文書ファイルデータ作成クラス
 */
public class BookSalesBookDocDataGenerator {

	public static BookSalesBookDocData generateBookSalesBookDocData(
			List<BookSalesBook> bookSalesBookList) {
		BookSalesBookDocData docData = new BookSalesBookDocData();

		int index = 0;
		for (BookSalesBook bookSalesBook : bookSalesBookList) {

			docData.setNendo(bookSalesBook.getNendo());
			docData.setFrom(bookSalesBook.getFrom());
			docData.setTo(bookSalesBook.getTo());

			docData.setVoucherDate(index, bookSalesBook.getVoucherDate());
			docData.setVoucherNo(index, bookSalesBook.getVoucherNo());
			docData.setClassName(index, bookSalesBook.getClassName());
			docData.setKana(index,bookSalesBook.getKana());
			docData.setName(index, bookSalesBook.getName());

			docData.setTaxExcludedAmount(index, bookSalesBook.getTaxExcludedAmount());
			docData.setTax(index, bookSalesBook.getTax());
			docData.setTaxIncludedAmount(index, bookSalesBook.getTaxIncludedAmount());
			docData.setDiscount(index, bookSalesBook.getDiscount());
			docData.setSalesPrice(index, bookSalesBook.getSalesPrice());
			docData.setDealDivision(index, bookSalesBook.getDealDivision());
			docData.setStaff(index, bookSalesBook.getStaff());
			docData.setGross(index, bookSalesBook.getGross());

			docData.setItemClassName(index, bookSalesBook.getItemClassName());
			docData.setItemKana(index, bookSalesBook.getItemKana());
			docData.setItemName(index, bookSalesBook.getItemName());
			docData.setItemCount(index, bookSalesBook.getItemCount());
			docData.setItemUnitPrice(index, bookSalesBook.getItemUnitPrice());
			docData.setItemPurchasePrice(index, bookSalesBook.getItemPurchasePrice());

			index++;
		}

		return docData;
	}
}
