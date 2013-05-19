package book.item.docwriter.stockTransition;

import java.util.List;

import book.item.bean.BookStockTransition;

/**
 * 在庫数量推移表文書ファイルデータ作成クラス
 */
public class BookStockTransitionDocDataGenerator {

	public static BookStockTransitionDocData generateBookStockTransitionDocData(
			List<BookStockTransition> bookStockTransitionList) {
		BookStockTransitionDocData docData = new BookStockTransitionDocData();

		for (BookStockTransition bookStockTransition : bookStockTransitionList) {

			docData.setNendo(bookStockTransition.getNendo());
			docData.setFrom(bookStockTransition.getFrom());
			docData.setTo(bookStockTransition.getTo());

			String code = bookStockTransition.getCode();

			docData.setClassName(code, bookStockTransition.getClassName());
			docData.setKana(code,bookStockTransition.getKana());
			docData.setName(code, bookStockTransition.getName());
			docData.setAplCount(code, (bookStockTransition.getAprCount() != null) ? bookStockTransition.getAprCount().toString() : null);
			docData.setMayCount(code, (bookStockTransition.getMayCount() != null) ? bookStockTransition.getMayCount().toString() : null);
			docData.setJunCount(code, (bookStockTransition.getJunCount() != null) ? bookStockTransition.getJunCount().toString() : null);
			docData.setJlyCount(code, (bookStockTransition.getJlyCount() != null) ? bookStockTransition.getJlyCount().toString() : null);
			docData.setAugCount(code, (bookStockTransition.getAugCount() != null) ? bookStockTransition.getAugCount().toString() : null);
			docData.setSepCount(code, (bookStockTransition.getSepCount() != null) ? bookStockTransition.getSepCount().toString() : null);
			docData.setOctCount(code, (bookStockTransition.getOctCount() != null) ? bookStockTransition.getOctCount().toString() : null);
			docData.setNovCount(code, (bookStockTransition.getNovCount() != null) ? bookStockTransition.getNovCount().toString() : null);
			docData.setDecCount(code, (bookStockTransition.getDecCount() != null) ? bookStockTransition.getDecCount().toString() : null);
			docData.setJanCount(code, (bookStockTransition.getJanCount() != null) ? bookStockTransition.getJanCount().toString() : null);
			docData.setFebCount(code, (bookStockTransition.getFebCount() != null) ? bookStockTransition.getFebCount().toString() : null);
			docData.setMarCount(code, (bookStockTransition.getMarCount() != null) ? bookStockTransition.getMarCount().toString() : null);

			docData.setAplPrice(code, bookStockTransition.getAprPrice());
			docData.setMayPrice(code, bookStockTransition.getMayPrice());
			docData.setJunPrice(code, bookStockTransition.getJunPrice());
			docData.setJlyPrice(code, bookStockTransition.getJlyPrice());
			docData.setAugPrice(code, bookStockTransition.getAugPrice());
			docData.setSepPrice(code, bookStockTransition.getSepPrice());
			docData.setOctPrice(code, bookStockTransition.getOctPrice());
			docData.setNovPrice(code, bookStockTransition.getNovPrice());
			docData.setDecPrice(code, bookStockTransition.getDecPrice());
			docData.setJanPrice(code, bookStockTransition.getJanPrice());
			docData.setFebPrice(code, bookStockTransition.getFebPrice());
			docData.setMarPrice(code, bookStockTransition.getMarPrice());
		}

		return docData;
	}
}
