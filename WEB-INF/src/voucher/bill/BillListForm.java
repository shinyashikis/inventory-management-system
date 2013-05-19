package voucher.bill;

import org.apache.struts.action.ActionForm;

/**
 * <p>請求書一覧フォーム</p>
 */
public class BillListForm extends ActionForm {
	private static final long serialVersionUID = -5986576685379730847L;

	private String[] sel;

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}
}
