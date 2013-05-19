package book.purchase.docwriter.kaikakeBook;

import java.util.List;

import book.purchase.bean.BookKaikakeBook;

/**
 * 買掛台帳文書ファイルデータ作成クラス
 */
public class BookKaikakeBookDocDataGenerator {

	public static BookKaikakeBookDocData generateBookKaikakeBookDocData(
			List<BookKaikakeBook> bookKaikakeBookList) {
		BookKaikakeBookDocData docData = new BookKaikakeBookDocData();

		int index = 0;
		for (BookKaikakeBook bookKaikakeBook : bookKaikakeBookList) {

			docData.setNendo(bookKaikakeBook.getNendo());
			docData.setFrom(bookKaikakeBook.getFrom());
			docData.setTo(bookKaikakeBook.getTo());

			docData.setVoucherDate(index, bookKaikakeBook.getVoucherDate());
			docData.setVoucherNo(index, bookKaikakeBook.getVoucherNo());
			docData.setClassName(index, bookKaikakeBook.getClassName());
			docData.setKana(index,bookKaikakeBook.getKana());
			docData.setName(index, bookKaikakeBook.getName());

			docData.setTaxExcludedAmount(index, bookKaikakeBook.getTaxExcludedAmount());
			docData.setTax(index, bookKaikakeBook.getTax());
			docData.setTaxIncludedAmount(index, bookKaikakeBook.getTaxIncludedAmount());
			docData.setDiscount(index, bookKaikakeBook.getDiscount());
			docData.setPurchasePrice(index, bookKaikakeBook.getPurchasePrice());
			docData.setDealDivision(index, bookKaikakeBook.getDealDivision());
			docData.setStaff(index, bookKaikakeBook.getStaff());

			docData.setItemClassName(index, bookKaikakeBook.getItemClassName());
			docData.setItemKana(index, bookKaikakeBook.getItemKana());
			docData.setItemName(index, bookKaikakeBook.getItemName());
			docData.setItemCount(index, bookKaikakeBook.getItemCount());
			docData.setItemUnitPrice(index, bookKaikakeBook.getItemPurchasePrice());

			index++;
		}

		return docData;
	}
}
