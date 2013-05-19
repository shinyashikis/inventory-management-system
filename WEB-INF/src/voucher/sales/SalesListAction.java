package voucher.sales;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.basic.bean.BasicBean;

import org.apache.commons.lang.SerializationUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import voucher.common.bean.VoucherBean;
import voucher.sales.bl.SalesBL;

import common.BizCommonConstants;

import fw.common.util.SystemSessionKey;
import fw.core.base.AbstractCommonEventDispatchAction;
import fw.core.base.SystemSessionManager;

/**
 * <p>売上伝票一覧アクション</p>
 */
public class SalesListAction extends AbstractCommonEventDispatchAction {

	/**
	 * 「登録」ボタン押下時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 売上伝票FormBean初期処理
		SalesForm salesForm = new SalesForm();
		salesForm.setVoucherNo(SalesBL.getNewVoucherNo());

		// 売上伝票FormBeanを元に伝票Beanを作成
		VoucherBean voucherBean = new VoucherBean();
		SalesBL.reflectInputToVoucherBean(voucherBean, salesForm);

		// 売上伝票FormBeanおよび伝票Beanをセッションに格納
		request.getSession(false).setAttribute(BizCommonConstants.FORM_BEAN_NAME_SALES_FORM, salesForm);
		request.getSession(false).setAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN, voucherBean);

		// 不要となるセッション情報を破棄
		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_SALES_LIST_FORM);

		return mapping.findForward(BizCommonConstants.FWD_ADD);
	}

	/**
	 * 「更新」ボタン押下時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SalesListForm listForm = (SalesListForm) form;
		String voucherNo = listForm.getSel()[0];

		// セッションの伝票BeanMapより選択した伝票Beanを取得。セッションに格納
		Map<String, VoucherBean> voucherBeanMap = (Map<String, VoucherBean>)
			request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN_MAP);
		VoucherBean voucherBean = (VoucherBean)SerializationUtils.clone(voucherBeanMap.get(voucherNo));
		request.getSession(false).setAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN, voucherBean);

		// 伝票Beanを元に売上伝票FormBeanを作成。セッションに格納
		SalesForm salesForm = new SalesForm();
		SalesBL.reflectVoucherBeanToSalesForm(salesForm, voucherBean);
		request.getSession(false).setAttribute(BizCommonConstants.FORM_BEAN_NAME_SALES_FORM, salesForm);

		// 不要となるセッション情報を破棄
		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_SALES_LIST_FORM);

		return mapping.findForward(BizCommonConstants.FWD_UPDATE);
	}

	/**
	 * 「削除」ボタン押下時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		SalesListForm listForm = (SalesListForm) form;
		String[] voucherNo = listForm.getSel();

		// 削除処理
		SalesBL.delete(voucherNo);

		// セッションより、消費税率を取得
		BigDecimal taxValue = ((BasicBean)SystemSessionManager.getValue(
				request.getSession(false), SystemSessionKey.BASIC_INFO)).getTaxVal();

		// 伝票BeanMap再取得後、セッションに格納する
		request.getSession(false).setAttribute(
				BizCommonConstants.BEAN_NAME_VOUCHER_BEAN_MAP, SalesBL.getVoucherBeanMap(taxValue));

		return mapping.findForward(BizCommonConstants.FWD_DELETE);
	}

	/**
	 * 「戻る」ボタン押下時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 不要となるセッション情報を破棄
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN_MAP);
		request.getSession(false).removeAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);
		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_SALES_LIST_FORM);
		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_SALES_FORM);
		return mapping.findForward(BizCommonConstants.FWD_BACK);
	}
}
