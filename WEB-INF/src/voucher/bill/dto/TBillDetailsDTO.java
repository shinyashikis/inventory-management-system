package voucher.bill.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * 請求書明細テーブルDTO
 */
public class TBillDetailsDTO implements CommonDTO {
	private static final long serialVersionUID = -3864917683256450298L;

	public static final String VOUCHER_NO = "voucherNo";
	public static final String SEQ = "seq";
	public static final String SALES_NO = "salesNo";
	public static final String SALES_SEQ = "salesSeq";
	public static final String SALES_DATE = "salesDate";
	public static final String ITEM_CODE = "itemCode";
	public static final String ITEM_NAME = "itemName";
	public static final String ITEM_KIKAKU = "itemKikaku";
	public static final String ITEM_COUNT = "itemCount";
	public static final String ITEM_UNIT = "itemUnit";
	public static final String ITEM_UNIT_PRICE = "itemUnitPrice";
	public static final String ITEM_PURCHASE_PRICE = "itemPurchasePrice";
	public static final String ITEM_MEMO = "itemMemo";
	public static final String UPDDATE = "updDate";

	private Integer voucherNo;
	private Integer seq;
	private Integer salesNo;
	private Integer salesSeq;
	private Date salesDate;
	private String itemCode;
	private String itemName;
	private String itemKikaku;
	private Integer itemCount;
	private String itemUnit;
	private BigDecimal itemUnitPrice;
	private String itemMemo;
	private BigDecimal itemPurchasePrice;
	private Timestamp updDate;

	public Integer getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(Integer voucherNo) {
		this.voucherNo = voucherNo;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getSalesNo() {
		return salesNo;
	}
	public void setSalesNo(Integer salesNo) {
		this.salesNo = salesNo;
	}
	public Integer getSalesSeq() {
		return salesSeq;
	}
	public void setSalesSeq(Integer salesSeq) {
		this.salesSeq = salesSeq;
	}
	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
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
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public BigDecimal getItemUnitPrice() {
		return itemUnitPrice;
	}
	public void setItemUnitPrice(BigDecimal itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}
	public String getItemMemo() {
		return itemMemo;
	}
	public void setItemMemo(String itemMemo) {
		this.itemMemo = itemMemo;
	}
	public BigDecimal getItemPurchasePrice() {
		return itemPurchasePrice;
	}
	public void setItemPurchasePrice(BigDecimal itemPurchasePrice) {
		this.itemPurchasePrice = itemPurchasePrice;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
}
