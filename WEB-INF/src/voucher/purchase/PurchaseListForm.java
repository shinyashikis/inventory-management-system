package voucher.purchase;

import org.apache.struts.action.ActionForm;

/**
 * <p>仕入伝票一覧フォーム</p>
 */
public class PurchaseListForm extends ActionForm {
	private static final long serialVersionUID = -358887063037298704L;

	private String[] sel;

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}
}
