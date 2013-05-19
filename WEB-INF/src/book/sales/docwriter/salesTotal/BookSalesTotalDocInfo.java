package book.sales.docwriter.salesTotal;

import fw.common.docwriter.DocInfo;

public abstract class BookSalesTotalDocInfo extends DocInfo {

	private static final long serialVersionUID = 4633528287137982557L;

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
