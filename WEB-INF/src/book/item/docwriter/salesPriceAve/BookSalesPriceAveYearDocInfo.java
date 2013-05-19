package book.item.docwriter.salesPriceAve;

import java.io.File;

import prop.CommonProperties;

/**
 * 年度平均販売価格文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookSalesPriceAveYearDocInfo extends BookSalesPriceAveDocInfo {

	private static final long serialVersionUID = -5990191204413541870L;

	/**
	 * コンストラクタ
	 */
	public BookSalesPriceAveYearDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_SALES_PRICE_AVE)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_SALES_PRICE_AVE_YEAR);
			activeSheet = 2;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
