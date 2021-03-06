package book.sales.docwriter.urikakeBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 売掛台帳(取引先)(伝票)(年度)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookUrikakeBookVoucherYearDocInfo extends BookUrikakeBookDocInfo {

	private static final long serialVersionUID = -5439215240295784116L;

	/**
	 * コンストラクタ
	 */
	public BookUrikakeBookVoucherYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_URIKAKE_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_URIKAKE_BOOK_VOUCHER_YEAR);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
