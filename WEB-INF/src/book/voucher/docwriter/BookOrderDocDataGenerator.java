package book.voucher.docwriter;

import java.util.List;

import book.voucher.bean.BookOrder;

/**
 * 注文書文書ファイルデータ作成クラス
 */
public class BookOrderDocDataGenerator {

	public static BookOrderDocData generateBookSalesBookDocData(
			List<BookOrder> bookOrderList) {
		BookOrderDocData docData = new BookOrderDocData();

		for (BookOrder bookOrder : bookOrderList) {

			docData.setNendo(bookOrder.getNendo());
			docData.setFrom(bookOrder.getFrom());
			docData.setTo(bookOrder.getTo());

			String key = bookOrder.getVoucherNo();

			docData.setVoucherDate(key, bookOrder.getVoucherDate());
			docData.setVoucherNo(key, bookOrder.getVoucherNo());
			docData.setCode(key, bookOrder.getCode());
			docData.setName(key, bookOrder.getName());
			docData.setStaff(key, bookOrder.getStaff());
			docData.setTaxExcludedAmount(key, bookOrder.getSumTaxExcludedAmount());
			docData.setTax(key, bookOrder.getTax());
			docData.setTaxIncludedAmount(key, bookOrder.getTaxIncludedAmount());
			docData.setDiscount(key, bookOrder.getDiscount());
			docData.setAllPrice(key, bookOrder.getAllPrice());
		}

		return docData;
	}
}
