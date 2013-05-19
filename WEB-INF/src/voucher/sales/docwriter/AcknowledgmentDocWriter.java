package voucher.sales.docwriter;

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

import prop.ViewProperties;
import voucher.common.docwriter.DealDocData;
import voucher.common.docwriter.VoucherDocData;

import fw.common.docwriter.AbstractDocWriter;
import fw.common.docwriter.DocInfo;
import fw.common.docwriter.DocWriterException;
import fw.common.util.CommonConstants;

/**
 * <p>領収書文書ファイル作成クラス</p>
 */
public class AcknowledgmentDocWriter extends AbstractDocWriter {

	/** 行番号(売上伝票番号) **/
	private static final int ROW_INDEX_VOUCHER_NO = 3;
	/** 行番号(日付) **/
	private static final int ROW_INDEX_DATE = 4;
	/** 行番号(郵便番号) **/
	private static final int ROW_INDEX_POST_CODE = 5;
	/** 行番号(住所1) **/
	private static final int ROW_INDEX_ADDR1 = 6;
	/** 行番号(住所2) **/
	private static final int ROW_INDEX_ADDR2 = 7;
	/** 行番号(取引先名) **/
	private static final int ROW_INDEX_DEAL_NAME = 8;
	/** 行番号(自社名) **/
	private static final int ROW_INDEX_MY_NAME = 18;
	/** 行番号(郵便番号) **/
	private static final int ROW_INDEX_MY_POST_CODE = 20;
	/** 行番号(住所1) **/
	private static final int ROW_INDEX_MY_ADDR1 = 21;
	/** 行番号(住所2) **/
	private static final int ROW_INDEX_MY_ADDR2 = 22;
	/** 行番号(TEL) **/
	private static final int ROW_INDEX_MY_TEL = 23;
	/** 行番号(FAX) **/
	private static final int ROW_INDEX_MY_FAX = 23;
	/** 行番号(金額) **/
	private static final int ROW_INDEX_AMOUNT = 12;
	/** 行番号(但書) **/
	private static final int ROW_INDEX_PROVISO = 14;
	/** 行番号(税抜金額) **/
	private static final int ROW_INDEX_TAX_EXCLUDED_PRICE = 20;
	/** 行番号(消費税) **/
	private static final int ROW_INDEX_TAX = 21;
	/** 行番号(値引) **/
	private static final int ROW_INDEX_DISCOUNT = 22;
	/** 行番号(合計) **/
	private static final int ROW_INDEX_SUM = 23;

	/** 行番号(控えとの差) **/
	private static final int ROW_INDEX_HIKAE = 26;

	/** 列番号(売上伝票番号) **/
	private static final int COL_INDEX_VOUCHER_NO = 18;
	/** 列番号(日付) **/
	private static final int COL_INDEX_DATE = 17;
	/** 列番号(郵便番号) **/
	private static final int COL_INDEX_POST_CODE = 0;
	/** 列番号(住所1) **/
	private static final int COL_INDEX_ADDR1 = 0;
	/** 列番号(住所2) **/
	private static final int COL_INDEX_ADDR2 = 0;
	/** 列番号(取引先名) **/
	private static final int COL_INDEX_DEAL_NAME = 0;
	/** 列番号(自社名) **/
	private static final int COL_INDEX_MY_NAME = 10;
	/** 列番号(郵便番号) **/
	private static final int COL_INDEX_MY_POST_CODE = 17;
	/** 列番号(住所1) **/
	private static final int COL_INDEX_MY_ADDR1 = 10;
	/** 列番号(住所2) **/
	private static final int COL_INDEX_MY_ADDR2 = 10;
	/** 列番号(TEL) **/
	private static final int COL_INDEX_MY_TEL = 12;
	/** 列番号(FAX) **/
	private static final int COL_INDEX_MY_FAX = 16;
	/** 列番号(金額) **/
	private static final int COL_INDEX_AMOUNT = 4;
	/** 列番号(但書) **/
	private static final int COL_INDEX_PROVISO = 3;
	/** 列番号(税抜金額) **/
	private static final int COL_INDEX_TAX_EXCLUDED_PRICE = 3;
	/** 列番号(消費税) **/
	private static final int COL_INDEX_TAX = 3;
	/** 列番号(値引) **/
	private static final int COL_INDEX_DISCOUNT = 3;
	/** 列番号(合計) **/
	private static final int COL_INDEX_SUM = 3;

