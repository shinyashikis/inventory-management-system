package book.item.docwriter.shipBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 出庫台帳(年度)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookShipBookYearDocInfo extends BookShipBookDocInfo {

	private static final long serialVersionUID = 8319882718414048212L;

	/**
	 * コンストラクタ
	 */
	public BookShipBookYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SHIP_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SHIP_BOOK_YEAR);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
