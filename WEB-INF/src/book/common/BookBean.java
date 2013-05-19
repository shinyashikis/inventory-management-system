package book.common;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import master.customer.bean.MCustomerBean;
import master.customer.bean.MCustomerClassBean;
import master.item.bean.MItemBean;
import master.item.bean.MItemClassBean;
import master.staff.bean.MStaffBean;
import master.supplier.bean.MSupplierBean;
import master.supplier.bean.MSupplierClassBean;

/**
 * <p>帳簿情報</p>
 */
public class BookBean implements Serializable {
	private static final long serialVersionUID = 8905182352212437527L;

	private String bookKind;
	private Map<String, MItemBean> mItemBeanMap = new LinkedHashMap<String, MItemBean>();
	private Map<String, MItemClassBean> mItemClassBeanMap = new LinkedHashMap<String, MItemClassBean>();
	private Map<String, MCustomerBean> mCustomerBeanMap = new LinkedHashMap<String, MCustomerBean>();
	private Map<String, MCustomerClassBean> mCustomerClassBeanMap = new LinkedHashMap<String, MCustomerClassBean>();
	private Map<String, MSupplierBean> mSupplierBeanMap = new LinkedHashMap<String, MSupplierBean>();
	private Map<String, MSupplierClassBean> mSupplierClassBeanMap = new LinkedHashMap<String, MSupplierClassBean>();
	private Map<String, MStaffBean> mStaffBeanMap = new LinkedHashMap<String, MStaffBean>();

	public String getBookKind() {
		return bookKind;
	}

	public void setBookKind(String bookKind) {
		this.bookKind = bookKind;
	}

	public Map<String, MItemBean> getmItemBeanMap() {
		return mItemBeanMap;
	}

	public void setmItemBeanMap(Map<String, MItemBean> mItemBeanMap) {
		this.mItemBeanMap = mItemBeanMap;
	}

	public Map<String, MItemClassBean> getmItemClassBeanMap() {
		return mItemClassBeanMap;
	}

	public void setmItemClassBeanMap(Map<String, MItemClassBean> mItemClassBeanMap) {
		this.mItemClassBeanMap = mItemClassBeanMap;
	}

	public Map<String, MCustomerBean> getmCustomerBeanMap() {
		return mCustomerBeanMap;
	}

	public void setmCustomerBeanMap(Map<String, MCustomerBean> mCustomerBeanMap) {
		this.mCustomerBeanMap = mCustomerBeanMap;
	}

	public Map<String, MCustomerClassBean> getmCustomerClassBeanMap() {
		return mCustomerClassBeanMap;
	}

	public void setmCustomerClassBeanMap(
			Map<String, MCustomerClassBean> mCustomerClassBeanMap) {
		this.mCustomerClassBeanMap = mCustomerClassBeanMap;
	}

	public Map<String, MSupplierBean> getmSupplierBeanMap() {
		return mSupplierBeanMap;
	}

	public void setmSupplierBeanMap(Map<String, MSupplierBean> mSupplierBeanMap) {
		this.mSupplierBeanMap = mSupplierBeanMap;
	}

	public Map<String, MSupplierClassBean> getmSupplierClassBeanMap() {
		return mSupplierClassBeanMap;
	}

	public void setmSupplierClassBeanMap(
			Map<String, MSupplierClassBean> mSupplierClassBeanMap) {
		this.mSupplierClassBeanMap = mSupplierClassBeanMap;
	}

	public Map<String, MStaffBean> getmStaffBeanMap() {
		return mStaffBeanMap;
	}

	public void setmStaffBeanMap(Map<String, MStaffBean> mStaffBeanMap) {
		this.mStaffBeanMap = mStaffBeanMap;
	}
}
