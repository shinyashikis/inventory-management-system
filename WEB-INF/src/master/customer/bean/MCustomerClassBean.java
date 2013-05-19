package master.customer.bean;

import java.io.Serializable;

/**
 * <p>得意先分類マスタBean</p>
 */
public class MCustomerClassBean implements Serializable {
	private static final long serialVersionUID = -9047560896276825847L;

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
