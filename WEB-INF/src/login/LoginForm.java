package login;


import org.apache.struts.validator.ValidatorForm;

/**
 * <p>ログインフォーム</p>
 */
public class LoginForm extends ValidatorForm{

	private static final long serialVersionUID = 1L;

	private String user;
	private String pswd;

	public LoginForm(){
	  super();
	}

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
