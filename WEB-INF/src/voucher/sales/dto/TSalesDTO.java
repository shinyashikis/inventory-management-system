package voucher.sales.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * 売上伝票テーブルDTO
 */
public class TSalesDTO implements CommonDTO {
	private static final long serialVersionUID = -1522826197171917115L;

	public static final String VOUCHER_NO = "voucherNo";
	public static final String VOUCHER_DATE = "voucherDate";
	public static final String DISCOUNT = "discount";
	public static final String ETC1 = "etc1";
	public static final String ETC2 = "etc2";
	public static final String RECEIPT_NO = "receiptNo";
	public static final String PROVISO = "proviso";
	public static final String RECEIPT_DATE = "receiptDate";
	public static final String BILL_NO = "billNo";
	public static final String MEMO = "memo";
	public static final String UPDDATE = "updDate";

	private Integer voucherNo;
	private Date voucherDate;
	private BigDecimal discount;
	private String etc1;
	private String etc2;
	private String receiptNo;
	private String proviso;
	private String receiptDate;
	private Integer billNo;
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
	public Integer getBillNo() {
		return billNo;
	}
	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
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
