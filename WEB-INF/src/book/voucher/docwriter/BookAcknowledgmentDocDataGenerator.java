package book.voucher.docwriter;

import java.util.List;

import book.voucher.bean.BookAcknowledgment;

/**
 * 領収書文書ファイルデータ作成クラス
 */
public class BookAcknowledgmentDocDataGenerator {

	public static BookAcknowledgmentDocData generateBookSalesBookDocData(
			List<BookAcknowledgment> bookAcknowledgmentList) {
		BookAcknowledgmentDocData docData = new BookAcknowledgmentDocData();

		for (BookAcknowledgment bookAcknowledgment : bookAcknowledgmentList) {

			docData.setNendo(bookAcknowledgment.getNendo());
			docData.setFrom(bookAcknowledgment.getFrom());
			docData.setTo(bookAcknowledgment.getTo());

			String key = bookAcknowledgment.getReceiptNo();

			docData.setReceiptDate(key, bookAcknowledgment.getReceiptDate());
			docData.setReceiptNo(key, bookAcknowledgment.getReceiptNo());
			docData.setVoucherDate(key, bookAcknowledgment.getVoucherDate());
			docData.setVoucherNo(key, bookAcknowledgment.getVoucherNo());
			docData.setCode(key, bookAcknowledgment.getCode());
			docData.setName(key, bookAcknowledgment.getName());
			docData.setStaff(key, bookAcknowledgment.getStaff());
			docData.setTaxExcludedAmount(key, bookAcknowledgment.getSumTaxExcludedAmount());
			docData.setTax(key, bookAcknowledgment.getTax());
			docData.setTaxIncludedAmount(key, bookAcknowledgment.getTaxIncludedAmount());
			docData.setDiscount(key, bookAcknowledgment.getDiscount());
			docData.setAllPrice(key, bookAcknowledgment.getAllPrice());
		}

		return docData;
	}
}
