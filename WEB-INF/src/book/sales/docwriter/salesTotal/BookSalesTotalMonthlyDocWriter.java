package book.sales.docwriter.salesTotal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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
 * 売上推移表(取引先)出力クラス
 *
 * @author shinyashiki
 *
 */
public class BookSalesTotalMonthlyDocWriter extends AbstractDocWriter {

	/** 列番号(ふりがな) **/
	private static final int COL_INDEX_KANA = 0;
	/** 列番号(得意先) **/
	private static final int COL_INDEX_NAME = 1;
	/** 列番号(4月度) **/
	private static final int COL_INDEX_APL_VALUE = 2;
	/** 列番号(5月度) **/
	private static final int COL_INDEX_MAY_VALUE = 3;
	/** 列番号(6月度) **/
	private static final int COL_INDEX_JUN_VALUE = 4;
	/** 列番号(7月度) **/
	private static final int COL_INDEX_JLY_VALUE = 5;
	/** 列番号(8月度) **/
	private static final int COL_INDEX_AUG_VALUE = 6;
	/** 列番号(9月度) **/
	private static final int COL_INDEX_SEP_VALUE = 7;
	/** 列番号(10月度) **/
	private static final int COL_INDEX_OCT_VALUE = 8;
	/** 列番号(11月度) **/
	private static final int COL_INDEX_NOV_VALUE = 9;
	/** 列番号(12月度) **/
	private static final int COL_INDEX_DEC_VALUE = 10;
	/** 列番号(1月度) **/
	private static final int COL_INDEX_JAN_VALUE = 11;
	/** 列番号(2月度) **/
	private static final int COL_INDEX_FEB_VALUE = 12;
	/** 列番号(3月度) **/
	private static final int COL_INDEX_MAR_VALUE = 13;

