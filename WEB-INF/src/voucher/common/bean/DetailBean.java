package voucher.common.bean;

import java.io.Serializable;

/**
 * 明細Bean
 */
public class DetailBean implements Serializable {
	private static final long serialVersionUID = -1259387941902121537L;

	private String voucherNo;
	private String seq;
	private String salesNo;
	private String salesSeq;
	private String salesDate;
	private String itemName;
	private String itemCode;
	private String itemKikaku;
	private String itemCount = "0";
	private String itemUnit;
	private String itemUnitPrice;
	private String itemPrice = "0";
	private String itemMemo;
	private String itemPurchasePrice;

	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSalesNo() {
		return salesNo;
	}
	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}
	public String getSalesSeq() {
		return salesSeq;
	}
	public void setSalesSeq(String salesSeq) {
		this.salesSeq = salesSeq;
	}
	public String getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemKikaku() {
		return itemKikaku;
	}
	public void setItemKikaku(String itemKikaku) {
		this.itemKikaku = itemKikaku;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public String getItemUnitPrice() {
		return itemUnitPrice;
	}
	public void setItemUnitPrice(String itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemMemo() {
		return itemMemo;
	}
	public void setItemMemo(String itemMemo) {
		this.itemMemo = itemMemo;
	}
	public String getItemPurchasePrice() {
		return itemPurchasePrice;
	}
	public void setItemPurchasePrice(String itemPurchasePrice) {
		this.itemPurchasePrice = itemPurchasePrice;
	}
}
