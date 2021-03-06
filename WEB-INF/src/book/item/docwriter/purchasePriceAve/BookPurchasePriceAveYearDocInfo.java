package book.item.docwriter.purchasePriceAve;

import java.io.File;

import prop.CommonProperties;

/**
 * 年度平均仕入価格文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookPurchasePriceAveYearDocInfo extends BookPurchasePriceAveDocInfo {

	private static final long serialVersionUID = 4734463887842744028L;

	/**
	 * コンストラクタ
	 */
	public BookPurchasePriceAveYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_PURCHASE_PRICE_AVE)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_PURCHASE_PRICE_AVE_YEAR);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
