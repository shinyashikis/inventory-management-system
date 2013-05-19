package book.item.docwriter.storageSum;

import java.io.File;

import prop.CommonProperties;

/**
 * 入庫数量推移表文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookStorageSumCountMonthlyDocInfo extends BookStorageSumDocInfo {
	private static final long serialVersionUID = 3943892562570563584L;

	/**
	 * コンストラクタ
	 */
	public BookStorageSumCountMonthlyDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_STORAGE_SUM)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_STORAGE_SUM_COUNT_MONTHLY);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
