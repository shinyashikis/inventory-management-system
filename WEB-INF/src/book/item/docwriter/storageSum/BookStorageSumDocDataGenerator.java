package book.item.docwriter.storageSum;

import java.util.List;

import book.item.bean.BookStorageSum;

/**
 * 入庫集計表文書ファイルデータ作成クラス
 */
public class BookStorageSumDocDataGenerator {

	public static BookStorageSumDocData generateBookStrageSumDocData(
			List<BookStorageSum> bookStorageSumList) {
		BookStorageSumDocData docData = new BookStorageSumDocData();

		for (BookStorageSum bookStorageSum : bookStorageSumList) {

			docData.setNendo(bookStorageSum.getNendo());
			docData.setFrom(bookStorageSum.getFrom());
			docData.setTo(bookStorageSum.getTo());

			String code = bookStorageSum.getCode();

			docData.setClassName(code, bookStorageSum.getClassName());
			docData.setKana(code,bookStorageSum.getKana());
			docData.setName(code, bookStorageSum.getName());

			docData.setSumCount(code, (bookStorageSum.getSumCount() != null) ? bookStorageSum.getSumCount().toString() : null);
			docData.setSumPrice(code, bookStorageSum.getSumPrice());

			docData.setAplCount(code, (bookStorageSum.getAprCount() != null) ? bookStorageSum.getAprCount().toString() : null);
			docData.setMayCount(code, (bookStorageSum.getMayCount() != null) ? bookStorageSum.getMayCount().toString() : null);
			docData.setJunCount(code, (bookStorageSum.getJunCount() != null) ? bookStorageSum.getJunCount().toString() : null);
			docData.setJlyCount(code, (bookStorageSum.getJlyCount() != null) ? bookStorageSum.getJlyCount().toString() : null);
			docData.setAugCount(code, (bookStorageSum.getAugCount() != null) ? bookStorageSum.getAugCount().toString() : null);
			docData.setSepCount(code, (bookStorageSum.getSepCount() != null) ? bookStorageSum.getSepCount().toString() : null);
			docData.setOctCount(code, (bookStorageSum.getOctCount() != null) ? bookStorageSum.getOctCount().toString() : null);
			docData.setNovCount(code, (bookStorageSum.getNovCount() != null) ? bookStorageSum.getNovCount().toString() : null);
			docData.setDecCount(code, (bookStorageSum.getDecCount() != null) ? bookStorageSum.getDecCount().toString() : null);
			docData.setJanCount(code, (bookStorageSum.getJanCount() != null) ? bookStorageSum.getJanCount().toString() : null);
			docData.setFebCount(code, (bookStorageSum.getFebCount() != null) ? bookStorageSum.getFebCount().toString() : null);
			docData.setMarCount(code, (bookStorageSum.getMarCount() != null) ? bookStorageSum.getMarCount().toString() : null);

			docData.setAplPrice(code, bookStorageSum.getAprPrice());
			docData.setMayPrice(code, bookStorageSum.getMayPrice());
			docData.setJunPrice(code, bookStorageSum.getJunPrice());
			docData.setJlyPrice(code, bookStorageSum.getJlyPrice());
			docData.setAugPrice(code, bookStorageSum.getAugPrice());
			docData.setSepPrice(code, bookStorageSum.getSepPrice());
			docData.setOctPrice(code, bookStorageSum.getOctPrice());
			docData.setNovPrice(code, bookStorageSum.getNovPrice());
			docData.setDecPrice(code, bookStorageSum.getDecPrice());
			docData.setJanPrice(code, bookStorageSum.getJanPrice());
			docData.setFebPrice(code, bookStorageSum.getFebPrice());
			docData.setMarPrice(code, bookStorageSum.getMarPrice());
		}

		return docData;
	}
}
