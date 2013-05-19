package book.sales.docwriter.urikakeBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 売掛台帳(取引先)(詳細)(年度)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookUrikakeBookDetailYearDocInfo extends BookUrikakeBookDocInfo {

	private static final long serialVersionUID = 2888307372466980L;

	/**
	 * コンストラクタ
	 */
	public BookUrikakeBookDetailYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_URIKAKE_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_URIKAKE_BOOK_DETAIL_YEAR);
			activeSheet = 3;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
