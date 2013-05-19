package book.item;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import prop.CommonProperties;
import prop.ViewProperties;

import fw.common.docwriter.AbstractDocWriter;
import fw.common.docwriter.DocInfo;
import fw.common.docwriter.DocWriterException;
import fw.common.util.CommonConstants;

import book.common.BookCommonAction;
import book.item.bean.BookGross;
import book.item.bean.BookItemStock;
import book.item.bean.BookProfit;
import book.item.bean.BookPurchasePriceAve;
import book.item.bean.BookSalesPriceAve;
import book.item.bean.BookShipBook;
import book.item.bean.BookShipSum;
import book.item.bean.BookStockTransition;
import book.item.bean.BookStorageBook;
import book.item.bean.BookStorageSum;
import book.item.bl.BookGrossBL;
import book.item.bl.BookItemStockBL;
import book.item.bl.BookProfitBL;
import book.item.bl.BookPurchasePriceAveBL;
import book.item.bl.BookSalesPriceAveBL;
import book.item.bl.BookShipBookBL;
import book.item.bl.BookShipSumBL;
import book.item.bl.BookStackTransitionBL;
import book.item.bl.BookStorageBookBL;
import book.item.bl.BookStorageSumBL;
import book.item.docwriter.gross.BookGrossDateDocInfo;
import book.item.docwriter.gross.BookGrossDateDocWriter;
import book.item.docwriter.gross.BookGrossDocDataGenerator;
import book.item.docwriter.gross.BookGrossMonthlyDocInfo;
import book.item.docwriter.gross.BookGrossMonthlyDocWriter;
import book.item.docwriter.gross.BookGrossYearDocInfo;
import book.item.docwriter.gross.BookGrossYearDocWriter;
import book.item.docwriter.itemStock.BookItemStockCountDocInfo;
import book.item.docwriter.itemStock.BookItemStockDocDataGenerator;
import book.item.docwriter.itemStock.BookItemStockCountDocWriter;
import book.item.docwriter.itemStock.BookItemStockPriceDocInfo;
import book.item.docwriter.itemStock.BookItemStockPriceDocWriter;
import book.item.docwriter.profit.BookProfitDateDocInfo;
import book.item.docwriter.profit.BookProfitDateDocWriter;
import book.item.docwriter.profit.BookProfitDocDataGenerator;
import book.item.docwriter.profit.BookProfitMonthlyDocInfo;
import book.item.docwriter.profit.BookProfitMonthlyDocWriter;
import book.item.docwriter.profit.BookProfitYearDocInfo;
import book.item.docwriter.profit.BookProfitYearDocWriter;
import book.item.docwriter.purchasePriceAve.BookPurchasePriceAveDateDocInfo;
import book.item.docwriter.purchasePriceAve.BookPurchasePriceAveDateDocWriter;
import book.item.docwriter.purchasePriceAve.BookPurchasePriceAveDocDataGenerator;
import book.item.docwriter.purchasePriceAve.BookPurchasePriceAveMonthlyDocInfo;
import book.item.docwriter.purchasePriceAve.BookPurchasePriceAveMonthlyDocWriter;
import book.item.docwriter.purchasePriceAve.BookPurchasePriceAveYearDocInfo;
import book.item.docwriter.purchasePriceAve.BookPurchasePriceAveYearDocWriter;
import book.item.docwriter.salesPriceAve.BookSalesPriceAveDateDocInfo;
import book.item.docwriter.salesPriceAve.BookSalesPriceAveDateDocWriter;
import book.item.docwriter.salesPriceAve.BookSalesPriceAveDocDataGenerator;
import book.item.docwriter.salesPriceAve.BookSalesPriceAveMonthlyDocInfo;
import book.item.docwriter.salesPriceAve.BookSalesPriceAveMonthlyDocWriter;
import book.item.docwriter.salesPriceAve.BookSalesPriceAveYearDocInfo;
import book.item.docwriter.salesPriceAve.BookSalesPriceAveYearDocWriter;
import book.item.docwriter.shipBook.BookShipBookDateDocInfo;
import book.item.docwriter.shipBook.BookShipBookDateDocWriter;
import book.item.docwriter.shipBook.BookShipBookDocDataGenerator;
import book.item.docwriter.shipBook.BookShipBookYearDocInfo;
import book.item.docwriter.shipBook.BookShipBookYearDocWriter;
import book.item.docwriter.shipSum.BookShipSumCountDateDocInfo;
import book.item.docwriter.shipSum.BookShipSumCountDateDocWriter;
import book.item.docwriter.shipSum.BookShipSumCountMonthlyDocInfo;
import book.item.docwriter.shipSum.BookShipSumCountMonthlyDocWriter;
import book.item.docwriter.shipSum.BookShipSumCountYearDocInfo;
import book.item.docwriter.shipSum.BookShipSumCountYearDocWriter;
import book.item.docwriter.shipSum.BookShipSumDocDataGenerator;
import book.item.docwriter.shipSum.BookShipSumPriceDateDocInfo;
import book.item.docwriter.shipSum.BookShipSumPriceDateDocWriter;
import book.item.docwriter.shipSum.BookShipSumPriceMonthlyDocInfo;
import book.item.docwriter.shipSum.BookShipSumPriceMonthlyDocWriter;
import book.item.docwriter.shipSum.BookShipSumPriceYearDocInfo;
import book.item.docwriter.shipSum.BookShipSumPriceYearDocWriter;
import book.item.docwriter.stockTransition.BookStockTransitionCountDocInfo;
import book.item.docwriter.stockTransition.BookStockTransitionCountDocWriter;
import book.item.docwriter.stockTransition.BookStockTransitionDocDataGenerator;
import book.item.docwriter.stockTransition.BookStockTransitionPriceDocInfo;
import book.item.docwriter.stockTransition.BookStockTransitionPriceDocWriter;
import book.item.docwriter.storageBook.BookStorageBookDateDocInfo;
import book.item.docwriter.storageBook.BookStorageBookDateDocWriter;
import book.item.docwriter.storageBook.BookStorageBookDocDataGenerator;
import book.item.docwriter.storageBook.BookStorageBookYearDocInfo;
import book.item.docwriter.storageBook.BookStorageBookYearDocWriter;
import book.item.docwriter.storageSum.BookStorageSumCountDateDocInfo;
import book.item.docwriter.storageSum.BookStorageSumCountDateDocWriter;
import book.item.docwriter.storageSum.BookStorageSumCountMonthlyDocInfo;
import book.item.docwriter.storageSum.BookStorageSumCountMonthlyDocWriter;
import book.item.docwriter.storageSum.BookStorageSumCountYearDocInfo;
import book.item.docwriter.storageSum.BookStorageSumCountYearDocWriter;
import book.item.docwriter.storageSum.BookStorageSumDocDataGenerator;
import book.item.docwriter.storageSum.BookStorageSumPriceDateDocInfo;
import book.item.docwriter.storageSum.BookStorageSumPriceDateDocWriter;
import book.item.docwriter.storageSum.BookStorageSumPriceMonthlyDocInfo;
import book.item.docwriter.storageSum.BookStorageSumPriceMonthlyDocWriter;
import book.item.docwriter.storageSum.BookStorageSumPriceYearDocInfo;
import book.item.docwriter.storageSum.BookStorageSumPriceYearDocWriter;

