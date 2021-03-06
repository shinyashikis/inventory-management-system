package book.purchase.docwriter.purchaseTotal;

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
 * 仕入集計表(取引先)(日付)出力クラス
 *
 * @author shinyashiki
 *
 */
public class BookPurchaseTotalDateDocWriter extends AbstractDocWriter {

	/** 列番号(ふりがな) **/
	private static final int COL_INDEX_KANA = 0;
	/** 列番号(仕入先) **/
	private static final int COL_INDEX_NAME = 1;
	/** 列番号(税抜金額) **/
	private static final int COL_INDEX_TAX_EXCLUDED_AMOUNT = 2;
	/** 列番号(消費税額) **/
	private static final int COL_INDEX_TAX = 3;
	/** 列番号(調整額) **/
	private static final int COL_INDEX_DISCOUNT = 4;
	/** 列番号(仕入合計) **/
	private static final int COL_INDEX_TAX_INCLUDED_AMOUNT = 5;

	public BookPurchaseTotalDateDocWriter(DocInfo docInfo) {
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
			BookPurchaseTotalDocInfo bookPurchaseTotalDocInfo = (BookPurchaseTotalDocInfo)docInfo;

			in = new BufferedInputStream(new FileInputStream(new File(bookPurchaseTotalDocInfo.getFormName())));
			Workbook book = new HSSFWorkbook(in);

			for (int i = 0; i < book.getNumberOfSheets(); i++) {
				book.getSheetAt(i).setSelected(false);
			}

			Sheet sheet = book.getSheet(bookPurchaseTotalDocInfo.getSheet());
			book.setActiveSheet(bookPurchaseTotalDocInfo.getActiveSheet());
			sheet.setSelected(true);
			setData(sheet, (BookPurchaseTotalDocData)bookPurchaseTotalDocInfo.getDocData());
			os = new BufferedOutputStream(new FileOutputStream(bookPurchaseTotalDocInfo.getOutFileName()));
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
	private void setData(Sheet sheet, BookPurchaseTotalDocData docData) {

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

		BigDecimal sumTaxExcludedAmount = new BigDecimal(0);
		BigDecimal sumTax = new BigDecimal(0);
		BigDecimal sumDiscount = new BigDecimal(0);
		BigDecimal sumTaxIncludedAmount = new BigDecimal(0);

		int rowIndex = 3;

		Iterator<String> itr = docData.getRowDataMap().keySet().iterator();
		while (itr.hasNext()) {
			row = sheet.createRow(rowIndex++);

			// 仕入先コード
			String code = itr.next();

			// ふりがな
			cell = row.createCell(COL_INDEX_KANA);
			cell.setCellValue(docData.getKana(code));
			// 仕入先
			cell = row.createCell(COL_INDEX_NAME);
			cell.setCellValue(docData.getName(code));
			// 税抜金額
			cell = row.createCell(COL_INDEX_TAX_EXCLUDED_AMOUNT);
			cell.setCellValue(docData.getTaxExcludedAmount(code).doubleValue());
			sumTaxExcludedAmount = sumTaxExcludedAmount.add(docData.getTaxExcludedAmount(code));
			// 消費税額
			cell = row.createCell(COL_INDEX_TAX);
			cell.setCellValue(docData.getTax(code).doubleValue());
			sumTax = sumTax.add(docData.getTax(code));
			// 調整額
			cell = row.createCell(COL_INDEX_DISCOUNT);
			cell.setCellValue(docData.getDiscount(code).doubleValue());
			sumDiscount = sumDiscount.add(docData.getDiscount(code));
			// 仕入合計
			cell = row.createCell(COL_INDEX_TAX_INCLUDED_AMOUNT);
			cell.setCellValue(docData.getTaxIncludedAmount(code).doubleValue());
			sumTaxIncludedAmount = sumTaxIncludedAmount.add(docData.getTaxIncludedAmount(code));
		}

		row = sheet.createRow(rowIndex++);

		cell = row.createCell(COL_INDEX_TAX_EXCLUDED_AMOUNT);
		cell.setCellValue(sumTaxExcludedAmount.doubleValue());

		cell = row.createCell(COL_INDEX_TAX);
		cell.setCellValue(sumTax.doubleValue());

		cell = row.createCell(COL_INDEX_DISCOUNT);
		cell.setCellValue(sumDiscount.doubleValue());

		cell = row.createCell(COL_INDEX_TAX_INCLUDED_AMOUNT);
		cell.setCellValue(sumTaxIncludedAmount.doubleValue());
	}
}
