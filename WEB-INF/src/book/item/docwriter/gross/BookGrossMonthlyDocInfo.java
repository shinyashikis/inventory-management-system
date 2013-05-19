package book.item.docwriter.gross;

import java.io.File;

import prop.CommonProperties;

/**
 * 粗利益推移表文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookGrossMonthlyDocInfo extends BookGrossDocInfo {

	private static final long serialVersionUID = 6124088690497263525L;

	/**
	 * コンストラクタ
	 */
	public BookGrossMonthlyDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_GROSS)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_GROSS_MONTHLY);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
