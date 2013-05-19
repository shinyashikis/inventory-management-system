package fw.common.docwriter;

import com.artofsolving.jodconverter.cli.ConvertDocument;

/**
 * <p>PDFファイル出力クラス</p>
 */
public class PDFWirter extends AbstractDocWriter {

	/**
	 * <p>コンストラクタ</p>
	 * @param docInfo
	 */
	public PDFWirter(DocInfo docInfo) {
		super(docInfo);
	}

	/**
	 * <p>PDFファイル作成処理</p>
	 * エクセルファイルをPDFへ変換し、PDFファイルとして出力する。
	 * @see fw.common.docwriter.AbstractDocWriter#write()
	 */
	@Override
	public void write() throws DocWriterException {
		try {
			String[] arguments = {
					docInfo.getInFileName(),
					docInfo.getOutFileName()};
			ConvertDocument.main(arguments);
		} catch (Exception e) {
			throw new DocWriterException(e);
		}
	}

}
