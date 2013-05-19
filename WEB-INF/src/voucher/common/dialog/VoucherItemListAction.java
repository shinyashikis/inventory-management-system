package voucher.common.dialog;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import voucher.common.bean.DetailBean;
import voucher.common.bean.VoucherBean;
import voucher.common.dialog.bl.VoucherItemBL;

import common.BizCommonConstants;

import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>(伝票)商品一覧アクション</p>
 */
public class VoucherItemListAction extends AbstractCommonEventDispatchAction {

	/**
	 * 初期表示時
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Object paramItemCode = request.getParameter("itemCode");
		String targetItemCode = (paramItemCode != null) ? (String)paramItemCode : null;

		// セッションより伝票Beanを取得
		VoucherBean voucherBean = (VoucherBean)
			request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

		// 対象となる商品(明細BeanMap)を取得後、セッションへ明細BeanMapを格納する
		request.getSession(false).setAttribute(
				BizCommonConstants.BEAN_NAME_DETAIL_BEAN_MAP,
				VoucherItemBL.getDetailBeanMap(voucherBean, targetItemCode));

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

		VoucherItemListForm listForm = (VoucherItemListForm)form;
		String[] sel = listForm.getSel();

		if (sel != null) {
			// セッションより伝票Beanを取得
			VoucherBean voucherBean = (VoucherBean)
				request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_VOUCHER_BEAN);

			// セッションより明細BeanMapを取得
			Map<String, DetailBean> detailBeanMap = (Map<String, DetailBean>)
				request.getSession(false).getAttribute(BizCommonConstants.BEAN_NAME_DETAIL_BEAN_MAP);

			int index = voucherBean.getDetailList().size();

			for (String itemCode : sel) {
				// 伝票Beanへ、画面にて選択した明細Beanを追加する
				DetailBean selDetailBean = detailBeanMap.get(itemCode);
				selDetailBean.setSeq(Integer.toString(index++));
				voucherBean.addDetail(selDetailBean);
			}

		}

		request.getSession(false).removeAttribute(BizCommonConstants.FORM_BEAN_NAME_VOUCHER_ITEM_LIST_FORM);
		return mapping.findForward(BizCommonConstants.FWD_CLOSE);
	}

}
