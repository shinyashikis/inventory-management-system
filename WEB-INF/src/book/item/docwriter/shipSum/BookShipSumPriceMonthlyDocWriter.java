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
 * 出庫金額推移表出力クラス
 *
 * @author shinyashiki
 *
 */
public class BookShipSumPriceMonthlyDocWriter extends AbstractDocWriter {

	/** 列番号(商品分類) **/
	private static final int COL_INDEX_CLASS_NAME = 0;
	/** 列番号(ふりがな) **/
	private static final int COL_INDEX_KANA = 1;
	/** 列番号(商品名) **/
	private static final int COL_INDEX_NAME = 2;
	/** 列番号(売上高) **/
	private static final int COL_INDEX_SUM_VALUE = 3;
	/** 列番号(4月度) **/
	private static final int COL_INDEX_APL_VALUE = 4;
	/** 列番号(5月度) **/
	private static final int COL_INDEX_MAY_VALUE = 5;
	/** 列番号(6月度) **/
	private static final int COL_INDEX_JUN_VALUE = 6;
	/** 列番号(7月度) **/
	private static final int COL_INDEX_JLY_VALUE = 7;
	/** 列番号(8月度) **/
	private static final int COL_INDEX_AUG_VALUE = 8;
	/** 列番号(9月度) **/
	private static final int COL_INDEX_SEP_VALUE = 9;
	/** 列番号(10月度) **/
	private static final int COL_INDEX_OCT_VALUE = 10;
	/** 列番号(11月度) **/
	private static final int COL_INDEX_NOV_VALUE = 11;
	/** 列番号(12月度) **/
	private static final int COL_INDEX_DEC_VALUE = 12;
	/** 列番号(1月度) **/
	private static final int COL_INDEX_JAN_VALUE = 13;
	/** 列番号(2月度) **/
	private static final int COL_INDEX_FEB_VALUE = 14;
	/** 列番号(3月度) **/
	private static final int COL_INDEX_MAR_VALUE = 15;

	public BookShipSumPriceMonthlyDocWriter(DocInfo docInfo) {
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
			// 売上高
			cell = row.createCell(COL_INDEX_SUM_VALUE);
			cell.setCellValue(docData.getSumPrice(code).doubleValue());
			// 4月度
			cell = row.createCell(COL_INDEX_APL_VALUE);
			cell.setCellValue(docData.getAplPrice(code).doubleValue());
			// 5月度
			cell = row.createCell(COL_INDEX_MAY_VALUE);
			cell.setCellValue(docData.getMayPrice(code).doubleValue());
			// 6月度
			cell = row.createCell(COL_INDEX_JUN_VALUE);
			cell.setCellValue(docData.getJunPrice(code).doubleValue());
			// 7月度
			cell = row.createCell(COL_INDEX_JLY_VALUE);
			cell.setCellValue(docData.getJlyPrice(code).doubleValue());
			// 8月度
			cell = row.createCell(COL_INDEX_AUG_VALUE);
			cell.setCellValue(docData.getAugPrice(code).doubleValue());
			// 9月度
			cell = row.createCell(COL_INDEX_SEP_VALUE);
			cell.setCellValue(docData.getSepPrice(code).doubleValue());
			// 10月度
			cell = row.createCell(COL_INDEX_OCT_VALUE);
			cell.setCellValue(docData.getOctPrice(code).doubleValue());
			// 11月度
			cell = row.createCell(COL_INDEX_NOV_VALUE);
			cell.setCellValue(docData.getNovPrice(code).doubleValue());
			// 12月度
			cell = row.createCell(COL_INDEX_DEC_VALUE);
			cell.setCellValue(docData.getDecPrice(code).doubleValue());
			// 1月度
			cell = row.createCell(COL_INDEX_JAN_VALUE);
			cell.setCellValue(docData.getJanPrice(code).doubleValue());
			// 2月度
			cell = row.createCell(COL_INDEX_FEB_VALUE);
			cell.setCellValue(docData.getFebPrice(code).doubleValue());
			// 3月度
			cell = row.createCell(COL_INDEX_MAR_VALUE);
			cell.setCellValue(docData.getMarPrice(code).doubleValue());
		}
	}
}
