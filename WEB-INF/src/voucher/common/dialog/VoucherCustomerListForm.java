package voucher.common.dialog;

import org.apache.struts.action.ActionForm;

/**
 * <p>(伝票)得意先一覧フォーム</p>
 */
public class VoucherCustomerListForm extends ActionForm {
	private static final long serialVersionUID = 4304176763927862461L;

	private String[] sel;

	public String[] getSel() {
		return sel;
	}
	public void setSel(String[] sel) {
		this.sel = sel;
	}
}
