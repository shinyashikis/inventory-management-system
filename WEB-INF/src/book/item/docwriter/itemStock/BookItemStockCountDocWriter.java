package book.item.docwriter.itemStock;

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

import book.item.bean.BookItemStock;

import fw.common.date.DateFormatPattern;
import fw.common.date.DateUtility;
import fw.common.docwriter.AbstractDocWriter;
import fw.common.docwriter.DocInfo;
import fw.common.docwriter.DocWriterException;

public class BookItemStockCountDocWriter extends AbstractDocWriter {

	public BookItemStockCountDocWriter(DocInfo docInfo) {
		super(docInfo);
	}

	@Override
	public void write() throws DocWriterException {
		OutputStream os = null;
		InputStream in = null;

		try {
			BookItemStockDocInfo bookItemDocInfo = (BookItemStockDocInfo)docInfo;

			in = new BufferedInputStream(new FileInputStream(new File(bookItemDocInfo.getFormName())));
			Workbook book = new HSSFWorkbook(in);

			for (int i = 0; i < book.getNumberOfSheets(); i++) {
				book.getSheetAt(i).setSelected(false);
			}

			Sheet sheet = book.getSheet(bookItemDocInfo.getSheet());
			book.setActiveSheet(bookItemDocInfo.getActiveSheet());
			sheet.setSelected(true);
			setData(sheet, (BookItemStockDocData)bookItemDocInfo.getDocData());
			os = new BufferedOutputStream(new FileOutputStream(bookItemDocInfo.getOutFileName()));
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

	private void setData(Sheet sheet, BookItemStockDocData docData) {
		// 現在日付
		Row row = sheet.createRow(1);
		Cell cell = row.createCell(0);
		cell.setCellValue(DateUtility.getSysDate(DateFormatPattern.PATTERN_YYYYMMDD_HYPHEN));

		int rowIndex = 3;
		for (BookItemStock bookItemStock : docData.getBookItemStockList()) {
			row = sheet.createRow(rowIndex++);
			// 商品分類
			cell = row.createCell(0);
			cell.setCellValue(bookItemStock.getClassName());
			// ふりがな
			cell = row.createCell(1);
			cell.setCellValue(bookItemStock.getKana());
			// 商品名
			cell = row.createCell(2);
			cell.setCellValue(bookItemStock.getName());
			// 在庫数量
			cell = row.createCell(3);
			cell.setCellValue(bookItemStock.getStock());
			// 適正在庫数量
			cell = row.createCell(4);
			cell.setCellValue(bookItemStock.getProperStock());
			// 過不足
			cell = row.createCell(5);
			cell.setCellValue(bookItemStock.getKabusoku());
		}
	}
}
