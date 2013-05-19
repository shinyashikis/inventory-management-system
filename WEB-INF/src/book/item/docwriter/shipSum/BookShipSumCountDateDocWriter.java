package book.item.docwriter.shipSum;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fw.common.docwriter.AbstractDocWriter;
import fw.common.docwriter.DocInfo;
import fw.common.docwriter.DocWriterException;

/**
 * 期間出庫数量出力クラス
 *
 * @author shinyashiki
 *
 */
public class BookShipSumCountDateDocWriter extends AbstractDocWriter {

	/** 列番号(商品分類) **/
	private static final int COL_INDEX_CLASS_NAME = 0;
	/** 列番号(ふりがな) **/
	private static final int COL_INDEX_KANA = 1;
	/** 列番号(商品名) **/
	private static final int COL_INDEX_NAME = 2;
	/** 列番号(売上総数) **/
	private static final int COL_INDEX_SUM_VALUE = 3;


	public BookShipSumCountDateDocWriter(DocInfo docInfo) {
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
			BookShipSumDocInfo bookShipSumDocInfo = (BookShipSumDocInfo)docInfo;

			in = new BufferedInputStream(new FileInputStream(new File(bookShipSumDocInfo.getFormName())));
			Workbook book = new HSSFWorkbook(in);

			for (int i = 0; i < book.getNumberOfSheets(); i++) {
				book.getSheetAt(i).setSelected(false);
			}

			Sheet sheet = book.getSheet(bookShipSumDocInfo.getSheet());
			book.setActiveSheet(bookShipSumDocInfo.getActiveSheet());
			sheet.setSelected(true);
			setData(sheet, (BookShipSumDocData)bookShipSumDocInfo.getDocData());
			os = new BufferedOutputStream(new FileOutputStream(bookShipSumDocInfo.getOutFileName()));
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
	private void setData(Sheet sheet, BookShipSumDocData docData) {

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

		Iterator<String> itr = docData.getRowDataMap().keySet().iterator();
		while (itr.hasNext()) {
			row = sheet.createRow(rowIndex++);

			// 商品コード
			String code = itr.next();

			// 商品分類
			cell = row.createCell(COL_INDEX_CLASS_NAME);
			cell.setCellValue(docData.getClassName(code));
			// ふりがな
			cell = row.createCell(COL_INDEX_KANA);
			cell.setCellValue(docData.getKana(code));
			// 商品名
			cell = row.createCell(COL_INDEX_NAME);
			cell.setCellValue(docData.getName(code));
			// 売上総数
			cell = row.createCell(COL_INDEX_SUM_VALUE);
			cell.setCellValue(Double.parseDouble(docData.getSumCount(code)));
		}
	}
}
