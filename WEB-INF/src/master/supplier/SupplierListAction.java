package master.supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.supplier.bl.SupplierBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import prop.ViewProperties;

import common.MsgResourcesConstants;
import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>仕入先一覧アクション</p>
 */
public class SupplierListAction extends AbstractCommonEventDispatchAction {

	private static final String FWD_ADD = "add";
	private static final String FWD_UPDATE = "update";
	private static final String FWD_DELETE = "delete";
	private static final String FWD_BACK = "back";

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
		SupplierForm supplierForm = new SupplierForm();
		supplierForm.setDealDivision(ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE));
		supplierForm.setTax(ViewProperties.getInstance().getValue(ViewProperties.TAX_SETTING_KAZEI, ViewProperties.VALUE));
		supplierForm.setCalc(ViewProperties.getInstance().getValue(ViewProperties.CALC_UCHIZEI, ViewProperties.VALUE));
		supplierForm.setCalcHasu(ViewProperties.getInstance().getValue(ViewProperties.CALC_HASU_SHISYAGONYU, ViewProperties.VALUE));
		request.setAttribute("SupplierForm", supplierForm);
		return mapping.findForward(FWD_ADD);
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
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SupplierListForm listForm = (SupplierListForm)form;
		String code = listForm.getSel()[0];
		request.setAttribute("SupplierForm",
				SupplierBL.convMSupplierBeanToCusotmerForm(listForm.getSupplierMap().get(code)));
		return mapping.findForward(FWD_UPDATE);
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
		SupplierBL.delete((SupplierListForm)form);
		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(
				MsgResourcesConstants.COMMON_MSG_DELETE, "得意先");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);

		request.getSession(false).setAttribute("SupplierListForm", SupplierBL.getSupplierListForm());
		return mapping.findForward(FWD_DELETE);
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
		request.getSession(false).removeAttribute("SupplierListForm");
		request.getSession(false).removeAttribute("SupplierClassListForm");
		request.getSession(false).removeAttribute("MStaffBeanList");
		return mapping.findForward(FWD_BACK);
	}

}
