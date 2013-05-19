package book.item.docwriter.salesPriceAve;

import java.io.File;

import prop.CommonProperties;

/**
 * 販売価格推移表文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookSalesPriceAveMonthlyDocInfo extends BookSalesPriceAveDocInfo {

	private static final long serialVersionUID = 5566321747824806184L;

	/**
	 * コンストラクタ
	 */
	public BookSalesPriceAveMonthlyDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SALES_PRICE_AVE)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SALES_PRICE_AVE_MONTHLY);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
