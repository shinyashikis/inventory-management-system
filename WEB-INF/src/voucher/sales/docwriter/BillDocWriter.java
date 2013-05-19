package voucher.sales.docwriter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import prop.ViewProperties;
import voucher.common.docwriter.DealDocData;
import voucher.common.docwriter.DetailDocData;
import voucher.common.docwriter.VoucherDocData;

import fw.common.docwriter.AbstractDocWriter;
import fw.common.docwriter.DocInfo;
import fw.common.docwriter.DocWriterException;
import fw.common.util.CommonConstants;

/**
 * <p>都度請求書文書ファイル作成クラス</p>
 */
public class BillDocWriter extends AbstractDocWriter {

	/** 行番号(売上伝票番号) **/
	private static final int ROW_INDEX_VOUCHER_NO = 2;
	/** 行番号(日付) **/
	private static final int ROW_INDEX_DATE = 3;
	/** 行番号(郵便番号) **/
	private static final int ROW_INDEX_POST_CODE = 4;
	/** 行番号(住所1) **/
	private static final int ROW_INDEX_ADDR1 = 5;
	/** 行番号(住所2) **/
	private static final int ROW_INDEX_ADDR2 = 6;
	/** 行番号(取引先名) **/
	private static final int ROW_INDEX_DEAL_NAME = 8;
	/** 行番号(取引先部署) **/
	private static final int ROW_INDEX_DEAL_BUSYO = 9;
	/** 行番号(取引先担当者) **/
	private static final int ROW_INDEX_DEAL_STAFF = 10;
	/** 行番号(自社名) **/
	private static final int ROW_INDEX_MY_NAME = 8;
	/** 行番号(郵便番号) **/
	private static final int ROW_INDEX_MY_POST_CODE = 9;
	/** 行番号(住所1) **/
	private static final int ROW_INDEX_MY_ADDR1 = 10;
	/** 行番号(住所2) **/
	private static final int ROW_INDEX_MY_ADDR2 = 11;
	/** 行番号(TEL) **/
	private static final int ROW_INDEX_MY_TEL = 12;
	/** 行番号(FAX) **/
	private static final int ROW_INDEX_MY_FAX = 12;
	/** 行番号(部署 担当者) **/
	private static final int ROW_INDEX_MY_STAFF = 13;
	/** 行番号(取引銀行) **/
	private static final int ROW_INDEX_MY_BANK = 14;
	/** 行番号(口座番号) **/
	private static final int ROW_INDEX_MY_ACCOUNT_NO = 15;
	/** 行番号(口座名) **/
	private static final int ROW_INDEX_MY_ACCOUNT_NAME = 17;
	/** 行番号(御請求額) **/
	private static final int ROW_INDEX_AMOUNT = 17;
	/** 行番号(明細の最初の行) **/
	private static final int ROW_INDEX_FIRST_DETAILS = 21;

	/** 列番号(売上伝票番号) **/
	private static final int COL_INDEX_VOUCHER_NO = 21;
	/** 列番号(日付) **/
	private static final int COL_INDEX_DATE = 20;
	/** 列番号(郵便番号) **/
	private static final int COL_INDEX_POST_CODE = 0;
	/** 列番号(住所1) **/
	private static final int COL_INDEX_ADDR1 = 0;
	/** 列番号(住所2) **/
	private static final int COL_INDEX_ADDR2 = 0;
	/** 列番号(取引先名) **/
	private static final int COL_INDEX_DEAL_NAME = 0;
	/** 列番号(取引先部署) **/
	private static final int COL_INDEX_DEAL_BUSYO = 1;
	/** 列番号(取引先担当者) **/
	private static final int COL_INDEX_DEAL_STAFF = 1;
	/** 列番号(自社名) **/
	private static final int COL_INDEX_MY_NAME = 15;
	/** 列番号(郵便番号) **/
	private static final int COL_INDEX_MY_POST_CODE = 15;
	/** 列番号(住所1) **/
	private static final int COL_INDEX_MY_ADDR1 = 15;
	/** 列番号(住所2) **/
	private static final int COL_INDEX_MY_ADDR2 = 15;
	/** 列番号(TEL) **/
	private static final int COL_INDEX_MY_TEL = 16;
	/** 列番号(FAX) **/
	private static final int COL_INDEX_MY_FAX = 20;
	/** 列番号(部署 担当者) **/
	private static final int COL_INDEX_MY_STAFF = 20;
	/** 列番号(取引銀行) **/
	private static final int COL_INDEX_MY_BANK = 18;
	/** 列番号(口座番号) **/
	private static final int COL_INDEX_MY_ACCOUNT_NO = 18;
	/** 列番号(口座名) **/
	private static final int COL_INDEX_MY_ACCOUNT_NAME = 18;
	/** 列番号(御請求額) **/
	private static final int COL_INDEX_AMOUNT = 5;

