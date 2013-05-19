package voucher.common.dialog;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.BizCommonConstants;

import voucher.common.bean.DealBean;
import voucher.common.bean.VoucherBean;
import voucher.common.dialog.bl.VoucherSupplierBL;

import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>(伝票)仕入先一覧アクション</p>
 */
public class VoucherSupplierListAction extends AbstractCommonEventDispatchAction {

	/**
	 * 初期表示時
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object paramDealCode = request.getParameter("dealCode");
		Object paramVoucherDate = request.getParameter("voucherDate");
		String targetDealCode = (paramDealCode != null) ? (String)paramDealCode : null;
		String targetVoucherDate = (paramVoucherDate != null) ? (String)paramVoucherDate : null;

		// 対象となる得意先(取引先BeanMap)を取得後、セッションへ取引先BeanMapを格納する
		request.getSession(false).setAttribute(
				BizCommonConstants.BEAN_NAME_DEAL_BEAN_MAP, VoucherSupplierBL.getDealBeanMap(targetDealCode, targetVoucherDate));
		return mapping.findForward(BizCommonConstants.FWD_INIT);
	}

	/**
	 * 閉じるボタン押下時
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward close(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		VoucherSupplierListForm listForm = (VoucherSupplierListForm)form;
		String[] sel = listForm.getSel();

		if (sel != null) {
			String dealCode = sel[0];

			// 選択した取引先Beanを取得
			Map<String, DealBean> dealBeanMap = (Map<String, DealBean>)
				request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_DEAL_BEAN_MAP);
			DealBean selDealBean = dealBeanMap.get(dealCode);

			// セッション内の伝票Bean取得
			VoucherBean voucherBean = (VoucherBean)
				request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

			// 伝票Beanが保持する取引先Beanを差し替える
			voucherBean.setDealBean(selDealBean);
		}

		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_VOUCHER_SUPPLIER_LIST_FORM);
		return mapping.findForward(BizCommonConstants.FWD_CLOSE);
	}
}
