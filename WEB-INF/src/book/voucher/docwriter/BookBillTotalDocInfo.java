package book.voucher.docwriter;

import java.io.File;

import prop.CommonProperties;
import fw.common.docwriter.DocInfo;

public class BookBillTotalDocInfo extends DocInfo {

	private static final long serialVersionUID = 6545809242365949007L;

	/** 文書フォーマットファイル名 */
	protected String formName;
	/** シート名 */
	protected String sheet;
	/** 選択シート */
	protected int activeSheet = 0;

	/**
	 * コンストラクタ
	 */
	public BookBillTotalDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.BOOK_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.BOOK_FORM_FILE_BILL_TOTAL)).toString();

			sheet = p.getValue(CommonProperties.BOOK_FORM_SHEET_BILL_TOTAL);
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
