package book.purchase.docwriter.purchaseTotal;

import java.io.File;

import prop.CommonProperties;

/**
 * KAIKAKE集計表(取引先)(日付)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookPurchaseTotalDateDocInfo extends BookPurchaseTotalDocInfo {

	private static final long serialVersionUID = 7553236921782819492L;

	/**
	 * コンストラクタ
	 */
	public BookPurchaseTotalDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PURCHASE_TOTAL)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PURCHASE_TOTAL_DATE);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
