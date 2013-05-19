package voucher.sales.docwriter;

import java.io.File;

import prop.CommonProperties;

import fw.common.docwriter.DocInfo;

/**
 * <p>受領書文書ファイル情報クラス</p>
 */
public class ReceiptDocInfo extends DocInfo {
	private static final long serialVersionUID = 7123927878667929612L;

	/** 文書フォーマットファイル名 */
	private String formName;
	/** シート名 */
	private String sheet;

	/**
	 * コンストラクタ
	 */
	public ReceiptDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.REPORT_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.REPORT_FORM_FILE_RECEIPT)).toString();

			sheet = p.getValue(CommonProperties.REPORT_FORM_SHEET_RECEIPT);

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
