package book.purchase.docwriter.purchaseBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 仕入台帳(取引先)(伝票)(年度)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookPurchaseBookVoucherYearDocInfo extends BookPurchaseBookDocInfo {

	private static final long serialVersionUID = -3431586566374859091L;

	/**
	 * コンストラクタ
	 */
	public BookPurchaseBookVoucherYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PURCHASE_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PURCHASE_BOOK_VOUCHER_YEAR);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
