package book.sales;

import book.common.BookCommonForm;

/**
 * <p>(帳簿)売上情報フォーム</p>
 */
public class BookSalesForm extends BookCommonForm {
	private static final long serialVersionUID = -6972736840987384633L;

	private String condition;
	private String customerCondition;
	private String customerClassCondition;
	private String customerStaff;

	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getCustomerCondition() {
		return customerCondition;
	}
	public void setCustomerCondition(String customerCondition) {
		this.customerCondition = customerCondition;
	}
	public String getCustomerClassCondition() {
		return customerClassCondition;
	}
	public void setCustomerClassCondition(String customerClassCondition) {
		this.customerClassCondition = customerClassCondition;
	}
	public String getCustomerStaff() {
		return customerStaff;
	}
	public void setCustomerStaff(String customerStaff) {
		this.customerStaff = customerStaff;
	}
}
