package master.customer;

import java.util.LinkedHashMap;
import java.util.Map;

import master.customer.bean.MCustomerBean;

import org.apache.struts.action.ActionForm;

/**
 * <p>得意先一覧フォーム</p>
 */
public class CustomerListForm extends ActionForm {
	private static final long serialVersionUID = 4031959502955239123L;

	private String[] sel;
	private Map<String,MCustomerBean> customerMap = new LinkedHashMap<String,MCustomerBean>();

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}

	public Map<String, MCustomerBean> getCustomerMap() {
		return customerMap;
	}

	public void setCustomerMap(Map<String, MCustomerBean> customerMap) {
		this.customerMap = customerMap;
	}

	public void setCustomer(String code, MCustomerBean customer) {
		customerMap.put(code, customer);
	}
}
