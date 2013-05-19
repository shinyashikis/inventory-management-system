package book.sales.docwriter.salesTotal;

import java.util.List;

import book.sales.bean.BookSalesTotal;

/**
 * 売上集計表文書ファイルデータ作成クラス
 */
public class BookSalesTotalDocDataGenerator {

	public static BookSalesTotalDocData generateBookSalesTotalDocData(
			List<BookSalesTotal> bookSalesTotalList) {
		BookSalesTotalDocData docData = new BookSalesTotalDocData();

		for (BookSalesTotal bookSalesTotal : bookSalesTotalList) {

			docData.setNendo(bookSalesTotal.getNendo());
			docData.setFrom(bookSalesTotal.getFrom());
			docData.setTo(bookSalesTotal.getTo());

			String code = bookSalesTotal.getCode();

			docData.setKana(code,bookSalesTotal.getKana());
			docData.setName(code, bookSalesTotal.getName());
			docData.setTaxExcludedAmount(code, bookSalesTotal.getTaxExcludedAmount());
			docData.setTax(code, bookSalesTotal.getTax());
			docData.setDiscount(code, bookSalesTotal.getDiscount());
			docData.setTaxIncludedAmount(code, bookSalesTotal.getTaxIncludedAmount());
			docData.setGross(code, bookSalesTotal.getGross());
			docData.setAplPrice(code, bookSalesTotal.getAprPrice());
			docData.setMayPrice(code, bookSalesTotal.getMayPrice());
			docData.setJunPrice(code, bookSalesTotal.getJunPrice());
			docData.setJlyPrice(code, bookSalesTotal.getJlyPrice());
			docData.setAugPrice(code, bookSalesTotal.getAugPrice());
			docData.setSepPrice(code, bookSalesTotal.getSepPrice());
			docData.setOctPrice(code, bookSalesTotal.getOctPrice());
			docData.setNovPrice(code, bookSalesTotal.getNovPrice());
			docData.setDecPrice(code, bookSalesTotal.getDecPrice());
			docData.setJanPrice(code, bookSalesTotal.getJanPrice());
			docData.setFebPrice(code, bookSalesTotal.getFebPrice());
			docData.setMarPrice(code, bookSalesTotal.getMarPrice());
		}

		return docData;
	}
}
