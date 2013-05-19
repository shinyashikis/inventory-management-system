package book.item.docwriter.shipSum;

import java.io.File;

import prop.CommonProperties;

/**
 * 出庫金額推移表文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookShipSumPriceMonthlyDocInfo extends BookShipSumDocInfo {

	private static final long serialVersionUID = 4102710764061078248L;

	/**
	 * コンストラクタ
	 */
	public BookShipSumPriceMonthlyDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SHIP_SUM)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SHIP_SUM_PRICE_MONTHLY);
			activeSheet = 3;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
