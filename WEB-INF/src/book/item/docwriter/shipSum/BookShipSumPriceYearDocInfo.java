package book.item.docwriter.shipSum;

import java.io.File;

import prop.CommonProperties;

/**
 * 年度出庫金額文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookShipSumPriceYearDocInfo extends BookShipSumDocInfo {

	private static final long serialVersionUID = 791658008251219194L;

	/**
	 * コンストラクタ
	 */
	public BookShipSumPriceYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SHIP_SUM)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SHIP_SUM_PRICE_YEAR);
			activeSheet = 5;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
