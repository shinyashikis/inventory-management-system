package book.sales.docwriter.salesBook;

import fw.common.docwriter.DocInfo;

public abstract class BookSalesBookDocInfo extends DocInfo {

	private static final long serialVersionUID = 4604969561646628915L;

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
