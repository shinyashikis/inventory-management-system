package book.purchase.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookPurchaseTotal implements Serializable {

	private static final long serialVersionUID = 5813303275323312923L;

	private String nendo;
	private String from;
	private String to;

	private String code;
	private String name;
	private String kana;

	private BigDecimal taxExcludedAmount;
	private BigDecimal tax;
	private BigDecimal discount;
	private BigDecimal taxIncludedAmount;

	private BigDecimal janPrice;
	private BigDecimal febPrice;
	private BigDecimal marPrice;
	private BigDecimal aprPrice;
	private BigDecimal mayPrice;
	private BigDecimal junPrice;
	private BigDecimal jlyPrice;
	private BigDecimal augPrice;
	private BigDecimal sepPrice;
	private BigDecimal octPrice;
	private BigDecimal novPrice;
	private BigDecimal decPrice;

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
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getTaxIncludedAmount() {
		return taxIncludedAmount;
	}
	public void setTaxIncludedAmount(BigDecimal taxIncludedAmount) {
		this.taxIncludedAmount = taxIncludedAmount;
	}
	public BigDecimal getJanPrice() {
		return janPrice;
	}
	public void setJanPrice(BigDecimal janPrice) {
		this.janPrice = janPrice;
	}
	public BigDecimal getFebPrice() {
		return febPrice;
	}
	public void setFebPrice(BigDecimal febPrice) {
		this.febPrice = febPrice;
	}
	public BigDecimal getMarPrice() {
		return marPrice;
	}
	public void setMarPrice(BigDecimal marPrice) {
		this.marPrice = marPrice;
	}
	public BigDecimal getAprPrice() {
		return aprPrice;
	}
	public void setAprPrice(BigDecimal aprPrice) {
		this.aprPrice = aprPrice;
	}
	public BigDecimal getMayPrice() {
		return mayPrice;
	}
	public void setMayPrice(BigDecimal mayPrice) {
		this.mayPrice = mayPrice;
	}
	public BigDecimal getJunPrice() {
		return junPrice;
	}
	public void setJunPrice(BigDecimal junPrice) {
		this.junPrice = junPrice;
	}
	public BigDecimal getJlyPrice() {
		return jlyPrice;
	}
	public void setJlyPrice(BigDecimal jlyPrice) {
		this.jlyPrice = jlyPrice;
	}
	public BigDecimal getAugPrice() {
		return augPrice;
	}
	public void setAugPrice(BigDecimal augPrice) {
		this.augPrice = augPrice;
	}
	public BigDecimal getSepPrice() {
		return sepPrice;
	}
	public void setSepPrice(BigDecimal sepPrice) {
		this.sepPrice = sepPrice;
	}
	public BigDecimal getOctPrice() {
		return octPrice;
	}
	public void setOctPrice(BigDecimal octPrice) {
		this.octPrice = octPrice;
	}
	public BigDecimal getNovPrice() {
		return novPrice;
	}
	public void setNovPrice(BigDecimal novPrice) {
		this.novPrice = novPrice;
	}
	public BigDecimal getDecPrice() {
		return decPrice;
	}
	public void setDecPrice(BigDecimal decPrice) {
		this.decPrice = decPrice;
	}
}
