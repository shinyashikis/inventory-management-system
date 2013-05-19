package voucher.sales;

import org.apache.struts.action.ActionForm;

/**
 * <p>売上伝票一覧フォーム</p>
 */
public class SalesListForm extends ActionForm {
	private static final long serialVersionUID = -6264298570483476775L;

	private String[] sel;

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}
}
