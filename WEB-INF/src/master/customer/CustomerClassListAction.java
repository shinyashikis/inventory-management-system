package master.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.customer.bl.CustomerClassBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import common.MsgResourcesConstants;
import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>得意先分類一覧アクション</p>
 */
public class CustomerClassListAction extends AbstractCommonEventDispatchAction {

	private static final String FWD_INIT = "init";
	private static final String FWD_ADD = "add";
	private static final String FWD_UPDATE = "update";
	private static final String FWD_DELETE = "delete";
	private static final String FWD_FK_ERROR = "fkError";

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
		// チェックボックスの選択状態を解除
		CustomerClassListForm customerClassListForm = (CustomerClassListForm)request.getSession(false).getAttribute("CustomerClassListForm");
		customerClassListForm.setSel(null);
		return mapping.findForward(FWD_INIT);
	}

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
		request.setAttribute("CustomerClassForm", new CustomerClassForm());
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
		CustomerClassListForm listForm = (CustomerClassListForm)form;
		String code = listForm.getSel()[0];
		request.setAttribute("CustomerClassForm",
				CustomerClassBL.convMCustomerClassBeanToCustomerClassForm(listForm.getCustomerClassMap().get(code)));
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
		ActionMessages errors = CustomerClassBL.delete((CustomerClassListForm)form);
		if (errors != null) {
			// 外部キー制約エラー時
			saveErrors(request, errors);
			return mapping.findForward(FWD_FK_ERROR);
		}

		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(
				MsgResourcesConstants.COMMON_MSG_DELETE, "得意先分類");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);

		request.getSession(false).setAttribute("CustomerClassListForm", CustomerClassBL.getCustomerClassListForm());
		return mapping.findForward(FWD_DELETE);
	}

}
