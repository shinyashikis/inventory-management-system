package master.supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.supplier.bl.SupplierClassBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import common.MsgResourcesConstants;
import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>仕入先分類一覧アクション</p>
 */
public class SupplierClassListAction extends AbstractCommonEventDispatchAction {

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
		SupplierClassListForm supplierClassListForm = (SupplierClassListForm)request.getSession(false).getAttribute("SupplierClassListForm");
		supplierClassListForm.setSel(null);
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
		request.setAttribute("SupplierClassForm", new SupplierClassForm());
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
		SupplierClassListForm listForm = (SupplierClassListForm)form;
		String code = listForm.getSel()[0];
		request.setAttribute("SupplierClassForm",
				SupplierClassBL.convMSupplierClassBeanToSupplierClassForm(listForm.getSupplierClassMap().get(code)));
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
		ActionMessages errors = SupplierClassBL.delete((SupplierClassListForm)form);
		if (errors != null) {
			saveErrors(request, errors);
			return mapping.findForward(FWD_FK_ERROR);
		}

		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(
				MsgResourcesConstants.COMMON_MSG_DELETE, "仕入先分類");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);

		request.getSession(false).setAttribute("SupplierClassListForm", SupplierClassBL.getSupplierClassListForm());
		return mapping.findForward(FWD_DELETE);
	}

}
