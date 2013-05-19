package book.item.docwriter.shipSum;

import fw.common.docwriter.DocInfo;

public abstract class BookShipSumDocInfo extends DocInfo {
	private static final long serialVersionUID = -5293981740331995371L;

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
