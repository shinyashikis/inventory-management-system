package voucher.quotation.docwriter;

import java.io.File;

import prop.CommonProperties;

import fw.common.docwriter.DocInfo;

/**
 * <p>見積文書ファイル情報クラス</p>
 */
public class QuotationDocInfo extends DocInfo {
	private static final long serialVersionUID = 6889295786115347072L;

	/** 文書フォーマットファイル名 */
	private String formName;
	/** シート名 */
	private String sheet;

	/**
	 * コンストラクタ
	 */
	public QuotationDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.REPORT_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.REPORT_FORM_FILE_QUOTATION)).toString();

			sheet = p.getValue(CommonProperties.REPORT_FORM_SHEET_QUOTATION);

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
