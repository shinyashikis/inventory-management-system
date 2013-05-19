package voucher.bill.dialog.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import voucher.common.bean.DealBean;
import voucher.common.bean.DetailBean;

/**
 * <p>請求書得意先Bean</p>
 */
public class BillCustomerBean implements Serializable {
	private static final long serialVersionUID = 7243490471162815743L;

	/** 取引先Bean */
	private DealBean dealBean = new DealBean();
	/** 明細Bean */
	private List<DetailBean> detailList = new ArrayList<DetailBean>();

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
