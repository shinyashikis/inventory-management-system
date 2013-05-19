package book.item.docwriter.profit;

import java.io.File;

import prop.CommonProperties;

/**
 * 期間利益率文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookProfitDateDocInfo extends BookProfitDocInfo {

	private static final long serialVersionUID = -230974233638369317L;

	/**
	 * コンストラクタ
	 */
	public BookProfitDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PROFIT)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PROFIT_DATE);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
