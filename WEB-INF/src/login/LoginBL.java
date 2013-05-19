package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import fw.common.db.SQLProperties;
import fw.common.db.dao.CLoginStatusDAO;
import fw.common.db.dao.CUserDAO;
import fw.common.db.dto.CommonDTO;
import fw.common.util.SystemSessionKey;
import fw.core.base.SystemSessionManager;
import fw.core.base.TransactionInfo;

public class LoginBL {

	/**
	 * <p>他ユーザログインチェック</p>
	 * 同一セッション内で他のユーザがログインしているか確認。
	 * @param session
	 * @param user
	 * @return
	 */
	static boolean isOtherLogin(HttpSession session, String user) {
		Object sessionUser = SystemSessionManager.getValue(session, SystemSessionKey.LOGIN_USER);
		if (sessionUser == null) {
			return false;
		}
		return !user.equals(sessionUser);
	}

	/**
	 * <p>ログイン済みチェック</p>
	 * ユーザが既にログインしているか確認。
	 * @param user
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	static boolean isAlreadyLogin(String user) throws IOException, SQLException {
		Connection conn = TransactionInfo.getConnection();
		CLoginStatusDAO dao = new CLoginStatusDAO(conn);
		String sql = SQLProperties.getInstance().getValue(CLoginStatusDAO.SQLID001);
		List<Object> params = new ArrayList<Object>();
		params.add(user);
		List<CommonDTO> ret = dao.select(sql, params);
		return ret.size() > 0;
	}

	/**
	 * <p>ログイン認証チェック</p>
	 * @param user
	 * @param pswd
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	static boolean chkLogin(String user, String pswd) throws IOException, SQLException {
		Connection conn = TransactionInfo.getConnection();
		CUserDAO dao = new CUserDAO(conn);
		String sql = SQLProperties.getInstance().getValue(CUserDAO.SQLID001);
		List<Object> params = new ArrayList<Object>();
		params.add(user);
		params.add(pswd);
		List<CommonDTO> ret = dao.select(sql, params);
		return ret.size() == 1;
	}

	/**
	 * <p>ログインステータス更新</p>
	 * @param user
	 * @param sid
	 * @throws IOException
	 * @throws SQLException
	 */
	static void updLoginStatus(String user, String sid) throws IOException, SQLException {
		Connection conn = TransactionInfo.getConnection();
		CLoginStatusDAO dao = new CLoginStatusDAO(conn);
		String sql = SQLProperties.getInstance().getValue(CLoginStatusDAO.SQLID003);
		List<Object> params = new ArrayList<Object>();
		params.add(sid);
		params.add(user);
		if (dao.update(sql, params) == 0) {
			sql = SQLProperties.getInstance().getValue(CLoginStatusDAO.SQLID002);
			params = new ArrayList<Object>();
			params.add(user);
			params.add(sid);
			dao.update(sql, params);
		}
	}
}
