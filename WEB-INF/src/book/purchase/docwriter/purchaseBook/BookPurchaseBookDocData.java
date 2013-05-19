package book.purchase.docwriter.purchaseBook;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fw.common.docwriter.DocData;

public class BookPurchaseBookDocData implements DocData {

	private static final long serialVersionUID = 8604874929361014327L;

	private String nendo;
	private String from;
	private String to;

	/** 行データ **/
	private List<RowData> rowDataList = new ArrayList<RowData>();

	public String getNendo() {
		return nendo;
	}

	void setNendo(String nendo) {
		this.nendo = nendo;
	}

	public String getFrom() {
		return from;
	}

	void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	void setTo(String to) {
		this.to = to;
	}

	public List<RowData> getRowDataList() {
		return rowDataList;
	}

	private void createRowData(int index) {
		if (rowDataList.size() == 0 ||
				rowDataList.size() <= index) {
			rowDataList.add(new RowData());
		}
	}

	void setVoucherDate(int index, String voucherDate) {
		createRowData(index);
		rowDataList.get(index).setVoucherDate(voucherDate);
	}

	public String getVoucherDate(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getVoucherDate();
	}

	void setVoucherNo(int index, String voucherNo) {
		createRowData(index);
		rowDataList.get(index).setVoucherNo(voucherNo);
	}

	public String getVoucherNo(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getVoucherNo();
	}

	void setClassName(int index, String className) {
		createRowData(index);
		rowDataList.get(index).setClassName(className);
	}

	public String getClassName(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getClassName();
	}

	void setKana(int index,String kana) {
		createRowData(index);
		rowDataList.get(index).setKana(kana);
	}

	public String getKana(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getKana();
	}

	void setName(int index, String name) {
		createRowData(index);
		rowDataList.get(index).setName(name);
	}

	public String getName(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getName();
	}

	void setTaxExcludedAmount(int index, BigDecimal taxExcludedAmount) {
		createRowData(index);
		rowDataList.get(index).setTaxExcludedAmount(taxExcludedAmount);
	}

	public BigDecimal getTaxExcludedAmount(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getTaxExcludedAmount();
	}

	void setTax(int index, BigDecimal tax) {
		createRowData(index);
		rowDataList.get(index).setTax(tax);
	}

	public BigDecimal getTax(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getTax();
	}

	void setTaxIncludedAmount(int index, BigDecimal taxIncludedAmount) {
		createRowData(index);
		rowDataList.get(index).setTaxIncludedAmount(taxIncludedAmount);
	}

	public BigDecimal getTaxIncludedAmount(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getTaxIncludedAmount();
	}

	void setDiscount(int index, BigDecimal discount) {
		createRowData(index);
		rowDataList.get(index).setDiscount(discount);
	}

	public BigDecimal getDiscount(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getDiscount();
	}

	void setPurchasePrice(int index, BigDecimal purchasePrice) {
		createRowData(index);
		rowDataList.get(index).setPurchasePrice(purchasePrice);
	}

	public BigDecimal getPurchasePrice(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getPurchasePrice();
	}

	void setDealDivision(int index, String dealDivision) {
		createRowData(index);
		rowDataList.get(index).setDealDivision(dealDivision);
	}

	public String getDealDivision(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getDealDivision();
	}

	void setStaff(int index, String staff) {
		createRowData(index);
		rowDataList.get(index).setStaff(staff);
	}

	public String getStaff(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getStaff();
	}

	void setItemClassName(int index, String itemClassName) {
		createRowData(index);
		rowDataList.get(index).setItemClassName(itemClassName);
	}

	public String getItemClassName(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getItemClassName();
	}

	void setItemKana(int index, String itemKana) {
		createRowData(index);
		rowDataList.get(index).setItemKana(itemKana);
	}

	public String getItemKana(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getItemKana();
	}

	void setItemName(int index, String itemName) {
		createRowData(index);
		rowDataList.get(index).setItemName(itemName);
	}

