package logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import fw.core.base.AbstractCommonAction;
import common.MsgResourcesConstants;

/**
 * <p>ログアウトアクション</p>
 */
public class LogoutAction extends AbstractCommonAction {

	private static final String FWD_LOGOUT = "logout";

	@Override
	protected ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionMessages messages = new ActionMessages();
		ActionMessage message = new ActionMessage(
				MsgResourcesConstants.LOGOUT_MSG_LOGOUT);
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);

		request.getSession(false).invalidate();

		return mapping.findForward(FWD_LOGOUT);
	}
}
