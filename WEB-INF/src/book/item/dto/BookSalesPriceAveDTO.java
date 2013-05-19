package book.item.dto;

import java.math.BigDecimal;

import fw.common.db.dto.CommonDTO;

public class BookSalesPriceAveDTO implements CommonDTO {

	private static final long serialVersionUID = -4312406415504939509L;

	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String KANA = "kana";
	public static final String CLASS_CODE = "classCode";
	public static final String CLASS_NAME = "className";
	public static final String AVE_PRICE = "avePrice";
	public static final String JAN_PRICE = "janPrice";
	public static final String FEB_PRICE = "febPrice";
	public static final String MAR_PRICE = "marPrice";
	public static final String APR_PRICE = "aprPrice";
	public static final String MAY_PRICE = "mayPrice";
	public static final String JUN_PRICE = "junPrice";
	public static final String JLY_PRICE = "jlyPrice";
	public static final String AUG_PRICE = "augPrice";
	public static final String SEP_PRICE = "sepPrice";
	public static final String OCT_PRICE = "octPrice";
	public static final String NOV_PRICE = "novPrice";
	public static final String DEC_PRICE = "decPrice";

	private String code;
	private String name;
	private String kana;
	private Integer classCode;
	private String className;
	private BigDecimal avePrice;
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
	public BigDecimal getAvePrice() {
		return avePrice;
	}
	public void setAvePrice(BigDecimal avePrice) {
		this.avePrice = avePrice;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
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
