package voucher.order.docwriter;

import java.io.File;

import prop.CommonProperties;

import fw.common.docwriter.DocInfo;

/**
 * <p>注文書文書ファイル情報クラス</p>
 */
public class OrderDocInfo extends DocInfo {
	private static final long serialVersionUID = -4348022195118241585L;

	/** 文書フォーマットファイル名 */
	private String formName;
	/** シート名 */
	private String sheet;

	/**
	 * コンストラクタ
	 */
	public OrderDocInfo() {
		try {
			// フォーマットファイル名とシート名の指定

			CommonProperties p = CommonProperties.getInstance();

			formName = new StringBuilder(p.getValue(CommonProperties.REPORT_FORM_DIR))
				.append(File.separator)
				.append(p.getValue(CommonProperties.REPORT_FORM_FILE_ORDER)).toString();

			sheet = p.getValue(CommonProperties.REPORT_FORM_SHEET_ORDER);

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
