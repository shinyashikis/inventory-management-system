package book.item.docwriter.shipSum;

import java.util.List;

import book.item.bean.BookShipSum;

/**
 * 出庫集計表文書ファイルデータ作成クラス
 */
public class BookShipSumDocDataGenerator {

	public static BookShipSumDocData generateBookShipSumDocData(
			List<BookShipSum> bookShipSumList) {
		BookShipSumDocData docData = new BookShipSumDocData();

		for (BookShipSum bookShipSum : bookShipSumList) {

			docData.setNendo(bookShipSum.getNendo());
			docData.setFrom(bookShipSum.getFrom());
			docData.setTo(bookShipSum.getTo());

			String code = bookShipSum.getCode();

			docData.setClassName(code, bookShipSum.getClassName());
			docData.setKana(code,bookShipSum.getKana());
			docData.setName(code, bookShipSum.getName());

			docData.setSumCount(code, (bookShipSum.getSumCount() != null) ? bookShipSum.getSumCount().toString() : null);
			docData.setSumPrice(code, bookShipSum.getSumPrice());

			docData.setAplCount(code, (bookShipSum.getAprCount() != null) ? bookShipSum.getAprCount().toString() : null);
			docData.setMayCount(code, (bookShipSum.getMayCount() != null) ? bookShipSum.getMayCount().toString() : null);
			docData.setJunCount(code, (bookShipSum.getJunCount() != null) ? bookShipSum.getJunCount().toString() : null);
			docData.setJlyCount(code, (bookShipSum.getJlyCount() != null) ? bookShipSum.getJlyCount().toString() : null);
			docData.setAugCount(code, (bookShipSum.getAugCount() != null) ? bookShipSum.getAugCount().toString() : null);
			docData.setSepCount(code, (bookShipSum.getSepCount() != null) ? bookShipSum.getSepCount().toString() : null);
			docData.setOctCount(code, (bookShipSum.getOctCount() != null) ? bookShipSum.getOctCount().toString() : null);
			docData.setNovCount(code, (bookShipSum.getNovCount() != null) ? bookShipSum.getNovCount().toString() : null);
			docData.setDecCount(code, (bookShipSum.getDecCount() != null) ? bookShipSum.getDecCount().toString() : null);
			docData.setJanCount(code, (bookShipSum.getJanCount() != null) ? bookShipSum.getJanCount().toString() : null);
			docData.setFebCount(code, (bookShipSum.getFebCount() != null) ? bookShipSum.getFebCount().toString() : null);
			docData.setMarCount(code, (bookShipSum.getMarCount() != null) ? bookShipSum.getMarCount().toString() : null);

			docData.setAplPrice(code, bookShipSum.getAprPrice());
			docData.setMayPrice(code, bookShipSum.getMayPrice());
			docData.setJunPrice(code, bookShipSum.getJunPrice());
			docData.setJlyPrice(code, bookShipSum.getJlyPrice());
			docData.setAugPrice(code, bookShipSum.getAugPrice());
			docData.setSepPrice(code, bookShipSum.getSepPrice());
			docData.setOctPrice(code, bookShipSum.getOctPrice());
			docData.setNovPrice(code, bookShipSum.getNovPrice());
			docData.setDecPrice(code, bookShipSum.getDecPrice());
			docData.setJanPrice(code, bookShipSum.getJanPrice());
			docData.setFebPrice(code, bookShipSum.getFebPrice());
			docData.setMarPrice(code, bookShipSum.getMarPrice());
		}

		return docData;
	}
}
