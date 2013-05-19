package voucher.common.docwriter;

import fw.common.docwriter.DocData;

/**
 * 明細文書ファイルデータ
 */
public class DetailDocData implements DocData {
	private static final long serialVersionUID = 1520791506613114569L;

	private String voucherNo;
	private String seq;
	private String itemName;
	private String itemCode;
	private String itemKikaku;
	private String itemCount;
	private String itemUnit;
	private String itemUnitPrice;
	private String itemPrice;
	private String itemMemo;
	private String itemPurchasePrice;
	private String salesNo;
	private String salesDate;

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
	public String getSalesNo() {
		return salesNo;
	}
	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}
	public String getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}
}
