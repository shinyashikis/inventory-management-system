package book.item.dto;

import java.math.BigDecimal;

import fw.common.db.dto.CommonDTO;

public class BookShipSumDTO implements CommonDTO {
	private static final long serialVersionUID = 10608578642782021L;

	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String KANA = "kana";
	public static final String CLASS_CODE = "classCode";
	public static final String CLASS_NAME = "className";

	public static final String SUM_COUNT = "sumCount";
	public static final String SUM_PRICE = "sumPrice";

	public static final String JAN_COUNT = "janCount";
	public static final String FEB_COUNT = "febCount";
	public static final String MAR_COUNT = "marCount";
	public static final String APR_COUNT = "aprCount";
	public static final String MAY_COUNT = "mayCount";
	public static final String JUN_COUNT = "junCount";
	public static final String JLY_COUNT = "jlyCount";
	public static final String AUG_COUNT = "augCount";
	public static final String SEP_COUNT = "sepCount";
	public static final String OCT_COUNT = "octCount";
	public static final String NOV_COUNT = "novCount";
	public static final String DEC_COUNT = "decCount";

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

	private BigDecimal sumCount;
	private BigDecimal sumPrice;

	private BigDecimal janCount;
	private BigDecimal febCount;
	private BigDecimal marCount;
	private BigDecimal aprCount;
	private BigDecimal mayCount;
	private BigDecimal junCount;
	private BigDecimal jlyCount;
	private BigDecimal augCount;
	private BigDecimal sepCount;
	private BigDecimal octCount;
	private BigDecimal novCount;
	private BigDecimal decCount;

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
	public BigDecimal getSumCount() {
		return sumCount;
	}
	public void setSumCount(BigDecimal sumCount) {
		this.sumCount = sumCount;
	}
	public BigDecimal getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public BigDecimal getJanCount() {
		return janCount;
	}
	public void setJanCount(BigDecimal janCount) {
		this.janCount = janCount;
	}
	public BigDecimal getFebCount() {
		return febCount;
	}
	public void setFebCount(BigDecimal febCount) {
		this.febCount = febCount;
	}
	public BigDecimal getMarCount() {
		return marCount;
	}
	public void setMarCount(BigDecimal marCount) {
		this.marCount = marCount;
	}
	public BigDecimal getAprCount() {
		return aprCount;
	}
	public void setAprCount(BigDecimal aprCount) {
		this.aprCount = aprCount;
	}
	public BigDecimal getMayCount() {
		return mayCount;
	}
	public void setMayCount(BigDecimal mayCount) {
		this.mayCount = mayCount;
	}
	public BigDecimal getJunCount() {
		return junCount;
	}
	public void setJunCount(BigDecimal junCount) {
		this.junCount = junCount;
	}
	public BigDecimal getJlyCount() {
		return jlyCount;
	}
	public void setJlyCount(BigDecimal jlyCount) {
		this.jlyCount = jlyCount;
	}
	public BigDecimal getAugCount() {
		return augCount;
	}
	public void setAugCount(BigDecimal augCount) {
		this.augCount = augCount;
	}
	public BigDecimal getSepCount() {
		return sepCount;
	}
	public void setSepCount(BigDecimal sepCount) {
		this.sepCount = sepCount;
	}
	public BigDecimal getOctCount() {
		return octCount;
	}
	public void setOctCount(BigDecimal octCount) {
		this.octCount = octCount;
	}
	public BigDecimal getNovCount() {
		return novCount;
	}
	public void setNovCount(BigDecimal novCount) {
		this.novCount = novCount;
	}
	public BigDecimal getDecCount() {
		return decCount;
	}
	public void setDecCount(BigDecimal decCount) {
		this.decCount = decCount;
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
