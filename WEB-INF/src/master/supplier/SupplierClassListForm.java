package master.supplier;

import java.util.LinkedHashMap;
import java.util.Map;

import master.supplier.bean.MSupplierClassBean;

import org.apache.struts.action.ActionForm;

/**
 * <p>仕入先分類一覧フォーム</p>
 */
public class SupplierClassListForm extends ActionForm {
	private static final long serialVersionUID = -6379226685607572322L;

	private String[] sel;
	private Map<String,MSupplierClassBean> supplierClassMap = new LinkedHashMap<String,MSupplierClassBean>();

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}

	public Map<String, MSupplierClassBean> getSupplierClassMap() {
		return supplierClassMap;
	}

	public void setSupplierClassMap(Map<String, MSupplierClassBean> supplierClassMap) {
		this.supplierClassMap = supplierClassMap;
	}

	public void setSupplierClass(String code, MSupplierClassBean supplierClass) {
		supplierClassMap.put(code, supplierClass);
	}

}
