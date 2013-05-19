package book.item.docwriter.profit;

import java.util.List;

import book.item.bean.BookProfit;

/**
 * 利益率文書ファイルデータ作成クラス
 */
public class BookProfitDocDataGenerator {

	public static BookProfitDocData generateBookProfitDocData(
			List<BookProfit> bookProfitList) {
		BookProfitDocData docData = new BookProfitDocData();

		for (BookProfit bookProfit : bookProfitList) {

			docData.setNendo(bookProfit.getNendo());
			docData.setFrom(bookProfit.getFrom());
			docData.setTo(bookProfit.getTo());

			String code = bookProfit.getCode();

			docData.setClassName(code, bookProfit.getClassName());
			docData.setKana(code,bookProfit.getKana());
			docData.setName(code, bookProfit.getName());

			docData.setProfit(code, bookProfit.getProfit());

			docData.setAplProfit(code, bookProfit.getAprProfit());
			docData.setMayProfit(code, bookProfit.getMayProfit());
			docData.setJunProfit(code, bookProfit.getJunProfit());
			docData.setJlyProfit(code, bookProfit.getJlyProfit());
			docData.setAugProfit(code, bookProfit.getAugProfit());
			docData.setSepProfit(code, bookProfit.getSepProfit());
			docData.setOctProfit(code, bookProfit.getOctProfit());
			docData.setNovProfit(code, bookProfit.getNovProfit());
			docData.setDecProfit(code, bookProfit.getDecProfit());
			docData.setJanProfit(code, bookProfit.getJanProfit());
			docData.setFebProfit(code, bookProfit.getFebProfit());
			docData.setMarProfit(code, bookProfit.getMarProfit());
		}

		return docData;
	}
}
