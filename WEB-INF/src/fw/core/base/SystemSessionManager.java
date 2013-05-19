package fw.core.base;

import javax.servlet.http.HttpSession;

import fw.common.util.SystemSessionKey;

public class SystemSessionManager {

	private static final String SYSTEM_SESSION_INFO = "SystemSessionInfo";

	static void init(HttpSession session) {
		session.setAttribute(SYSTEM_SESSION_INFO, new SystemSessionInfo());
	}

	static void terminate(HttpSession session) {
		session.removeAttribute(SYSTEM_SESSION_INFO);
	}

	public static Object getValue(HttpSession session, SystemSessionKey key) {
		Object info = session.getAttribute(SYSTEM_SESSION_INFO);
		if (info == null) {
			return null;
		}
		return ((SystemSessionInfo)info).getValue(key.getKey());
	}

	public static void setValue(HttpSession session, SystemSessionKey key, Object value) {
		Object info = session.getAttribute(SYSTEM_SESSION_INFO);
		if (info == null) {
			return;
		}
		((SystemSessionInfo)info).setValue(key.getKey(), value);
	}

	public static void removeValue(HttpSession session, SystemSessionKey key) {
		Object info = session.getAttribute(SYSTEM_SESSION_INFO);
		if (info == null) {
			return;
		}
		((SystemSessionInfo)info).removeValue(key.getKey());
	}

	public static void clear(HttpSession session) {
		Object info = session.getAttribute(SYSTEM_SESSION_INFO);
		if (info == null) {
			return;
		}
		((SystemSessionInfo)info).clear();
	}
}
