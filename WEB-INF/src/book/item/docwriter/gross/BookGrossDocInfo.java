package book.item.docwriter.gross;

import fw.common.docwriter.DocInfo;

public abstract class BookGrossDocInfo extends DocInfo {

	private static final long serialVersionUID = 700866543006526113L;

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
