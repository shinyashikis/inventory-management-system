package book.purchase.docwriter.purchaseTotal;

import java.util.List;

import book.purchase.bean.BookPurchaseTotal;

/**
 * 仕入集計表文書ファイルデータ作成クラス
 */
public class BookPurchaseTotalDocDataGenerator {

	public static BookPurchaseTotalDocData generateBookPurchaseTotalDocData(
			List<BookPurchaseTotal> bookPurchaseTotalList) {
		BookPurchaseTotalDocData docData = new BookPurchaseTotalDocData();

		for (BookPurchaseTotal bookPurchaseTotal : bookPurchaseTotalList) {

			docData.setNendo(bookPurchaseTotal.getNendo());
			docData.setFrom(bookPurchaseTotal.getFrom());
			docData.setTo(bookPurchaseTotal.getTo());

			String code = bookPurchaseTotal.getCode();

			docData.setKana(code,bookPurchaseTotal.getKana());
			docData.setName(code, bookPurchaseTotal.getName());
			docData.setTaxExcludedAmount(code, bookPurchaseTotal.getTaxExcludedAmount());
			docData.setTax(code, bookPurchaseTotal.getTax());
			docData.setDiscount(code, bookPurchaseTotal.getDiscount());
			docData.setTaxIncludedAmount(code, bookPurchaseTotal.getTaxIncludedAmount());
			docData.setAplPrice(code, bookPurchaseTotal.getAprPrice());
			docData.setMayPrice(code, bookPurchaseTotal.getMayPrice());
			docData.setJunPrice(code, bookPurchaseTotal.getJunPrice());
			docData.setJlyPrice(code, bookPurchaseTotal.getJlyPrice());
			docData.setAugPrice(code, bookPurchaseTotal.getAugPrice());
			docData.setSepPrice(code, bookPurchaseTotal.getSepPrice());
			docData.setOctPrice(code, bookPurchaseTotal.getOctPrice());
			docData.setNovPrice(code, bookPurchaseTotal.getNovPrice());
			docData.setDecPrice(code, bookPurchaseTotal.getDecPrice());
			docData.setJanPrice(code, bookPurchaseTotal.getJanPrice());
			docData.setFebPrice(code, bookPurchaseTotal.getFebPrice());
			docData.setMarPrice(code, bookPurchaseTotal.getMarPrice());
		}

		return docData;
	}
}
