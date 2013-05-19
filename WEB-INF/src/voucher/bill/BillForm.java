package voucher.bill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;

import fw.common.date.DateFormatPattern;
import fw.common.date.DateUtility;

import prop.ViewProperties;

/**
 * 請求書FormBean
 */
public class BillForm extends ValidatorForm {
	private static final long serialVersionUID = 484499771435900846L;

	/** 伝票番号 */
	private String voucherNo;
	/** 伝票日付 */
	private String voucherDate = DateUtility.getSysDate(DateFormatPattern.PATTERN_YYYYMMDD);
	/** 摘要 */
	private String memo;

	/** 取引先コード */
	private String dealCode;
	/** 取引先区分 */
	private String dealKind = ViewProperties.getInstance().getValue(ViewProperties.DEAL_KIND_CUSTOMER, ViewProperties.VALUE);

	/** 調整額 */
	private String discount = "0";

	/** 印刷区分 **/
	private String printKind = ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_BILL_DETAIL, ViewProperties.VALUE);

	/** 明細 **/
	private List<Map<String,String>> detailList = new ArrayList<Map<String,String>>();

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDealCode() {
		return dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}

	public String getDealKind() {
		return dealKind;
	}

	public void setDealKind(String dealKind) {
		this.dealKind = dealKind;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getPrintKind() {
		return printKind;
	}

	public void setPrintKind(String printKind) {
		this.printKind = printKind;
	}

	public List<Map<String, String>> getDetailList() {
		return detailList;
	}

	public Map<String, String> getDetail(int index) {
		return detailList.get(index);
	}

	public void setDetailList(List<Map<String, String>> detailList) {
		this.detailList = detailList;
	}

	public void setDetail(int index, Map<String, String> detail) {
		this.detailList.set(index, detail);
	}

	public void setDetail(Map<String, String> detail) {
		this.detailList.add(detail);
	}
}
