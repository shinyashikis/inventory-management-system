package book.voucher.docwriter;

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
 * 見積書出力クラス
 *
 * @author shinyashiki
 *
 */
public class BookQuotationDocWriter extends AbstractDocWriter {

	/** 列番号(伝票日付) **/
	private static final int COL_INDEX_VOUCHER_DATE = 0;
	/** 列番号(伝票番号) **/
	private static final int COL_INDEX_VOUCHER_NO = 1;
	/** 列番号(コード) **/
	private static final int COL_INDEX_CODE = 2;
	/** 列番号(得意先名) **/
	private static final int COL_INDEX_NAME = 3;
	/** 列番号(担当者名) **/
	private static final int COL_INDEX_STAFF = 4;
	/** 列番号(税抜金額) **/
	private static final int COL_INDEX_TAX_EXCLUDED_AMOUNT = 5;
	/** 列番号(消費税額) **/
	private static final int COL_INDEX_TAX = 6;
	/** 列番号(税込金額) **/
	private static final int COL_INDEX_TAX_INCLUDED_AMOUNT = 7;
	/** 列番号(調整額) **/
	private static final int COL_INDEX_DISCOUNT = 8;
	/** 列番号(見積金額) **/
	private static final int COL_INDEX_ALL_PRICE = 9;

	public BookQuotationDocWriter(DocInfo docInfo) {
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
			BookQuotationDocInfo bookQuotationDocInfo = (BookQuotationDocInfo)docInfo;

			in = new BufferedInputStream(new FileInputStream(new File(bookQuotationDocInfo.getFormName())));
			Workbook book = new HSSFWorkbook(in);

			for (int i = 0; i < book.getNumberOfSheets(); i++) {
				book.getSheetAt(i).setSelected(false);
			}

			Sheet sheet = book.getSheet(bookQuotationDocInfo.getSheet());
			book.setActiveSheet(bookQuotationDocInfo.getActiveSheet());
			sheet.setSelected(true);
			setData(sheet, (BookQuotationDocData)bookQuotationDocInfo.getDocData());
			os = new BufferedOutputStream(new FileOutputStream(bookQuotationDocInfo.getOutFileName()));
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
	private void setData(Sheet sheet, BookQuotationDocData docData) {

		// 年度(from ～ to)
		Row row = sheet.createRow(1);
		Cell cell = row.createCell(0);
		StringBuilder sb = new StringBuilder()
			.append("対象検索範囲：")
			.append(docData.getFrom())
			.append(" ～ ")
			.append(docData.getTo());
		cell.setCellValue(sb.toString());

		int rowIndex = 3;

		Iterator<String> itr = docData.getRowDataMap().keySet().iterator();
		while (itr.hasNext()) {
			row = sheet.createRow(rowIndex++);

			// 伝票番号
			String key = itr.next();

			// 伝票日付
			cell = row.createCell(COL_INDEX_VOUCHER_DATE);
			cell.setCellValue(docData.getVoucherDate(key));
			// 伝票番号
			cell = row.createCell(COL_INDEX_VOUCHER_NO);
			cell.setCellValue(docData.getVoucherNo(key));
			// コード
			cell = row.createCell(COL_INDEX_CODE);
			cell.setCellValue(docData.getCode(key));
			// 得意先名
			cell = row.createCell(COL_INDEX_NAME);
			cell.setCellValue(docData.getName(key));
			// 担当者名
			cell = row.createCell(COL_INDEX_STAFF);
			cell.setCellValue(docData.getStaff(key));
			// 税抜金額
			cell = row.createCell(COL_INDEX_TAX_EXCLUDED_AMOUNT);
			cell.setCellValue(docData.getTaxExcludedAmount(key).doubleValue());
			// 消費税額
			cell = row.createCell(COL_INDEX_TAX);
			cell.setCellValue(docData.getTax(key).doubleValue());
			// 税込金額
			cell = row.createCell(COL_INDEX_TAX_INCLUDED_AMOUNT);
			cell.setCellValue(docData.getTaxIncludedAmount(key).doubleValue());
			// 調整額
			cell = row.createCell(COL_INDEX_DISCOUNT);
			cell.setCellValue(docData.getDiscount(key).doubleValue());
			// 見積金額
			cell = row.createCell(COL_INDEX_ALL_PRICE);
			cell.setCellValue(docData.getAllPrice(key).doubleValue());
		}
	}
}
