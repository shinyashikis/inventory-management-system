package book.purchase.docwriter.purchaseTotal;

import java.io.File;

import prop.CommonProperties;

/**
 * 仕入推移表(取引先)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookPurchaseTotalMonthlyDocInfo extends BookPurchaseTotalDocInfo {

	private static final long serialVersionUID = -5651743531242882071L;

	/**
	 * コンストラクタ
	 */
	public BookPurchaseTotalMonthlyDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PURCHASE_TOTAL)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PURCHASE_TOTAL_MONTHLY);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
