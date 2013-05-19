package fw.common.util;

public enum SystemSessionKey {

	LOGIN_USER("loginUser"),
	BASIC_INFO("basicInfo"),
	CALC_HASU("calcHasu"),
	DISP_DATE("dispDate");

	private String key;

	private SystemSessionKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
