package book.item.docwriter.storageSum;

import java.io.File;

import prop.CommonProperties;

/**
 * 期間入庫金額文書ファイル情報クラス
 *
 * @author shinyashiki
 *
 */
public class BookStorageSumPriceDateDocInfo extends BookStorageSumDocInfo {

	private static final long serialVersionUID = -6669116406447646783L;

	/**
	 * コンストラクタ
	 */
	public BookStorageSumPriceDateDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_STORAGE_SUM)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_STORAGE_SUM_PRICE_DATE);
			activeSheet = 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
