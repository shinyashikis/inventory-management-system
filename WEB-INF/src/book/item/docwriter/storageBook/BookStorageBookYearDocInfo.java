package book.item.docwriter.storageBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 入庫台帳(年度)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookStorageBookYearDocInfo extends BookStorageBookDocInfo {

	private static final long serialVersionUID = -3710294387439797493L;

	/**
	 * コンストラクタ
	 */
	public BookStorageBookYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_STORAGE_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_STORAGE_BOOK_YEAR);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