	public String getItemName(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getItemName();
	}

	void setItemCount(int index, String itemCount) {
		createRowData(index);
		rowDataList.get(index).setItemCount(itemCount);
	}

	public String getItemCount(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getItemCount();
	}

	void setItemUnitPrice(int index, BigDecimal itemUnitPrice) {
		createRowData(index);
		rowDataList.get(index).setItemUnitPrice(itemUnitPrice);
	}

	public BigDecimal getItemUnitPrice(int index) {
		return (rowDataList.get(index) == null)
			? null : rowDataList.get(index).getItemUnitPrice();
	}

	/**
	 * 行データ
	 */
	private class RowData {
		private String voucherDate;
		private String voucherNo;
		private String className;
		private String kana;
		private String name;

		private BigDecimal taxExcludedAmount = new BigDecimal(0);
		private BigDecimal tax = new BigDecimal(0);
		private BigDecimal taxIncludedAmount = new BigDecimal(0);
		private BigDecimal discount = new BigDecimal(0);
		private BigDecimal purchasePrice = new BigDecimal(0);
		private String dealDivision;
		private String staff;

		private String itemClassName;
		private String itemKana;
		private String itemName;
		private String itemCount;
		private BigDecimal itemUnitPrice;

		private String getVoucherDate() {
			return voucherDate;
		}
		private void setVoucherDate(String voucherDate) {
			this.voucherDate = voucherDate;
		}
		private String getVoucherNo() {
			return voucherNo;
		}
		private void setVoucherNo(String voucherNo) {
			this.voucherNo = voucherNo;
		}
		private String getClassName() {
			return className;
		}
		private void setClassName(String className) {
			this.className = className;
		}
		private String getKana() {
			return kana;
		}
		private void setKana(String kana) {
			this.kana = kana;
		}
		private String getName() {
			return name;
		}
		private void setName(String name) {
			this.name = name;
		}
		private BigDecimal getTaxExcludedAmount() {
			return taxExcludedAmount;
		}
		private void setTaxExcludedAmount(BigDecimal taxExcludedAmount) {
			this.taxExcludedAmount = taxExcludedAmount;
		}
		private BigDecimal getTax() {
			return tax;
		}
		private void setTax(BigDecimal tax) {
			this.tax = tax;
		}
		private BigDecimal getTaxIncludedAmount() {
			return taxIncludedAmount;
		}
		private void setTaxIncludedAmount(BigDecimal taxIncludedAmount) {
			this.taxIncludedAmount = taxIncludedAmount;
		}
		private BigDecimal getDiscount() {
			return discount;
		}
		private void setDiscount(BigDecimal discount) {
			this.discount = discount;
		}
		private BigDecimal getPurchasePrice() {
			return purchasePrice;
		}
		private void setPurchasePrice(BigDecimal purchasePrice) {
			this.purchasePrice = purchasePrice;
		}
		private String getDealDivision() {
			return dealDivision;
		}
		private void setDealDivision(String dealDivision) {
			this.dealDivision = dealDivision;
		}
		private String getStaff() {
			return staff;
		}
		private void setStaff(String staff) {
			this.staff = staff;
		}
		private String getItemClassName() {
			return itemClassName;
		}
		private void setItemClassName(String itemClassName) {
			this.itemClassName = itemClassName;
		}
		private String getItemKana() {
			return itemKana;
		}
		private void setItemKana(String itemKana) {
			this.itemKana = itemKana;
		}
		private String getItemName() {
			return itemName;
		}
		private void setItemName(String itemName) {
			this.itemName = itemName;
		}
		private String getItemCount() {
			return itemCount;
		}
		private void setItemCount(String itemCount) {
			this.itemCount = itemCount;
		}
		private BigDecimal getItemUnitPrice() {
			return itemUnitPrice;
		}
		private void setItemUnitPrice(BigDecimal itemUnitPrice) {
			this.itemUnitPrice = itemUnitPrice;
		}
	}
}
