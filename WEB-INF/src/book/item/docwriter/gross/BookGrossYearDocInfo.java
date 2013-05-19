package book.item.docwriter.gross;

import java.io.File;

import prop.CommonProperties;

/**
 * 年度粗利益文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookGrossYearDocInfo extends BookGrossDocInfo {

	private static final long serialVersionUID = -1435151832401083960L;

	/**
	 * コンストラクタ
	 */
	public BookGrossYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_GROSS)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_GROSS_YEAR);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
