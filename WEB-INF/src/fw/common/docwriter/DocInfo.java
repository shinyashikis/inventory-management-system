package fw.common.docwriter;

import java.io.Serializable;

/**
 * <p>文書ファイル情報クラス</p>
 */
public class DocInfo implements Serializable {
	private static final long serialVersionUID = 6166676903399189388L;

	/** インプットファイル名 */
	private String inFileName;

	/** アウトプットファイル名 */
	private String outFileName;

	/** アウトプットファイル削除フラグ */
	private boolean delFlg = false;

	/** 文書ファイルデータ */
	private DocData docData;

	public String getInFileName() {
		return inFileName;
	}
	public void setInFileName(String inFileName) {
		this.inFileName = inFileName;
	}
	public String getOutFileName() {
		return outFileName;
	}
	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}
	public boolean isDelFlg() {
		return delFlg;
	}
	public void setDelFlg(boolean delFlg) {
		this.delFlg = delFlg;
	}
	public DocData getDocData() {
		return docData;
	}
	public void setDocData(DocData docData) {
		this.docData = docData;
	}

}
