package book.item.docwriter.shipBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 出庫台帳(日付)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookShipBookDateDocInfo extends BookShipBookDocInfo {

	private static final long serialVersionUID = -1814867897597321724L;

	/**
	 * コンストラクタ
	 */
	public BookShipBookDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SHIP_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SHIP_BOOK_DATE);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
