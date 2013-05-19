package book.item.docwriter.profit;

import java.io.File;

import prop.CommonProperties;

/**
 * 年度平均利益率文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookProfitYearDocInfo extends BookProfitDocInfo {

	private static final long serialVersionUID = -1435151832401083960L;

	/**
	 * コンストラクタ
	 */
	public BookProfitYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PROFIT)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PROFIT_YEAR);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
