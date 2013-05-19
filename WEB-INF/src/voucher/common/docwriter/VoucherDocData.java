package voucher.common.docwriter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>伝票文書ファイルデータ</p>
 */
public class VoucherDocData extends CommonDocData {

	private static final long serialVersionUID = 3719906279939100117L;

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
	private String taxExcludedAmount;
	private String tax;
	private String taxVal;
	private String taxIncludedAmount;
	private String grossProfit;
	private String discount;
	private String amount;

	private DealDocData deal;
	private List<DetailDocData> details = new ArrayList<DetailDocData>();

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
	public String getTaxExcludedAmount() {
		return taxExcludedAmount;
	}
	public void setTaxExcludedAmount(String taxExcludedAmount) {
		this.taxExcludedAmount = taxExcludedAmount;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getTaxVal() {
		return taxVal;
	}
	public void setTaxVal(String taxVal) {
		this.taxVal = taxVal;
	}
	public String getTaxIncludedAmount() {
		return taxIncludedAmount;
	}
	public void setTaxIncludedAmount(String taxIncludedAmount) {
		this.taxIncludedAmount = taxIncludedAmount;
	}
	public String getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public DealDocData getDeal() {
		return deal;
	}
	public void setDeal(DealDocData deal) {
		this.deal = deal;
	}
	public List<DetailDocData> getDetails() {
		return details;
	}
	public void setDetails(List<DetailDocData> details) {
		this.details = details;
	}
	public void addDetail(DetailDocData detail) {
		this.details.add(detail);
	}
}
