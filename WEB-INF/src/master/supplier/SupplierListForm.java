package master.supplier;

import java.util.LinkedHashMap;
import java.util.Map;

import master.supplier.bean.MSupplierBean;

import org.apache.struts.action.ActionForm;

/**
 * <p>仕入先一覧フォーム</p>
 */
public class SupplierListForm extends ActionForm {
	private static final long serialVersionUID = 4031959502955239123L;

	private String[] sel;
	private Map<String,MSupplierBean> supplierMap = new LinkedHashMap<String,MSupplierBean>();

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}

	public Map<String, MSupplierBean> getSupplierMap() {
		return supplierMap;
	}

	public void setSupplierMap(Map<String, MSupplierBean> supplierMap) {
		this.supplierMap = supplierMap;
	}

	public void setSupplier(String code, MSupplierBean supplier) {
		supplierMap.put(code, supplier);
	}
}
