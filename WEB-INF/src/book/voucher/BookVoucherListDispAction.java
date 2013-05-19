package book.voucher;

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
import book.voucher.bean.BookAcknowledgment;
import book.voucher.bean.BookBillTotal;
import book.voucher.bean.BookOrder;
import book.voucher.bean.BookQuotation;
import book.voucher.bl.BookAcknowledgmentBL;
import book.voucher.bl.BookBillTotalBL;
import book.voucher.bl.BookOrderBL;
import book.voucher.bl.BookQuotationBL;
import book.voucher.docwriter.BookAcknowledgmentDocDataGenerator;
import book.voucher.docwriter.BookAcknowledgmentDocInfo;
import book.voucher.docwriter.BookAcknowledgmentDocWriter;
import book.voucher.docwriter.BookBillTotalDocDataGenerator;
import book.voucher.docwriter.BookBillTotalDocInfo;
import book.voucher.docwriter.BookBillTotalDocWriter;
import book.voucher.docwriter.BookOrderDocDataGenerator;
import book.voucher.docwriter.BookOrderDocInfo;
import book.voucher.docwriter.BookOrderDocWriter;
import book.voucher.docwriter.BookQuotationDocDataGenerator;
import book.voucher.docwriter.BookQuotationDocInfo;
import book.voucher.docwriter.BookQuotationDocWriter;

public class BookVoucherListDispAction extends BookCommonAction {

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
		disp((BookVoucherListForm)form, response);
		return null;
	}

	/**
	 * 表示処理
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void disp(BookVoucherListForm form, HttpServletResponse response) throws Exception {

		String dispTarget = form.getDispTarget();
		ViewProperties viewProp = ViewProperties.getInstance();
		if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_QUOTATION, ViewProperties.VALUE).equals(dispTarget)) {
			// 見積書
			dispForQuotation(form, response);

		} else if (viewProp.getValue(
					ViewProperties.BOOK_DISP_TARGET_BILL_TOTAL, ViewProperties.VALUE).equals(dispTarget)) {
			// 合計請求書
			dispForBillTotal(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_ACKNOWLEDGMENT, ViewProperties.VALUE).equals(dispTarget)) {
			// 領収書
			dispForAcknowledgment(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_ORDER, ViewProperties.VALUE).equals(dispTarget)) {
			// 注文書
			dispForOrder(form, response);
		}
	}

	/**
	 * 表示処理(見積書)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForQuotation(BookVoucherListForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForQuotation(form, BookQuotationBL.getBookQuotationList(form)));
	}

	/**
	 * 表示処理(合計請求書)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForBillTotal(BookVoucherListForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForBillTotal(form, BookBillTotalBL.getBookBillTotalList(form)));
	}

	/**
	 * 表示処理(領収書)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForAcknowledgment(BookVoucherListForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForAcknowledgment(form, BookAcknowledgmentBL.getBookAcknowledgmentList(form)));
	}

	/**
	 * 表示処理(注文書)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForOrder(BookVoucherListForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForOrder(form, BookOrderBL.getBookOrderList(form)));
	}

	/**
	 * 出力処理(見積書)
	 * @param form
	 * @param bookQuotationTotalList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForQuotation(BookVoucherListForm form, List<BookQuotation> bookQuotationList) throws Exception {

		DocInfo excelInfo = new BookQuotationDocInfo();
		AbstractDocWriter excelWriter = new BookQuotationDocWriter(excelInfo);

		try {
			String fileName = new StringBuilder("quotation").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookQuotationDocDataGenerator.generateBookSalesBookDocData(bookQuotationList));
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
	 * 出力処理(合計請求書)
	 * @param form
	 * @param bookBillTotalTotalList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForBillTotal(BookVoucherListForm form, List<BookBillTotal> bookBillTotalList) throws Exception {

		DocInfo excelInfo = new BookBillTotalDocInfo();
		AbstractDocWriter excelWriter = new BookBillTotalDocWriter(excelInfo);

		try {
			String fileName = new StringBuilder("billTotal").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookBillTotalDocDataGenerator.generateBookSalesBookDocData(bookBillTotalList));
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
	 * 出力処理(領収書)
	 * @param form
	 * @param bookAcknowledgmentTotalList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForAcknowledgment(BookVoucherListForm form, List<BookAcknowledgment> bookAcknowledgmentList) throws Exception {

		DocInfo excelInfo = new BookAcknowledgmentDocInfo();
		AbstractDocWriter excelWriter = new BookAcknowledgmentDocWriter(excelInfo);

		try {
			String fileName = new StringBuilder("acknowledgment").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookAcknowledgmentDocDataGenerator.generateBookSalesBookDocData(bookAcknowledgmentList));
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
	 * 出力処理(注文書)
	 * @param form
	 * @param bookOrderTotalList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForOrder(BookVoucherListForm form, List<BookOrder> bookOrderList) throws Exception {

		DocInfo excelInfo = new BookOrderDocInfo();
		AbstractDocWriter excelWriter = new BookOrderDocWriter(excelInfo);

		try {
			String fileName = new StringBuilder("order").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookOrderDocDataGenerator.generateBookSalesBookDocData(bookOrderList));
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
