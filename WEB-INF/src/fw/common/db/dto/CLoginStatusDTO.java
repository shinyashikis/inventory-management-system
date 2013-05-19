package fw.common.db.dto;

public class CLoginStatusDTO implements CommonDTO {

	private static final long serialVersionUID = -1605047717169863622L;

	public static final String COLUMN_USER = "USER";
	public static final String COLUMN_SID = "SID";

	private String user;
	private String sid;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}

}
