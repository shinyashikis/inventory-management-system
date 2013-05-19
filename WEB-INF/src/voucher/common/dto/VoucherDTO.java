package voucher.common.dto;

import java.math.BigDecimal;
import java.sql.Date;

import fw.common.db.dto.CommonDTO;

/**
 * 伝票DTO
 */
public class VoucherDTO implements CommonDTO {
	private static final long serialVersionUID = -7964181196303366100L;

	// 伝票
	public static final String VOUCHER_NO = "voucherNo";
	public static final String VOUCHER_DATE = "voucherDate";
	public static final String DISCOUNT = "discount";
	public static final String VOUCHER_NAME = "voucherName";
	public static final String PAYMENT_LIMIT = "paymentLimit";
	public static final String PAYMENT_PLACE = "paymentPlace";
	public static final String PAYMENT_CONDITION = "paymentCondition";
	public static final String EXPIRATION_DATE = "expirationDate";
	public static final String MEMO = "memo";
	public static final String ETC1 = "etc1";
	public static final String ETC2 = "etc2";
	public static final String RECEIPT_NO = "receiptNo";
	public static final String PROVISO = "proviso";

	// 取引先
	public static final String DEAL_KIND = "dealKind";
	public static final String DEAL_CODE = "dealCode";
	public static final String DEAL_NAME = "dealName";
	public static final String DEAL_CLASS_CODE = "dealClassCode";
	public static final String DEAL_CLASS_NAME = "dealClassName";
	public static final String DEAL_STAFF_SEI = "dealStaffSei";
	public static final String DEAL_STAFF_NAME = "dealStaffName";
	public static final String DEAL_STAFF_KEISYO = "dealStaffKeisyo";
	public static final String DEAL_STAFF_BUSYO = "dealStaffBusyo";
	public static final String DEAL_TEL = "dealTel";
	public static final String DEAL_POSTCODE1 = "dealPostCode1";
	public static final String DEAL_POSTCODE2 = "dealPostCode2";
	public static final String DEAL_ADDR1 = "dealAddr1";
	public static final String DEAL_ADDR2 = "dealAddr2";
	public static final String SHIMEBI_MONTHLY = "shimebiMonthly";
	public static final String KESSAI_MONTHLY = "kessaiMonthly";
	public static final String SHIMEBI_KESSAI = "shimebiKessai";
	public static final String DEAL_DIVISION = "dealDivision";
	public static final String PRICE_DIVISION = "priceDivision";
	public static final String TAX = "tax";
	public static final String CALC = "calc";
	public static final String CALC_HASU = "calcHasu";
	public static final String STAFF_CODE = "staffCode";
	public static final String STAFF_SEI = "staffSei";
	public static final String STAFF_NAME = "staffName";
	public static final String STAFF_BUSYO = "staffBusyo";

	// 明細
	public static final String SEQ = "seq";
	public static final String ITEM_SALES_NO = "salesNo";
	public static final String ITEM_SALES_SEQ = "salesSeq";
	public static final String ITEM_SALES_DATE = "salesDate";
	public static final String ITEM_CODE = "itemCode";
	public static final String ITEM_NAME = "itemName";
	public static final String ITEM_KIKAKU = "itemKikaku";
	public static final String ITEM_COUNT = "itemCount";
	public static final String ITEM_UNIT = "itemUnit";
	public static final String ITEM_UNIT_PRICE = "itemUnitPrice";
	public static final String ITEM_MEMO = "itemMemo";
	public static final String SALES_NO = "salesNo";
	public static final String SALES_DATE = "salesDate";
	public static final String ITEM_PURCHASE_PRICE = "itemPurchasePrice";

	// 伝票
	private Integer voucherNo;
	private Date voucherDate;
	private BigDecimal discount;
	private String voucherName;
	private String paymentLimit;
	private String paymentPlace;
	private String paymentCondition;
	private String expirationDate;
	private String memo;
	private String etc1;
	private String etc2;
	private String receiptNo;
	private String proviso;
	private Date receiptDate;

	// 取引先
	private Integer dealKind;
	private String dealCode;
	private String dealName;
	private Integer dealClassCode;
	private String dealClassName;
	private String dealStaffSei;
	private String dealStaffName;
	private Integer dealStaffKeisyo;
	private String dealStaffBusyo;
	private String dealTel;
	private String dealPostCode1;
	private String dealPostCode2;
	private String dealAddr1;
	private String dealAddr2;
	private Integer shimebiMonthly;
	private Integer kessaiMonthly;
	private Integer shimebiKessai;
	private Integer dealDivision;
	private Integer priceDivision;
	private Integer tax;
	private Integer calc;
	private Integer calcHasu;
	private Integer staffCode;
	private String staffSei;
	private String staffName;
	private String staffBusyo;

	// 明細
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