/**
 * 帳簿「商品情報」アクション
 *
 * @author shinyashiki
 *
 */
public class BookItemDispAction extends BookCommonAction {

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
		disp((BookItemForm)form, response);
		return null;
	}

	/**
	 * 表示処理
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void disp(BookItemForm form, HttpServletResponse response) throws Exception {

		String dispTarget = form.getDispTarget();
		ViewProperties viewProp = ViewProperties.getInstance();
		if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_ITEM_STOCK, ViewProperties.VALUE).equals(dispTarget)) {
			// 商品在庫表
			dispForItemStock(form, response);

		} else if (viewProp.getValue(
					ViewProperties.BOOK_DISP_TARGET_STOCK_TRANSITION, ViewProperties.VALUE).equals(dispTarget)) {
			// 在庫推移表
			dispForStockTransition(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_SHIP_SUM, ViewProperties.VALUE).equals(dispTarget)) {
			// 出庫集計表
			dispForShipSum(form, response);

		} else if (viewProp.getValue(
			ViewProperties.BOOK_DISP_TARGET_STORAGE_SUM, ViewProperties.VALUE).equals(dispTarget)) {
			// 入庫集計表
			dispForStorageSum(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_SHIP_BOOK, ViewProperties.VALUE).equals(dispTarget)) {
			// 出庫台帳
			dispForShipBook(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_STORAGE_BOOK, ViewProperties.VALUE).equals(dispTarget)) {
			// 入庫台帳
			dispForStorageBook(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_SALES_PRICE_AVE, ViewProperties.VALUE).equals(dispTarget)) {
			// 平均販売価格
			dispForSalesPriceAve(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_PURCHASE_PRICE_AVE, ViewProperties.VALUE).equals(dispTarget)) {
			// 平均仕入価格
			dispForPurchasePriceAve(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_GROSS, ViewProperties.VALUE).equals(dispTarget)) {
			// 粗利益
			dispForGross(form, response);

		} else if (viewProp.getValue(
				ViewProperties.BOOK_DISP_TARGET_PROFIT, ViewProperties.VALUE).equals(dispTarget)) {
			// 利益率
			dispForProfit(form, response);

		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 表示処理(商品在庫表)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForItemStock(BookItemForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForItemStock(form, BookItemStockBL.getBookItemStockList(form)));
	}

	/**
	 * 表示処理(在庫推移表)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForStockTransition(BookItemForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForStockTransition(form, BookStackTransitionBL.getBookStockTransitionList(form)));
	}

	/**
	 * 表示処理(出庫集計表)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForShipSum(BookItemForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForShipSum(form, BookShipSumBL.getBookShipSumList(form)));
	}

	/**
	 * 表示処理(入庫集計表)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForStorageSum(BookItemForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForStorageSum(form, BookStorageSumBL.getBookStorageSumList(form)));
	}

	/**
	 * 表示処理(出庫台帳)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForShipBook(BookItemForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForShipBook(form, BookShipBookBL.getBookShipBookList(form)));
	}

	/**
	 * 表示処理(入庫台帳)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForStorageBook(BookItemForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForStorageBook(form, BookStorageBookBL.getBookStorageBookList(form)));
	}

	/**
	 * 表示処理(平均販売価格)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForSalesPriceAve(BookItemForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForSalesPriceAve(form, BookSalesPriceAveBL.getBookSalesPriceAveList(form)));
	}

	/**
	 * 表示処理(平均仕入価格)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForPurchasePriceAve(BookItemForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForPurchasePriceAve(form, BookPurchasePriceAveBL.getBookPurchasePriceAveList(form)));
	}

	/**
	 * 表示処理(粗利益)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForGross(BookItemForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForGross(form, BookGrossBL.getBookGrossList(form)));
	}

	/**
	 * 表示処理(利益率)
	 * @param form
	 * @param response
	 * @throws Exception
	 */
	private void dispForProfit(BookItemForm form, HttpServletResponse response) throws Exception {
		download(response,
				outputFileForProfit(form, BookProfitBL.getBookProfitList(form)));
	}

	/**
	 * 出力処理(商品在庫表)
	 * @param form
	 * @param bookItemStockList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForItemStock(BookItemForm form, List<BookItemStock> bookItemStockList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String dispTargetDetail = form.getDispTargetDetail();
		if (ViewProperties.getInstance().getValue(
				ViewProperties.BOOK_DISP_DETAIL_COUNT, ViewProperties.VALUE).equals(dispTargetDetail)) {
			// 「数量表示」
			excelInfo = new BookItemStockCountDocInfo();
			excelWriter = new BookItemStockCountDocWriter(excelInfo);

		} else if (ViewProperties.getInstance().getValue(
				ViewProperties.BOOK_DISP_DETAIL_PRICE, ViewProperties.VALUE).equals(dispTargetDetail)) {
			// 「金額表示」
			excelInfo = new BookItemStockPriceDocInfo();
			excelWriter = new BookItemStockPriceDocWriter(excelInfo);

		} else {
			throw new IllegalArgumentException();
		}

		try {
			String fileName = new StringBuilder("itemStock").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDocData(
					BookItemStockDocDataGenerator.generateBookItemStockDocData(bookItemStockList));
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
	 * 出力処理(在庫推移表)
	 * @param form
	 * @param bookStockTransitionList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForStockTransition(
			BookItemForm form, List<BookStockTransition> bookStockTransitionList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String dispTargetDetail = form.getDispTargetDetail();
		if (ViewProperties.getInstance().getValue(
				ViewProperties.BOOK_DISP_DETAIL_COUNT, ViewProperties.VALUE).equals(dispTargetDetail)) {
			// 「数量表示」
			excelInfo = new BookStockTransitionCountDocInfo();
			excelWriter = new BookStockTransitionCountDocWriter(excelInfo);

		} else if (ViewProperties.getInstance().getValue(
				ViewProperties.BOOK_DISP_DETAIL_PRICE, ViewProperties.VALUE).equals(dispTargetDetail)) {
			// 「金額表示」
			excelInfo = new BookStockTransitionPriceDocInfo();
			excelWriter = new BookStockTransitionPriceDocWriter(excelInfo);

		} else {
			throw new IllegalArgumentException();
		}

		try {
			String fileName = new StringBuilder("stockTransition").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);
			excelInfo.setDocData(
					BookStockTransitionDocDataGenerator.generateBookStockTransitionDocData(bookStockTransitionList));

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}

	/**
	 * 出力処理(出庫集計表)
	 * @param form
	 * @param bookShipSumList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForShipSum(
			BookItemForm form, List<BookShipSum> bookShipSumList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String dispTargetDetail = form.getDispTargetDetail();
		String selectDateRange = form.getSelectDateRange();

		if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_COUNT, ViewProperties.VALUE))) {
			// 数量表示

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				excelInfo = new BookShipSumCountDateDocInfo();
				excelWriter = new BookShipSumCountDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
				// 月次推移表

				excelInfo = new BookShipSumCountMonthlyDocInfo();
				excelWriter = new BookShipSumCountMonthlyDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookShipSumCountYearDocInfo();
				excelWriter = new BookShipSumCountYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_PRICE, ViewProperties.VALUE))) {
			// 金額表示

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				excelInfo = new BookShipSumPriceDateDocInfo();
				excelWriter = new BookShipSumPriceDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
				// 月次推移表

				excelInfo = new BookShipSumPriceMonthlyDocInfo();
				excelWriter = new BookShipSumPriceMonthlyDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookShipSumPriceYearDocInfo();
				excelWriter = new BookShipSumPriceYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else {
			throw new IllegalArgumentException();
		}


		try {
			String fileName = new StringBuilder("shipSum").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);
			excelInfo.setDocData(
					BookShipSumDocDataGenerator.generateBookShipSumDocData(bookShipSumList));

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}

	/**
	 * 出力処理(入庫集計表)
	 * @param form
	 * @param bookStorageSumList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForStorageSum(
			BookItemForm form, List<BookStorageSum> bookStorageSumList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String dispTargetDetail = form.getDispTargetDetail();
		String selectDateRange = form.getSelectDateRange();

		if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_COUNT, ViewProperties.VALUE))) {
			// 数量表示

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				excelInfo = new BookStorageSumCountDateDocInfo();
				excelWriter = new BookStorageSumCountDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
				// 月次推移表

				excelInfo = new BookStorageSumCountMonthlyDocInfo();
				excelWriter = new BookStorageSumCountMonthlyDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookStorageSumCountYearDocInfo();
				excelWriter = new BookStorageSumCountYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else if (dispTargetDetail.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_DISP_DETAIL_PRICE, ViewProperties.VALUE))) {
			// 金額表示

			if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
				// 日付範囲指定

				excelInfo = new BookStorageSumPriceDateDocInfo();
				excelWriter = new BookStorageSumPriceDateDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
				// 月次推移表

				excelInfo = new BookStorageSumPriceMonthlyDocInfo();
				excelWriter = new BookStorageSumPriceMonthlyDocWriter(excelInfo);

			} else if (selectDateRange.equals(
					ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
				// 年度合計

				excelInfo = new BookStorageSumPriceYearDocInfo();
				excelWriter = new BookStorageSumPriceYearDocWriter(excelInfo);

			} else {
				throw new IllegalArgumentException();
			}

		} else {
			throw new IllegalArgumentException();
		}


		try {
			String fileName = new StringBuilder("storageSum").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);
			excelInfo.setDocData(
					BookStorageSumDocDataGenerator.generateBookStrageSumDocData(bookStorageSumList));

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}

	/**
	 * 出力処理(出庫台帳)
	 * @param form
	 * @param bookShipBookList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForShipBook(
			BookItemForm form, List<BookShipBook> bookShipBookList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String selectDateRange = form.getSelectDateRange();

		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			excelInfo = new BookShipBookDateDocInfo();
			excelWriter = new BookShipBookDateDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			excelInfo = new BookShipBookYearDocInfo();
			excelWriter = new BookShipBookYearDocWriter(excelInfo);

		} else {
			throw new IllegalArgumentException();
		}

		try {
			String fileName = new StringBuilder("shipBook").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);
			excelInfo.setDocData(
					BookShipBookDocDataGenerator.generateBookShipBookDocData(bookShipBookList));

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}

	/**
	 * 出力処理(入庫台帳)
	 * @param form
	 * @param bookStorageBookList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForStorageBook(
			BookItemForm form, List<BookStorageBook> bookStorageBookList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String selectDateRange = form.getSelectDateRange();

		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			excelInfo = new BookStorageBookDateDocInfo();
			excelWriter = new BookStorageBookDateDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			excelInfo = new BookStorageBookYearDocInfo();
			excelWriter = new BookStorageBookYearDocWriter(excelInfo);

		} else {
			throw new IllegalArgumentException();
		}

		try {
			String fileName = new StringBuilder("storageBook").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);
			excelInfo.setDocData(
					BookStorageBookDocDataGenerator.generateBookStorageBookDocData(bookStorageBookList));

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}

	/**
	 * 出力処理(平均販売価格)
	 * @param form
	 * @param bookSalesPriceAveList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForSalesPriceAve(
			BookItemForm form, List<BookSalesPriceAve> bookSalesPriceAveList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String selectDateRange = form.getSelectDateRange();

		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			excelInfo = new BookSalesPriceAveDateDocInfo();
			excelWriter = new BookSalesPriceAveDateDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
			// 月次推移表

			excelInfo = new BookSalesPriceAveMonthlyDocInfo();
			excelWriter = new BookSalesPriceAveMonthlyDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			excelInfo = new BookSalesPriceAveYearDocInfo();
			excelWriter = new BookSalesPriceAveYearDocWriter(excelInfo);

		} else {
			throw new IllegalArgumentException();
		}


		try {
			String fileName = new StringBuilder("salesPriceAve").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);
			excelInfo.setDocData(
					BookSalesPriceAveDocDataGenerator.generateBookSalesPriceAveDocData(bookSalesPriceAveList));

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}

	/**
	 * 出力処理(平均仕入価格)
	 * @param form
	 * @param bookPurchasePriceAveList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForPurchasePriceAve(
			BookItemForm form, List<BookPurchasePriceAve> bookPurchasePriceAveList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String selectDateRange = form.getSelectDateRange();

		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			excelInfo = new BookPurchasePriceAveDateDocInfo();
			excelWriter = new BookPurchasePriceAveDateDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
			// 月次推移表

			excelInfo = new BookPurchasePriceAveMonthlyDocInfo();
			excelWriter = new BookPurchasePriceAveMonthlyDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			excelInfo = new BookPurchasePriceAveYearDocInfo();
			excelWriter = new BookPurchasePriceAveYearDocWriter(excelInfo);

		} else {
			throw new IllegalArgumentException();
		}


		try {
			String fileName = new StringBuilder("purchasePriceAve").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);
			excelInfo.setDocData(
					BookPurchasePriceAveDocDataGenerator.generateBookPurchasePriceAveDocData(bookPurchasePriceAveList));

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}

	/**
	 * 出力処理(粗利益)
	 * @param form
	 * @param bookGrossList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForGross(
			BookItemForm form, List<BookGross> bookGrossList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String selectDateRange = form.getSelectDateRange();

		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			excelInfo = new BookGrossDateDocInfo();
			excelWriter = new BookGrossDateDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
			// 月次推移表

			excelInfo = new BookGrossMonthlyDocInfo();
			excelWriter = new BookGrossMonthlyDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			excelInfo = new BookGrossYearDocInfo();
			excelWriter = new BookGrossYearDocWriter(excelInfo);

		} else {
			throw new IllegalArgumentException();
		}


		try {
			String fileName = new StringBuilder("gross").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);
			excelInfo.setDocData(
					BookGrossDocDataGenerator.generateBookGrossDocData(bookGrossList));

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}

	/**
	 * 出力処理(利益率)
	 * @param form
	 * @param bookProfitList
	 * @return
	 * @throws Exception
	 */
	private byte[] outputFileForProfit(
			BookItemForm form, List<BookProfit> bookProfitList) throws Exception {

		DocInfo excelInfo = null;
		AbstractDocWriter excelWriter = null;

		String selectDateRange = form.getSelectDateRange();

		if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE))) {
			// 日付範囲指定

			excelInfo = new BookProfitDateDocInfo();
			excelWriter = new BookProfitDateDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_MONTHLY_TRANSITION, ViewProperties.VALUE))) {
			// 月次推移表

			excelInfo = new BookProfitMonthlyDocInfo();
			excelWriter = new BookProfitMonthlyDocWriter(excelInfo);

		} else if (selectDateRange.equals(
				ViewProperties.getInstance().getValue(ViewProperties.BOOK_SELECT_DATE_RANGE_YEAR_SUM, ViewProperties.VALUE))) {
			// 年度合計

			excelInfo = new BookProfitYearDocInfo();
			excelWriter = new BookProfitYearDocWriter(excelInfo);

		} else {
			throw new IllegalArgumentException();
		}


		try {
			String fileName = new StringBuilder("profit").append(
					Calendar.getInstance().getTimeInMillis()).toString();

			String excelFileName = new StringBuilder(
					CommonProperties.getInstance().getValue(CommonProperties.TMP_DIR))
						.append(File.separator)
						.append(fileName)
						.append(CommonConstants.PERIOD)
						.append(CommonConstants.EXTENTIONS_XLS).toString();

			excelInfo.setDelFlg(true);
			excelInfo.setOutFileName(excelFileName);
			excelInfo.setDocData(
					BookProfitDocDataGenerator.generateBookProfitDocData(bookProfitList));

			excelWriter.write();
			return FileUtils.readFileToByteArray(new File(excelFileName));
		} catch (DocWriterException e) {
			throw e;
		} finally {
			if (excelWriter != null) excelWriter.terminate();
		}
	}
}
