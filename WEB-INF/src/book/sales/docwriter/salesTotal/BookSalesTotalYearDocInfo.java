package book.sales.docwriter.salesTotal;

import java.io.File;

import prop.CommonProperties;

/**
 * 売上集計表(取引先)(年度)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookSalesTotalYearDocInfo extends BookSalesTotalDocInfo {

	private static final long serialVersionUID = -3949255233522936107L;

	/**
	 * コンストラクタ
	 */
	public BookSalesTotalYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SALES_TOTAL)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SALES_TOTAL_YEAR);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
