package book.purchase.docwriter.purchaseBook;

import java.util.List;

import book.purchase.bean.BookPurchaseBook;

/**
 * KAIKAKE台帳文書ファイルデータ作成クラス
 */
public class BookPurchaseBookDocDataGenerator {

	public static BookPurchaseBookDocData generateBookPurchaseBookDocData(
			List<BookPurchaseBook> bookPurchaseBookList) {
		BookPurchaseBookDocData docData = new BookPurchaseBookDocData();

		int index = 0;
		for (BookPurchaseBook bookPurchaseBook : bookPurchaseBookList) {

			docData.setNendo(bookPurchaseBook.getNendo());
			docData.setFrom(bookPurchaseBook.getFrom());
			docData.setTo(bookPurchaseBook.getTo());

			docData.setVoucherDate(index, bookPurchaseBook.getVoucherDate());
			docData.setVoucherNo(index, bookPurchaseBook.getVoucherNo());
			docData.setClassName(index, bookPurchaseBook.getClassName());
			docData.setKana(index,bookPurchaseBook.getKana());
			docData.setName(index, bookPurchaseBook.getName());

			docData.setTaxExcludedAmount(index, bookPurchaseBook.getTaxExcludedAmount());
			docData.setTax(index, bookPurchaseBook.getTax());
			docData.setTaxIncludedAmount(index, bookPurchaseBook.getTaxIncludedAmount());
			docData.setDiscount(index, bookPurchaseBook.getDiscount());
			docData.setPurchasePrice(index, bookPurchaseBook.getPurchasePrice());
			docData.setDealDivision(index, bookPurchaseBook.getDealDivision());
			docData.setStaff(index, bookPurchaseBook.getStaff());

			docData.setItemClassName(index, bookPurchaseBook.getItemClassName());
			docData.setItemKana(index, bookPurchaseBook.getItemKana());
			docData.setItemName(index, bookPurchaseBook.getItemName());
			docData.setItemCount(index, bookPurchaseBook.getItemCount());
			docData.setItemUnitPrice(index, bookPurchaseBook.getItemPurchasePrice());

			index++;
		}

		return docData;
	}
}