	public BookSalesTotalMonthlyDocWriter(DocInfo docInfo) {
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
			BookSalesTotalDocInfo bookSalesTotalDocInfo = (BookSalesTotalDocInfo)docInfo;

			in = new BufferedInputStream(new FileInputStream(new File(bookSalesTotalDocInfo.getFormName())));
			Workbook book = new HSSFWorkbook(in);

			for (int i = 0; i < book.getNumberOfSheets(); i++) {
				book.getSheetAt(i).setSelected(false);
			}

			Sheet sheet = book.getSheet(bookSalesTotalDocInfo.getSheet());
			book.setActiveSheet(bookSalesTotalDocInfo.getActiveSheet());
			sheet.setSelected(true);
			setData(sheet, (BookSalesTotalDocData)bookSalesTotalDocInfo.getDocData());
			os = new BufferedOutputStream(new FileOutputStream(bookSalesTotalDocInfo.getOutFileName()));
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
	private void setData(Sheet sheet, BookSalesTotalDocData docData) {

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

		BigDecimal aplPriceSum = new BigDecimal(0);
		BigDecimal mayPriceSum = new BigDecimal(0);
		BigDecimal junPriceSum = new BigDecimal(0);
		BigDecimal jlyPriceSum = new BigDecimal(0);
		BigDecimal augPriceSum = new BigDecimal(0);
		BigDecimal sepPriceSum = new BigDecimal(0);
		BigDecimal octPriceSum = new BigDecimal(0);
		BigDecimal novPriceSum = new BigDecimal(0);
		BigDecimal decPriceSum = new BigDecimal(0);
		BigDecimal janPriceSum = new BigDecimal(0);
		BigDecimal febPriceSum = new BigDecimal(0);
		BigDecimal marPriceSum = new BigDecimal(0);

		int rowIndex = 3;

		Iterator<String> itr = docData.getRowDataMap().keySet().iterator();
		while (itr.hasNext()) {
			row = sheet.createRow(rowIndex++);

			// 得意先コード
			String code = itr.next();

			// ふりがな
			cell = row.createCell(COL_INDEX_KANA);
			cell.setCellValue(docData.getKana(code));
			// 得意先
			cell = row.createCell(COL_INDEX_NAME);
			cell.setCellValue(docData.getName(code));
			// 4月度
			cell = row.createCell(COL_INDEX_APL_VALUE);
			cell.setCellValue(docData.getAplPrice(code).doubleValue());
			aplPriceSum = aplPriceSum.add(docData.getAplPrice(code));
			// 5月度
			cell = row.createCell(COL_INDEX_MAY_VALUE);
			cell.setCellValue(docData.getMayPrice(code).doubleValue());
			mayPriceSum = mayPriceSum.add(docData.getMayPrice(code));
			// 6月度
			cell = row.createCell(COL_INDEX_JUN_VALUE);
			cell.setCellValue(docData.getJunPrice(code).doubleValue());
			junPriceSum = junPriceSum.add(docData.getJunPrice(code));
			// 7月度
			cell = row.createCell(COL_INDEX_JLY_VALUE);
			cell.setCellValue(docData.getJlyPrice(code).doubleValue());
			jlyPriceSum = jlyPriceSum.add(docData.getJlyPrice(code));
			// 8月度
			cell = row.createCell(COL_INDEX_AUG_VALUE);
			cell.setCellValue(docData.getAugPrice(code).doubleValue());
			augPriceSum = augPriceSum.add(docData.getAugPrice(code));
			// 9月度
			cell = row.createCell(COL_INDEX_SEP_VALUE);
			cell.setCellValue(docData.getSepPrice(code).doubleValue());
			sepPriceSum = sepPriceSum.add(docData.getSepPrice(code));
			// 10月度
			cell = row.createCell(COL_INDEX_OCT_VALUE);
			cell.setCellValue(docData.getOctPrice(code).doubleValue());
			octPriceSum = octPriceSum.add(docData.getOctPrice(code));
			// 11月度
			cell = row.createCell(COL_INDEX_NOV_VALUE);
			cell.setCellValue(docData.getNovPrice(code).doubleValue());
			novPriceSum = novPriceSum.add(docData.getNovPrice(code));
			// 12月度
			cell = row.createCell(COL_INDEX_DEC_VALUE);
			cell.setCellValue(docData.getDecPrice(code).doubleValue());
			decPriceSum = decPriceSum.add(docData.getDecPrice(code));
			// 1月度
			cell = row.createCell(COL_INDEX_JAN_VALUE);
			cell.setCellValue(docData.getJanPrice(code).doubleValue());
			janPriceSum = janPriceSum.add(docData.getJanPrice(code));
			// 2月度
			cell = row.createCell(COL_INDEX_FEB_VALUE);
			cell.setCellValue(docData.getFebPrice(code).doubleValue());
			febPriceSum = febPriceSum.add(docData.getFebPrice(code));
			// 3月度
			cell = row.createCell(COL_INDEX_MAR_VALUE);
			cell.setCellValue(docData.getMarPrice(code).doubleValue());
			marPriceSum = marPriceSum.add(docData.getMarPrice(code));
		}

		row = sheet.createRow(rowIndex++);
		cell = row.createCell(COL_INDEX_APL_VALUE);
		cell.setCellValue(aplPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_MAY_VALUE);
		cell.setCellValue(mayPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_JUN_VALUE);
		cell.setCellValue(junPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_JLY_VALUE);
		cell.setCellValue(jlyPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_AUG_VALUE);
		cell.setCellValue(augPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_SEP_VALUE);
		cell.setCellValue(sepPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_OCT_VALUE);
		cell.setCellValue(octPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_NOV_VALUE);
		cell.setCellValue(novPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_DEC_VALUE);
		cell.setCellValue(decPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_JAN_VALUE);
		cell.setCellValue(janPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_FEB_VALUE);
		cell.setCellValue(febPriceSum.doubleValue());

		cell = row.createCell(COL_INDEX_MAR_VALUE);
		cell.setCellValue(marPriceSum.doubleValue());
	}
}
