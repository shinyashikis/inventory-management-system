package book.sales.docwriter.urikakeBook;

import fw.common.docwriter.DocInfo;

public abstract class BookUrikakeBookDocInfo extends DocInfo {

	private static final long serialVersionUID = -4757890684403920314L;

	/** 文書フォーマットファイル名 */
	protected String formName;
	/** シート名 */
	protected String sheet;
	/** 選択シート */
	protected int activeSheet = 0;

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
