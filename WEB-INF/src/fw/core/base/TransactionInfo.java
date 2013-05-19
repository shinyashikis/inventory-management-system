package fw.core.base;

import java.sql.Connection;

public class TransactionInfo {

	private static ThreadLocal<TransactionInfo> threadLocal = new ThreadLocal<TransactionInfo>();

	private Connection conn = null;

	private TransactionInfo() {}

	static void init() {
		threadLocal.set(new TransactionInfo());
	}

	static void terminate() {
		threadLocal.remove();
	}

	static void setConnection(Connection conn) {
		threadLocal.get().conn = conn;
	}

	public static Connection getConnection() {
		return threadLocal.get().conn;
	}

}
