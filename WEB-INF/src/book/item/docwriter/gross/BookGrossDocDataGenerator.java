package book.item.docwriter.gross;

import java.util.List;

import book.item.bean.BookGross;

/**
 * 粗利益文書ファイルデータ作成クラス
 */
public class BookGrossDocDataGenerator {

	public static BookGrossDocData generateBookGrossDocData(
			List<BookGross> bookGrossList) {
		BookGrossDocData docData = new BookGrossDocData();

		for (BookGross bookGross : bookGrossList) {

			docData.setNendo(bookGross.getNendo());
			docData.setFrom(bookGross.getFrom());
			docData.setTo(bookGross.getTo());

			String code = bookGross.getCode();

			docData.setClassName(code, bookGross.getClassName());
			docData.setKana(code,bookGross.getKana());
			docData.setName(code, bookGross.getName());

			docData.setGross(code, bookGross.getGross());

			docData.setAplPrice(code, bookGross.getAprPrice());
			docData.setMayPrice(code, bookGross.getMayPrice());
			docData.setJunPrice(code, bookGross.getJunPrice());
			docData.setJlyPrice(code, bookGross.getJlyPrice());
			docData.setAugPrice(code, bookGross.getAugPrice());
			docData.setSepPrice(code, bookGross.getSepPrice());
			docData.setOctPrice(code, bookGross.getOctPrice());
			docData.setNovPrice(code, bookGross.getNovPrice());
			docData.setDecPrice(code, bookGross.getDecPrice());
			docData.setJanPrice(code, bookGross.getJanPrice());
			docData.setFebPrice(code, bookGross.getFebPrice());
			docData.setMarPrice(code, bookGross.getMarPrice());
		}

		return docData;
	}
}
