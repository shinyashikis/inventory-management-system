package fw.common.db.dto;

public class CUserDTO implements CommonDTO {

	private static final long serialVersionUID = 1665368542330201253L;

	public static final String COLUMN_USER = "USER";
	public static final String COLUMN_PSWD = "PSWD";

	private String user;
	private String pswd;

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

}
