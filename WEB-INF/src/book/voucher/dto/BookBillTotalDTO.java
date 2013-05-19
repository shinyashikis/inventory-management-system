package book.voucher.dto;

import java.math.BigDecimal;

import fw.common.db.dto.CommonDTO;

public class BookBillTotalDTO implements CommonDTO {

	private static final long serialVersionUID = -6381121392618191028L;

	public static final String VOUCHER_DATE = "voucherDate";
	public static final String VOUCHER_NO = "voucherNo";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String STAFF_SEI = "staffSei";
	public static final String STAFF_NAME = "staffName";
	public static final String SUM_TAX_EXCLUDED_PRICE = "sumTaxExcludedAmount";
	public static final String DISCOUNT = "discount";

	private String voucherDate;
	private Integer voucherNo;
	private String code;
	private String name;
	private String staffSei;
	private String staffName;
	private BigDecimal sumTaxExcludedAmount;
	private BigDecimal discount;

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
	public String getStaffSei() {
		return staffSei;
	}
	public void setStaffSei(String staffSei) {
		this.staffSei = staffSei;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public BigDecimal getSumTaxExcludedAmount() {
		return sumTaxExcludedAmount;
	}
	public void setSumTaxExcludedAmount(BigDecimal sumTaxExcludedAmount) {
		this.sumTaxExcludedAmount = sumTaxExcludedAmount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}
