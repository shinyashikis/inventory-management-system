package voucher.bill.dialog;

import org.apache.struts.action.ActionForm;

/**
 * <p>(請求書)得意先一覧フォーム</p>
 */
public class BillCustomerListForm extends ActionForm {
	private static final long serialVersionUID = 2811826329272719922L;

	private String[] sel;

	public String[] getSel() {
		return sel;
	}
	public void setSel(String[] sel) {
		this.sel = sel;
	}
}
