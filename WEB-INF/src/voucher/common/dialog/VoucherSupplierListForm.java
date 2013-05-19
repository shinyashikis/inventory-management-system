package voucher.common.dialog;

import org.apache.struts.action.ActionForm;

/**
 * <p>(伝票)仕入先一覧フォーム</p>
 */
public class VoucherSupplierListForm extends ActionForm {

	private static final long serialVersionUID = 5138368328636340052L;

	private String[] sel;

	public String[] getSel() {
		return sel;
	}
	public void setSel(String[] sel) {
		this.sel = sel;
	}
}
