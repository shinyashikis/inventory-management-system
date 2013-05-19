package book.purchase;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fw.common.docwriter.AbstractDocWriter;
import fw.common.docwriter.DocInfo;
import fw.common.docwriter.DocWriterException;
import fw.common.util.CommonConstants;

import prop.CommonProperties;
import prop.ViewProperties;

import book.common.BookCommonAction;
import book.purchase.bean.BookPurchaseBook;
import book.purchase.bean.BookPurchaseTotal;
import book.purchase.bean.BookKaikakeBook;
import book.purchase.bl.BookPurchaseBookBL;
import book.purchase.bl.BookPurchaseTotalBL;
import book.purchase.bl.BookKaikakeBookBL;
import book.purchase.docwriter.purchaseBook.BookPurchaseBookDetailDateDocInfo;
import book.purchase.docwriter.purchaseBook.BookPurchaseBookDetailDateDocWriter;
import book.purchase.docwriter.purchaseBook.BookPurchaseBookDetailYearDocInfo;
import book.purchase.docwriter.purchaseBook.BookPurchaseBookDetailYearDocWriter;
import book.purchase.docwriter.purchaseBook.BookPurchaseBookDocDataGenerator;
import book.purchase.docwriter.purchaseBook.BookPurchaseBookVoucherDateDocInfo;
import book.purchase.docwriter.purchaseBook.BookPurchaseBookVoucherDateDocWriter;
import book.purchase.docwriter.purchaseBook.BookPurchaseBookVoucherYearDocInfo;
import book.purchase.docwriter.purchaseBook.BookPurchaseBookVoucherYearDocWriter;
import book.purchase.docwriter.purchaseTotal.BookPurchaseTotalDateDocInfo;
import book.purchase.docwriter.purchaseTotal.BookPurchaseTotalDateDocWriter;
import book.purchase.docwriter.purchaseTotal.BookPurchaseTotalDocDataGenerator;
import book.purchase.docwriter.purchaseTotal.BookPurchaseTotalMonthlyDocInfo;
import book.purchase.docwriter.purchaseTotal.BookPurchaseTotalMonthlyDocWriter;
import book.purchase.docwriter.purchaseTotal.BookPurchaseTotalYearDocInfo;
import book.purchase.docwriter.purchaseTotal.BookPurchaseTotalYearDocWriter;
import book.purchase.docwriter.kaikakeBook.BookKaikakeBookDetailDateDocInfo;
import book.purchase.docwriter.kaikakeBook.BookKaikakeBookDetailDateDocWriter;
import book.purchase.docwriter.kaikakeBook.BookKaikakeBookDetailYearDocInfo;
import book.purchase.docwriter.kaikakeBook.BookKaikakeBookDetailYearDocWriter;
import book.purchase.docwriter.kaikakeBook.BookKaikakeBookDocDataGenerator;
import book.purchase.docwriter.kaikakeBook.BookKaikakeBookVoucherDateDocInfo;
import book.purchase.docwriter.kaikakeBook.BookKaikakeBookVoucherDateDocWriter;
import book.purchase.docwriter.kaikakeBook.BookKaikakeBookVoucherYearDocInfo;
import book.purchase.docwriter.kaikakeBook.BookKaikakeBookVoucherYearDocWriter;

public class BookPurchaseDispAction extends BookCommonAction {

