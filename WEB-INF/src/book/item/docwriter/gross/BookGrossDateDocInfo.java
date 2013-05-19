package book.item.docwriter.gross;

import java.io.File;

import prop.CommonProperties;

/**
 * 期間粗利益文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookGrossDateDocInfo extends BookGrossDocInfo {

	private static final long serialVersionUID = -230974233638369317L;

	/**
	 * コンストラクタ
	 */
	public BookGrossDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_GROSS)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_GROSS_DATE);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
