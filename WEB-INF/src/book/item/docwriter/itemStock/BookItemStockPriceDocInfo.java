package book.item.docwriter.itemStock;

import java.io.File;

import prop.CommonProperties;

public class BookItemStockPriceDocInfo extends BookItemStockDocInfo {
	private static final long serialVersionUID = 7123927878667929612L;

	/**
	 * コンストラクタ
	 */
	public BookItemStockPriceDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_ITEM_STOCK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_ITEM_STOCK_PRICE);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
