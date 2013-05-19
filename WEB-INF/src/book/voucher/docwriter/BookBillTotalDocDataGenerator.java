package book.voucher.docwriter;

import java.util.List;

import book.voucher.bean.BookBillTotal;

/**
 * 合計請求書文書ファイルデータ作成クラス
 */
public class BookBillTotalDocDataGenerator {

	public static BookBillTotalDocData generateBookSalesBookDocData(
			List<BookBillTotal> bookBillTotalList) {
		BookBillTotalDocData docData = new BookBillTotalDocData();

		for (BookBillTotal bookBillTotal : bookBillTotalList) {

			docData.setNendo(bookBillTotal.getNendo());
			docData.setFrom(bookBillTotal.getFrom());
			docData.setTo(bookBillTotal.getTo());

			String key = bookBillTotal.getVoucherNo();

			docData.setVoucherDate(key, bookBillTotal.getVoucherDate());
			docData.setVoucherNo(key, bookBillTotal.getVoucherNo());
			docData.setCode(key, bookBillTotal.getCode());
			docData.setName(key, bookBillTotal.getName());
			docData.setStaff(key, bookBillTotal.getStaff());
			docData.setTaxExcludedAmount(key, bookBillTotal.getSumTaxExcludedAmount());
			docData.setTax(key, bookBillTotal.getTax());
			docData.setTaxIncludedAmount(key, bookBillTotal.getTaxIncludedAmount());
			docData.setDiscount(key, bookBillTotal.getDiscount());
			docData.setAllPrice(key, bookBillTotal.getAllPrice());
		}

		return docData;
	}
}
