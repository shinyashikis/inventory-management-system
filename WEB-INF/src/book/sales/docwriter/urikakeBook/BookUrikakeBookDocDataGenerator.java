package book.sales.docwriter.urikakeBook;

import java.util.List;

import book.sales.bean.BookUrikakeBook;

/**
 * 売掛台帳文書ファイルデータ作成クラス
 */
public class BookUrikakeBookDocDataGenerator {

	public static BookUrikakeBookDocData generateBookUrikakeBookDocData(
			List<BookUrikakeBook> bookUrikakeBookList) {
		BookUrikakeBookDocData docData = new BookUrikakeBookDocData();

		int index = 0;
		for (BookUrikakeBook bookUrikakeBook : bookUrikakeBookList) {

			docData.setNendo(bookUrikakeBook.getNendo());
			docData.setFrom(bookUrikakeBook.getFrom());
			docData.setTo(bookUrikakeBook.getTo());

			docData.setVoucherDate(index, bookUrikakeBook.getVoucherDate());
			docData.setVoucherNo(index, bookUrikakeBook.getVoucherNo());
			docData.setClassName(index, bookUrikakeBook.getClassName());
			docData.setKana(index,bookUrikakeBook.getKana());
			docData.setName(index, bookUrikakeBook.getName());

			docData.setTaxExcludedAmount(index, bookUrikakeBook.getTaxExcludedAmount());
			docData.setTax(index, bookUrikakeBook.getTax());
			docData.setTaxIncludedAmount(index, bookUrikakeBook.getTaxIncludedAmount());
			docData.setDiscount(index, bookUrikakeBook.getDiscount());
			docData.setSalesPrice(index, bookUrikakeBook.getSalesPrice());
			docData.setDealDivision(index, bookUrikakeBook.getDealDivision());
			docData.setStaff(index, bookUrikakeBook.getStaff());

			docData.setItemClassName(index, bookUrikakeBook.getItemClassName());
			docData.setItemKana(index, bookUrikakeBook.getItemKana());
			docData.setItemName(index, bookUrikakeBook.getItemName());
			docData.setItemCount(index, bookUrikakeBook.getItemCount());
			docData.setItemUnitPrice(index, bookUrikakeBook.getItemUnitPrice());

			index++;
		}

		return docData;
	}
}
