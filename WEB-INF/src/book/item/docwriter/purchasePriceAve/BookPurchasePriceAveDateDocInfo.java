package book.item.docwriter.purchasePriceAve;

import java.io.File;

import prop.CommonProperties;

/**
 * 期間平均仕入価格文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookPurchasePriceAveDateDocInfo extends BookPurchasePriceAveDocInfo {

	private static final long serialVersionUID = 3356177840698722285L;

	/**
	 * コンストラクタ
	 */
	public BookPurchasePriceAveDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PURCHASE_PRICE_AVE)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PURCHASE_PRICE_AVE_DATE);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
