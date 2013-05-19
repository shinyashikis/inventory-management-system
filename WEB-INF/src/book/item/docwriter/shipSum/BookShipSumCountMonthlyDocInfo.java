package book.item.docwriter.shipSum;

import java.io.File;

import prop.CommonProperties;

/**
 * 出庫数量推移表文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookShipSumCountMonthlyDocInfo extends BookShipSumDocInfo {

	private static final long serialVersionUID = -591032727622460700L;

	/**
	 * コンストラクタ
	 */
	public BookShipSumCountMonthlyDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SHIP_SUM)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SHIP_SUM_COUNT_MONTHLY);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
