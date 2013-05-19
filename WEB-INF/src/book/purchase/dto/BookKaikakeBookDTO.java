package book.purchase.dto;

import java.math.BigDecimal;

import fw.common.db.dto.CommonDTO;

public class BookKaikakeBookDTO implements CommonDTO {

	private static final long serialVersionUID = 7768331589476822265L;

	public static final String VOUCHER_DATE = "voucherDate";
	public static final String VOUCHER_NO = "voucherNo";
	public static final String CLASS_CODE = "classCode";
	public static final String CLASS_NAME = "className";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String KANA = "kana";

	public static final String SUM_TAX_EXCLUDED_PRICE = "sumTaxExcludedPrice";
	public static final String DISCOUNT = "discount";
	public static final String DEAL_DIVISION = "dealDivision";
	public static final String STAFF_SEI = "staffSei";
	public static final String STAFF_NAME = "staffName";

	public static final String ITEM_CODE = "itemCode";
	public static final String ITEM_NAME = "itemName";
	public static final String ITEM_KANA = "itemKana";
	public static final String ITEM_CLASS_CODE = "itemClassCode";
	public static final String ITEM_CLASS_NAME = "itemClassName";
	public static final String ITEM_COUNT = "itemCount";
	public static final String ITEM_PURCHASE_PRICE = "itemPurchasePrice";

	private String voucherDate;
	private Integer voucherNo;
	private Integer classCode;
	private String className;
	private String code;
	private String name;
	private String kana;

	private BigDecimal sumTaxExcludedPrice;
	private BigDecimal discount;
	private Integer dealDivision;
	private String staffSei;
	private String staffName;

	private String itemCode;
	private String itemName;
	private String itemKana;
	private Integer itemClassCode;
	private String itemClassName;
	private Integer itemCount;
	private BigDecimal itemPurchasePrice;

	public String getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}

	public Integer getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(Integer voucherNo) {
		this.voucherNo = voucherNo;
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

	public BigDecimal getSumTaxExcludedPrice() {
		return sumTaxExcludedPrice;
	}

	public void setSumTaxExcludedPrice(BigDecimal sumTaxExcludedPrice) {
		this.sumTaxExcludedPrice = sumTaxExcludedPrice;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getDealDivision() {
		return dealDivision;
	}

	public void setDealDivision(Integer dealDivision) {
		this.dealDivision = dealDivision;
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

	public String getItemKana() {
		return itemKana;
	}

	public void setItemKana(String itemKana) {
		this.itemKana = itemKana;
	}

	public Integer getItemClassCode() {
		return itemClassCode;
	}

	public void setItemClassCode(Integer itemClassCode) {
		this.itemClassCode = itemClassCode;
	}

	public String getItemClassName() {
		return itemClassName;
	}

	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public BigDecimal getItemPurchasePrice() {
		return itemPurchasePrice;
	}

	public void setItemPurchasePrice(BigDecimal itemPurchasePrice) {
		this.itemPurchasePrice = itemPurchasePrice;
	}

}