	/** 列番号(商品名の最初の列) **/
	private static final int COL_INDEX_FIRST_ITEM_NAME = 0;
	/** 列番号(商品名の最後の列) **/
	private static final int COL_INDEX_LAST_ITEM_NAME = 6;
	/** 列番号(規格・仕様の最初の列) **/
	private static final int COL_INDEX_FIRST_ITEM_KIKAKU = 7;
	/** 列番号(規格・仕様の最後の列) **/
	private static final int COL_INDEX_LAST_ITEM_KIKAKU = 10;
	/** 列番号(数量の最初の列) **/
	private static final int COL_INDEX_FIRST_ITEM_COUNT = 11;
	/** 列番号(数量の最後の列) **/
	private static final int COL_INDEX_LAST_ITEM_COUNT = 12;
	/** 列番号(単位の最初の列) **/
	private static final int COL_INDEX_FIRST_ITEM_UNIT = 13;
	/** 列番号(単位の最後の列) **/
	private static final int COL_INDEX_LAST_ITEM_UNIT = 14;
	/** 列番号(単価の最初の列) **/
	private static final int COL_INDEX_FIRST_ITEM_UNIT_PRICE = 15;
	/** 列番号(単価の最後の列) **/
	private static final int COL_INDEX_LAST_ITEM_UNIT_PRICE = 17;
	/** 列番号(金額の最初の列) **/
	private static final int COL_INDEX_FIRST_ITEM_PRICE = 18;
	/** 列番号(金額の最後の列) **/
	private static final int COL_INDEX_LAST_ITEM_PRICE = 20;
	/** 列番号(摘要の最初の列) **/
	private static final int COL_INDEX_FIRST_ITEM_MEMO = 21;
	/** 列番号(摘要の最後の列) **/
	private static final int COL_INDEX_LAST_ITEM_MEMO = 24;
	/** 列番号(明細フッター1の最初の列) **/
	private static final int COL_INDEX_FIRST_DETAILS_FOOTER1 = 0;
	/** 列番号(明細フッター1の最後の列) **/
	private static final int COL_INDEX_LAST_DETAILS_FOOTER1 = 14;
	/** 列番号(明細フッター2の最初の列) **/
	private static final int COL_INDEX_FIRST_DETAILS_FOOTER2 = 15;
	/** 列番号(明細フッター2の最後の列) **/
	private static final int COL_INDEX_LAST_DETAILS_FOOTER2 = 17;
	/** 列番号(明細フッター3の最初の列) **/
	private static final int COL_INDEX_FIRST_DETAILS_FOOTER3 = 18;
	/** 列番号(明細フッター3の最後の列) **/
	private static final int COL_INDEX_LAST_DETAILS_FOOTER3 = 20;
	/** 列番号(明細フッター4の最初の列) **/
	private static final int COL_INDEX_FIRST_DETAILS_FOOTER4 = 21;
	/** 列番号(明細フッター4の最後の列) **/
	private static final int COL_INDEX_LAST_DETAILS_FOOTER4 = 24;
	/** 列番号(摘要の最初の列) **/
	private static final int COL_INDEX_FIRST_MEMO_LABEL = 0;
	/** 列番号(摘要の最後の列) **/
	private static final int COL_INDEX_LAST_MEMO_LABEL = 1;
	/** 列番号(摘要の最初の列) **/
	private static final int COL_INDEX_FIRST_MEMO_VALUE = 2;
	/** 列番号(摘要の最後の列) **/
	private static final int COL_INDEX_LAST_MEMO_VALUE = 24;

