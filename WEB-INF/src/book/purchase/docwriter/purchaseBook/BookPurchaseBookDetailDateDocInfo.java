package book.purchase.docwriter.purchaseBook;

import java.io.File;

import prop.CommonProperties;

/**
 * 仕入台帳(取引先)(詳細)(日付)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookPurchaseBookDetailDateDocInfo extends BookPurchaseBookDocInfo {

	private static final long serialVersionUID = -1543088445195504213L;

	/**
	 * コンストラクタ
	 */
	public BookPurchaseBookDetailDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PURCHASE_BOOK)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PURCHASE_BOOK_DETAIL_DATE);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
