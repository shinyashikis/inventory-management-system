package master.supplier.bean;

import java.io.Serializable;

/**
 * <p>仕入先分類マスタBean</p>
 */
public class MSupplierClassBean implements Serializable {
	private static final long serialVersionUID = 5372532412439424936L;

	private String code;
	private String name;
	private String kana;
	private String updDate;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

}
