package book.item.docwriter.storageSum;

import java.io.File;

import prop.CommonProperties;

/**
 * 年度入庫金額文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookStorageSumPriceYearDocInfo extends BookStorageSumDocInfo {

	private static final long serialVersionUID = -3111074416980081045L;

	/**
	 * コンストラクタ
	 */
	public BookStorageSumPriceYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_STORAGE_SUM)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_STORAGE_SUM_PRICE_YEAR);
			activeSheet = 5;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
