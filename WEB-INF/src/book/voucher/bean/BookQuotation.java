package book.voucher.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookQuotation implements Serializable {

	private static final long serialVersionUID = 9027785156321506618L;

	private String nendo;
	private String from;
	private String to;

	private String voucherDate;
	private String voucherNo;
	private String code;
	private String name;
	private String staff;
	private BigDecimal sumTaxExcludedAmount;
	private BigDecimal tax;
	private BigDecimal taxIncludedAmount;
	private BigDecimal discount;
	private BigDecimal allPrice;

	public String getNendo() {
		return nendo;
	}
	public void setNendo(String nendo) {
		this.nendo = nendo;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
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
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public BigDecimal getSumTaxExcludedAmount() {
		return sumTaxExcludedAmount;
	}
	public void setSumTaxExcludedAmount(BigDecimal sumTaxExcludedAmount) {
		this.sumTaxExcludedAmount = sumTaxExcludedAmount;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getTaxIncludedAmount() {
		return taxIncludedAmount;
	}
	public void setTaxIncludedAmount(BigDecimal taxIncludedAmount) {
		this.taxIncludedAmount = taxIncludedAmount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getAllPrice() {
		return allPrice;
	}
	public void setAllPrice(BigDecimal allPrice) {
		this.allPrice = allPrice;
	}

}
