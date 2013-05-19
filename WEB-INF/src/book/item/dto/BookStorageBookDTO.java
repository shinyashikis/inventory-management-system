package book.item.dto;

import fw.common.db.dto.CommonDTO;

public class BookStorageBookDTO implements CommonDTO {
	private static final long serialVersionUID = -4017832474614510771L;

	public static final String VOUCHER_DATE = "voucherDate";
	public static final String VOUCHER_NO = "voucherNo";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String KANA = "kana";
	public static final String CLASS_CODE = "classCode";
	public static final String CLASS_NAME = "className";
	public static final String COUNT = "count";

	private String voucherDate;
	private Integer voucherNo;
	private String code;
	private String name;
	private String kana;
	private Integer classCode;
	private String className;
	private Integer count;

	public String getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}
	public Integer getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(Integer voucherNo) {
		this.voucherNo = voucherNo;
	}
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
	public Integer getClassCode() {
		return classCode;
	}
	public void setClassCode(Integer classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
