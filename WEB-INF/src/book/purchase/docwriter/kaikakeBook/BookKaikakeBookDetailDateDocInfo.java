package book.purchase.docwriter.kaikakeBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 売掛台帳(取引先)(詳細)(日付)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookKaikakeBookDetailDateDocInfo extends BookKaikakeBookDocInfo {

	private static final long serialVersionUID = -1543088445195504213L;

	/**
	 * コンストラクタ
	 */
	public BookKaikakeBookDetailDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_KAIKAKE_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_KAIKAKE_BOOK_DETAIL_DATE);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
