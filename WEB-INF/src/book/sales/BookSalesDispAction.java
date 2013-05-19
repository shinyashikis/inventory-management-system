package book.sales;

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
import book.sales.bean.BookSalesBook;
import book.sales.bean.BookSalesTotal;
import book.sales.bean.BookUrikakeBook;
import book.sales.bl.BookSalesBookBL;
import book.sales.bl.BookSalesTotalBL;
import book.sales.bl.BookUrikakeBookBL;
import book.sales.docwriter.salesBook.BookSalesBookDetailDateDocInfo;
import book.sales.docwriter.salesBook.BookSalesBookDetailDateDocWriter;
import book.sales.docwriter.salesBook.BookSalesBookDetailYearDocInfo;
import book.sales.docwriter.salesBook.BookSalesBookDetailYearDocWriter;
import book.sales.docwriter.salesBook.BookSalesBookDocDataGenerator;
import book.sales.docwriter.salesBook.BookSalesBookVoucherDateDocInfo;
import book.sales.docwriter.salesBook.BookSalesBookVoucherDateDocWriter;
import book.sales.docwriter.salesBook.BookSalesBookVoucherYearDocInfo;
import book.sales.docwriter.salesBook.BookSalesBookVoucherYearDocWriter;
import book.sales.docwriter.salesTotal.BookSalesTotalDateDocInfo;
import book.sales.docwriter.salesTotal.BookSalesTotalDateDocWriter;
import book.sales.docwriter.salesTotal.BookSalesTotalDocDataGenerator;
import book.sales.docwriter.salesTotal.BookSalesTotalMonthlyDocInfo;
import book.sales.docwriter.salesTotal.BookSalesTotalMonthlyDocWriter;
import book.sales.docwriter.salesTotal.BookSalesTotalYearDocInfo;
import book.sales.docwriter.salesTotal.BookSalesTotalYearDocWriter;
import book.sales.docwriter.urikakeBook.BookUrikakeBookDetailDateDocInfo;
import book.sales.docwriter.urikakeBook.BookUrikakeBookDetailDateDocWriter;
import book.sales.docwriter.urikakeBook.BookUrikakeBookDetailYearDocInfo;
import book.sales.docwriter.urikakeBook.BookUrikakeBookDetailYearDocWriter;
import book.sales.docwriter.urikakeBook.BookUrikakeBookDocDataGenerator;
import book.sales.docwriter.urikakeBook.BookUrikakeBookVoucherDateDocInfo;
import book.sales.docwriter.urikakeBook.BookUrikakeBookVoucherDateDocWriter;
import book.sales.docwriter.urikakeBook.BookUrikakeBookVoucherYearDocInfo;
import book.sales.docwriter.urikakeBook.BookUrikakeBookVoucherYearDocWriter;

public class BookSalesDispAction extends BookCommonAction {

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
		disp((BookSalesForm)form, response);
		return null;
	}

	/**
	 * 表示処理
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void disp(BookSalesForm form, HttpServletResponse response) throws Exception {

		String dispTarget = form.getDispTarget();
		ViewProperties viewProp = ViewProperties.getInstance();
		if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_SALES_TOTAL, ViewProperties.VALUE).equals(dispTarget)) {
			// 売上集計表
			dispForSalesTotal(form, response);

		} else if (viewProp.getValue(
					ViewProperties.BOOK_DISP_TARGET_SALES_BOOK, ViewProperties.VALUE).equals(dispTarget)) {
			// 売上台帳
			dispForSalesBook(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_URIKAKE_BOOK, ViewProperties.VALUE).equals(dispTarget)) {
			// 売掛台帳
			dispForUrikakeBook(form, response);
		}
	}

	/**
	 * 表示処理(売上集計表)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForSalesTotal(BookSalesForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForSalesTotal(form, BookSalesTotalBL.getBookSalesTotalList(form)));
	}

	/**
	 * 表示処理(売上台帳)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForSalesBook(BookSalesForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForSalesBook(form, BookSalesBookBL.getBookSalesBookList(form)));
	}

	/**
	 * 表示処理(売掛台帳)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForUrikakeBook(BookSalesForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForUrikakeBook(form, BookUrikakeBookBL.getBookUrikakeBookList(form)));
	}

	/**
	 * 出力処理(売上集計表)
	 * @param form
	 * @param bookSalesTotalList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForSalesTotal(BookSalesForm form, List<BookSalesTotal> bookSalesTotalList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String selectDateRange = form.getSelectDateRange();
		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			excelInfo = new BookSalesTotalDateDocInfo();
			excelWriter = new BookSalesTotalDateDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
			// 月次推移表

			excelInfo = new BookSalesTotalMonthlyDocInfo();
			excelWriter = new BookSalesTotalMonthlyDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			excelInfo = new BookSalesTotalYearDocInfo();
			excelWriter = new BookSalesTotalYearDocWriter(excelInfo);

		} else {
			throw new IllegalArgumentException();
		}

		try {
			String fileName = new StringBuilder("salesTotal").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookSalesTotalDocDataGenerator.generateBookSalesTotalDocData(bookSalesTotalList));
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
	 * 出力処理(売上台帳)
	 * @param form
	 * @param bookSalesBookList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForSalesBook(
			BookSalesForm form, List<BookSalesBook> bookSalesBookList) throws Exception {

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

				excelInfo = new BookSalesBookVoucherDateDocInfo();
				excelWriter = new BookSalesBookVoucherDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookSalesBookVoucherYearDocInfo();
				excelWriter = new BookSalesBookVoucherYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_DETAIL, ViewProperties.VALUE))) {
			// 詳細

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				excelInfo = new BookSalesBookDetailDateDocInfo();
				excelWriter = new BookSalesBookDetailDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookSalesBookDetailYearDocInfo();
				excelWriter = new BookSalesBookDetailYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else {
			throw new IllegalArgumentException();
		}


		try {
			String fileName = new StringBuilder("salesBook").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookSalesBookDocDataGenerator.generateBookSalesBookDocData(bookSalesBookList));
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
	 * 出力処理(売掛台帳)
	 * @param form
	 * @param bookUrikakeBookList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForUrikakeBook(
			BookSalesForm form, List<BookUrikakeBook> bookUrikakeBookList) throws Exception {

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

				excelInfo = new BookUrikakeBookVoucherDateDocInfo();
				excelWriter = new BookUrikakeBookVoucherDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookUrikakeBookVoucherYearDocInfo();
				excelWriter = new BookUrikakeBookVoucherYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_DETAIL, ViewProperties.VALUE))) {
			// 詳細

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				excelInfo = new BookUrikakeBookDetailDateDocInfo();
				excelWriter = new BookUrikakeBookDetailDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookUrikakeBookDetailYearDocInfo();
				excelWriter = new BookUrikakeBookDetailYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else {
			throw new IllegalArgumentException();
		}


		try {
			String fileName = new StringBuilder("urikakeBook").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookUrikakeBookDocDataGenerator.generateBookUrikakeBookDocData(bookUrikakeBookList));
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
