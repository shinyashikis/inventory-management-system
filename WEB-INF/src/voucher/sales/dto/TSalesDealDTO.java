package voucher.sales.dto;

import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * 売上伝票取引先テーブルDTO
 */
public class TSalesDealDTO implements CommonDTO {
	private static final long serialVersionUID = -7370213231785905286L;

	public static final String VOUCHER_NO = "voucherNo";
	public static final String DEAL_KIND = "dealKind";
	public static final String DEAL_CODE = "dealCode";
	public static final String UPDDATE = "updDate";

	private Integer voucherNo;
	private Integer dealKind;
	private String dealCode;
	private Timestamp updDate;

	public Integer getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(Integer voucherNo) {
		this.voucherNo = voucherNo;
	}
	public Integer getDealKind() {
		return dealKind;
	}
	public void setDealKind(Integer dealKind) {
		this.dealKind = dealKind;
	}
	public String getDealCode() {
		return dealCode;
	}
	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

}
