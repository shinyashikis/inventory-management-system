package book.item.docwriter.salesPriceAve;

import java.io.File;

import prop.CommonProperties;

/**
 * 期間平均販売価格文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookSalesPriceAveDateDocInfo extends BookSalesPriceAveDocInfo {

	private static final long serialVersionUID = 6819171287022099487L;

	/**
	 * コンストラクタ
	 */
	public BookSalesPriceAveDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SALES_PRICE_AVE)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SALES_PRICE_AVE_DATE);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
