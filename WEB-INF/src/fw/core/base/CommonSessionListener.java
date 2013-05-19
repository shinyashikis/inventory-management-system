package fw.core.base;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import fw.common.db.ConnectionFactory;
import fw.common.db.SQLProperties;
import fw.common.db.dao.CLoginStatusDAO;
import fw.common.util.SystemSessionKey;

/**
 * <p>共通セッションリスナー</p>
 */
public class CommonSessionListener implements HttpSessionListener {

	/**
	 * <p>セッション生成時処理</p>
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		SystemSessionManager.terminate(session);
		SystemSessionManager.init(session);
	}

	/**
	 * <p>セッション破棄時処理</p>
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		try {
			release(session);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>リソース解放</p>
	 * @param session
	 * @throws IOException
	 * @throws SQLException
	 */
	private void release(HttpSession session) throws IOException, SQLException {
		Object user = SystemSessionManager.getValue(session, SystemSessionKey.LOGIN_USER);

		if (user == null) {
			return;
		}

		Connection conn = ConnectionFactory.getConnection();

		CLoginStatusDAO dao = new CLoginStatusDAO(conn);
		String sql = SQLProperties.getInstance().getValue(CLoginStatusDAO.SQLID004);
		List<Object> params = new ArrayList<Object>();
		params.add(user);

		try {
			dao.update(sql, params);
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
