package master.staff.bean;


import java.io.Serializable;

/**
 * <p>担当者マスタBean</p>
 */
public class MStaffBean implements Serializable {
	private static final long serialVersionUID = -3543927292321141899L;

	private String code;
	private String sei;
	private String name;
	private String seiKana;
	private String nameKana;
	private String busyo;
	private String updDate;
	private String fullName;

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
	public String getFullName() {
		return new StringBuilder().append(sei).append(name).toString();
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
