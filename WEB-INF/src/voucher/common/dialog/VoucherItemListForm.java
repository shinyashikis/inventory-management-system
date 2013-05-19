package voucher.common.dialog;

import org.apache.struts.action.ActionForm;

/**
 * <p>(伝票)商品リストフォーム</p>
 */
public class VoucherItemListForm extends ActionForm {
	private static final long serialVersionUID = 3651795558491821917L;

	private String[] sel;

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}

}
