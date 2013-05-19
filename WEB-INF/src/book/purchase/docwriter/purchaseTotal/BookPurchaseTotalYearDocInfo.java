package book.purchase.docwriter.purchaseTotal;

import java.io.File;

import prop.CommonProperties;

/**
 * 仕入集計表(取引先)(年度)文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookPurchaseTotalYearDocInfo extends BookPurchaseTotalDocInfo {

	private static final long serialVersionUID = -6587330485855068072L;

	/**
	 * コンストラクタ
	 */
	public BookPurchaseTotalYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PURCHASE_TOTAL)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PURCHASE_TOTAL_YEAR);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
