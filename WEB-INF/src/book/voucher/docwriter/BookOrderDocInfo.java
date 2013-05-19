package book.voucher.docwriter;

import java.io.File;

import prop.CommonProperties;
import fw.common.docwriter.DocInfo;

public class BookOrderDocInfo extends DocInfo {

	private static final long serialVersionUID = 8917032951599228868L;

	/** 文書フォーマットファイル名 */
	protected String formName;
	/** シート名 */
	protected String sheet;
	/** 選択シート */
	protected int activeSheet = 0;

	/**
	 * コンストラクタ
	 */
	public BookOrderDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_ORDER)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_ORDER);
			activeSheet = 0;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getFormName() {
		return formName;
	}

	public String getSheet() {
		return sheet;
	}

	public int getActiveSheet() {
		return activeSheet;
	}

}
