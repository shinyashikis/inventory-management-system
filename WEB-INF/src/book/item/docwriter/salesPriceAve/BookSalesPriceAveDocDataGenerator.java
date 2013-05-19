package book.item.docwriter.salesPriceAve;

import java.util.List;

import book.item.bean.BookSalesPriceAve;

/**
 * 平均販売価格文書ファイルデータ作成クラス
 */
public class BookSalesPriceAveDocDataGenerator {

	public static BookSalesPriceAveDocData generateBookSalesPriceAveDocData(
			List<BookSalesPriceAve> bookSalesPriceAveList) {
		BookSalesPriceAveDocData docData = new BookSalesPriceAveDocData();

		for (BookSalesPriceAve bookSalesPriceAve : bookSalesPriceAveList) {

			docData.setNendo(bookSalesPriceAve.getNendo());
			docData.setFrom(bookSalesPriceAve.getFrom());
			docData.setTo(bookSalesPriceAve.getTo());

			String code = bookSalesPriceAve.getCode();

			docData.setClassName(code, bookSalesPriceAve.getClassName());
			docData.setKana(code,bookSalesPriceAve.getKana());
			docData.setName(code, bookSalesPriceAve.getName());

			docData.setAvePrice(code, bookSalesPriceAve.getAvePrice());

			docData.setAplPrice(code, bookSalesPriceAve.getAprPrice());
			docData.setMayPrice(code, bookSalesPriceAve.getMayPrice());
			docData.setJunPrice(code, bookSalesPriceAve.getJunPrice());
			docData.setJlyPrice(code, bookSalesPriceAve.getJlyPrice());
			docData.setAugPrice(code, bookSalesPriceAve.getAugPrice());
			docData.setSepPrice(code, bookSalesPriceAve.getSepPrice());
			docData.setOctPrice(code, bookSalesPriceAve.getOctPrice());
			docData.setNovPrice(code, bookSalesPriceAve.getNovPrice());
			docData.setDecPrice(code, bookSalesPriceAve.getDecPrice());
			docData.setJanPrice(code, bookSalesPriceAve.getJanPrice());
			docData.setFebPrice(code, bookSalesPriceAve.getFebPrice());
			docData.setMarPrice(code, bookSalesPriceAve.getMarPrice());
		}

		return docData;
	}
}
