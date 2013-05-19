package book.voucher.docwriter;

import java.util.List;

import book.voucher.bean.BookQuotation;

/**
 * 見積書文書ファイルデータ作成クラス
 */
public class BookQuotationDocDataGenerator {

	public static BookQuotationDocData generateBookSalesBookDocData(
			List<BookQuotation> bookQuotationList) {
		BookQuotationDocData docData = new BookQuotationDocData();

		for (BookQuotation bookQuotation : bookQuotationList) {

			docData.setNendo(bookQuotation.getNendo());
			docData.setFrom(bookQuotation.getFrom());
			docData.setTo(bookQuotation.getTo());

			String key = bookQuotation.getVoucherNo();

			docData.setVoucherDate(key, bookQuotation.getVoucherDate());
			docData.setVoucherNo(key, bookQuotation.getVoucherNo());
			docData.setCode(key, bookQuotation.getCode());
			docData.setName(key, bookQuotation.getName());
			docData.setStaff(key, bookQuotation.getStaff());
			docData.setTaxExcludedAmount(key, bookQuotation.getSumTaxExcludedAmount());
			docData.setTax(key, bookQuotation.getTax());
			docData.setTaxIncludedAmount(key, bookQuotation.getTaxIncludedAmount());
			docData.setDiscount(key, bookQuotation.getDiscount());
			docData.setAllPrice(key, bookQuotation.getAllPrice());
		}

		return docData;
	}
}
