package book.sales.docwriter.salesTotal;

import java.io.File;

import prop.CommonProperties;

/**
 * 売上集計表(取引先)(日付)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookSalesTotalDateDocInfo extends BookSalesTotalDocInfo {

	private static final long serialVersionUID = -230974233638369317L;

	/**
	 * コンストラクタ
	 */
	public BookSalesTotalDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SALES_TOTAL)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SALES_TOTAL_DATE);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
