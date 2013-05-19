package book.item.docwriter.storageBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 入庫台帳(日付)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookStorageBookDateDocInfo extends BookStorageBookDocInfo {

	private static final long serialVersionUID = -1621891794449510826L;

	/**
	 * コンストラクタ
	 */
	public BookStorageBookDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_STORAGE_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_STORAGE_BOOK_DATE);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
