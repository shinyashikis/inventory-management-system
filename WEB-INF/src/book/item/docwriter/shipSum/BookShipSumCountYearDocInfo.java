package book.item.docwriter.shipSum;

import java.io.File;

import prop.CommonProperties;

/**
 * 年度出庫数量文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookShipSumCountYearDocInfo extends BookShipSumDocInfo {

	private static final long serialVersionUID = 9156554264700851306L;

	/**
	 * コンストラクタ
	 */
	public BookShipSumCountYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SHIP_SUM)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SHIP_SUM_COUNT_YEAR);
			activeSheet = 4;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
