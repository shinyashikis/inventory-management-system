package book.common;

import java.io.IOException;
import java.sql.SQLException;

import fw.common.date.DateFormatPattern;
import fw.common.date.DateUtility;

import prop.ViewProperties;

import book.item.BookItemForm;
import book.purchase.BookPurchaseForm;
import book.sales.BookSalesForm;
import book.voucher.BookVoucherListForm;

import master.customer.bl.CustomerBL;
import master.customer.bl.CustomerClassBL;
import master.item.bl.ItemBL;
import master.item.bl.ItemClassBL;
import master.staff.bl.StaffBL;
import master.supplier.bl.SupplierBL;
import master.supplier.bl.SupplierClassBL;

/**
 * 帳簿ビジネスロジック
 */
public class BookBL {

	/**
	 * (帳簿)商品情報フォーム初期化処理
	 *
	 * @return
	 */
	public static BookItemForm initBookItemForm() {
		BookItemForm bookItemForm = new BookItemForm();
		// 表示対象指定
		bookItemForm.setDispTarget(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_ITEM_STOCK, ViewProperties.VALUE));
		// 表示対象詳細指定
		bookItemForm.setDispTargetDetail(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_DETAIL_COUNT, ViewProperties.VALUE));
		// 日付範囲指定
		bookItemForm.setSelectDateRange(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE));

		String thisMonth = DateUtility.getSysDate(DateFormatPattern.PATTERN_M);
		// 検索日付FROM月
		bookItemForm.setSearchDateMonthFrom(thisMonth);
		// 検索日付FROM日
		bookItemForm.setSearchDateDayFrom("1");
		// 検索日付TO月
		bookItemForm.setSearchDateMonthTo(thisMonth);
		// 検索日付TO日
		bookItemForm.setSearchDateDayTo(DateUtility.getSysDate(DateFormatPattern.PATTERN_D));

		// 対象年度
		bookItemForm.setNendo(DateUtility.getSysDate(DateFormatPattern.PATTERN_YYYY));

		// 表示順序指定
		bookItemForm.setSelectSort(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_ASC, ViewProperties.VALUE));
		bookItemForm.setSelectSortKind(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_KIND_KANA, ViewProperties.VALUE));
		return bookItemForm;
	}

	/**
	 * (帳簿)売上情報フォーム初期化処理
	 *
	 * @return
	 */
	public static BookSalesForm initBookSalesForm() {
		BookSalesForm bookSalesForm = new BookSalesForm();
		// 表示対象指定
		bookSalesForm.setDispTarget(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_SALES_TOTAL, ViewProperties.VALUE));
		// 表示対象詳細指定
		bookSalesForm.setDispTargetDetail(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_DETAIL_VOUCHER, ViewProperties.VALUE));
		// 日付範囲指定
		bookSalesForm.setSelectDateRange(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE));

		String thisMonth = DateUtility.getSysDate(DateFormatPattern.PATTERN_M);
		// 検索日付FROM月
		bookSalesForm.setSearchDateMonthFrom(thisMonth);
		// 検索日付FROM日
		bookSalesForm.setSearchDateDayFrom("1");
		// 検索日付TO月
		bookSalesForm.setSearchDateMonthTo(thisMonth);
		// 検索日付TO日
		bookSalesForm.setSearchDateDayTo(DateUtility.getSysDate(DateFormatPattern.PATTERN_D));

		// 対象年度
		bookSalesForm.setNendo(DateUtility.getSysDate(DateFormatPattern.PATTERN_YYYY));

		// 表示順序指定
		bookSalesForm.setSelectSort(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_ASC, ViewProperties.VALUE));
		bookSalesForm.setSelectSortKind(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_KIND_KANA, ViewProperties.VALUE));
		return bookSalesForm;
	}

	/**
	 * (帳簿)仕入情報フォーム初期化処理
	 *
	 * @return
	 */
	public static BookPurchaseForm initBookPurchaseForm() {
		BookPurchaseForm bookPurchaseForm = new BookPurchaseForm();
		// 表示対象指定
		bookPurchaseForm.setDispTarget(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_PURCHASE_TOTAL, ViewProperties.VALUE));
		// 表示対象詳細指定
		bookPurchaseForm.setDispTargetDetail(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_DETAIL_VOUCHER, ViewProperties.VALUE));
		// 日付範囲指定
		bookPurchaseForm.setSelectDateRange(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE));

		String thisMonth = DateUtility.getSysDate(DateFormatPattern.PATTERN_M);
		// 検索日付FROM月
		bookPurchaseForm.setSearchDateMonthFrom(thisMonth);
		// 検索日付FROM日
		bookPurchaseForm.setSearchDateDayFrom("1");
		// 検索日付TO月
		bookPurchaseForm.setSearchDateMonthTo(thisMonth);
		// 検索日付TO日
		bookPurchaseForm.setSearchDateDayTo(DateUtility.getSysDate(DateFormatPattern.PATTERN_D));

		// 対象年度
		bookPurchaseForm.setNendo(DateUtility.getSysDate(DateFormatPattern.PATTERN_YYYY));

		// 表示順序指定
		bookPurchaseForm.setSelectSort(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_ASC, ViewProperties.VALUE));
		bookPurchaseForm.setSelectSortKind(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_KIND_KANA, ViewProperties.VALUE));
		return bookPurchaseForm;
	}

	/**
	 * (帳簿)伝票一覧表フォーム初期化処理
	 *
	 * @return
	 */
	public static BookVoucherListForm initBookVoucherListForm() {
		BookVoucherListForm bookVoucherListForm = new BookVoucherListForm();
		// 表示対象指定
		bookVoucherListForm.setDispTarget(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_DISP_TARGET_QUOTATION, ViewProperties.VALUE));
		// 日付範囲指定
		bookVoucherListForm.setSelectDateRange(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_DATE_RANGE_DATE, ViewProperties.VALUE));

		String thisMonth = DateUtility.getSysDate(DateFormatPattern.PATTERN_M);
		// 検索日付FROM月
		bookVoucherListForm.setSearchDateMonthFrom(thisMonth);
		// 検索日付FROM日
		bookVoucherListForm.setSearchDateDayFrom("1");
		// 検索日付TO月
		bookVoucherListForm.setSearchDateMonthTo(thisMonth);
		// 検索日付TO日
		bookVoucherListForm.setSearchDateDayTo(DateUtility.getSysDate(DateFormatPattern.PATTERN_D));

		// 対象年度
		bookVoucherListForm.setNendo(DateUtility.getSysDate(DateFormatPattern.PATTERN_YYYY));

		// 表示順序指定
		bookVoucherListForm.setSelectSort(
				ViewProperties.getInstance().getValue(prop.ViewProperties.BOOK_SELECT_SORT_ASC, ViewProperties.VALUE));
		return bookVoucherListForm;
	}

	/**
	 * 帳簿情報Bean初期化処理(商品情報用)
	 *
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static BookBean initBookBeanForItem() throws IOException, SQLException {
		BookBean bookBean = new BookBean();
		bookBean.setBookKind(ViewProperties.getInstance().getValue(
				ViewProperties.BOOK_KIND_ITEM, ViewProperties.VALUE));
		bookBean.setmItemBeanMap(ItemBL.getMItemBeanList());
		bookBean.setmItemClassBeanMap(ItemClassBL.getMItemClassBeanMap());

		return bookBean;
	}

	/**
	 * 帳簿情報Bean初期化処理(売上情報用)
	 *
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static BookBean initBookBeanForSales() throws IOException, SQLException {
		BookBean bookBean = new BookBean();
		bookBean.setBookKind(ViewProperties.getInstance().getValue(
				ViewProperties.BOOK_KIND_SALES, ViewProperties.VALUE));
		bookBean.setmCustomerBeanMap(CustomerBL.getMCustomerBeanMap());
		bookBean.setmCustomerClassBeanMap(CustomerClassBL.getMCustomerClassBeanMap());
		bookBean.setmStaffBeanMap(StaffBL.getMStaffBeanMap());
		return bookBean;
	}

	/**
	 * 帳簿情報Bean初期化処理(仕入情報用)
	 *
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static BookBean initBookBeanForPurchase() throws IOException, SQLException {
		BookBean bookBean = new BookBean();
		bookBean.setBookKind(ViewProperties.getInstance().getValue(
				ViewProperties.BOOK_KIND_PURCHASE, ViewProperties.VALUE));
		bookBean.setmSupplierBeanMap(SupplierBL.getMSupplierBeanMap());
		bookBean.setmSupplierClassBeanMap(SupplierClassBL.getMSupplierClassBeanMap());
		bookBean.setmStaffBeanMap(StaffBL.getMStaffBeanMap());
		return bookBean;
	}

	/**
	 * 帳簿情報Bean初期化処理(伝票一覧表用)
	 *
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static BookBean initBookBeanForVoucherList() throws IOException, SQLException {
		BookBean bookBean = new BookBean();
		bookBean.setBookKind(ViewProperties.getInstance().getValue(
				ViewProperties.BOOK_KIND_VOUCHER_LIST, ViewProperties.VALUE));
		return bookBean;
	}

	/**
	 * コードソートチェック
	 *
	 * @param form
	 * @return
	 */
	public static boolean isSortCode(BookItemForm form) {
		String selectSortKind = form.getSelectSortKind();

		ViewProperties viewProp = ViewProperties.getInstance();
		if (viewProp.getValue(
				ViewProperties.BOOK_SELECT_SORT_KIND_CODE, ViewProperties.VALUE).equals(selectSortKind)) {
			return true;

		} else if (viewProp.getValue(
					ViewProperties.BOOK_SELECT_SORT_KIND_KANA, ViewProperties.VALUE).equals(selectSortKind)) {
			return false;

		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 降順チェック
	 *
	 * @param form
	 * @return
	 */
	public static boolean isSortDesc(BookItemForm form) {
		String selectSort = form.getSelectSort();

		ViewProperties viewProp = ViewProperties.getInstance();
		if (viewProp.getValue(
				ViewProperties.BOOK_SELECT_SORT_DSC, ViewProperties.VALUE).equals(selectSort)) {
			return true;

		} else if (viewProp.getValue(
					ViewProperties.BOOK_SELECT_SORT_ASC, ViewProperties.VALUE).equals(selectSort)) {
			return false;

		} else {
			throw new IllegalArgumentException();
		}
	}
}
