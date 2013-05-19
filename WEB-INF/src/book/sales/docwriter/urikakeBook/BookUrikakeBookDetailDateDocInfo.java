package book.sales.docwriter.urikakeBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 売掛台帳(取引先)(詳細)(日付)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookUrikakeBookDetailDateDocInfo extends BookUrikakeBookDocInfo {

	private static final long serialVersionUID = -1543088445195504213L;

	/**
	 * コンストラクタ
	 */
	public BookUrikakeBookDetailDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_URIKAKE_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_URIKAKE_BOOK_DETAIL_DATE);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
