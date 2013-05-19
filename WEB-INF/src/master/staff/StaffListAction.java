package master.staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.staff.bl.StaffBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import common.MsgResourcesConstants;
import fw.core.base.AbstractCommonEventDispatchAction;

/**
 * <p>担当者設定(一覧)アクション</p>
 */
public class StaffListAction extends AbstractCommonEventDispatchAction {

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
		request.setAttribute("StaffForm", new StaffForm());
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
		StaffListForm listForm = (StaffListForm)form;
		String code = listForm.getSel()[0];
		request.setAttribute("StaffForm",
				StaffBL.convMStaffBeanToStaffForm(listForm.getStaffMap().get(code)));
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
		StaffBL.delete((StaffListForm)form);
		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(
				MsgResourcesConstants.COMMON_MSG_DELETE, "担当者");
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);

		request.getSession(false).setAttribute("StaffListForm", StaffBL.getStaffListForm());
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
		request.getSession(false).removeAttribute("StaffListForm");
		return mapping.findForward(FWD_BACK);
	}

}
