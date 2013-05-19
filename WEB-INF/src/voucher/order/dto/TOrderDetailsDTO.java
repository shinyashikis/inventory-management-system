package voucher.order.dto;

import java.sql.Timestamp;

import fw.common.db.dto.CommonDTO;

/**
 * 注文書明細テーブルDTO
 */
public class TOrderDetailsDTO implements CommonDTO {
	private static final long serialVersionUID = -1807293394859139184L;

	public static final String VOUCHER_NO = "voucherNo";
	public static final String SEQ = "seq";
	public static final String ITEM_CODE = "itemCode";
	public static final String ITEM_COUNT = "itemCount";
	public static final String ITEM_MEMO = "itemMemo";
	public static final String UPDDATE = "updDate";

	private Integer voucherNo;
	private Integer seq;
	private String itemCode;
	private Integer itemCount;
	private String itemMemo;
	private Timestamp updDate;

	public Integer getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(Integer voucherNo) {
		this.voucherNo = voucherNo;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	public String getItemMemo() {
		return itemMemo;
	}
	public void setItemMemo(String itemMemo) {
		this.itemMemo = itemMemo;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

}
