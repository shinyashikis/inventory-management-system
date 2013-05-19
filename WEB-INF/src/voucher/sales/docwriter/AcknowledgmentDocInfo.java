package voucher.sales.docwriter;

import java.io.File;

import prop.CommonProperties;

import fw.common.docwriter.DocInfo;

/**
 * <p>領収書文書ファイル情報クラス</p>
 */
public class AcknowledgmentDocInfo extends DocInfo {
	private static final long serialVersionUID = -4959609579682567891L;

	/** 文書フォーマットファイル名 */
	private String formName;
	/** シート名 */
	private String sheet;

	/**
	 * コンストラクタ
	 */
	public AcknowledgmentDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.REPORT_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.REPORT_FORM_FILE_ACKNOWLEDGMENT)).toString();

			sheet = p.getValue(CommonProperties.REPORT_FORM_SHEET_ACKNOWLEDGMENT);

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
