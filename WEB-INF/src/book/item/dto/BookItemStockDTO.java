package book.item.dto;

import java.math.BigDecimal;

import fw.common.db.dto.CommonDTO;

public class BookItemStockDTO implements CommonDTO {
	private static final long serialVersionUID = 1060022445347120098L;

	public static final String CLASS_NAME = "className";
	public static final String KANA = "kana";
	public static final String NAME = "name";
	public static final String STOCK = "stock";
	public static final String PROPER_STOCK = "properStock";
	public static final String PRICE = "price";

	private String className;
	private String kana;
	private String name;
	private Integer stock;
	private Integer properStock;
	private BigDecimal price;

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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getProperStock() {
		return properStock;
	}
	public void setProperStock(Integer properStock) {
		this.properStock = properStock;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
