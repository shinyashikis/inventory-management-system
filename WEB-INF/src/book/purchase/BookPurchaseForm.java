package book.purchase;

import book.common.BookCommonForm;

/**
 * <p>(帳簿)仕入情報フォーム</p>
 */
public class BookPurchaseForm extends BookCommonForm {
	private static final long serialVersionUID = 818236191109530202L;
	private String condition;
	private String supplierCondition;
	private String supplierClassCondition;
	private String supplierStaff;

	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getSupplierCondition() {
		return supplierCondition;
	}
	public void setSupplierCondition(String supplierCondition) {
		this.supplierCondition = supplierCondition;
	}
	public String getSupplierClassCondition() {
		return supplierClassCondition;
	}
	public void setSupplierClassCondition(String supplierClassCondition) {
		this.supplierClassCondition = supplierClassCondition;
	}
	public String getSupplierStaff() {
		return supplierStaff;
	}
	public void setSupplierStaff(String supplierStaff) {
		this.supplierStaff = supplierStaff;
	}
}
