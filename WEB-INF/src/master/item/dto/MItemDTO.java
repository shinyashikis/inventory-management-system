package master.item.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * <p>商品マスタDTO</p>
 */
public class MItemDTO implements CommonDTO {
	private static final long serialVersionUID = 3522511748084010775L;

	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String KANA = "kana";
	public static final String KIKAU = "kikaku";
	public static final String CLASS_CODE = "classCode";
	public static final String STOCK = "stock";
	public static final String PROPER_STOCK = "properStock";
	public static final String KISYU_STOCK = "kisyuStock";
	public static final String KICYU_STOCK_UPDOWN = "kicyuStockUpDown";
	public static final String UNIT = "unit";
	public static final String PRICE = "price";
	public static final String KISYU_PRICE = "kisyuPrice";
	public static final String STANDARD_PRICE = "standardPrice";
	public static final String SELLING_PRICE2 = "sellingPrice2";
	public static final String SELLING_PRICE3 = "sellingPrice3";
	public static final String SELLING_PRICE4 = "sellingPrice4";
	public static final String SELLING_PRICE5 = "sellingPrice5";
	public static final String UPD_DATE = "updDate";

	private String code;
	private String name;
	private String kana;
	private String kikaku;
	private Integer classCode;
	private Integer stock;
	private Integer properStock;
	private Integer kisyuStock;
	private Integer kicyuStockUpDown;
	private String unit;
	private BigDecimal price;
	private BigDecimal kisyuPrice;
	private BigDecimal standardPrice;
	private BigDecimal sellingPrice2;
	private BigDecimal sellingPrice3;
	private BigDecimal sellingPrice4;
	private BigDecimal sellingPrice5;
	private Timestamp updDate;

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
	public String getKikaku() {
		return kikaku;
	}
	public void setKikaku(String kikaku) {
		this.kikaku = kikaku;
	}
	public Integer getClassCode() {
		return classCode;
	}
	public void setClassCode(Integer classCode) {
		this.classCode = classCode;
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
	public Integer getKisyuStock() {
		return kisyuStock;
	}
	public void setKisyuStock(Integer kisyuStock) {
		this.kisyuStock = kisyuStock;
	}
	public Integer getKicyuStockUpDown() {
		return kicyuStockUpDown;
	}
	public void setKicyuStockUpDown(Integer kicyuStockUpDown) {
		this.kicyuStockUpDown = kicyuStockUpDown;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getKisyuPrice() {
		return kisyuPrice;
	}
	public void setKisyuPrice(BigDecimal kisyuPrice) {
		this.kisyuPrice = kisyuPrice;
	}
	public BigDecimal getStandardPrice() {
		return standardPrice;
	}
	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}
	public BigDecimal getSellingPrice2() {
		return sellingPrice2;
	}
	public void setSellingPrice2(BigDecimal sellingPrice2) {
		this.sellingPrice2 = sellingPrice2;
	}
	public BigDecimal getSellingPrice3() {
		return sellingPrice3;
	}
	public void setSellingPrice3(BigDecimal sellingPrice3) {
		this.sellingPrice3 = sellingPrice3;
	}
	public BigDecimal getSellingPrice4() {
		return sellingPrice4;
	}
	public void setSellingPrice4(BigDecimal sellingPrice4) {
		this.sellingPrice4 = sellingPrice4;
	}
	public BigDecimal getSellingPrice5() {
		return sellingPrice5;
	}
	public void setSellingPrice5(BigDecimal sellingPrice5) {
		this.sellingPrice5 = sellingPrice5;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

}
