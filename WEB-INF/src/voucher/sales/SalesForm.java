package voucher.sales;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;

import fw.common.date.DateFormatPattern;
import fw.common.date.DateUtility;

import prop.ViewProperties;

/**
 * 売上伝票FormBean
 */
public class SalesForm extends ValidatorForm {
	private static final long serialVersionUID = 8246050381387490905L;

	/** 明細キー(商品コード) **/
	public static final String DETAIL_KEY_ITEM_CODE = "itemCode";
	/** 明細キー(商品名) **/
	public static final String DETAIL_KEY_ITEM_NAME = "itemName";
	/** 明細キー(規格・仕様) **/
	public static final String DETAIL_KEY_ITEM_KIKAKU = "itemKikaku";
	/** 明細キー(単位) **/
	public static final String DETAIL_KEY_ITEM_UNIT = "itemUnit";
	/** 明細キー(金額) **/
	public static final String DETAIL_KEY_ITEM_PRICE = "itemPrice";
	/** 明細キー(数量) **/
	public static final String DETAIL_KEY_ITEM_COUNT = "itemCount";
	/** 明細キー(単価) **/
	public static final String DETAIL_KEY_ITEM_UNIT_PRICE = "itemUnitPrice";
	/** 明細キー(摘要) **/
	public static final String DETAIL_KEY_ITEM_MEMO = "itemMemo";
	/** 明細キー(仕入金額) **/
	public static final String DETAIL_KEY_ITEM_PURCHASE_PRICE = "itemPurchasePrice";

	/** 伝票番号 */
	private String voucherNo;
	/** 伝票日付 */
	private String voucherDate = DateUtility.getSysDate(DateFormatPattern.PATTERN_YYYYMMDD);
	/** その他1 */
	private String etc1;
	/** その他2 */
	private String etc2;
	/** 領収書番号 */
	private String receiptNo;
	/** 領収書但書 */
	private String proviso;
	/** 領収書日付 */
	private String receiptDate;
	/** 摘要 */
	private String memo;

	/** 取引先コード */
	private String dealCode;
	/** 取引先区分 */
	private String dealKind = ViewProperties.getInstance().getValue(ViewProperties.DEAL_KIND_CUSTOMER, ViewProperties.VALUE);

	/** 調整額 */
	private String discount = "0";

	/** 印刷区分 **/
	private String printKind = ViewProperties.getInstance().getValue(ViewProperties.PRINT_KIND_SALES, ViewProperties.VALUE);

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
