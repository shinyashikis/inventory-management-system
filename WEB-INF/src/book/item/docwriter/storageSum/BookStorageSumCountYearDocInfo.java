package book.item.docwriter.storageSum;

import java.io.File;

import prop.CommonProperties;

/**
 * 年度入庫数量文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookStorageSumCountYearDocInfo extends BookStorageSumDocInfo {

	private static final long serialVersionUID = -5849307320552748555L;

	/**
	 * コンストラクタ
	 */
	public BookStorageSumCountYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_STORAGE_SUM)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_STORAGE_SUM_COUNT_YEAR);
			activeSheet = 4;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
