package master.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.customer.bl.CustomerBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import prop.ViewProperties;

import common.MsgResourcesConstants;
import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>得意先一覧アクション</p>
 */
public class CustomerListAction extends AbstractCommonEventDispatchAction {

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
		CustomerForm nextForm = new CustomerForm();
		nextForm.setDealDivision(ViewProperties.getInstance().getValue(ViewProperties.DEAL_DIVISION_CASH, ViewProperties.VALUE));
		request.setAttribute("CustomerForm", nextForm);
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
		CustomerListForm listForm = (CustomerListForm)form;
		String code = listForm.getSel()[0];
		request.setAttribute("CustomerForm",
				CustomerBL.convMCustomerBeanToCusotmerForm(listForm.getCustomerMap().get(code)));
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
		CustomerBL.delete((CustomerListForm)form);
		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(
				MsgResourcesConstants.COMMON_MSG_DELETE, "得意先");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);

		request.getSession(false).setAttribute("CustomerListForm", CustomerBL.getCustomerListForm());
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
		request.getSession(false).removeAttribute("CustomerListForm");
		request.getSession(false).removeAttribute("CustomerClassListForm");
		request.getSession(false).removeAttribute("MStaffBeanList");
		return mapping.findForward(FWD_BACK);
	}

}
