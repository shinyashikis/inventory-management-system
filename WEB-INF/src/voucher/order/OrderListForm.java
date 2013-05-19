package voucher.order;

import org.apache.struts.action.ActionForm;

/**
 * <p>注文書一覧フォーム</p>
 */
public class OrderListForm extends ActionForm {
	private static final long serialVersionUID = 8654449241163413362L;

	private String[] sel;

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}
}
