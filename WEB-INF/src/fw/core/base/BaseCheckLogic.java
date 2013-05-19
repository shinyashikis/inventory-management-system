package fw.core.base;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;

import fw.common.db.SQLProperties;
import fw.common.db.dao.CLoginStatusDAO;
import fw.common.db.dto.CLoginStatusDTO;
import fw.common.db.dto.CommonDTO;
import fw.common.util.SystemSessionKey;

public class BaseCheckLogic {

	static final String CONFIG_PROP_TOKEN_KEY = "token";
	static final String CONFIG_PROP_TOKEN__VAL_SAVE = "save";
	static final String CONFIG_PROP_TOKEN__VAL_RESET = "reset";
	static final String CONFIG_PROP_TOKEN__VAL_CHECK = "check";
	static final String CONFIG_PROP_TOKEN__VAL_CHECK_SAVE = "check_save";
	static final String CONFIG_PROP_TOKEN__VAL_CHECK_RESET = "check_reset";

	/**
	 * <p>ログインチェック</p>
	 * @param session
	 * @return
	 */
	static boolean isLogin(HttpSession session) {
		Object user = SystemSessionManager.getValue(session, SystemSessionKey.LOGIN_USER);
		return user != null;
	}

	/**
	 * <p>ログイン有効チェック</p>
	 * @param session
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	static boolean isAvailableLogin(HttpSession session) throws IOException, SQLException {
		Object sessionUser = SystemSessionManager.getValue(session, SystemSessionKey.LOGIN_USER);

		Connection conn = TransactionInfo.getConnection();
		CLoginStatusDAO dao = new CLoginStatusDAO(conn);
		String sql = SQLProperties.getInstance().getValue(CLoginStatusDAO.SQLID001);
		List<Object> params = new ArrayList<Object>();
		params.add(sessionUser);

		String sid = null;

		List<CommonDTO> ret = dao.select(sql, params);
		for (CommonDTO dto : ret) {
			sid = ((CLoginStatusDTO)dto).getSid();
		}

		return ((sid != null) && sid.equals(session.getId()));
	}

	/**
	 * <p>同期トークンチェック処理</p>
	 * @param request
	 * @param action
	 * @param token
	 */
	static boolean checkToken(HttpServletRequest request, Action action, String token) {
		if (token == null) {
			return true;
		}

		if (token.equals(CONFIG_PROP_TOKEN__VAL_SAVE)) {
			resetToken(request, action);
			saveToken(request, action);

		} else if (token.equals(CONFIG_PROP_TOKEN__VAL_RESET)) {
			resetToken(request, action);

		} else if (token.equals(CONFIG_PROP_TOKEN__VAL_CHECK)) {
			if (!isTokenValid(request, action, false)) {
				return false;
			}

		} else if (token.equals(CONFIG_PROP_TOKEN__VAL_CHECK_SAVE)) {
			if (!isTokenValid(request, action, false)) {
				return false;
			}
			resetToken(request, action);
			saveToken(request, action);

		} else if (token.equals(CONFIG_PROP_TOKEN__VAL_CHECK_RESET)) {
			if (!isTokenValid(request, action, false)) {
				return false;
			}
			resetToken(request, action);
		}

		return true;
	}

	/**
	 * <p>トークン生成処理</p>
	 * @param request
	 * @param action
	 */
	private static void saveToken(HttpServletRequest request, Action action) {
		if (action instanceof AbstractCommonAction) {
			((AbstractCommonAction)action).saveToken(request);
		} else if (action instanceof AbstractCommonEventDispatchAction) {
			((AbstractCommonEventDispatchAction)action).saveToken(request);
		}
	}

	/**
	 * <p>トークン削除処理</p>
	 * @param request
	 * @param action
	 */
	private static void resetToken(HttpServletRequest request, Action action) {
		if (action instanceof AbstractCommonAction) {
			((AbstractCommonAction)action).resetToken(request);
		} else if (action instanceof AbstractCommonEventDispatchAction) {
			((AbstractCommonEventDispatchAction)action).resetToken(request);
		}
	}

	/**
	 * <p>トークンチェック処理</p>
	 * @param request
	 * @param action
	 * @param reset
	 * @return
	 */
	private static boolean isTokenValid(HttpServletRequest request, Action action, boolean reset) {
		boolean ret = true;
		if (action instanceof AbstractCommonAction) {
			ret = ((AbstractCommonAction)action).isTokenValid(request, reset);
		} else if (action instanceof AbstractCommonEventDispatchAction) {
			ret = ((AbstractCommonEventDispatchAction)action).isTokenValid(request, reset);
		}
		return ret;
	}
}
