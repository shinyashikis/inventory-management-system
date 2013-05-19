package voucher.bill.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * 請求書テーブルDTO
 */
public class TBillDTO implements CommonDTO {
	private static final long serialVersionUID = 7725381963102184700L;

	public static final String VOUCHER_NO = "voucherNo";
	public static final String VOUCHER_DATE = "voucherDate";
	public static final String DISCOUNT = "discount";
	public static final String MEMO = "memo";
	public static final String UPDDATE = "updDate";

	private Integer voucherNo;
	private Date voucherDate;
	private BigDecimal discount;
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
