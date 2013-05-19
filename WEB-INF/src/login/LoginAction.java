package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import master.basic.bl.BasicBL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import common.MsgResourcesConstants;

import fw.core.base.AbstractCommonEventDispatchAction;
import fw.core.base.SystemSessionManager;
import fw.common.util.SystemSessionKey;

/**
 * <p>ログインアクション</p>
 */
public class LoginAction extends AbstractCommonEventDispatchAction {

	private static final String FWD_INIT = "init";
	private static final String FWD_LOGIN = "login";
	private static final String FWD_ERROR = "error";

	/**
	 * <p>初回処理</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward(FWD_INIT);
	}

	/**
	 * <p>ログイン</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ((LoginForm)form).getUser();
		String pswd = ((LoginForm)form).getPswd();

		HttpSession session = request.getSession(false);

		if (LoginBL.isOtherLogin(session, user)) {
			// 既に他のユーザがログインしています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.LOGIN_ERRMSG_OTHER_LOGIN);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.findForward(FWD_ERROR);
		}

/*
後勝ちログイン
		if (LoginBL.isAlreadyLogin(user)) {
			// 既にログインしています。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.LOGIN_ERRMSG_ALREADY_LOGIN);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.findForward(FWD_ERROR);
		}
*/

		if (!LoginBL.chkLogin(user, pswd)) {
			// ログイン認証に失敗しました。
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(
					MsgResourcesConstants.LOGIN_ERRMSG_LOGIN_FAIL);
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.findForward(FWD_ERROR);
		}

		session.invalidate();
		HttpSession newSession = request.getSession(true);

		saveToken(request);

		// ログインステータス更新
		LoginBL.updLoginStatus(user, newSession.getId());

		SystemSessionManager.setValue(
				newSession, SystemSessionKey.LOGIN_USER, user);

		SystemSessionManager.setValue(
				newSession, SystemSessionKey.BASIC_INFO, BasicBL.getBasicBean());

		return mapping.findForward(FWD_LOGIN);
	}
}
