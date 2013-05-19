package book.sales.docwriter.salesBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 売上台帳(取引先)(伝票)(年度)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookSalesBookVoucherYearDocInfo extends BookSalesBookDocInfo {

	private static final long serialVersionUID = -8688633386646603396L;

	/**
	 * コンストラクタ
	 */
	public BookSalesBookVoucherYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SALES_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SALES_BOOK_VOUCHER_YEAR);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
