package voucher.bill.docwriter;

import java.io.File;

import prop.CommonProperties;

import fw.common.docwriter.DocInfo;

/**
 * <p>請求書文書ファイル情報クラス</p>
 */
public class BillDetailDocInfo extends DocInfo {
	private static final long serialVersionUID = 7123927878667929612L;

	/** 文書フォーマットファイル名 */
	private String formName;
	/** シート名 */
	private String sheet;

	/**
	 * コンストラクタ
	 */
	public BillDetailDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.REPORT_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.REPORT_FORM_FILE_BILL_DETAIL)).toString();

			sheet = p.getValue(CommonProperties.REPORT_FORM_SHEET_BILL_DETAIL);

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

}
