package menu;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.basic.bean.BasicBean;
import master.basic.bl.BasicBL;
import master.customer.bl.CustomerBL;
import master.customer.bl.CustomerClassBL;
import master.item.bl.ItemBL;
import master.item.bl.ItemClassBL;
import master.staff.bl.StaffBL;
import master.supplier.bl.SupplierBL;
import master.supplier.bl.SupplierClassBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import book.common.BookBL;

import common.BizCommonConstants;

import voucher.bill.bl.BillBL;
import voucher.order.bl.OrderBL;
import voucher.purchase.bl.PurchaseBL;
import voucher.sales.bl.SalesBL;
import voucher.quotation.bl.QuotationBL;

import fw.common.util.SystemSessionKey;
import fw.core.base.AbstractCommonEventDispatchAction;
import fw.core.base.SystemSessionManager;

/**
 * <p>メニューアクション</p>
 */
public class MenuAction extends AbstractCommonEventDispatchAction {

	// 基本設定
	private static final String FWD_BASIC = "basic";
	private static final String FWD_STAFF = "staff";
	private static final String FWD_ITEM = "item";
	private static final String FWD_CUSTOMER = "customer";
	private static final String FWD_SUPPLIER = "supplier";

	// 伝票／印刷
	private static final String FWD_QUOTATION = "quotation";
	private static final String FWD_ORDER = "order";
	private static final String FWD_SALES = "sales";
	private static final String FWD_PURCHASE = "purchase";
	private static final String FWD_BILL = "bill";

	// 帳簿
	private static final String FWD_BOOK_ITEM = "book_item";
	private static final String FWD_BOOK_SALES = "book_sales";
	private static final String FWD_BOOK_PURCHASE = "book_purchase";
	private static final String FWD_BOOK_VOUCHER_LIST = "book_voucher_list";

	/**
	 * <p>「基本設定」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward basic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("BasicForm", BasicBL.getBasicForm());
		return mapping.findForward(FWD_BASIC);
	}

	/**
	 * <p>「担当者設定」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward staff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).setAttribute("StaffListForm", StaffBL.getStaffListForm());
		return mapping.findForward(FWD_STAFF);
	}

	/**
	 * <p>「商品登録」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward item(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).setAttribute("ItemListForm", ItemBL.getItemListForm());
		request.getSession(false).setAttribute("ItemClassListForm", ItemClassBL.getItemClassListForm());
		return mapping.findForward(FWD_ITEM);
	}

	/**
	 * <p>「得意先登録」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).setAttribute("CustomerListForm", CustomerBL.getCustomerListForm());
		request.getSession(false).setAttribute("CustomerClassListForm", CustomerClassBL.getCustomerClassListForm());
		request.getSession(false).setAttribute("MStaffBeanList", StaffBL.getMStaffBeanList());
		return mapping.findForward(FWD_CUSTOMER);
	}

	/**
	 * <p>「仕入先登録」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward supplier(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).setAttribute("SupplierListForm", SupplierBL.getSupplierListForm());
		request.getSession(false).setAttribute("SupplierClassListForm", SupplierClassBL.getSupplierClassListForm());
		request.getSession(false).setAttribute("MStaffBeanList", StaffBL.getMStaffBeanList());
		return mapping.findForward(FWD_SUPPLIER);
	}

	/**
	 * <p>「見積書」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward quotation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 伝票BeanMapをセッションに格納
		request.getSession(false).setAttribute(
				BizCommonConstants.BEAN_NAME_VOUCHER_BEAN_MAP, QuotationBL.getVoucherBeanMap(taxValue));
		return mapping.findForward(FWD_QUOTATION);
	}

	/**
	 * <p>「注文書」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward order(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 伝票BeanMapをセッションに格納
		request.getSession(false).setAttribute(
				BizCommonConstants.BEAN_NAME_VOUCHER_BEAN_MAP, OrderBL.getVoucherBeanMap(taxValue));
		return mapping.findForward(FWD_ORDER);
	}

	/**
	 * <p>「売上伝票」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sales(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 伝票BeanMapをセッションに格納
		request.getSession(false).setAttribute(
				BizCommonConstants.BEAN_NAME_VOUCHER_BEAN_MAP, SalesBL.getVoucherBeanMap(taxValue));
		return mapping.findForward(FWD_SALES);
	}

	/**
	 * <p>「仕入伝票」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward purchase(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 伝票BeanMapをセッションに格納
		request.getSession(false).setAttribute(
				BizCommonConstants.BEAN_NAME_VOUCHER_BEAN_MAP, PurchaseBL.getVoucherBeanMap(taxValue));
		return mapping.findForward(FWD_PURCHASE);
	}

	/**
	 * <p>「請求書」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bill(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 伝票BeanMapをセッションに格納
		request.getSession(false).setAttribute(
				BizCommonConstants.BEAN_NAME_VOUCHER_BEAN_MAP, BillBL.getVoucherBeanMap(taxValue));
		return mapping.findForward(FWD_BILL);
	}

	/**
	 * <p>「商品情報」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bookItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).setAttribute(
				"BookItemForm", BookBL.initBookItemForm());
		request.getSession(false).setAttribute(
				"BookBean", BookBL.initBookBeanForItem());
		return mapping.findForward(FWD_BOOK_ITEM);
	}

	/**
	 * <p>「売上情報」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bookSales(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).setAttribute(
				"BookSalesForm", BookBL.initBookSalesForm());
		request.getSession(false).setAttribute(
				"BookBean", BookBL.initBookBeanForSales());
		return mapping.findForward(FWD_BOOK_SALES);
	}

	/**
	 * <p>「仕入情報」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bookPurchase(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).setAttribute(
				"BookPurchaseForm", BookBL.initBookPurchaseForm());
		request.getSession(false).setAttribute(
				"BookBean", BookBL.initBookBeanForPurchase());
		return mapping.findForward(FWD_BOOK_PURCHASE);
	}

	/**
	 * <p>「伝票一覧表」押下時</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bookVoucherList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).setAttribute(
				"BookVoucherListForm", BookBL.initBookVoucherListForm());
		request.getSession(false).setAttribute(
				"BookBean", BookBL.initBookBeanForVoucherList());
		return mapping.findForward(FWD_BOOK_VOUCHER_LIST);
	}
}