	/**
	 * 「表示」ボタン押下時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward disp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		disp((BookPurchaseForm)form, response);
		return null;
	}

	/**
	 * 表示処理
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void disp(BookPurchaseForm form, HttpServletResponse response) throws Exception {

		String dispTarget = form.getDispTarget();
		ViewProperties viewProp = ViewProperties.getInstance();
		if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_PURCHASE_TOTAL, ViewProperties.VALUE).equals(dispTarget)) {
			// 仕入集計表
			dispForPurchaseTotal(form, response);

		} else if (viewProp.getValue(
					ViewProperties.BOOK_DISP_TARGET_PURCHASE_BOOK, ViewProperties.VALUE).equals(dispTarget)) {
			// 仕入台帳
			dispForPurchaseBook(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_KAIKAKE_BOOK, ViewProperties.VALUE).equals(dispTarget)) {
			// 買掛台帳
			dispForKaikakeBook(form, response);
		}
	}

	/**
	 * 表示処理(仕入集計表)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForPurchaseTotal(BookPurchaseForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForPurchaseTotal(form, BookPurchaseTotalBL.getBookPurchaseTotalList(form)));
	}

	/**
	 * 表示処理(仕入台帳)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForPurchaseBook(BookPurchaseForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForPurchaseBook(form, BookPurchaseBookBL.getBookPurchaseBookList(form)));
	}

	/**
	 * 表示処理(買掛台帳)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForKaikakeBook(BookPurchaseForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForKaikakeBook(form, BookKaikakeBookBL.getBookKaikakeBookList(form)));
	}

	/**
	 * 出力処理(仕入集計表)
	 * @param form
	 * @param bookPurchaseTotalList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForPurchaseTotal(BookPurchaseForm form, List<BookPurchaseTotal> bookPurchaseTotalList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String selectDateRange = form.getSelectDateRange();
		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			excelInfo = new BookPurchaseTotalDateDocInfo();
			excelWriter = new BookPurchaseTotalDateDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
			// 月次推移表

			excelInfo = new BookPurchaseTotalMonthlyDocInfo();
			excelWriter = new BookPurchaseTotalMonthlyDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			excelInfo = new BookPurchaseTotalYearDocInfo();
			excelWriter = new BookPurchaseTotalYearDocWriter(excelInfo);

		} else {
			throw new IllegalArgumentException();
		}

		try {
			String fileName = new StringBuilder("purchaseTotal").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookPurchaseTotalDocDataGenerator.generateBookPurchaseTotalDocData(bookPurchaseTotalList));
			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}

	/**
	 * 出力処理(仕入台帳)
	 * @param form
	 * @param bookPurchaseBookList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForPurchaseBook(
			BookPurchaseForm form, List<BookPurchaseBook> bookPurchaseBookList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String dispTargetDetail = form.getDispTargetDetail();
		String selectDateRange = form.getSelectDateRange();
		if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_VOUCHER, ViewProperties.VALUE))) {
			// 伝票別

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				excelInfo = new BookPurchaseBookVoucherDateDocInfo();
				excelWriter = new BookPurchaseBookVoucherDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookPurchaseBookVoucherYearDocInfo();
				excelWriter = new BookPurchaseBookVoucherYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_DETAIL, ViewProperties.VALUE))) {
			// 詳細

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				excelInfo = new BookPurchaseBookDetailDateDocInfo();
				excelWriter = new BookPurchaseBookDetailDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookPurchaseBookDetailYearDocInfo();
				excelWriter = new BookPurchaseBookDetailYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else {
			throw new IllegalArgumentException();
		}


		try {
			String fileName = new StringBuilder("purchaseBook").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookPurchaseBookDocDataGenerator.generateBookPurchaseBookDocData(bookPurchaseBookList));
			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}

	/**
	 * 出力処理(買掛台帳)
	 * @param form
	 * @param bookKaikakeBookList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForKaikakeBook(
			BookPurchaseForm form, List<BookKaikakeBook> bookKaikakeBookList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String dispTargetDetail = form.getDispTargetDetail();
		String selectDateRange = form.getSelectDateRange();
		if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_VOUCHER, ViewProperties.VALUE))) {
			// 伝票別

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				excelInfo = new BookKaikakeBookVoucherDateDocInfo();
				excelWriter = new BookKaikakeBookVoucherDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookKaikakeBookVoucherYearDocInfo();
				excelWriter = new BookKaikakeBookVoucherYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_DETAIL, ViewProperties.VALUE))) {
			// 詳細

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				excelInfo = new BookKaikakeBookDetailDateDocInfo();
				excelWriter = new BookKaikakeBookDetailDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookKaikakeBookDetailYearDocInfo();
				excelWriter = new BookKaikakeBookDetailYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else {
			throw new IllegalArgumentException();
		}


		try {
			String fileName = new StringBuilder("kaikakeBook").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookKaikakeBookDocDataGenerator.generateBookKaikakeBookDocData(bookKaikakeBookList));
			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}
}
