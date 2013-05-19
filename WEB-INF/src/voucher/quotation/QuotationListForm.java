package voucher.quotation;

import org.apache.struts.action.ActionForm;

/**
 * <p>見積書一覧フォーム</p>
 */
public class QuotationListForm extends ActionForm {
	private static final long serialVersionUID = -7949819601601388178L;

	private String[] sel;

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}
}