	/** 一ページ最大明細行 */
	private static final int MAX_DETAILS_ROW = 25;

	/**
	 * <p>コンストラクタ</p>
	 * @param docInfo
	 */
	public BillDocWriter(DocInfo docInfo) {
		super(docInfo);
	}

	/**
	 * <p>都度請求書文書作成処理</p>
	 *
	 * 都度請求書文書ファイル(エクセル)を作成する。
	 *
	 * @throws DocFileWriterException
	 */
	@Override
	public void write() throws DocWriterException {
		OutputStream os = null;
		InputStream in = null;
		try {
			BillDocInfo billDocInfo = (BillDocInfo)docInfo;

			in = new BufferedInputStream(new FileInputStream(new File(billDocInfo.getFormName())));
			Workbook book = new HSSFWorkbook(in);
			Sheet sheet = book.getSheet(billDocInfo.getSheet());

			// 文書データ設定
			VoucherDocData voucherDocData = (VoucherDocData)billDocInfo.getDocData();
			setVoucher(sheet, voucherDocData);
			setDeal(sheet, voucherDocData.getDeal());
			setMyCompany(sheet, voucherDocData);
			int detailRowIndex = setDetail(book, sheet, voucherDocData.getDetails());
			setDetailFooter(book, sheet, voucherDocData, detailRowIndex);

			// 文書出力
			os = new BufferedOutputStream(new FileOutputStream(billDocInfo.getOutFileName()));
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
	 * 伝票情報設定
	 *
	 * @param sheet
	 * @param voucherData
	 */
	private void setVoucher(Sheet sheet, VoucherDocData voucherData) {

		// 見積番号
		Row row = sheet.getRow(ROW_INDEX_VOUCHER_NO);
		Cell cell = row.getCell(COL_INDEX_VOUCHER_NO);
		cell.setCellValue(voucherData.getVoucherNo());

		// 日付
		row = sheet.getRow(ROW_INDEX_DATE);
		cell = row.getCell(COL_INDEX_DATE);
		cell.setCellValue(voucherData.getVoucherDate());

		// 御請求額
		row = sheet.getRow(ROW_INDEX_AMOUNT);
		cell = row.getCell(COL_INDEX_AMOUNT);
		cell.setCellValue(Double.parseDouble(voucherData.getAmount()));
	}

	/**
	 * 取引先情報設定
	 *
	 * @param sheet
	 * @param dealDocData
	 */
	private void setDeal(Sheet sheet, DealDocData dealDocData) {

		// 郵便番号
		Row row = sheet.getRow(ROW_INDEX_POST_CODE);
		Cell cell = row.getCell(COL_INDEX_POST_CODE);
		cell.setCellValue(new StringBuilder(CommonConstants.POST_MARK).append(
				dealDocData.getPostCode()).toString());

		// 住所
		row = sheet.getRow(ROW_INDEX_ADDR1);
		cell = row.getCell(COL_INDEX_ADDR1);
		cell.setCellValue(dealDocData.getAddr1());
		row = sheet.getRow(ROW_INDEX_ADDR2);
		cell = row.getCell(COL_INDEX_ADDR2);
		cell.setCellValue(dealDocData.getAddr2());

		// 取引先名
		row = sheet.getRow(ROW_INDEX_DEAL_NAME);
		cell = row.getCell(COL_INDEX_DEAL_NAME);
		cell.setCellValue(new StringBuilder().append(
				dealDocData.getDealName()).append(
						CommonConstants.DOUBLE_SPACE).append(
								ViewProperties.getInstance().getValue(
										ViewProperties.KEISYO_ONCYU, ViewProperties.DISP_VALUE)).toString());

		// 取引先部署
		row = sheet.getRow(ROW_INDEX_DEAL_BUSYO);
		cell = row.getCell(COL_INDEX_DEAL_BUSYO);
		cell.setCellValue(new StringBuilder().append(
				dealDocData.getDealBusyo()).append(
						CommonConstants.DOUBLE_SPACE).append(
								ViewProperties.getInstance().getValue(
										ViewProperties.KEISYO_ONCYU, ViewProperties.DISP_VALUE)).toString());

		// 取引先担当者
		row = sheet.getRow(ROW_INDEX_DEAL_STAFF);
		cell = row.getCell(COL_INDEX_DEAL_STAFF);
		cell.setCellValue(new StringBuilder().append(
				dealDocData.getDealStaff())
				.append(CommonConstants.DOUBLE_SPACE)
				.append(dealDocData.getDealStaffKeisyo()).toString());
	}

	/**
	 * 自社情報設定
	 *
	 * @param sheet
	 * @param voucher
	 */
	private void setMyCompany(Sheet sheet, VoucherDocData voucherDocData) {

		// 自社名
		Row row = sheet.getRow(ROW_INDEX_MY_NAME);
		Cell cell = row.getCell(COL_INDEX_MY_NAME);
		cell.setCellValue(voucherDocData.getName());

		// 郵便番号
		row = sheet.getRow(ROW_INDEX_MY_POST_CODE);
		cell = row.getCell(COL_INDEX_MY_POST_CODE);
		cell.setCellValue(
				new StringBuilder(CommonConstants.POST_MARK).append(
						voucherDocData.getPostCode1()).append("-").append(voucherDocData.getPostCode2()).toString());

		// 住所
		row = sheet.getRow(ROW_INDEX_MY_ADDR1);
		cell = row.getCell(COL_INDEX_MY_ADDR1);
		cell.setCellValue(voucherDocData.getAddr1());
		row = sheet.getRow(ROW_INDEX_MY_ADDR2);
		cell = row.getCell(COL_INDEX_MY_ADDR2);
		cell.setCellValue(voucherDocData.getAddr2());

		// TEL
		row = sheet.getRow(ROW_INDEX_MY_TEL);
		cell = row.getCell(COL_INDEX_MY_TEL);
		cell.setCellValue(voucherDocData.getTel());

		// FAX
		row = sheet.getRow(ROW_INDEX_MY_FAX);
		cell = row.getCell(COL_INDEX_MY_FAX);
		cell.setCellValue(voucherDocData.getFax());

		// 部署 担当者
		row = sheet.getRow(ROW_INDEX_MY_STAFF);
		cell = row.getCell(COL_INDEX_MY_STAFF);
		cell.setCellValue(new StringBuilder().append(
				voucherDocData.getDeal().getBusyo()).append(
						CommonConstants.DOUBLE_SPACE).append(voucherDocData.getDeal().getStaff()).toString());

		// 取引銀行
		row = sheet.getRow(ROW_INDEX_MY_BANK);
		cell = row.getCell(COL_INDEX_MY_BANK);
		cell.setCellValue(new StringBuilder().append(
				voucherDocData.getBank()).append(
						CommonConstants.DOUBLE_SPACE).append(voucherDocData.getBranch()).toString());

		// 口座番号
		row = sheet.getRow(ROW_INDEX_MY_ACCOUNT_NO);
		cell = row.getCell(COL_INDEX_MY_ACCOUNT_NO);
		cell.setCellValue(voucherDocData.getAccountNo());

		// 口座名
		row = sheet.getRow(ROW_INDEX_MY_ACCOUNT_NAME);
		cell = row.getCell(COL_INDEX_MY_ACCOUNT_NAME);
		cell.setCellValue(voucherDocData.getAccountName());
	}

	/**
	 * 明細情報設定
	 *
	 * @param book
	 * @param sheet
	 * @param detailDocDataList
	 * @return
	 */
	private int setDetail(Workbook book, Sheet sheet, List<DetailDocData> detailDocDataList) {

		Row row;

		CellStyle cellStyle = book.createCellStyle();
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);

		CellStyle priceCellStyle = book.createCellStyle();
		priceCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		priceCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		priceCellStyle.setBorderRight(CellStyle.BORDER_THIN);
		priceCellStyle.setBorderTop(CellStyle.BORDER_THIN);
		priceCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

		int rowCnt = 1;
		int rowIndex = ROW_INDEX_FIRST_DETAILS;

		for (DetailDocData detailDocData : detailDocDataList) {

			if (rowCnt++ == MAX_DETAILS_ROW) {
				// 明細25行で改ページ
				sheet.setRowBreak(rowIndex);
			}

			// 行作成
			row = sheet.createRow(rowIndex);

			// 商品名
			createCell(row, cellStyle, COL_INDEX_FIRST_ITEM_NAME, detailDocData.getItemName());
			createCell(row, cellStyle, COL_INDEX_LAST_ITEM_NAME, null);
			sheet.addMergedRegion(new CellRangeAddress(
					rowIndex, rowIndex, COL_INDEX_FIRST_ITEM_NAME, COL_INDEX_LAST_ITEM_NAME));

			// 規格・仕様
			createCell(row, cellStyle, COL_INDEX_FIRST_ITEM_KIKAKU, detailDocData.getItemKikaku());
			createCell(row, cellStyle, COL_INDEX_LAST_ITEM_KIKAKU, null);
			sheet.addMergedRegion(new CellRangeAddress(
					rowIndex, rowIndex, COL_INDEX_FIRST_ITEM_KIKAKU, COL_INDEX_LAST_ITEM_KIKAKU));

			// 数量
			createCell(row, cellStyle, COL_INDEX_FIRST_ITEM_COUNT, Double.valueOf(detailDocData.getItemCount()));
			createCell(row, cellStyle, COL_INDEX_LAST_ITEM_COUNT, null);
			sheet.addMergedRegion(new CellRangeAddress(
					rowIndex, rowIndex, COL_INDEX_FIRST_ITEM_COUNT, COL_INDEX_LAST_ITEM_COUNT));

			// 単位
			createCell(row, cellStyle, COL_INDEX_FIRST_ITEM_UNIT, detailDocData.getItemUnit());
			createCell(row, cellStyle, COL_INDEX_LAST_ITEM_UNIT, null);
			sheet.addMergedRegion(new CellRangeAddress(
					rowIndex, rowIndex, COL_INDEX_FIRST_ITEM_UNIT, COL_INDEX_LAST_ITEM_UNIT));

			// 単価
			createCell(row, priceCellStyle, COL_INDEX_FIRST_ITEM_UNIT_PRICE, Double.valueOf(detailDocData.getItemUnitPrice()));
			createCell(row, priceCellStyle, COL_INDEX_LAST_ITEM_UNIT_PRICE, null);
			sheet.addMergedRegion(new CellRangeAddress(
					rowIndex, rowIndex, COL_INDEX_FIRST_ITEM_UNIT_PRICE, COL_INDEX_LAST_ITEM_UNIT_PRICE));

			// 金額
			createCell(row, priceCellStyle, COL_INDEX_FIRST_ITEM_PRICE, Double.valueOf(detailDocData.getItemPrice()));
			createCell(row, priceCellStyle, COL_INDEX_LAST_ITEM_PRICE, null);
			sheet.addMergedRegion(new CellRangeAddress(
					rowIndex, rowIndex, COL_INDEX_FIRST_ITEM_PRICE, COL_INDEX_LAST_ITEM_PRICE));

			// 摘要
			createCell(row, cellStyle, COL_INDEX_FIRST_ITEM_MEMO, detailDocData.getItemMemo());
			createCell(row, cellStyle, COL_INDEX_LAST_ITEM_MEMO, null);
			sheet.addMergedRegion(new CellRangeAddress(
					rowIndex, rowIndex, COL_INDEX_FIRST_ITEM_MEMO, COL_INDEX_LAST_ITEM_MEMO));

			rowIndex++;
		}

		return rowIndex;
	}

	/**
	 * 明細情報(フッター)設定
	 *
	 * @param book
	 * @param sheet
	 * @param voucherDocData
	 * @param detailRowIndex
	 */
	private void setDetailFooter(Workbook book, Sheet sheet, VoucherDocData voucherDocData, int detailRowIndex) {

		// セルスタイル作成
		CellStyle cellStyle = book.createCellStyle();
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

		int syokei = detailRowIndex;
		int tax = syokei + 1;
		int discount = tax + 1;
		int sum = discount + 1;
		int memo = sum + 1;

		// 小計
		createDetailFooterCell(sheet, new int[]{syokei,syokei}, cellStyle, "小計", Double.parseDouble(voucherDocData.getTaxExcludedAmount()));
		// 消費税
		createDetailFooterCell(sheet, new int[]{tax, tax}, cellStyle, "消費税", Double.parseDouble(voucherDocData.getTax()));
		// 調整額
		createDetailFooterCell(sheet, new int[]{discount, discount}, cellStyle, "調整額", Double.parseDouble(voucherDocData.getDiscount()));
		// 合計金額
		createDetailFooterCell(sheet, new int[]{sum, sum}, cellStyle, "合計金額", Double.parseDouble(voucherDocData.getAmount()));

		/*
		 * 摘要
		 */
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);

		Row row = sheet.createRow(memo);
		createCell(row, cellStyle, COL_INDEX_FIRST_MEMO_LABEL, "備考：");
		createCell(row, cellStyle, COL_INDEX_LAST_MEMO_LABEL, null);
		createCell(row, cellStyle, COL_INDEX_FIRST_MEMO_VALUE, voucherDocData.getMemo());
		createCell(row, cellStyle, COL_INDEX_LAST_MEMO_VALUE, null);

		row = sheet.createRow(memo + 1);
		createCell(row, cellStyle, COL_INDEX_FIRST_MEMO_LABEL, null);
		createCell(row, cellStyle, COL_INDEX_LAST_MEMO_LABEL, null);
		createCell(row, cellStyle, COL_INDEX_FIRST_MEMO_VALUE, null);
		createCell(row, cellStyle, COL_INDEX_LAST_MEMO_VALUE, null);
		sheet.addMergedRegion(new CellRangeAddress(memo, memo + 1, COL_INDEX_FIRST_MEMO_LABEL, COL_INDEX_LAST_MEMO_LABEL));
		sheet.addMergedRegion(new CellRangeAddress(memo, memo + 1, COL_INDEX_FIRST_MEMO_VALUE, COL_INDEX_LAST_MEMO_VALUE));
	}

