package book.sales.docwriter.urikakeBook;

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
 * 売掛台帳(取引先)(伝票)(年度)出力クラス
 *
 * @author shinyashiki
 *
 */
public class BookUrikakeBookVoucherYearDocWriter extends AbstractDocWriter {

	/** 列番号(伝票日付) **/
	private static final int COL_INDEX_VOUCHER_DATE = 0;
	/** 列番号(伝票番号) **/
	private static final int COL_INDEX_VOUCHER_NO = 1;
	/** 列番号(得意先分類) **/
	private static final int COL_INDEX_CLASS_NAME = 2;
	/** 列番号(ふりがな) **/
	private static final int COL_INDEX_KANA = 3;
	/** 列番号(得意先) **/
	private static final int COL_INDEX_NAME = 4;
	/** 列番号(税抜金額) **/
	private static final int COL_INDEX_TAX_EXCLUDED_AMOUNT = 5;
	/** 列番号(消費税額) **/
	private static final int COL_INDEX_TAX = 6;
	/** 列番号(調整額) **/
	private static final int COL_INDEX_DISCOUNT = 7;
	/** 列番号(売掛金額) **/
	private static final int COL_INDEX_SALES_PRICE = 8;
	/** 列番号(売掛区分) **/
	private static final int COL_INDEX_DEAL_DIVISION = 9;
	/** 列番号(担当者) **/
	private static final int COL_INDEX_STAFF = 10;

	public BookUrikakeBookVoucherYearDocWriter(DocInfo docInfo) {
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
			BookUrikakeBookDocInfo bookUrikakeBookDocInfo = (BookUrikakeBookDocInfo)docInfo;

			in = new BufferedInputStream(new FileInputStream(new File(bookUrikakeBookDocInfo.getFormName())));
			Workbook book = new HSSFWorkbook(in);

			for (int i = 0; i < book.getNumberOfSheets(); i++) {
				book.getSheetAt(i).setSelected(false);
			}

			Sheet sheet = book.getSheet(bookUrikakeBookDocInfo.getSheet());
			book.setActiveSheet(bookUrikakeBookDocInfo.getActiveSheet());
			sheet.setSelected(true);
			setData(sheet, (BookUrikakeBookDocData)bookUrikakeBookDocInfo.getDocData());
			os = new BufferedOutputStream(new FileOutputStream(bookUrikakeBookDocInfo.getOutFileName()));
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
	private void setData(Sheet sheet, BookUrikakeBookDocData docData) {

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
			// 得意先分類
			cell = row.createCell(COL_INDEX_CLASS_NAME);
			cell.setCellValue(docData.getClassName(index));
			// ふりがな
			cell = row.createCell(COL_INDEX_KANA);
			cell.setCellValue(docData.getKana(index));
			// 得意先
			cell = row.createCell(COL_INDEX_NAME);
			cell.setCellValue(docData.getName(index));
			// 税抜金額
			cell = row.createCell(COL_INDEX_TAX_EXCLUDED_AMOUNT);
			cell.setCellValue(docData.getTaxExcludedAmount(index).doubleValue());
			// 消費税額
			cell = row.createCell(COL_INDEX_TAX);
			cell.setCellValue(docData.getTax(index).doubleValue());
			// 調整額
			cell = row.createCell(COL_INDEX_DISCOUNT);
			cell.setCellValue(docData.getDiscount(index).doubleValue());
			// 売掛金額
			cell = row.createCell(COL_INDEX_SALES_PRICE);
			cell.setCellValue(docData.getSalesPrice(index).doubleValue());
			// 売掛区分
			cell = row.createCell(COL_INDEX_DEAL_DIVISION);
			cell.setCellValue(docData.getDealDivision(index));
			// 担当者
			cell = row.createCell(COL_INDEX_STAFF);
			cell.setCellValue(docData.getStaff(index));
		}

	}
}
