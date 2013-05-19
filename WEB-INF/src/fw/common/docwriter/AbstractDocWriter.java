package fw.common.docwriter;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

/**
 * <p>文書ファイル作成抽象クラス</p>
 */
public abstract class AbstractDocWriter {

	/** 文書ファイル情報 */
	protected DocInfo docInfo;

	/**
	 * <p>コンストラクタ</p>
	 */
	@SuppressWarnings("unused")
	private AbstractDocWriter() {}

	/**
	 * <p>コンストラクタ</p>
	 * @param docInfo 文書ファイル情報
	 */
	public AbstractDocWriter(DocInfo docInfo) {
		this.docInfo = docInfo;
	}

	/**
	 * <p>初期処理</p>
	 * @throws DocWriterException
	 */
	public void init() throws DocWriterException {}

	/**
	 * <p>文書ファイル作成処理</p>
	 * @throws DocWriterException
	 */
	abstract public void write() throws DocWriterException;

	/**
	 * <p>終了処理</p>
	 * @throws DocWriterException
	 */
	public void terminate() throws DocWriterException {
		if (docInfo.isDelFlg()) {
			// 文書ファイル削除処理
			File f = new File(docInfo.getOutFileName());
			if (f.exists()) {
				f.delete();
			}
		}
	}

	/**
	 * <p>セル作成処理</p>
	 * @param row
	 * @param cellStyle
	 * @param cellIndex
	 * @param cellValue
	 */
	protected void createCell(Row row, CellStyle cellStyle, int cellIndex, Object cellValue) {
		Cell cell = row.createCell(cellIndex);
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}

		if (cellValue != null) {
			if (cellValue instanceof Boolean) {
				cell.setCellValue((Boolean)cellValue);
			} else if (cellValue instanceof Calendar) {
				cell.setCellValue((Calendar)cellValue);
			} else if (cellValue instanceof Date) {
				cell.setCellValue((Date)cellValue);
			} else if (cellValue instanceof Double) {
				cell.setCellValue((Double)cellValue);
			} else if (cellValue instanceof String) {
				cell.setCellValue((String)cellValue);
			}
		}
	}
}
