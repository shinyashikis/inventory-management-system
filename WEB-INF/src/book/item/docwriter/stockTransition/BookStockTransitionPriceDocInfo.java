package book.item.docwriter.stockTransition;

import java.io.File;

import prop.CommonProperties;

public class BookStockTransitionPriceDocInfo extends BookStockTransitionDocInfo {

	private static final long serialVersionUID = 7819007737816296080L;

	/**
	 * コンストラクタ
	 */
	public BookStockTransitionPriceDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_STOCK_TRANSITION)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_STOCK_TRANSITION_PRICE);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
