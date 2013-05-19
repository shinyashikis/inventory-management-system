package book.purchase.docwriter.purchaseBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 仕入台帳(取引先)(伝票)(日付)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookPurchaseBookVoucherDateDocInfo extends BookPurchaseBookDocInfo {

	private static final long serialVersionUID = 3033301729693456056L;

	/**
	 * コンストラクタ
	 */
	public BookPurchaseBookVoucherDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PURCHASE_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PURCHASE_BOOK_VOUCHER_DATE);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