	public Integer getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(Integer voucherNo) {
		this.voucherNo = voucherNo;
	}
	public Date getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public String getVoucherName() {
		return voucherName;
	}
	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}
	public String getPaymentLimit() {
		return paymentLimit;
	}
	public void setPaymentLimit(String paymentLimit) {
		this.paymentLimit = paymentLimit;
	}
	public String getPaymentPlace() {
		return paymentPlace;
	}
	public void setPaymentPlace(String paymentPlace) {
		this.paymentPlace = paymentPlace;
	}
	public String getPaymentCondition() {
		return paymentCondition;
	}
	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getEtc1() {
		return etc1;
	}
	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}
	public String getEtc2() {
		return etc2;
	}
	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getProviso() {
		return proviso;
	}
	public void setProviso(String proviso) {
		this.proviso = proviso;
	}
	public Date getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	public Integer getDealKind() {
		return dealKind;
	}
	public void setDealKind(Integer dealKind) {
		this.dealKind = dealKind;
	}
	public String getDealCode() {
		return dealCode;
	}
	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}
	public String getDealName() {
		return dealName;
	}
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	public Integer getDealClassCode() {
		return dealClassCode;
	}
	public void setDealClassCode(Integer dealClassCode) {
		this.dealClassCode = dealClassCode;
	}
	public String getDealClassName() {
		return dealClassName;
	}
	public void setDealClassName(String dealClassName) {
		this.dealClassName = dealClassName;
	}
	public String getDealStaffSei() {
		return dealStaffSei;
	}
	public void setDealStaffSei(String dealStaffSei) {
		this.dealStaffSei = dealStaffSei;
	}
	public String getDealStaffName() {
		return dealStaffName;
	}
	public void setDealStaffName(String dealStaffName) {
		this.dealStaffName = dealStaffName;
	}
	public Integer getDealStaffKeisyo() {
		return dealStaffKeisyo;
	}
	public void setDealStaffKeisyo(Integer dealStaffKeisyo) {
		this.dealStaffKeisyo = dealStaffKeisyo;
	}
	public String getDealStaffBusyo() {
		return dealStaffBusyo;
	}
	public void setDealStaffBusyo(String dealStaffBusyo) {
		this.dealStaffBusyo = dealStaffBusyo;
	}
	public String getDealTel() {
		return dealTel;
	}
	public void setDealTel(String dealTel) {
		this.dealTel = dealTel;
	}
	public String getDealPostCode1() {
		return dealPostCode1;
	}
	public void setDealPostCode1(String dealPostCode1) {
		this.dealPostCode1 = dealPostCode1;
	}
	public String getDealPostCode2() {
		return dealPostCode2;
	}
	public void setDealPostCode2(String dealPostCode2) {
		this.dealPostCode2 = dealPostCode2;
	}
	public String getDealAddr1() {
		return dealAddr1;
	}
	public void setDealAddr1(String dealAddr1) {
		this.dealAddr1 = dealAddr1;
	}
	public String getDealAddr2() {
		return dealAddr2;
	}
	public void setDealAddr2(String dealAddr2) {
		this.dealAddr2 = dealAddr2;
	}
	public Integer getShimebiMonthly() {
		return shimebiMonthly;
	}
	public void setShimebiMonthly(Integer shimebiMonthly) {
		this.shimebiMonthly = shimebiMonthly;
	}
	public Integer getKessaiMonthly() {
		return kessaiMonthly;
	}
	public void setKessaiMonthly(Integer kessaiMonthly) {
		this.kessaiMonthly = kessaiMonthly;
	}
	public Integer getShimebiKessai() {
		return shimebiKessai;
	}
	public void setShimebiKessai(Integer shimebiKessai) {
		this.shimebiKessai = shimebiKessai;
	}
	public Integer getDealDivision() {
		return dealDivision;
	}
	public void setDealDivision(Integer dealDivision) {
		this.dealDivision = dealDivision;
	}
	public Integer getPriceDivision() {
		return priceDivision;
	}
	public void setPriceDivision(Integer priceDivision) {
		this.priceDivision = priceDivision;
	}
	public Integer getTax() {
		return tax;
	}
	public void setTax(Integer tax) {
		this.tax = tax;
	}
	public Integer getCalc() {
		return calc;
	}
	public void setCalc(Integer calc) {
		this.calc = calc;
	}
	public Integer getCalcHasu() {
		return calcHasu;
	}
	public void setCalcHasu(Integer calcHasu) {
		this.calcHasu = calcHasu;
	}
	public Integer getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(Integer staffCode) {
		this.staffCode = staffCode;
	}
	public String getStaffSei() {
		return staffSei;
	}
	public void setStaffSei(String staffSei) {
		this.staffSei = staffSei;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffBusyo() {
		return staffBusyo;
	}
	public void setStaffBusyo(String staffBusyo) {
		this.staffBusyo = staffBusyo;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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
	public String getItemMemo() {
		return itemMemo;
	}
	public void setItemMemo(String itemMemo) {
		this.itemMemo = itemMemo;
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
	public BigDecimal getItemPurchasePrice() {
		return itemPurchasePrice;
	}
	public void setItemPurchasePrice(BigDecimal itemPurchasePrice) {
		this.itemPurchasePrice = itemPurchasePrice;
	}
	public BigDecimal getItemUnitPrice() {
		return itemUnitPrice;
	}
	public void setItemUnitPrice(BigDecimal itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}
}
