package book.voucher.docwriter;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import fw.common.docwriter.DocData;

public class BookBillTotalDocData implements DocData {

	private static final long serialVersionUID = -1834231611168290113L;

	private String nendo;
	private String from;
	private String to;

	/** 行データ(key:伝票番号、value:RowData **/
	private Map<String, RowData> rowDataMap = new LinkedHashMap<String, BookBillTotalDocData.RowData>();

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

	public Map<String, RowData> getRowDataMap() {
		return rowDataMap;
	}

	private void createRowData(String key) {
		RowData rowData = rowDataMap.get(key);
		if (rowData == null) {
			rowData = new RowData();
			rowDataMap.put(key, rowData);
		}
	}

	void setVoucherDate(String key,String voucherDate) {
		createRowData(key);
		rowDataMap.get(key).setVoucherDate(voucherDate);
	}

	public String getVoucherDate(String key) {
		return (rowDataMap.get(key) == null)
			? null : rowDataMap.get(key).getVoucherDate();
	}

	void setVoucherNo(String key,String voucherNo) {
		createRowData(key);
		rowDataMap.get(key).setVoucherNo(voucherNo);
	}

	public String getVoucherNo(String key) {
		return (rowDataMap.get(key) == null)
			? null : rowDataMap.get(key).getVoucherNo();
	}

	void setCode(String key,String code) {
		createRowData(key);
		rowDataMap.get(key).setCode(code);
	}

	public String getCode(String key) {
		return (rowDataMap.get(key) == null)
			? null : rowDataMap.get(key).getCode();
	}

	void setName(String key,String name) {
		createRowData(key);
		rowDataMap.get(key).setName(name);
	}

	public String getName(String key) {
		return (rowDataMap.get(key) == null)
			? null : rowDataMap.get(key).getName();
	}

	void setStaff(String key,String staff) {
		createRowData(key);
		rowDataMap.get(key).setStaff(staff);
	}

	public String getStaff(String key) {
		return (rowDataMap.get(key) == null)
			? null : rowDataMap.get(key).getStaff();
	}

	void setTaxExcludedAmount(String key,BigDecimal taxExcludedAmount) {
		createRowData(key);
		rowDataMap.get(key).setTaxExcludedAmount(taxExcludedAmount);
	}

	public BigDecimal getTaxExcludedAmount(String key) {
		return (rowDataMap.get(key) == null)
			? null : rowDataMap.get(key).getTaxExcludedAmount();
	}

	void setTax(String key,BigDecimal tax) {
		createRowData(key);
		rowDataMap.get(key).setTax(tax);
	}

	public BigDecimal getTax(String key) {
		return (rowDataMap.get(key) == null)
			? null : rowDataMap.get(key).getTax();
	}

	void setTaxIncludedAmount(String key,BigDecimal taxIncludedAmount) {
		createRowData(key);
		rowDataMap.get(key).setTaxIncludedAmount(taxIncludedAmount);
	}

	public BigDecimal getTaxIncludedAmount(String key) {
		return (rowDataMap.get(key) == null)
			? null : rowDataMap.get(key).getTaxIncludedAmount();
	}

	void setDiscount(String key,BigDecimal discount) {
		createRowData(key);
		rowDataMap.get(key).setDiscount(discount);
	}

	public BigDecimal getDiscount(String key) {
		return (rowDataMap.get(key) == null)
			? null : rowDataMap.get(key).getDiscount();
	}

	void setAllPrice(String key,BigDecimal allPrice) {
		createRowData(key);
		rowDataMap.get(key).setAllPrice(allPrice);
	}

	public BigDecimal getAllPrice(String key) {
		return (rowDataMap.get(key) == null)
			? null : rowDataMap.get(key).getAllPrice();
	}


	/**
	 * 行データ
	 */
	private class RowData {
		private String voucherDate;
		private String voucherNo;
		private String code;
		private String name;
		private String staff;
		private BigDecimal taxExcludedAmount = new BigDecimal(0);
		private BigDecimal tax = new BigDecimal(0);
		private BigDecimal taxIncludedAmount = new BigDecimal(0);
		private BigDecimal discount = new BigDecimal(0);
		private BigDecimal allPrice = new BigDecimal(0);

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
		private String getCode() {
			return code;
		}
		private void setCode(String code) {
			this.code = code;
		}
		private String getName() {
			return name;
		}
		private void setName(String name) {
			this.name = name;
		}
		private String getStaff() {
			return staff;
		}
		private void setStaff(String staff) {
			this.staff = staff;
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
		private BigDecimal getAllPrice() {
			return allPrice;
		}
		private void setAllPrice(BigDecimal allPrice) {
			this.allPrice = allPrice;
		}
	}
}
