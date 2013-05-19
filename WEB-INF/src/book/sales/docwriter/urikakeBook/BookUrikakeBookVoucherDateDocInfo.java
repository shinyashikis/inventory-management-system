package book.sales.docwriter.urikakeBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 売掛台帳(取引先)(伝票)(日付)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookUrikakeBookVoucherDateDocInfo extends BookUrikakeBookDocInfo {

	private static final long serialVersionUID = -3606015244451088043L;

	/**
	 * コンストラクタ
	 */
	public BookUrikakeBookVoucherDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_URIKAKE_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_URIKAKE_BOOK_VOUCHER_DATE);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
