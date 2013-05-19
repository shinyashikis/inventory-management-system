package voucher.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>伝票Bean</p>
 */
public class VoucherBean implements Serializable {
	private static final long serialVersionUID = 6729708478879637927L;

	private String voucherKind;
	private String voucherNo;
	private String voucherDate;
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
	private String receiptDate;

	private BigDecimal taxExcludedAmount = new BigDecimal(0);
	private BigDecimal tax = new BigDecimal(0);
	private BigDecimal taxIncludedAmount = new BigDecimal(0);
	private BigDecimal grossProfit = new BigDecimal(0);
	private BigDecimal discount = new BigDecimal(0);
	private BigDecimal amount = new BigDecimal(0);

	/** 取引先Bean */
	private DealBean dealBean = new DealBean();
	/** 明細Bean */
	private List<DetailBean> detailList = new ArrayList<DetailBean>();

	public String getVoucherKind() {
		return voucherKind;
	}
	public void setVoucherKind(String voucherKind) {
		this.voucherKind = voucherKind;
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
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
	public String getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}
	public BigDecimal getTaxExcludedAmount() {
		return taxExcludedAmount;
	}
	public void setTaxExcludedAmount(BigDecimal taxExcludedAmount) {
		this.taxExcludedAmount = taxExcludedAmount;
	}
	public void addTaxExcludedAmount(BigDecimal taxExcludedAmount) {
		if (this.taxExcludedAmount == null) {
			setTaxExcludedAmount(taxExcludedAmount);
			return;
		}
		setTaxExcludedAmount(this.taxExcludedAmount.add(taxExcludedAmount));
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getTaxIncludedAmount() {
		return taxIncludedAmount;
	}
	public void setTaxIncludedAmount(BigDecimal taxIncludedAmount) {
		this.taxIncludedAmount = taxIncludedAmount;
	}
	public BigDecimal getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}
	public void addGrossProfit(BigDecimal grossProfit) {
		if (this.grossProfit == null) {
			setGrossProfit(grossProfit);
			return;
		}
		setGrossProfit(this.grossProfit.add(grossProfit));
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public DealBean getDealBean() {
		return dealBean;
	}
	public void setDealBean(DealBean dealBean) {
		this.dealBean = dealBean;
	}
	public List<DetailBean> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<DetailBean> detailList) {
		this.detailList = detailList;
	}
	public void addDetail(DetailBean value) {
		this.detailList.add(value);
	}

}