	/**
	 * 明細フッタセル作成
	 *
	 * @param sheet
	 * @param rowIndex
	 * @param cellStyle
	 * @param label
	 * @param value
	 */
	private void createDetailFooterCell(
			Sheet sheet, int[] rowIndex, CellStyle cellStyle, String label, Object value) {
		Row row = sheet.createRow(rowIndex[0]);
		createCell(row, cellStyle, COL_INDEX_FIRST_DETAILS_FOOTER1, null);
		createCell(row, cellStyle, COL_INDEX_LAST_DETAILS_FOOTER1, null);
		sheet.addMergedRegion(new CellRangeAddress(
				rowIndex[0], rowIndex[1], COL_INDEX_FIRST_DETAILS_FOOTER1, COL_INDEX_LAST_DETAILS_FOOTER1));

		createCell(row, cellStyle, COL_INDEX_FIRST_DETAILS_FOOTER2, label);
		createCell(row, cellStyle, COL_INDEX_LAST_DETAILS_FOOTER2, null);
		sheet.addMergedRegion(new CellRangeAddress(
				rowIndex[0], rowIndex[1], COL_INDEX_FIRST_DETAILS_FOOTER2, COL_INDEX_LAST_DETAILS_FOOTER2));

		createCell(row, cellStyle, COL_INDEX_FIRST_DETAILS_FOOTER3, value);
		createCell(row, cellStyle, COL_INDEX_LAST_DETAILS_FOOTER3, null);
		sheet.addMergedRegion(new CellRangeAddress(
				rowIndex[0], rowIndex[1], COL_INDEX_FIRST_DETAILS_FOOTER3, COL_INDEX_LAST_DETAILS_FOOTER3));

		createCell(row, cellStyle, COL_INDEX_FIRST_DETAILS_FOOTER4, null);
		createCell(row, cellStyle, COL_INDEX_LAST_DETAILS_FOOTER4, null);
		sheet.addMergedRegion(new CellRangeAddress(
				rowIndex[0], rowIndex[1], COL_INDEX_FIRST_DETAILS_FOOTER4, COL_INDEX_LAST_DETAILS_FOOTER4));
	}

}
