package book.item.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookItemStock implements Serializable {
	private static final long serialVersionUID = 7853823916146081747L;

	private String className;
	private String kana;
	private String name;
	private Integer stock;
	private Integer properStock;
	private Integer kabusoku;
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
	public Integer getKabusoku() {
		return kabusoku;
	}
	public void setKabusoku(Integer kabusoku) {
		this.kabusoku = kabusoku;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
