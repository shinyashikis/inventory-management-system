package book.item.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookProfit implements Serializable {

	private static final long serialVersionUID = -7146277174086815080L;

	private String nendo;
	private String from;
	private String to;

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
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
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
