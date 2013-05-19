package master.staff;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * <p>担当者フォーム</p>
 */
public class StaffForm extends ValidatorForm{
	private static final long serialVersionUID = 1L;

	private String code;
	private String sei;
	private String name;
	private String seiKana;
	private String nameKana;
	private String busyo;
	private String updDate;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSei() {
		return sei;
	}
	public void setSei(String sei) {
		this.sei = sei;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeiKana() {
		return seiKana;
	}
	public void setSeiKana(String seiKana) {
		this.seiKana = seiKana;
	}
	public String getNameKana() {
		return nameKana;
	}
	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}
	public String getBusyo() {
		return busyo;
	}
	public void setBusyo(String busyo) {
		this.busyo = busyo;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		@SuppressWarnings("rawtypes")
		Map m = request.getParameterMap();
		if (m.size() == 0 || m.get("back") != null) {
			return new ActionErrors();
		}
		return super.validate(mapping, request);
	}
}
