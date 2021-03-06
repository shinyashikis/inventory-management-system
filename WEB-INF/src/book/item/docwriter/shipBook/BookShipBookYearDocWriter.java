package book.item.docwriter.shipBook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fw.common.docwriter.AbstractDocWriter;
import fw.common.docwriter.DocInfo;
import fw.common.docwriter.DocWriterException;

/**
 * 出庫台帳(年度)出力クラス
 *
 * @author shinyashiki
 *
 */
public class BookShipBookYearDocWriter extends AbstractDocWriter {

	/** 列番号(日付) **/
	private static final int COL_INDEX_VOUCHER_DATE = 0;
	/** 列番号(伝票番号) **/
	private static final int COL_INDEX_VOUCHER_NO = 1;
	/** 列番号(商品分類) **/
	private static final int COL_INDEX_CLASS_NAME = 2;
	/** 列番号(ふりがな) **/
	private static final int COL_INDEX_KANA = 3;
	/** 列番号(商品名) **/
	private static final int COL_INDEX_NAME = 4;
	/** 列番号(数量) **/
	private static final int COL_INDEX_COUNT = 5;


	public BookShipBookYearDocWriter(DocInfo docInfo) {
		super(docInfo);
	}

	/* (非 Javadoc)
	 * @see fw.common.docwriter.AbstractDocWriter#write()
	 */
	@Override
	public void write() throws DocWriterException {
		OutputStream os = null;
		InputStream in = null;

		try {
			BookShipBookDocInfo bookShipBookDocInfo = (BookShipBookDocInfo)docInfo;

			in = new BufferedInputStream(new FileInputStream(new File(bookShipBookDocInfo.getFormName())));
			Workbook book = new HSSFWorkbook(in);

			for (int i = 0; i < book.getNumberOfSheets(); i++) {
				book.getSheetAt(i).setSelected(false);
			}

			Sheet sheet = book.getSheet(bookShipBookDocInfo.getSheet());
			book.setActiveSheet(bookShipBookDocInfo.getActiveSheet());
			sheet.setSelected(true);
			setData(sheet, (BookShipBookDocData)bookShipBookDocInfo.getDocData());
			os = new BufferedOutputStream(new FileOutputStream(bookShipBookDocInfo.getOutFileName()));
			book.write(os);

		} catch (Exception e) {
			throw new DocWriterException(e);

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new DocWriterException(e);
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					throw new DocWriterException(e);
				}
			}
		}
	}

	/**
	 * 出力情報設定
	 *
	 * @param sheet
	 * @param docData
	 */
	private void setData(Sheet sheet, BookShipBookDocData docData) {

		// 年度(from ～ to)
		Row row = sheet.createRow(1);
		Cell cell = row.createCell(0);
		StringBuilder sb = new StringBuilder()
			.append(docData.getNendo())
			.append("年度(")
			.append(docData.getFrom())
			.append(" ～ ")
			.append(docData.getTo())
			.append(")");
		cell.setCellValue(sb.toString());

		int rowIndex = 3;

		for (int index = 0; index < docData.getRowDataList().size(); index++) {
			row = sheet.createRow(rowIndex++);

			// 日付
			cell = row.createCell(COL_INDEX_VOUCHER_DATE);
			cell.setCellValue(docData.getVoucherDate(index));
			// 伝票番号
			cell = row.createCell(COL_INDEX_VOUCHER_NO);
			cell.setCellValue(docData.getVoucherNo(index));
			// 商品分類
			cell = row.createCell(COL_INDEX_CLASS_NAME);
			cell.setCellValue(docData.getClassName(index));
			// ふりがな
			cell = row.createCell(COL_INDEX_KANA);
			cell.setCellValue(docData.getKana(index));
			// 商品名
			cell = row.createCell(COL_INDEX_NAME);
			cell.setCellValue(docData.getName(index));
			// 数量
			cell = row.createCell(COL_INDEX_COUNT);
			cell.setCellValue(Double.parseDouble(docData.getCount(index)));
		}
	}
}
