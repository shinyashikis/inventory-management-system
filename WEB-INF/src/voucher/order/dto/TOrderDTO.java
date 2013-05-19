package voucher.order.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * 注文書テーブルDTO
 */
public class TOrderDTO implements CommonDTO {

	private static final long serialVersionUID = -9127491103383970314L;

	public static final String VOUCHER_NO = "voucherNo";
	public static final String VOUCHER_DATE = "voucherDate";
	public static final String DISCOUNT = "discount";
	public static final String ORDER_NAME = "orderName";
	public static final String PAYMENT_LIMIT = "paymentLimit";
	public static final String PAYMENT_PLACE = "paymentPlace";
	public static final String PAYMENT_CONDITION = "paymentCondition";
	public static final String EXPIRATION_DATE = "expirationDate";
	public static final String MEMO = "memo";
	public static final String UPDDATE = "updDate";

	private Integer voucherNo;
	private Date voucherDate;
	private BigDecimal discount;
	private String orderName;
	private String paymentLimit;
	private String paymentPlace;
	private String paymentCondition;
	private String expirationDate;
	private String memo;
	private Timestamp updDate;

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
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
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
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

}