	/**
	 * <p>コンストラクタ</p>
	 * @param docInfo
	 */
	public AcknowledgmentDocWriter(DocInfo docInfo) {
		super(docInfo);
	}

	/**
	 * <p>領収書文書作成処理</p>
	 *
	 * 領収書文書ファイル(エクセル)を作成する。
	 *
	 * @throws DocFileWriterException
	 */
	@Override
	public void write() throws DocWriterException {
		OutputStream os = null;
		InputStream in = null;
		try {
			AcknowledgmentDocInfo acknowledgmentDocInfo = (AcknowledgmentDocInfo)docInfo;

			in = new BufferedInputStream(new FileInputStream(new File(acknowledgmentDocInfo.getFormName())));
			Workbook book = new HSSFWorkbook(in);
			Sheet sheet = book.getSheet(acknowledgmentDocInfo.getSheet());

			// 文書データ設定
			VoucherDocData voucherDocData = (VoucherDocData)acknowledgmentDocInfo.getDocData();
			setVoucher(sheet, voucherDocData, 0);
			setDeal(sheet, voucherDocData.getDeal(), 0);
			setMyCompany(sheet, voucherDocData, 0);
			setPrice(sheet, voucherDocData, 0);

			// 控作成
			setVoucher(sheet, voucherDocData, ROW_INDEX_HIKAE);
			setDeal(sheet, voucherDocData.getDeal(), ROW_INDEX_HIKAE);
			setMyCompany(sheet, voucherDocData, ROW_INDEX_HIKAE);
			setPrice(sheet, voucherDocData, ROW_INDEX_HIKAE);

			// 文書出力
			os = new BufferedOutputStream(new FileOutputStream(acknowledgmentDocInfo.getOutFileName()));
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
	 * @param addRowIndex
	 */
	private void setVoucher(Sheet sheet, VoucherDocData voucherData, int addRowIndex) {

		// 見積番号
		Row row = sheet.getRow(ROW_INDEX_VOUCHER_NO + addRowIndex);
		Cell cell = row.getCell(COL_INDEX_VOUCHER_NO);
		cell.setCellValue(voucherData.getVoucherNo());

		// 日付
		row = sheet.getRow(ROW_INDEX_DATE + addRowIndex);
		cell = row.getCell(COL_INDEX_DATE);
		cell.setCellValue(voucherData.getVoucherDate());

		// 金額
		row = sheet.getRow(ROW_INDEX_AMOUNT + addRowIndex);
		cell = row.getCell(COL_INDEX_AMOUNT);
		cell.setCellValue(Double.parseDouble(voucherData.getAmount()));

		// 但書
		row = sheet.getRow(ROW_INDEX_PROVISO + addRowIndex);
		cell = row.getCell(COL_INDEX_PROVISO);
		cell.setCellValue(voucherData.getProviso());
	}

	/**
	 * 取引先情報設定
	 *
	 * @param sheet
	 * @param dealDocData
	 * @param addRowIndex
	 */
	private void setDeal(Sheet sheet, DealDocData dealDocData, int addRowIndex) {

		// 郵便番号
		Row row = sheet.getRow(ROW_INDEX_POST_CODE + addRowIndex);
		Cell cell = row.getCell(COL_INDEX_POST_CODE);
		cell.setCellValue(new StringBuilder(CommonConstants.POST_MARK).append(
				dealDocData.getPostCode()).toString());

		// 住所
		row = sheet.getRow(ROW_INDEX_ADDR1 + addRowIndex);
		cell = row.getCell(COL_INDEX_ADDR1);
		cell.setCellValue(dealDocData.getAddr1());
		row = sheet.getRow(ROW_INDEX_ADDR2 + addRowIndex);
		cell = row.getCell(COL_INDEX_ADDR2);
		cell.setCellValue(dealDocData.getAddr2());

		// 取引先名
		row = sheet.getRow(ROW_INDEX_DEAL_NAME + addRowIndex);
		cell = row.getCell(COL_INDEX_DEAL_NAME);
		cell.setCellValue(new StringBuilder().append(
				dealDocData.getDealName()).append(
						CommonConstants.DOUBLE_SPACE).append(
								ViewProperties.getInstance().getValue(
										ViewProperties.KEISYO_ONCYU, ViewProperties.DISP_VALUE)).toString());
	}

	/**
	 * 自社情報設定
	 *
	 * @param sheet
	 * @param voucherDocData
	 * @param addRowIndex
	 */
	private void setMyCompany(Sheet sheet, VoucherDocData voucherDocData, int addRowIndex) {

		// 自社名
		Row row = sheet.getRow(ROW_INDEX_MY_NAME + addRowIndex);
		Cell cell = row.getCell(COL_INDEX_MY_NAME);
		cell.setCellValue(voucherDocData.getName());

		// 郵便番号
		row = sheet.getRow(ROW_INDEX_MY_POST_CODE + addRowIndex);
		cell = row.getCell(COL_INDEX_MY_POST_CODE);
		cell.setCellValue(
				new StringBuilder(CommonConstants.POST_MARK).append(
						voucherDocData.getPostCode1()).append("-").append(voucherDocData.getPostCode2()).toString());

		// 住所
		row = sheet.getRow(ROW_INDEX_MY_ADDR1 + addRowIndex);
		cell = row.getCell(COL_INDEX_MY_ADDR1);
		cell.setCellValue(voucherDocData.getAddr1());
		row = sheet.getRow(ROW_INDEX_MY_ADDR2 + addRowIndex);
		cell = row.getCell(COL_INDEX_MY_ADDR2);
		cell.setCellValue(voucherDocData.getAddr2());

		// TEL
		row = sheet.getRow(ROW_INDEX_MY_TEL + addRowIndex);
		cell = row.getCell(COL_INDEX_MY_TEL);
		cell.setCellValue(voucherDocData.getTel());

		// FAX
		row = sheet.getRow(ROW_INDEX_MY_FAX + addRowIndex);
		cell = row.getCell(COL_INDEX_MY_FAX);
		cell.setCellValue(voucherDocData.getFax());
	}

	/**
	 * 金額情報設定
	 *
	 * @param sheet
	 * @param voucherDocData
	 * @param addRowIndex
	 */
	private void setPrice(Sheet sheet, VoucherDocData voucherDocData, int addRowIndex) {

		// 税抜金額
		Row row = sheet.getRow(ROW_INDEX_TAX_EXCLUDED_PRICE + addRowIndex);
		Cell cell = row.getCell(COL_INDEX_TAX_EXCLUDED_PRICE);
		cell.setCellValue(Double.parseDouble(voucherDocData.getTaxExcludedAmount()));

		// 消費税額
		row = sheet.getRow(ROW_INDEX_TAX + addRowIndex);
		cell = row.getCell(COL_INDEX_TAX);
		cell.setCellValue(Double.parseDouble(voucherDocData.getTax()));

		// 調整額
		row = sheet.getRow(ROW_INDEX_DISCOUNT + addRowIndex);
		cell = row.getCell(COL_INDEX_DISCOUNT);
		cell.setCellValue(Double.parseDouble(voucherDocData.getDiscount()));

		// 合計
		row = sheet.getRow(ROW_INDEX_SUM + addRowIndex);
		cell = row.getCell(COL_INDEX_SUM);
		cell.setCellValue(Double.parseDouble(voucherDocData.getAmount()));
	}

}
