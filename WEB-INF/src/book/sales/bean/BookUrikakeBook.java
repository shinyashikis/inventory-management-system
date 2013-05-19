package book.sales.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookUrikakeBook implements Serializable {

	private static final long serialVersionUID = 4133251948984112890L;

	private String nendo;
	private String from;
	private String to;

	private String voucherDate;
	private String voucherNo;
	private String className;
	private String kana;
	private String name;

	private BigDecimal taxExcludedAmount;
	private BigDecimal tax;
	private BigDecimal taxIncludedAmount;
	private BigDecimal discount;
	private BigDecimal salesPrice;
	private String dealDivision;
	private String staff;

	private String itemClassName;
	private String itemKana;
	private String itemName;
	private String itemCount;
	private BigDecimal itemUnitPrice;

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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getTaxExcludedAmount() {
		return taxExcludedAmount;
	}
	public void setTaxExcludedAmount(BigDecimal taxExcludedAmount) {
		this.taxExcludedAmount = taxExcludedAmount;
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
	public BigDecimal getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}
	public String getDealDivision() {
		return dealDivision;
	}
	public void setDealDivision(String dealDivision) {
		this.dealDivision = dealDivision;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getItemClassName() {
		return itemClassName;
	}
	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}
	public String getItemKana() {
		return itemKana;
	}
	public void setItemKana(String itemKana) {
		this.itemKana = itemKana;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	public BigDecimal getItemUnitPrice() {
		return itemUnitPrice;
	}
	public void setItemUnitPrice(BigDecimal itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}
}
