package master.item.dto;

import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * <p>商品分類マスタDTO</p>
 */
public class MItemClassDTO implements CommonDTO {
	private static final long serialVersionUID = 1174574675300897712L;

	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String KANA = "kana";
	public static final String UPD_DATE = "updDate";

	private Integer code;
	private String name;
	private String kana;
	private Timestamp updDate;

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
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
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

}
