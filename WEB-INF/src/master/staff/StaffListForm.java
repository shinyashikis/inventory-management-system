package master.staff;


import java.util.LinkedHashMap;
import java.util.Map;

import master.staff.bean.MStaffBean;

import org.apache.struts.action.ActionForm;

/**
 * <p>担当者一覧フォーム</p>
 */
public class StaffListForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private String[] sel;
	private Map<String,MStaffBean> staffMap = new LinkedHashMap<String,MStaffBean>();

	public String[] getSel() {
		return sel;
	}

	public void setSel(String[] sel) {
		this.sel = sel;
	}

	public Map<String, MStaffBean> getStaffMap() {
		return staffMap;
	}

	public void setStaffMap(Map<String, MStaffBean> staffMap) {
		this.staffMap = staffMap;
	}

	public void setStaff(String code, MStaffBean staff) {
		staffMap.put(code, staff);
	}
}
