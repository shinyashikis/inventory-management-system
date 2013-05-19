package book.item.docwriter.purchasePriceAve;

import java.util.List;

import book.item.bean.BookPurchasePriceAve;

/**
 * 平均仕入価格文書ファイルデータ作成クラス
 */
public class BookPurchasePriceAveDocDataGenerator {

	public static BookPurchasePriceAveDocData generateBookPurchasePriceAveDocData(
			List<BookPurchasePriceAve> bookPurchasePriceAveList) {
		BookPurchasePriceAveDocData docData = new BookPurchasePriceAveDocData();

		for (BookPurchasePriceAve bookPurchasePriceAve : bookPurchasePriceAveList) {

			docData.setNendo(bookPurchasePriceAve.getNendo());
			docData.setFrom(bookPurchasePriceAve.getFrom());
			docData.setTo(bookPurchasePriceAve.getTo());

			String code = bookPurchasePriceAve.getCode();

			docData.setClassName(code, bookPurchasePriceAve.getClassName());
			docData.setKana(code,bookPurchasePriceAve.getKana());
			docData.setName(code, bookPurchasePriceAve.getName());

			docData.setAvePrice(code, bookPurchasePriceAve.getAvePrice());

			docData.setAplPrice(code, bookPurchasePriceAve.getAprPrice());
			docData.setMayPrice(code, bookPurchasePriceAve.getMayPrice());
			docData.setJunPrice(code, bookPurchasePriceAve.getJunPrice());
			docData.setJlyPrice(code, bookPurchasePriceAve.getJlyPrice());
			docData.setAugPrice(code, bookPurchasePriceAve.getAugPrice());
			docData.setSepPrice(code, bookPurchasePriceAve.getSepPrice());
			docData.setOctPrice(code, bookPurchasePriceAve.getOctPrice());
			docData.setNovPrice(code, bookPurchasePriceAve.getNovPrice());
			docData.setDecPrice(code, bookPurchasePriceAve.getDecPrice());
			docData.setJanPrice(code, bookPurchasePriceAve.getJanPrice());
			docData.setFebPrice(code, bookPurchasePriceAve.getFebPrice());
			docData.setMarPrice(code, bookPurchasePriceAve.getMarPrice());
		}

		return docData;
	}
}
