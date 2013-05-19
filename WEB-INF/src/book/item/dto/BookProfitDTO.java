package book.item.dto;

import java.math.BigDecimal;

import fw.common.db.dto.CommonDTO;

public class BookProfitDTO implements CommonDTO {

	private static final long serialVersionUID = 6552343087836798807L;

	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String KANA = "kana";
	public static final String CLASS_CODE = "classCode";
	public static final String CLASS_NAME = "className";
	public static final String PROFIT = "profit";
	public static final String JAN_Profit = "janProfit";
	public static final String FEB_Profit = "febProfit";
	public static final String MAR_Profit = "marProfit";
	public static final String APR_Profit = "aprProfit";
	public static final String MAY_Profit = "mayProfit";
	public static final String JUN_Profit = "junProfit";
	public static final String JLY_Profit = "jlyProfit";
	public static final String AUG_Profit = "augProfit";
	public static final String SEP_Profit = "sepProfit";
	public static final String OCT_Profit = "octProfit";
	public static final String NOV_Profit = "novProfit";
	public static final String DEC_Profit = "decProfit";

	private String code;
	private String name;
	private String kana;
	private Integer classCode;
	private String className;
	private BigDecimal profit;
	private BigDecimal janProfit;
	private BigDecimal febProfit;
	private BigDecimal marProfit;
	private BigDecimal aprProfit;
	private BigDecimal mayProfit;
	private BigDecimal junProfit;
	private BigDecimal jlyProfit;
	private BigDecimal augProfit;
	private BigDecimal sepProfit;
	private BigDecimal octProfit;
	private BigDecimal novProfit;
	private BigDecimal decProfit;

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
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public BigDecimal getJanProfit() {
		return janProfit;
	}
	public void setJanProfit(BigDecimal janProfit) {
		this.janProfit = janProfit;
	}
	public BigDecimal getFebProfit() {
		return febProfit;
	}
	public void setFebProfit(BigDecimal febProfit) {
		this.febProfit = febProfit;
	}
	public BigDecimal getMarProfit() {
		return marProfit;
	}
	public void setMarProfit(BigDecimal marProfit) {
		this.marProfit = marProfit;
	}
	public BigDecimal getAprProfit() {
		return aprProfit;
	}
	public void setAprProfit(BigDecimal aprProfit) {
		this.aprProfit = aprProfit;
	}
	public BigDecimal getMayProfit() {
		return mayProfit;
	}
	public void setMayProfit(BigDecimal mayProfit) {
		this.mayProfit = mayProfit;
	}
	public BigDecimal getJunProfit() {
		return junProfit;
	}
	public void setJunProfit(BigDecimal junProfit) {
		this.junProfit = junProfit;
	}
	public BigDecimal getJlyProfit() {
		return jlyProfit;
	}
	public void setJlyProfit(BigDecimal jlyProfit) {
		this.jlyProfit = jlyProfit;
	}
	public BigDecimal getAugProfit() {
		return augProfit;
	}
	public void setAugProfit(BigDecimal augProfit) {
		this.augProfit = augProfit;
	}
	public BigDecimal getSepProfit() {
		return sepProfit;
	}
	public void setSepProfit(BigDecimal sepProfit) {
		this.sepProfit = sepProfit;
	}
	public BigDecimal getOctProfit() {
		return octProfit;
	}
	public void setOctProfit(BigDecimal octProfit) {
		this.octProfit = octProfit;
	}
	public BigDecimal getNovProfit() {
		return novProfit;
	}
	public void setNovProfit(BigDecimal novProfit) {
		this.novProfit = novProfit;
	}
	public BigDecimal getDecProfit() {
		return decProfit;
	}
	public void setDecProfit(BigDecimal decProfit) {
		this.decProfit = decProfit;
	}
}
