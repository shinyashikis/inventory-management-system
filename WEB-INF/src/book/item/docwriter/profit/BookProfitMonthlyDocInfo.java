package book.item.docwriter.profit;

import java.io.File;

import prop.CommonProperties;

/**
 * 利益率推移表文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookProfitMonthlyDocInfo extends BookProfitDocInfo {

	private static final long serialVersionUID = 6124088690497263525L;

	/**
	 * コンストラクタ
	 */
	public BookProfitMonthlyDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PROFIT)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PROFIT_MONTHLY);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
