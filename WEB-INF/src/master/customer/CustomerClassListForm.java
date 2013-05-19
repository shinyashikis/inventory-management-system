package master.customer;

import java.util.LinkedHashMap;
import java.util.Map;

import master.customer.bean.MCustomerClassBean;

import org.apache.struts.action.ActionForm;

/**
 * <p>得意先分類一覧フォーム</p>
 */
public class CustomerClassListForm extends ActionForm {
	private static final long serialVersionUID = -6379226685607572322L;

	private String[] sel;
	private Map<String,MCustomerClassBean> customerClassMap = new LinkedHashMap<String,MCustomerClassBean>();

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}

	public Map<String, MCustomerClassBean> getCustomerClassMap() {
		return customerClassMap;
	}

	public void setCustomerClassMap(Map<String, MCustomerClassBean> customerClassMap) {
		this.customerClassMap = customerClassMap;
	}

	public void setCustomerClass(String code, MCustomerClassBean customerClass) {
		customerClassMap.put(code, customerClass);
	}

}
