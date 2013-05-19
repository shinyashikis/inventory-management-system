package master.staff.dto;

import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * <p>担当者マスタDTO</p>
 */
public class MStaffDTO implements CommonDTO {
	private static final long serialVersionUID = 3201094223119764032L;

	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_SEI = "sei";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_SEI_KANA = "seiKana";
	public static final String COLUMN_NAME_KANA = "nameKana";
	public static final String COLUMN_BUSYO = "busyo";
	public static final String COLUMN_UPD_DATE = "updDate";

	private Integer code;
	private String sei;
	private String name;
	private String seiKana;
	private String nameKana;
	private String busyo;
	private Timestamp updDate;

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
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
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

}
