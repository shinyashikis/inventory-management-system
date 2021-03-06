package book.item.docwriter.storageSum;

import java.io.File;

import prop.CommonProperties;

/**
 * 入庫金額推移表文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookStorageSumPriceMonthlyDocInfo extends BookStorageSumDocInfo {

	private static final long serialVersionUID = 7020505514501770781L;

	/**
	 * コンストラクタ
	 */
	public BookStorageSumPriceMonthlyDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_STORAGE_SUM)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_STORAGE_SUM_PRICE_MONTHLY);
			activeSheet = 3;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
