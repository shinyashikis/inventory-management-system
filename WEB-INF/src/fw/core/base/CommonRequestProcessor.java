package fw.core.base;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.RequestProcessor;

import fw.common.db.ConnectionFactory;
import fw.common.util.StrutsConfigConstants;
import fw.core.common.ComMsgResourcesConstants;

/**
 * <p>共通リクエストプロセッサ</p>
 */
public class CommonRequestProcessor extends RequestProcessor {

	/**
	 * <p>アクションクラス呼出</p>
	 * @see org.apache.struts.action.RequestProcessor#processActionPerform(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.struts.action.Action, org.apache.struts.action.ActionForm, org.apache.struts.action.ActionMapping)
	 */
	@Override
	protected ActionForward processActionPerform(HttpServletRequest request,
			HttpServletResponse response, Action action, ActionForm form,
			ActionMapping mapping) throws IOException, ServletException {

		ActionForward forward = null;
		Connection conn = null;

		try {
			TransactionInfo.init();
			conn = ConnectionFactory.getConnection();
			TransactionInfo.setConnection(conn);

			ActionMessages errors = chkCommon(action, request);
			if (errors != null) {
				return mapping.findForward(StrutsConfigConstants.FWD_ERROR);
			}

			forward = action.execute(mapping, form, request, response);

			conn.commit();

			return forward;

		} catch (Exception e) {
			e.printStackTrace();
			forward = processException(request, response, e, form, mapping);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
				throw new ServletException(e1);
			}
			return forward;

		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
				TransactionInfo.terminate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
	}

	/**
	 * <p>共通チェック</p>
	 * @param action
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	private ActionMessages chkCommon(Action action, HttpServletRequest request) throws IOException, SQLException {

		// ログイン済みチェック
		String actionName = action.getClass().getSimpleName();
		if (!actionName.equals(StrutsConfigConstants.ACTION_LOGIN)) {
			HttpSession session = request.getSession(false);
			if (!BaseCheckLogic.isLogin(session)) {
				ActionMessages errors = new ActionMessages();
				ActionMessage error = new ActionMessage(ComMsgResourcesConstants.COMMON_ERRMSG_NOLOGIN);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				request.setAttribute("org.apache.struts.action.ERROR", errors);
				return errors;
			}
			if (!BaseCheckLogic.isAvailableLogin(session)) {
				// 後勝ちログインされた場合
				ActionMessages errors = new ActionMessages();
				ActionMessage error = new ActionMessage(ComMsgResourcesConstants.COMMON_ERRMSG_LOGIN_INVALID);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				request.setAttribute("org.apache.struts.action.ERROR", errors);
				return errors;
			}
		}

		return null;
	}

}
