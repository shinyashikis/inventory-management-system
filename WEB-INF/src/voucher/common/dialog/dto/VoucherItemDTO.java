package voucher.common.dialog.dto;

import java.math.BigDecimal;

import fw.common.db.dto.CommonDTO;

/**
 * <p>(伝票)商品DTO</p>
 */
public class VoucherItemDTO implements CommonDTO {

	private static final long serialVersionUID = -2766089437087549879L;

	public static final String ITEM_CODE = "itemCode";
	public static final String ITEM_NAME = "itemName";
	public static final String ITEM_KIKAKU = "itemKikaku";
	public static final String ITEM_UNIT = "itemUnit";
	public static final String ITEM_UNIT_PRICE1 = "itemUnitPrice1";
	public static final String ITEM_UNIT_PRICE2 = "itemUnitPrice2";
	public static final String ITEM_UNIT_PRICE3 = "itemUnitPrice3";
	public static final String ITEM_UNIT_PRICE4 = "itemUnitPrice4";
	public static final String ITEM_UNIT_PRICE5 = "itemUnitPrice5";
	public static final String ITEM_PURCHASE_PRICE = "itemPurchasePrice";

	private String itemCode;
	private String itemName;
	private String itemKikaku;
	private String itemUnit;
	private BigDecimal itemUnitPrice1;
	private BigDecimal itemUnitPrice2;
	private BigDecimal itemUnitPrice3;
	private BigDecimal itemUnitPrice4;
	private BigDecimal itemUnitPrice5;
	private BigDecimal itemPurchasePrice;

	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemKikaku() {
		return itemKikaku;
	}
	public void setItemKikaku(String itemKikaku) {
		this.itemKikaku = itemKikaku;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public BigDecimal getItemUnitPrice1() {
		return itemUnitPrice1;
	}
	public void setItemUnitPrice1(BigDecimal itemUnitPrice1) {
		this.itemUnitPrice1 = itemUnitPrice1;
	}
	public BigDecimal getItemUnitPrice2() {
		return itemUnitPrice2;
	}
	public void setItemUnitPrice2(BigDecimal itemUnitPrice2) {
		this.itemUnitPrice2 = itemUnitPrice2;
	}
	public BigDecimal getItemUnitPrice3() {
		return itemUnitPrice3;
	}
	public void setItemUnitPrice3(BigDecimal itemUnitPrice3) {
		this.itemUnitPrice3 = itemUnitPrice3;
	}
	public BigDecimal getItemUnitPrice4() {
		return itemUnitPrice4;
	}
	public void setItemUnitPrice4(BigDecimal itemUnitPrice4) {
		this.itemUnitPrice4 = itemUnitPrice4;
	}
	public BigDecimal getItemUnitPrice5() {
		return itemUnitPrice5;
	}
	public void setItemUnitPrice5(BigDecimal itemUnitPrice5) {
		this.itemUnitPrice5 = itemUnitPrice5;
	}
	public BigDecimal getItemPurchasePrice() {
		return itemPurchasePrice;
	}
	public void setItemPurchasePrice(BigDecimal itemPurchasePrice) {
		this.itemPurchasePrice = itemPurchasePrice;
	}
}
