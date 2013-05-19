package fw.common.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * <p>コネクションファクトリー</p>
 */
public class ConnectionFactory {

	/** <p>データソース</p> */
	private static DataSource ds = null;

	/**
	 * <p>初回処理</p>
	 * Webアプリケーション起動時に実施
	 * @throws NamingException
	 * @throws IOException
	 */
	public static void init() throws NamingException, IOException {
		InitialContext ic = null;
		try {
			ic = new InitialContext();
			String dsName = DBProperties.getInstance().getValue(DBProperties.KEY_DS_NAME);
			ds = (DataSource)ic.lookup(dsName);
		} finally {
			if (ic != null) {
				ic.close();
			}
		}
	}

	/**
	 * <p>コネクション取得</p>
	 * @param jndi
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = ds.getConnection();
		conn.setAutoCommit(false);
		return conn;
	}

}
